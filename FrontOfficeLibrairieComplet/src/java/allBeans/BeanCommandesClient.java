/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package allBeans;

import allClasses.AccesBD;
import allClasses.Auteur;
import allClasses.Client;
import allClasses.Commande;
import allClasses.Editeur;
import allClasses.LigneCommande;
import allClasses.Livraison;
import allClasses.Livre;
import allClasses.SousTheme;
import allClasses.Theme;
import allClasses.ThemePrincipal;
import allClasses.Tva;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author cdi412
 */
public class BeanCommandesClient implements Serializable {

    private ArrayList<Commande> lesCommandes;

    public BeanCommandesClient() {
    }

    public BeanCommandesClient(String idClient) {
        lesCommandes = new ArrayList();
        Client c = affecterLeClient(idClient);
    }

    private Client affecterLeClient(String idClient) {
        Client c = null;
        String queryClient = "SELECT IDCLIENT,NOMCLIENT,PRENOMCLIENT,MOTDEPASSECLIENT,"
                + "TELCLIENT,EMAILCLIENT,SIRET,RAISONSOCIALE,FORMESOCIALE\n"
                + "FROM CLIENTS\n"
                + "WHERE IDCLIENT = " + idClient;

        AccesBD abd = new AccesBD("sa", "sa", "localhost", 1433, "LIBRAIRIE");
        ResultSet rs = null;

        abd.ouvrirConnexionBase();
        abd.ouvrirLienRequete();
        rs = abd.executerRequete(queryClient);
        try {
            while (rs.next()) {
                c = new Client(rs.getString("MOTDEPASSECLIENT"),
                        rs.getString("TELCLIENT"),
                        rs.getString("EMAILCLIENT"),
                        rs.getString("SIRET"),
                        rs.getString("RAISONSOCIALE"),
                        rs.getString("FORMESOCIALE"),
                        rs.getString("PRENOMCLIENT"),
                        rs.getString("NOMCLIENT"),
                        Integer.parseInt(idClient));
            }
        } catch (SQLException ex) {
            System.err.println("Problème de lecture du resultat : " + ex.getMessage());
        }
        abd.fermerLienRequete(rs);
        abd.fermerConnexionBase();
        return c;
    }

    private Livraison affecterAdresse(int numCommande) {
        Livraison l = null;
        String queryAdrFact = "SELECT NUMLIVRAISON,"
                + "NUMFACTURE,DATEFACTURE,DATELIVRAISON,"
                + "IDADDRESSE,NOMADRESSE,RUE,CODEPOSTAL,VILLE,PAYS\n"
                + "FROM LIVRAISONS L LEFT JOIN ADRESSES A ON L.IDADDRESSEFACTURE = A.IDADDRESSE\n"
                + "WHERE NUMCOMMANDE = " + numCommande;
        String queryAdrLiv = "SELECT NUMLIVRAISON,"
                + "NUMFACTURE,DATEFACTURE,DATELIVRAISON,"
                + "IDADDRESSE,NOMADRESSE,RUE,CODEPOSTAL,VILLE,PAYS\n"
                + "FROM LIVRAISONS L LEFT JOIN ADRESSES A ON L.IDADDRESSELIVRAISON = A.IDADDRESSE\n"
                + "WHERE NUMCOMMANDE = " + numCommande;

        AccesBD abd = new AccesBD("sa", "sa", "localhost", 1433, "LIBRAIRIE");
        ResultSet rsFact = null;
        ResultSet rsLiv = null;

        abd.ouvrirConnexionBase();
        abd.ouvrirLienRequete();
        rsFact = abd.executerRequete(queryAdrFact);
        rsLiv = abd.executerRequete(queryAdrLiv);
        try {
            while (rsFact.next()) {
                
            }
        } catch (SQLException ex) {
            System.err.println("Problème de lecture du resultat : " + ex.getMessage());
        }
        abd.fermerLienRequete(rsFact);
        abd.fermerLienRequete(rsLiv);
        abd.fermerConnexionBase();
        return l;
    }

    private ArrayList<LigneCommande> affecterLesLigneCommande(int numCommande) {
        ArrayList<LigneCommande> lc = new ArrayList();
        String queryLc = "SELECT L.ISBN,TITRELIVRE,SOUSTITRE,LANGUELIVRE,NBPAGELIVRE,"
                + "DATEEDITIONLIVRE,IMAGELIVRE,RESUMELIVRE,PRIXLIVRE,POIDSLIVRE,"
                + "PRIXLIVREHTLC,QUANTITECOMMANDE,TVALC,"
                + "L.IDAUTEUR,NOMAUTEUR,PRENOMAUTEUR,"
                + "L.IDEDITEUR,NOMEDITEUR,"
                + "L.IDTVA,TAUXTVA,LIBELLETVA,"
                + "L.CODETHEME,LIBELLETHEME,"
                + "L.CODESOUSTHEME,L.CODETHEME,LIBELLESOUSTHEME\n"
                + "FROM LIVRES L, LIGNE_COMMANDE LC, AUTEURS A, EDITEURS E, TVA T, THEMES TH, SOUS_THEMES ST\n"
                + "WHERE L.ISBN = LC.ISBN\n"
                + "AND L.IDAUTEUR = A.IDAUTEUR\n"
                + "AND L.IDEDITEUR = E.IDEDITEUR\n"
                + "AND L.IDTVA = T.IDTVA\n"
                + "AND L.CODETHEME = TH.CODETHEME\n"
                + "AND L.CODESOUSTHEME = ST.CODESOUSTHEME\n"
                + "AND LC.NUMCOMMANDE = " + numCommande;

        AccesBD abd = new AccesBD("sa", "sa", "localhost", 1433, "LIBRAIRIE");
        ResultSet rs = null;
        abd.ouvrirConnexionBase();
        abd.ouvrirLienRequete();
        rs = abd.executerRequete(queryLc);
        try {
            while (rs.next()) {
                Auteur a = new Auteur(rs.getString("PRENOMAUTEUR"), rs.getString("NOMAUTEUR"), rs.getInt("IDAUTEUR"));
                Editeur e = new Editeur(rs.getString("NOMEDITEUR"), rs.getInt("IDEDITEUR"));
                Tva tv = new Tva(rs.getInt("IDTVA"), rs.getString("LIBELLETVA"), rs.getFloat("TAUXTVA"));
                ThemePrincipal t = new ThemePrincipal(rs.getString("LIBELLETHEME"), rs.getString("CODETHEME"));
                SousTheme st = new SousTheme(t, rs.getString("LIBELLESOUSTHEME"), rs.getString("CODESOUSTHEME"));
                Livre l = new Livre(rs.getString("ISBN"), t, st, e, a, tv,
                        rs.getString("TITRELIVRE"),
                        rs.getString("SOUSTITRE"),
                        rs.getString("LANGUELIVRE"),
                        rs.getInt("NBPAGELIVRE"),
                        rs.getDate("DATEEDITIONLIVRE"),
                        rs.getString("IMAGELIVRE"),
                        rs.getString("RESUMELIVRE"),
                        rs.getFloat("PRIXLIVRE"),
                        rs.getFloat("POIDSLIVRE"));
                LigneCommande ligne = new LigneCommande(l, rs.getFloat("PRIXLIVREHTLC"),
                        rs.getInt("QUANTITECOMMANDE"), rs.getFloat("TVALC"));

                lc.add(ligne);
            }
        } catch (SQLException ex) {
            System.err.println("Problème de lecture du resultat : " + ex.getMessage());
        }
        abd.fermerLienRequete(rs);
        abd.fermerConnexionBase();
        return lc;
    }
}
