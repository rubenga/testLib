/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package allBeans;

import allClasses.AccesBD;
import allClasses.Commande;
import allClasses.LigneCommande;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author cdi412
 */
public class BeanPaiementCommande implements Serializable {
    
    private final String codeStatut = "1";
    private final String formatDate = "SET DATEFORMAT dmy;\n";
    private int numCom;
    private int numLiv;
    private int nbLigneAffectee[];
    
    public BeanPaiementCommande() {
    }
    
    public BeanPaiementCommande(ArrayList<LigneCommande> lc, String idClient, String codePaiement, String idModLiv, String comCommande, String idAdrLiv, String idAdrFact) {
        numCom = leNumCommande();
        numLiv = leNumLivraison();

        /* Vérifier que les identifiants ont bien été affectés aux variables */
        if (numCom != 0 && numLiv != 0) {
            String debTrans = "BEGIN TRANSACTION\n";
            String queryCommande = insertCommande(idClient, codePaiement, idModLiv, comCommande) + "\n";
            String queryLigneCommande = insertLigneCommande(lc);/*Retour à la ligne déjà inséré*/
            String queryLivraison = insertLivraison(idAdrLiv, idAdrFact) + "\n";
            String queryUpdateCom = updateCommande() + "\n";
            String finTrans = "COMMIT\n";
            System.out.println("Transaction \n"+debTrans + formatDate + queryCommande + queryLigneCommande + queryLivraison + queryUpdateCom + finTrans);
            AccesBD abd = new AccesBD("sa", "sa", "localhost", 1433, "LIBRAIRIE");
                        
            abd.ouvrirConnexionBase();
            abd.ouvrirLienRequete();
            abd.addBatch(debTrans + formatDate + queryCommande + queryLigneCommande + queryLivraison + queryUpdateCom + finTrans);
            nbLigneAffectee = abd.executerRequeteTransaction();
            abd.fermerLienRequete();
            abd.fermerConnexionBase();
        }
    }

    /**
     * Avoir le numéro de la commande
     *
     * @return le numéro de commande pour l'insertion de la commande en cours
     */
    private int leNumCommande() {
        int numCommande = 0;
        String query = "SELECT MAX(NUMCOMMANDE) [numComMax] FROM COMMANDES";
        AccesBD abd = new AccesBD("sa", "sa", "localhost", 1433, "LIBRAIRIE");
        ResultSet rs;
        
        abd.ouvrirConnexionBase();
        abd.ouvrirLienRequete();
        rs = abd.executerRequete(query);
        try {
            while (rs.next()) {
                numCommande = rs.getInt("numComMax") + 1;
            }
        } catch (SQLException ex) {
            System.err.println("Problème de lecture du resultat : " + ex.getMessage());
        }
        abd.fermerLienRequete(rs);
        abd.fermerConnexionBase();
        
        return numCommande;
    }

    /**
     * Avoir le numéro de la livraison
     *
     * @return le numéro de la livraison pour l'insertion de la commande en
     * cours
     */
    private int leNumLivraison() {
        int numLivraison = 0;
        String query = "SELECT MAX(NUMLIVRAISON) [numLivMax] FROM LIVRAISONS";
        AccesBD abd = new AccesBD("sa", "sa", "localhost", 1433, "LIBRAIRIE");
        ResultSet rs;
        
        abd.ouvrirConnexionBase();
        abd.ouvrirLienRequete();
        rs = abd.executerRequete(query);
        try {
            while (rs.next()) {
                numLivraison = rs.getInt("numLivMax") + 1;
            }
        } catch (SQLException ex) {
            System.err.println("Problème de lecture du resultat : " + ex.getMessage());
        }
        abd.fermerLienRequete(rs);
        abd.fermerConnexionBase();
        
        return numLivraison;
    }

    /**
     * A faire en premier
     *
     * @param idClient
     * @param codePaiement
     * @param idModLiv
     * @param comCommande
     * @return La requête pour faire l'insertion de la commande
     */
    private String insertCommande(String idClient, String codePaiement, String idModLiv, String comCommande) {
        String queryCommande = "insert into COMMANDES "
                + "(NUMCOMMANDE, "
                + "CODEMOYENPAIEMENT, "
                + "IDCLIENT, "
                + "IDMODELIVRAISON, "
                + "CODESTATUT, "
                + "DATECOMMANDE, "
                + "COMMENTAIRECOMMANDE)\n"
                + "values (" + numCom + ", "
                + "'" + codePaiement + "', "
                + idClient + ", "
                + idModLiv + ", "
                + codeStatut + ", "
                + "Convert(VarChar, GetDate(), 103), "
                + "'" + comCommande.replace("'", "''") + "');";
        return queryCommande;
    }

    /**
     * A faire après l'insertion de la commande
     *
     * @param lc : les lignes de la commande.
     * @return La requête pour faire l'insertion des lignes de la commande.
     */
    private String insertLigneCommande(ArrayList<LigneCommande> ligneCommande) {
        String queryLigneCommande = "";
        for (LigneCommande lc : ligneCommande) {
            queryLigneCommande += "insert into LIGNE_COMMANDE "
                    + "(ISBN, "
                    + "NUMCOMMANDE, "
                    + "PRIXLIVREHTLC, "
                    + "QUANTITECOMMANDE, "
                    + "TVALC)\n"
                    + "values ('" + lc.getLiv().getIsbn() + "', "
                    + numCom + ", "
                    + lc.getPrixHt() + ", "
                    + lc.getQuantite() + ", "
                    + lc.getTvaCommande() + ");\n";
        }
        return queryLigneCommande;
    }

    /**
     * A faire après la commande
     *
     * @return La requête pour faire l'insertion de la livraison et des adresses
     * de facturation et livraison.
     */
    private String insertLivraison(String idAdrLiv, String idAdrFact) {
        String queryLivraison = "insert into LIVRAISONS "
                + "(NUMLIVRAISON, "
                + "IDADDRESSELIVRAISON, "
                + "IDADDRESSEFACTURE, "
                + "NUMCOMMANDE, "
                + "NUMFACTURE, "
                + "DATELIVRAISON, "
                + "DATEFACTURE) \n"
                + "values (" + numLiv + ", "
                + idAdrLiv + ", "
                + idAdrFact + ", "
                + numCom + ", "
                + "null, "
                + "'', "
                + "'');";
        return queryLivraison;
    }

    /**
     * Mettre à jour la commande quand la livraison est insérée. A faire après
     * l'insertion de la livraison.
     *
     * @return La requête pour faire la mise à jour de la commande (affecter une
     * mivraison à la commande).
     */
    private String updateCommande() {
        String queryCommande = "UPDATE COMMANDES SET NUMLIVRAISON = " + numLiv + " WHERE NUMCOMMANDE = " + numCom;
        return queryCommande;
    }

    @Override
    public String toString() {
        String text = "";
        for (int i = 0; i < nbLigneAffectee.length; i++) {
            text += nbLigneAffectee[i]+"\n";
        }
        return text;
    }
    
}
