package allServlets;

import allBeans.*;
import allClasses.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MasterController", urlPatterns = {"/MasterController"})
public class MasterController extends HttpServlet {
    //IS PULLING THE STRINGS!

    //parcourir le tableau des cookies
    private Cookie obtenirCookie(Cookie[] cookies, String name) {
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals(name)) {
                    return c;
                }
            }
        }
        return null;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        /*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ CA @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
        /* Adresse de la page principale, par défaut */
        String url = "/WEB-INF/jspPageAccueil.jsp";
        String result = null;
        Map<String, String> errors = new HashMap<String, String>();

        /* Session pour récupérer les beans */
        HttpSession session = request.getSession();

//        //RECHERCHE PAR 
//        if ("arbo".equals(request.getParameter("section"))) {
//            url = "/WEB-INF/jspArboCategories.jsp";
//        }
        Cookie loggedOrNot = obtenirCookie(request.getCookies(), "Valider");
        if (loggedOrNot != null) {
            session.setAttribute("userlogged", "Déconnexion");
        } else {
            session.setAttribute("userlogged", "Login");
        }

        if ("viePanier".equals(request.getParameter("section"))) {
            /* Mon bean panier à partir de la session le panier en cours */
            BeanPanier bPan = (BeanPanier) session.getAttribute("monPanier");
            float montantTotal = 0;

            if (bPan == null) {
                bPan = new BeanPanier();
                /* Renvoie le bean panier creer s'il n'existe pas */
                session.setAttribute("monPanier", bPan);
            }
            if (request.getParameter("add") != null) {
                BeanRecherche bLivre = new BeanRecherche();
                bPan.ajouter(bLivre.ficheProduit(request.getParameter("addHid")));
            }
            if (request.getParameter("dec") != null) {
                BeanRecherche bLivre = new BeanRecherche();
                bPan.decrementer(bLivre.ficheProduit(request.getParameter("decHid")));
            }
            if (request.getParameter("del") != null) {
                bPan.supprimer(request.getParameter("delHid"));
            }
            if (request.getParameter("clear") != null) {
                bPan.vider();
            }
            montantTotal = bPan.montantTotal();
            request.setAttribute("montantTotal", montantTotal);
            request.setAttribute("listeLivre", bPan.listeLivre());
            url = "/WEB-INF/jspPagePanier.jsp";
        }

        if ("panier".equals(request.getParameter("section"))) {
            /* Mon bean panier à partir de la session le panier en cours */
            BeanPanier bPan = (BeanPanier) session.getAttribute("monPanier");
            float montantTotal = 0;

            if (bPan == null) {
                bPan = new BeanPanier();
                /* Renvoie le bean panier creer s'il n'existe pas */
                session.setAttribute("monPanier", bPan);
            }
            if (bPan.isEmpty()) {
                url = "/WEB-INF/jspPagePanierVide.jsp";
                System.err.println("panier vide");
            } else {
                url = "/WEB-INF/jspPagePanier.jsp";
                request.setAttribute("listeLivre", bPan.listeLivre());
            }
            montantTotal = bPan.montantTotal();
            request.setAttribute("montantTotal", montantTotal);
        }
        if ("validerPanier".equals(request.getParameter("section"))) {
            /* Mon bean panier à partir de la session le panier en cours */
            BeanPanier bPan = (BeanPanier) session.getAttribute("monPanier");
            float montantTotal = 0;

            if (bPan == null) {
                bPan = new BeanPanier();
                /* Renvoie le bean panier creer s'il n'existe pas */
                session.setAttribute("monPanier", bPan);
            }
            request.setAttribute("listeLivre", bPan.listeLivre());
            montantTotal = bPan.montantTotal();
            request.setAttribute("montantTotal", montantTotal);
            url = "/WEB-INF/jspPageCommande.jsp";
        }

        if ("finCommande".equals(request.getParameter("section"))) {
            /* Mon bean panier à partir de la session le panier en cours */
            BeanPanier bPan = (BeanPanier) session.getAttribute("monPanier");
            /* Ma commande */
            BeanCommande bCom;
            /* Mes livraisons */
            BeanLivraison bLiv = new BeanLivraison();
            /* Mes moyens de paiement */
            BeanMoyenPaiement bMoypaie = new BeanMoyenPaiement();

            float montantTotal = 0;

            if (bPan == null) {
                bPan = new BeanPanier();
                /* Renvoie le bean panier creer s'il n'existe pas */
                session.setAttribute("monPanier", bPan);
            }
            bCom = new BeanCommande(bPan);
            session.setAttribute("maCommande", bCom);

            montantTotal = bPan.montantTotal();
            request.setAttribute("montantTotal", montantTotal);
            request.setAttribute("mesLivraisons", bLiv.getListeFp());
            request.setAttribute("mesPaiements", bMoypaie.getListeMoyenPaiement());

            /* Vérifier que l'utilisateur a été authentifié */
            Cookie controleOK = obtenirCookie(request.getCookies(), "Valider");
            if (controleOK != null) {
                BeanAdresse bAdr = new BeanAdresse(Integer.parseInt(controleOK.getValue()));
                if (!bAdr.isEmpty()) {
                    session.setAttribute("listeAdresse", bAdr.listeAdresseDuClient());
                }
                url = "/WEB-INF/jspPageLivraison.jsp";
            } else {
                url = "/WEB-INF/jspFormLogin.jsp";
            }
        }
        if ("paiementCommande".equals(request.getParameter("section"))) {
            if (request.getParameter("validCommande") != null) {
                Cookie controleOK = obtenirCookie(request.getCookies(), "Valider");
                /* Ma commande */
                BeanCommande bCom = (BeanCommande) session.getAttribute("maCommande");
                /* Mon Paiement et la sauvegarde de la commande */
                BeanPaiementCommande bPaieCom;

                String idClient = controleOK.getValue();
                String idModeLivaraison = request.getParameter("typetransport");
                String codePaiement = request.getParameter("typepaiement");
                String idAdrFact = request.getParameter("adrFact");
                String idAdrLiv = request.getParameter("adrLiv");
                String comCommande = request.getParameter("usercomment");

                if (bCom == null) {
                    bCom = new BeanCommande();
                    session.setAttribute("maCommande", bCom);
                }
                System.out.println("bCom : " + bCom.toString());
                System.out.println("idClient : " + idClient);
                System.out.println("idModeLivaraison : " + idModeLivaraison);
                System.out.println("codePaiement : " + codePaiement);
                System.out.println("idAdrFact : " + idAdrFact);
                System.out.println("idAdrLiv : " + idAdrLiv);
                System.out.println("comCommande : " + comCommande);

                bPaieCom = new BeanPaiementCommande(bCom.getArticleCommande(),
                        idClient,
                        codePaiement,
                        idModeLivaraison,
                        comCommande,
                        idAdrLiv,
                        idAdrFact);

                url = "/WEB-INF/jspPagePaiementEffectue.jsp";
            }
        }
        /*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ AN @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
        //LOGIN
        String bienvenue = null;
        boolean resCreation = false;

        //IDENTIFICATION
        if ("login".equals(request.getParameter("section"))) {
            url = "/WEB-INF/jspFormLogin.jsp";

            /*la tentative de connexion*/
            Integer nbEssai = (Integer) session.getAttribute("essais");
            if (request.getParameter("doIt") != null) {
                BeanLogin login = (BeanLogin) session.getAttribute("monLogin");
                if (login == null) {
                    login = new BeanLogin();
                    session.setAttribute("monLogin", login);
                }
                if (login.validate(request.getParameter("user"), request.getParameter("password"))) {
                    request.setAttribute("login", login.recupNom());// pour récupérer le nom correspondant à l'email
                    //request.setAttribute("idClient", login.recupIdClient());//pour renvoyer id client à commande.............

                    session.setAttribute("userlogged", "Déconnexion");

                    Cookie cc = new Cookie("Valider", Integer.toString(login.recupIdClient()));
                    response.addCookie(cc);
                    if (session.getAttribute("maCommande") == null && session.getAttribute("whatwasidoin") == null) {
                        url = "/WEB-INF/jspPageBienvenueLogin.jsp";
                    } else {
                        if (session.getAttribute("whatwasidoin") != null) {
                            session.getAttribute("comment");
//			session.getAttribute("note");
                            url = (String) session.getAttribute("whatwasidoin");
                        } else {
                            url = "/MasterController?section=finCommande";
                        }
                    }
                    //rediriger l'utilisateur vers la page en cours AVANT login, i.e. section commentaires

                } else {
                    session.setAttribute("userlogged", "Login");
                    request.setAttribute("login", request.getParameter("user"));
                    request.setAttribute("msg", "Utilisateur/mot de passe invalide !");
                    url = "/WEB-INF/jspFormLogin.jsp";
                    if (nbEssai == null) {
                        nbEssai = 1;
                    } else {
                        nbEssai++;
                    }
                    session.setAttribute("essais", nbEssai);
                }
            }
            if (nbEssai != null) {
                if (nbEssai > 3) {
                    request.setAttribute("fatalerror", "Nombre de tentatives dépassé !");
                    url = "/WEB-INF/jspPageFatalErrorLogin.jsp";
                }
            }
        }

        /* CREATION COMPTE */
        if ("creer".equals(request.getParameter("section"))) {
            if (request.getParameter("valid") != null) {
                BeanLogin creation = (BeanLogin) session.getAttribute("maCreation");//beans pour creation
                if (creation == null) {
                    creation = new BeanLogin();
                    session.setAttribute("maCreation", creation);
                }
//                try {
//                    ConroleSaisieChamps.validationEmail(request.getParameter("EmlCli"));
//                } catch (Exception e) {
//                    errors.put(ConroleSaisieChamps.CHAMP_EMAIL, e.getMessage());
//                }
                /* Validation des champs mot de passe et confirmation. */
                try {
                    ConroleSaisieChamps.validationMotsDePasse(request.getParameter("MdpCli"));
                } catch (Exception e) {
                    errors.put(ConroleSaisieChamps.CHAMP_PASS, e.getMessage());
                }
                try {
                    ConroleSaisieChamps.validationTelephone(request.getParameter("TelCli"));
                } catch (Exception e) {
                    errors.put(ConroleSaisieChamps.CHAMP_TELE, e.getMessage());
                }

                /* si le hash map errors est vide on crée le nouveau compte */
                if (errors.isEmpty()) {
                    result = "Succès de l'inscription.";
                    resCreation = creation.creerCompte(request.getParameter("NomCli"), request.getParameter("PrenomCli"), request.getParameter("MdpCli"), request.getParameter("TelCli"), request.getParameter("EmlCli"), request.getParameter("Siret"), request.getParameter("RaisonSociale"), request.getParameter("FormeSociale"));//
                } else {
                    result = "Échec de l'inscription.";
                }
                request.setAttribute(ConroleSaisieChamps.ATT_ERREURS, errors);
                request.setAttribute(ConroleSaisieChamps.ATT_RESULTAT, result);
            }
            url = "/WEB-INF/jspFormInscription.jsp";
            if (resCreation == true) {
                request.setAttribute("creer", request.getParameter("NomCli"));// envoie pour afficher dans la page bienvenucreation.
                url = "/WEB-INF/jspPageBienvenueCreation.jsp";
            } else {
                url = "/WEB-INF/jspFormInscription.jsp";
            }
        }

        /* Pour gérer le déconnexion ; session est mise à zéro*/
        if ("deconnect".equals(request.getParameter("section"))) {
            //TEST COOKIE DE CONNEXION
            Cookie controleOK = obtenirCookie(request.getCookies(), "Valider");
            if (controleOK != null) {
                controleOK.setMaxAge(0);
//		controleOK.setPath("/");
                response.addCookie(controleOK);
                session = request.getSession();
//                session.removeAttribute("monLogin");
                session.invalidate();
                url = "/WEB-INF/jspFormLogin.jsp";
            }
        }

        /* pour afficher le compte */
        if ("moncompte".equals(request.getParameter("section"))) {
            if (loggedOrNot != null) {
                BeanLogin login = (BeanLogin) session.getAttribute("monLogin");
                if (login == null) {
                    login = new BeanLogin();
                    session.setAttribute("maCreation", login);
                    session.setAttribute("userlogged", "Login");
                    url = "MasterController?section=login";
                } else {
                    request.setAttribute("compteNom", login.recupNom());
                    request.setAttribute("comptePrenom", login.recupPreNom());
                    request.setAttribute("compteTel", login.recupTel());
                    request.setAttribute("compteEml", login.recupEml());
                    request.setAttribute("compteRais", login.recupRaisonSoc());
                    url = "/WEB-INF/jspPageMonCompte.jsp";
                }
            } else {
                session.setAttribute("userlogged", "Login");
                url = "MasterController?section=login";
            }
        }

        /*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ RU @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
        //SELECTION D'UN LIVRE DANS UNE LISTE
        if (request.getParameter("paramIsbn") != null) {
            url = "/WEB-INF/jspPageFicheProduit.jsp";
//	  String isb = request.getParameter("paramIsbn");

            BeanRecherche beanProd = (BeanRecherche) request.getAttribute("monProduit");
            if (beanProd == null) {
                beanProd = new BeanRecherche();
                request.setAttribute("monProduit", beanProd);
            }
            beanProd.setISBN(request.getParameter("paramIsbn"));
            Livre l = beanProd.ficheProduit(request.getParameter("paramIsbn"));
            request.setAttribute("livre", l);
            request.setAttribute("prixlivre", ItemLivre.prixTtcReduc(l));

            BeanRecherche beanComment = (BeanRecherche) request.getAttribute("commentaires");
            if (beanComment == null) {
                beanComment = new BeanRecherche();
                request.setAttribute("commentaires", beanComment);
            }

            Collection<Evaluation> evals = beanProd.collectionCommentaires(request.getParameter("paramIsbn"));
            request.setAttribute("commentaires", evals);
        }

        //redirection href 'recherche avancee' depuis jspFormRecherche
        if (request.getParameter("adv") != null) {
            url = "/WEB-INF/jspFormRechercheAvancee.jsp";
        }

        //RECHERCHE DEPUIS ARBORESCENCE DES THEMES
        if (request.getParameter("souscat") != null) {
            BeanRecherche beanM = (BeanRecherche) request.getAttribute("beanTheme");
            if (beanM == null) {
                beanM = new BeanRecherche();
                request.setAttribute("beanTheme", beanM);
            }
            Collection<SousTheme> col = beanM.collectionArboSousThemes(request.getParameter("souscat"));
            request.setAttribute("themeC", col);
            url = "/WEB-INF/jspPageSousThemes.jsp";
            if (col == null || col.isEmpty()) {
                url = "/WEB-INF/jspPageAucunResultat.jsp";
            }
        }

        //RECHERCHE DEPUIS ARBORESCENCE DES SOUS-THEMES
        if (request.getParameter("livresparcat") != null) {
            System.out.println("CAT : " + request.getParameter("livresparcat"));
            BeanRecherche beanM = (BeanRecherche) request.getAttribute("beanSousTheme");
            if (beanM == null) {
                beanM = new BeanRecherche();
                request.setAttribute("beanSousTheme", beanM);
            }
            Collection<Livre> col = beanM.collectionLivresParSousTheme(request.getParameter("livresparcat"));
            request.setAttribute("collectionC", col);
            url = "/WEB-INF/jspResultatRecherche.jsp";
            if (col == null || col.isEmpty()) {
                url = "/WEB-INF/jspPageAucunResultat.jsp";
            }
        }

        //RECHERCHE DEPUIS LISTE DES EVENEMENTS
        if (request.getParameter("eventid") != null) {
            BeanRecherche beanE = (BeanRecherche) request.getAttribute("beanEvent");
            if (beanE == null) {
                beanE = new BeanRecherche();
                request.setAttribute("beanEvent", beanE);
            }
            Collection<Livre> col = beanE.collectionLivresParEvenement(request.getParameter("eventid"));
            request.setAttribute("collectionC", col);
            url = "/WEB-INF/jspResultatRecherche.jsp";
        }

        //SAISIE COMMENTAIRES
        if ("comment".equals(request.getParameter("section"))) {
            session.setAttribute("comment", null);
//	  session.setAttribute("note", null);
            if (request.getParameter("doItC") != null) {
                Cookie logged = obtenirCookie(request.getCookies(), "Valider");
                BeanRecherche br = (BeanRecherche) request.getAttribute("beanComment");
                if (br == null) {
                    br = new BeanRecherche();
                    request.setAttribute("beanComment", br);
                }
                try {
                    if (logged == null) {
                        url = "/WEB-INF/jspFormLogin.jsp";
                        System.out.println("none of them cookies for yer, landlubber");
                        session.setAttribute("whatwasidoin", "MasterController?paramIsbn=" + br.getISBN());
                        session.setAttribute("comment", request.getParameter("usercomment"));
//			br.setUserComment(request.getParameter("usercomment"));
//			session.setAttribute("note", request.getParameter("note"));
//			br.setNote(request.getParameter("note"));
                    } else {
                        System.out.println("COOKIE AVEC IDCLIENT " + logged.getValue());

                        String msg = "";
                        boolean commented = br.insererCommentaire(br.getISBN(), Integer.parseInt(logged.getValue()), request.getParameter("usercomment").trim(), Integer.parseInt(request.getParameter("note")));
                        if (commented) {
                            msg = "Vous avez déjà commenté ce livre";
                        } else {
                            msg = "Merci d'avoir commenté !";
                        }
                        System.out.println(msg);
//			request.setAttribute("messagecom", msg);
                        response.sendRedirect("MasterController?paramIsbn=" + br.getISBN());
                    }
                } catch (Exception ex) {
                    System.err.println("Erreur cookie: " + ex.getMessage());
                }
            }
        }

        //RECHERCHE DEPUIS FORMULAIRE SIMPLE
        if ("recherchesimple".equals(request.getParameter("section"))) {
            url = "/WEB-INF/jspFormRechercheSimple.jsp";

            if (request.getParameter("doIt") != null) {
                url = "/WEB-INF/jspResultatRecherche.jsp";
                BeanRecherche beanRS = (BeanRecherche) request.getAttribute("maRecherche");
                if (beanRS == null) {
                    beanRS = new BeanRecherche();
                    request.setAttribute("maRecherche", beanRS);
                }

                Collection<Livre> col = null;
                //type recherche ET
                if (request.getParameter("typerecherche").equals("and")) {
                    col = beanRS.collectionRechercheSimpleAND(request.getParameter("auteur"), request.getParameter("titre"), request.getParameter("isbn"), request.getParameter("keyword"));
                    //type recherche OU
                } else {
                    col = beanRS.collectionRechercheSimpleOR(request.getParameter("auteur"), request.getParameter("titre"), request.getParameter("isbn"), request.getParameter("keyword"));
                }
                //AUCUN RESULTAT DANS LA BASE
                if (col == null || col.isEmpty()) {
                    url = "/WEB-INF/jspPageAucunResultat.jsp";
                }
                request.setAttribute("collectionC", col);
            }
        }

        //RECHERCHE DEPUIS FORMULAIRE AVANCE
        if ("rechercheavancee".equals(request.getParameter("section"))) {
            if (request.getParameter("doItAdv") != null) {
                url = "/WEB-INF/jspResultatRecherche.jsp";
                BeanRecherche beanRA = (BeanRecherche) request.getAttribute("maRechercheAvancee");
                if (beanRA == null) {
                    beanRA = new BeanRecherche();
                    request.setAttribute("maRechercheAvancee", beanRA);
                }
                Collection<Livre> col = null;
                //type recherche ET
                if (request.getParameter("typerecherche").equals("and")) {
                    col = beanRA.collectionRechercheAvanceeAND(request.getParameter("auteur"), request.getParameter("titre"), request.getParameter("isbn"),
                            request.getParameter("keyword"), request.getParameter("editeur"), request.getParameter("edition"));
                } else {
                    //type recherche OU
                    col = beanRA.collectionRechercheAvanceeOR(request.getParameter("auteur"), request.getParameter("titre"), request.getParameter("isbn"),
                            request.getParameter("keyword"), request.getParameter("editeur"), request.getParameter("edition"));
                }
                //AUCUN RESULTAT DANS LA BASE
                if (col == null || col.isEmpty()) {
                    url = "/WEB-INF/jspPageAucunResultat.jsp";
                }
                request.setAttribute("collectionC", col);
            }
        }

        /* Lien vers une adresse */
        request.getRequestDispatcher(url).include(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
