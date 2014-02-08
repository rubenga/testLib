package allClasses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import allClasses.*;

public class Librairie {

    AccesBD abd = new AccesBD("sa", "sa", "localhost", 1433, "LIBRAIRIE");
    ResultSet rs;
//    ResultSet rs2;

    public Vector<ThemePrincipal> vecteurThemes(int i) {
        abd.ouvrirConnexionBase();
        String query = "Select * FROM THEMES ORDER BY LIBELLETHEME";
        Vector v = new Vector();
        ThemePrincipal tp;
        abd.ouvrirLienRequete();
        rs = abd.executerRequete(query);

        if (i == 1) {
            //cas avec enonce (jcombobox)
            try {
                v.add(tp = new ThemePrincipal("Liste des thèmes :"));
                while (rs.next()) {
                    tp = new ThemePrincipal(rs.getString("LIBELLETHEME"), rs.getString("CODETHEME"));
                    v.add(tp);
                }
            } catch (SQLException ex) {
                System.err.println("SQL:" + ex.getMessage() + ex.getErrorCode());
            }
        } else {
            //cas sans enonce (jlist)
            try {
                while (rs.next()) {
                    tp = new ThemePrincipal(rs.getString("LIBELLETHEME"), rs.getString("CODETHEME"));
                    v.add(tp);
                }
            } catch (SQLException ex) {
                System.err.println("SQL:" + ex.getMessage() + ex.getErrorCode());
            }
        }
        abd.fermerLienRequete(rs);
        abd.fermerConnexionBase();
        return v;
    }

    public Vector<SousTheme> vecteurSousThemes() {
        abd.ouvrirConnexionBase();
        String query = "SELECT DISTINCT * FROM SOUS_THEMES ORDER BY LIBELLESOUSTHEME";
        Vector v = new Vector();

        abd.ouvrirLienRequete();
        rs = abd.executerRequete(query);

        try {
            ThemePrincipal tp;

            while (rs.next()) {
                String query2 = "SELECT LIBELLETHEME FROM THEMES WHERE CODETHEME LIKE '" + rs.getString("CODETHEME") + "'";
                String rechercheLibelle = rechercheStringBase(query2, "LIBELLETHEME");
                tp = new ThemePrincipal(rechercheLibelle, rs.getString("CODETHEME"));

                SousTheme st = new SousTheme(tp, rs.getString("LIBELLESOUSTHEME"), rs.getString("CODESOUSTHEME"));
                v.add(st);
            }

        } catch (SQLException ex) {
            System.err.println("SQL:" + ex.getMessage() + ex.getErrorCode());
        }

        abd.fermerLienRequete(rs);
        abd.fermerConnexionBase();
        return v;
    }

    public Vector<SousTheme> vecteurSelectionSousThemes(String s) {
        abd.ouvrirConnexionBase();

        String query = "SELECT * FROM SOUS_THEMES WHERE CODETHEME LIKE '" + s + "' ORDER BY LIBELLESOUSTHEME";

        Vector v = new Vector();

        abd.ouvrirLienRequete();
        rs = abd.executerRequete(query);

        try {
            ThemePrincipal tp;

            while (rs.next()) {
                String query2 = "SELECT LIBELLETHEME FROM THEMES WHERE CODETHEME LIKE '" + rs.getString("CODETHEME") + "'";
                String rechercheLibelle = rechercheStringBase(query2, "LIBELLETHEME");
                tp = new ThemePrincipal(rechercheLibelle, rs.getString("CODETHEME"));

                SousTheme st = new SousTheme(tp, rs.getString("LIBELLESOUSTHEME"), rs.getString("CODESOUSTHEME"));
                v.add(st);
            }
        } catch (SQLException ex) {
            System.err.println("SQL:" + ex.getMessage() + ex.getErrorCode());
            JOptionPane.showMessageDialog(null, "vecteurSelectionSousThemesFermer");
        }

        abd.fermerLienRequete(rs);
        abd.fermerConnexionBase();
        return v;
    }

    public Vector<Client> vecteurCommentairesClient() {
        abd.ouvrirConnexionBase();
        String query = "SELECT DISTINCT C.PRENOMCLIENT, C.NOMCLIENT, C.IDCLIENT FROM CLIENTS C, EVALUER E WHERE C.IDCLIENT = E.IDCLIENT ORDER BY C.IDCLIENT";
        Vector v = new Vector();
        Client cl;
        abd.ouvrirLienRequete();
        rs = abd.executerRequete(query);

        try {
            while (rs.next()) {
                cl = new Client(rs.getString("PRENOMCLIENT"), rs.getString("NOMCLIENT"), rs.getInt("IDCLIENT"));
                v.add(cl);
            }
        } catch (SQLException ex) {
            System.err.println("SQL:" + ex.getMessage() + ex.getErrorCode());
        }

        abd.fermerLienRequete(rs);
        abd.fermerConnexionBase();
        return v;
    }

    public Vector<Client> vecteurCommentairesLivre() {
        abd.ouvrirConnexionBase();
        String query = "SELECT DISTINCT L.TITRELIVRE, L.ISBN FROM LIVRES L, EVALUER E WHERE L.ISBN = E.ISBN ORDER BY L.TITRELIVRE";
        Vector v = new Vector();
        Livre lb;
        abd.ouvrirLienRequete();
        rs = abd.executerRequete(query);

        try {
            Auteur aut;
            while (rs.next()) {
                String query2 = "SELECT IDAUTEUR FROM LIVRES WHERE ISBN LIKE '" + rs.getString("ISBN") + "'";
                int rechercheId = rechercheIntBase(query2, "IDAUTEUR");
                aut = new Auteur("", "", rechercheId); //Auteur(String prenom, String nom, int id)

                lb = new Livre(rs.getString("ISBN"), null, null, null, aut, null, rs.getString("TITRELIVRE"), null, "", 0, null, "", "", 0, 0); //rs.getString("TITRELIVRE")  Livre(String isbn, Auteur auteurLivre, String titreLivre
//                System.out.println(lb);

//                lb = new Livre(rs.getString("TITRELIVRE")); //affiche titres, mais il faut une liste d'objets comportant un ISBN
                v.add(lb);
            }
        } catch (SQLException ex) {
            System.err.println("SQL:" + ex.getMessage() + ex.getErrorCode());
        }

        abd.fermerLienRequete(rs);
        abd.fermerConnexionBase();
        return v;
    }

    public Vector<Evaluation> vecteurSelectionCommentairesClient(int i) {
        abd.ouvrirConnexionBase();

        String query = "SELECT * FROM EVALUER WHERE IDCLIENT LIKE '" + i + "' ORDER BY ISBN";

        Vector v = new Vector();

        abd.ouvrirLienRequete();
        rs = abd.executerRequete(query);

        try {
            Livre lb;
            while (rs.next()) {
                String query2 = "SELECT TITRELIVRE FROM LIVRES WHERE ISBN LIKE '" + rs.getString("ISBN") + "'";
                String rechercheLibelle = rechercheStringBase(query2, "TITRELIVRE");
                lb = new Livre(rechercheLibelle); //Livre(String titreLivre)
                Evaluation eva = new Evaluation(rs.getString("ISBN"), rs.getInt("IDCLIENT"), lb, rs.getString("COMMENTAIRE"), rs.getInt("EVALUATION"));
                v.add(eva);
            }
        } catch (SQLException ex) {
            System.err.println("SQL:" + ex.getMessage() + ex.getErrorCode());
            JOptionPane.showMessageDialog(null, "vecteurSelectionSousThemesFermer");
        }

        abd.fermerLienRequete(rs);
        abd.fermerConnexionBase();
        return v;
    }

    public Vector<Evaluation> vecteurSelectionCommentairesLivre(String isbn) { //lb.getIsbn()
        abd.ouvrirConnexionBase();

        String query = "SELECT * FROM EVALUER WHERE ISBN LIKE '" + isbn + "' ORDER BY ISBN";

        Vector v = new Vector();

        abd.ouvrirLienRequete();
        rs = abd.executerRequete(query);

        try {
            Livre lb;
            while (rs.next()) {
                String query2 = "SELECT TITRELIVRE FROM LIVRES WHERE ISBN LIKE '" + rs.getString("ISBN") + "'";
                String rechercheLibelle = rechercheStringBase(query2, "TITRELIVRE");
                lb = new Livre(rechercheLibelle); //Livre(String titreLivre)
                Evaluation eva = new Evaluation(rs.getString("ISBN"), rs.getInt("IDCLIENT"), lb, rs.getString("COMMENTAIRE"), rs.getInt("EVALUATION"));
                v.add(eva);
            }
        } catch (SQLException ex) {
            System.err.println("SQL:" + ex.getMessage() + ex.getErrorCode());
            JOptionPane.showMessageDialog(null, "vecteurSelectionSousThemesFermer");
        }

        abd.fermerLienRequete(rs);
        abd.fermerConnexionBase();
        return v;
    }

    public Vector vecteurSelectionClient(String isbn) { //lb.getIsbn()
        abd.ouvrirConnexionBase();

        String query = "SELECT C.PRENOMCLIENT, C.NOMCLIENT, E.IDCLIENT, E.ISBN, E.EVALUATION FROM EVALUER E, CLIENTS C WHERE C.IDCLIENT = E.IDCLIENT AND E.ISBN LIKE '" + isbn + "' ORDER BY E.ISBN";

        Vector v = new Vector();

        abd.ouvrirLienRequete();
        rs = abd.executerRequete(query);

        try {
            while (rs.next()) {
                Client cl = new Client(rs.getString("PRENOMCLIENT"), rs.getString("NOMCLIENT"), rs.getInt("IDCLIENT"));
//                String clientNote = cl.toString() + " (note : " + rs.getInt("EVALUATION") + ")";
//                v.add(clientNote);
                v.add(cl);
            }
        } catch (SQLException ex) {
            System.err.println("SQL:" + ex.getMessage() + ex.getErrorCode());
        }

        abd.fermerLienRequete(rs);
        abd.fermerConnexionBase();
        return v;
    }

    public String rechercheStringBase(String s, String columnName) {
        String reponse = null;
        ResultSet rsR;

        Vector v = new Vector();

        abd.ouvrirLienRequete();
        rsR = abd.executerRequete(s);

        try {
            while (rsR.next()) {
                reponse = rsR.getString(columnName);
            }

        } catch (SQLException ex) {
            System.err.println("SQL:" + ex.getMessage() + ex.getErrorCode());
        }

        abd.fermerLienRequete(rsR);

        return reponse;
    }

    public int rechercheIntBase(String s, String columnName) {
        int reponse = 0;
        ResultSet rsR;

        Vector v = new Vector();

        abd.ouvrirLienRequete();
        rsR = abd.executerRequete(s);

        try {
            while (rsR.next()) {
                reponse = rsR.getInt(columnName);
            }

        } catch (SQLException ex) {
            System.err.println("SQL:" + ex.getMessage() + ex.getErrorCode());
        }

        abd.fermerLienRequete(rsR);

        return reponse;
    }

    public boolean suppressionSousTheme(String libelle, String code) {
        boolean existeDansBase = false;
        abd.ouvrirConnexionBase();
        String query = "DELETE FROM SOUS_THEMES WHERE CODESOUSTHEME LIKE '" + code + "' AND LIBELLESOUSTHEME LIKE '" + libelle + "'";
        abd.ouvrirLienRequete();
        int rezReq = abd.executerRequeteUpdate(query);
        if (rezReq == 0) {
            existeDansBase = false;
        } else {
            existeDansBase = true;
        }
        abd.fermerLienRequete();
        abd.fermerConnexionBase();
        return existeDansBase;
    }

    public boolean suppressionTheme(String libelle, String code) {
        boolean existeDansBase = false;
        abd.ouvrirConnexionBase();
        String query = "DELETE FROM THEMES WHERE CODETHEME LIKE '" + code + "' AND LIBELLETHEME LIKE '" + libelle + "'";
        //gerer erreur si livre existant avec ce theme
        abd.ouvrirLienRequete();
        int rezReq = abd.executerRequeteUpdate(query);
        System.out.println(rezReq);
        if (rezReq == 0) {
            existeDansBase = false;
        } else {
            existeDansBase = true;
        }
        abd.fermerLienRequete();
        abd.fermerConnexionBase();
        return existeDansBase;
    }

    public void modificationSousTheme(SousTheme st, SousTheme newst) {
        abd.ouvrirConnexionBase();
        //modification données table sous_theme
        String query = "UPDATE SOUS_THEMES SET CODESOUSTHEME = '" + newst.code + "', LIBELLESOUSTHEME = '" + newst.nomTheme + "', CODETHEME = '" + newst.getThemeDuSousTheme().getCode() + "' WHERE CODESOUSTHEME = '" + st.code + "'";
        //modification données table livres
//    String query2 = "UPDATE LIVRES SET CODESOUSTHEME = '"+newst.code+"', CODETHEME = '"+newst.getThemeDuSousTheme().getCode()+"' WHERE CODESOUSTHEME = '" + st.code + "'";

        abd.ouvrirLienRequete();
        abd.executerRequeteUpdate(query);
//	abd.executerRequeteUpdate(query2);   
        abd.fermerLienRequete();
        abd.fermerConnexionBase();

//      suppressionSousTheme(st.nomTheme, st.code);
//	st.quicksave(newst);
    }

    public void modificationTheme(ThemePrincipal tp, ThemePrincipal newtp) {
        abd.ouvrirConnexionBase();
        String query = "UPDATE THEMES SET CODETHEME = '" + newtp.code + "', LIBELLETHEME = '" + newtp.nomTheme + "' WHERE CODETHEME = '" + tp.code + "'";

        abd.ouvrirLienRequete();
        abd.executerRequeteUpdate(query);
        abd.fermerLienRequete();
        abd.fermerConnexionBase();
    }

    public boolean rechercheIndexSousThemeBase(String s) {
        boolean trouve = false;
        String query = "SELECT * FROM SOUS_THEMES WHERE CODESOUSTHEME = '" + s + "'";
        abd.ouvrirConnexionBase();
        abd.ouvrirLienRequete();
        rs = abd.executerRequete(query);
        String test = null;
        try {
            while (rs.next()) {
                test = rs.getString("CODESOUSTHEME");
                if (test.equals(s)) {
                    trouve = true;
                } else {
                    trouve = false;
                }
            }
        } catch (SQLException ex) {
            System.err.println("SQL:" + ex.getMessage() + ex.getErrorCode());
        }
        abd.fermerLienRequete(rs);
        abd.fermerConnexionBase();
        return trouve;
    }

    public boolean rechercheIndexThemeBase(String s) {
        boolean trouve = false;
        String query = "SELECT * FROM THEMES WHERE CODETHEME = '" + s + "'";
        abd.ouvrirConnexionBase();
        abd.ouvrirLienRequete();
        rs = abd.executerRequete(query);
        String test = null;
        try {
            while (rs.next()) {
                test = rs.getString("CODETHEME");
                if (test.equals(s)) {
                    trouve = true;
                } else {
                    trouve = false;
                }
            }
        } catch (SQLException ex) {
            System.err.println("SQL:" + ex.getMessage() + ex.getErrorCode());
        }
        abd.fermerLienRequete(rs);
        abd.fermerConnexionBase();
        return trouve;
    }

    public String getCommentaire(int idClient) {
        String commentaire = null;
        String query = "SELECT COMMENTAIRE FROM EVALUER WHERE IDCLIENT = '" + idClient + "'";
        abd.ouvrirConnexionBase();
        abd.ouvrirLienRequete();
        rs = abd.executerRequete(query);

        try {
            while (rs.next()) {
                commentaire = rs.getString("COMMENTAIRE");
            }
        } catch (SQLException ex) {
            System.err.println("SQL:" + ex.getMessage() + ex.getErrorCode());
        }

        abd.fermerLienRequete(rs);
        abd.fermerConnexionBase();
        return commentaire;
    }

    public Vector rechercheCommentaireParOuvrage(String terme) {
        Vector v = new Vector();
        String query = "SELECT DISTINCT ISBN, IDCLIENT FROM EVALUER WHERE EXISTS \n"
                + "(SELECT L.ISBN, L.IDEDITEUR FROM LIVRES L, EDITEURS E \n"
                + "WHERE L.IDEDITEUR = E.IDEDITEUR AND E.NOMEDITEUR LIKE '%" + terme + "%') \n"
                + "UNION \n"
                + "(SELECT L.ISBN, L.IDAUTEUR FROM LIVRES L, AUTEURS A \n"
                + "WHERE A.NOMAUTEUR LIKE '%" + terme + "%' AND L.IDAUTEUR = A.IDAUTEUR) \n"
                + "UNION \n"
                + "(SELECT L.ISBN, L.IDAUTEUR FROM LIVRES L, AUTEURS A \n"
                + "WHERE A.PRENOMAUTEUR LIKE '%" + terme + "%' AND L.IDAUTEUR = A.IDAUTEUR) \n"
                + "UNION \n"
                + "(SELECT L.ISBN, L.SOUSTITRE FROM LIVRES L, EVALUER EV \n"
                + "WHERE L.ISBN = EV.ISBN AND L.SOUSTITRE LIKE '%" + terme + "%') \n"
                + "UNION \n"
                + "(SELECT L.ISBN, EV.IDCLIENT FROM LIVRES L, EVALUER EV \n"
                + "WHERE L.ISBN = EV.ISBN AND L.ISBN LIKE '%" + terme + "%') \n"
                + "UNION \n"
                + "(SELECT L.ISBN, EV.IDCLIENT FROM LIVRES L, EVALUER EV \n"
                + "WHERE L.ISBN = EV.ISBN AND L.TITRELIVRE LIKE '%" + terme + "%') \n"
                + "UNION \n"
                + "(SELECT L.ISBN, EV.IDCLIENT FROM LIVRES L, EVALUER EV \n"
                + "WHERE L.ISBN = EV.ISBN AND L.TITRELIVRE LIKE '%" + terme + "%') \n"
                + "UNION \n"
                + "(SELECT L.ISBN, EV.IDCLIENT FROM LIVRES L, EVALUER EV \n"
                + "WHERE L.ISBN = EV.ISBN AND L.RESUMELIVRE LIKE '%" + terme + "%')";
        abd.ouvrirConnexionBase();
        abd.ouvrirLienRequete();
        rs = abd.executerRequete(query);
        try {
            while (rs.next()) {
                String query2 = "SELECT * FROM EVALUER WHERE ISBN = '" + rs.getString("ISBN") + "' AND IDCLIENT = '" + rs.getInt("IDCLIENT") + "'";
                ResultSet rs2;
                abd.ouvrirLienRequete();
                rs2 = abd.executerRequete(query2);
                try {
                    while (rs.next()) {
                        String query3 = "SELECT TITRELIVRE FROM LIVRES WHERE ISBN LIKE '" + rs.getString("ISBN") + "'";
                        String rechercheLibelle = rechercheStringBase(query3, "TITRELIVRE");
                        Livre lb = new Livre(rechercheLibelle);
                        System.out.println(lb);
                        Evaluation eva = new Evaluation(rs.getString("ISBN"), rs.getInt("IDCLIENT"), lb, rs.getString("COMMENTAIRE"), rs.getInt("EVALUATION"));
                        v.add(eva);
                        System.out.println(v);
                    }
                } catch (SQLException ex) {
                    System.err.println("SQL:" + ex.getMessage() + ex.getErrorCode());
                }
                abd.fermerLienRequete(rs2);
            }
        } catch (SQLException ex) {
            System.err.println("SQL:" + ex.getMessage() + ex.getErrorCode());
        }
        System.out.println(v);
        abd.fermerLienRequete(rs);
        abd.fermerConnexionBase();
        return v;
    }

    public void suppressionCommentaire(String recherche) {
        String query = "DELETE FROM EVALUER WHERE COMMENTAIRE LIKE '" + recherche + "'";
        abd.ouvrirLienRequete();
        abd.executerRequeteUpdate(query);
        abd.fermerLienRequete();
        abd.fermerConnexionBase();
    }

//    public void modificationSousTheme(String libelle, String code, SousTheme st) {
//        suppressionSousTheme(libelle, code);
//        st.quicksave(st);
//    }
//    public Vector<SousTheme> vecteurSelectionSousThemes(String s) {
//        String query = "SELECT * FROM SOUS_THEMES WHERE CODETHEME LIKE '"+s+"' ORDER BY LIBELLESOUSTHEME";
//        Connection connexion = conBase();
//        Vector v = new Vector();
//        try {
//            Statement stmt = connexion.createStatement();
//            ResultSet rs = stmt.executeQuery(query);
//            
//            while (rs.next()) {
//                v.add(rs.getString("LIBELLESOUSTHEME"));
//            }
//
//            rs.close();
//            stmt.close();
//        } catch (SQLException ex) {
//            System.err.println("SQL:" + ex.getMessage() + ex.getErrorCode());
//        }
//        try {
//            connexion.close();
//        } catch (SQLException ex) {
//            System.err.println("Close:" + ex.getMessage());
//        }
//        return v;
//    }
    public AccesBD getAbd() {
        return abd;
    }
//  public Vector<SousTheme> vecteurSelectionSousThemes(String s) {
//	Vector<SousTheme> v = new Vector<>();
//	v = vecteurSousThemes();
//	Vector<SousTheme> v2 = new Vector<>();
//	for (SousTheme st : v2) {
//	  if(st.getCode().equals(s)){
//		v2.add(st);
//	  }
//	}
//	return v2;
//  }
}
