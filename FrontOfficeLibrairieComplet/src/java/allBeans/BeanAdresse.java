/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package allBeans;

import allClasses.AccesBD;
import allClasses.Adresse;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author cdi412
 */
public class BeanAdresse implements Serializable {

    private HashMap<Integer, Adresse> listeAdresseClient;

    public BeanAdresse() {
    }
    /* Récupérer les adresses déjà connues du client */

    public BeanAdresse(int idClient) {
        listeAdresseClient = new HashMap();
        /* Vérifie s'il a déjà des commandes */
        if (nombreCommandeClient(idClient) > 0) {
            ArrayList<Integer> lesNumLiv;
            /* Récupérer le numéro de livraison des commandes */
            lesNumLiv = lesNumLivraisonDuClient(idClient);
            AccesBD abd = new AccesBD("sa", "sa", "localhost", 1433, "LIBRAIRIE");
            ResultSet rs;
            abd.ouvrirConnexionBase();
            for (int i : lesNumLiv) {
                abd.ouvrirLienRequete();
                /* Avoir ses adresses de facturation */
                rs = abd.executerRequete("SELECT IDADDRESSE, NOMADRESSE, RUE, CODEPOSTAL, VILLE, PAYS\n"
                        + "FROM ADRESSES A LEFT JOIN LIVRAISONS L ON A.IDADDRESSE = L.IDADDRESSEFACTURE\n"
                        + "WHERE NUMLIVRAISON = " + i);
                try {
                    while (rs.next()) {
                        if (!listeAdresseClient.containsKey(rs.getInt("IDADDRESSE"))) {
                            Adresse a = new Adresse(rs.getInt("IDADDRESSE"),
                                    rs.getString("NOMADRESSE"),
                                    rs.getString("RUE"),
                                    rs.getString("CODEPOSTAL"),
                                    rs.getString("VILLE"),
                                    rs.getString("PAYS"));
                            listeAdresseClient.put(rs.getInt("IDADDRESSE"), a);
                        }
                    }

                } catch (SQLException ex) {
                    System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());
                }
                abd.fermerLienRequete(rs);
                abd.ouvrirLienRequete();
                /* Avoir ses adresses de livraison */
                rs = abd.executerRequete("SELECT IDADDRESSE, NOMADRESSE, RUE, CODEPOSTAL, VILLE, PAYS\n"
                        + "FROM ADRESSES A LEFT JOIN LIVRAISONS L ON A.IDADDRESSE = L.IDADDRESSELIVRAISON\n"
                        + "WHERE NUMLIVRAISON = " + i);
                try {
                    while (rs.next()) {
                        if (!listeAdresseClient.containsKey(rs.getInt("IDADDRESSE"))) {
                            Adresse a = new Adresse(rs.getInt("IDADDRESSE"),
                                    rs.getString("NOMADRESSE"),
                                    rs.getString("RUE"),
                                    rs.getString("CODEPOSTAL"),
                                    rs.getString("VILLE"),
                                    rs.getString("PAYS"));
                            listeAdresseClient.put(rs.getInt("IDADDRESSE"), a);
                        }
                    }

                } catch (SQLException ex) {
                    System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());
                }
                abd.fermerLienRequete(rs);
            }
            abd.fermerConnexionBase();
        }
    }

    private static int nombreCommandeClient(int idClient) {
        int nbCom = 0;
        AccesBD abd = new AccesBD("sa", "sa", "localhost", 1433, "LIBRAIRIE");
        ResultSet rs;
        abd.ouvrirConnexionBase();
        abd.ouvrirLienRequete();
        rs = abd.executerRequete("SELECT count(NUMCOMMANDE) [nbCommande]\n"
                + "FROM COMMANDES\n"
                + "WHERE IDCLIENT = " + idClient);
        try {
            rs.next();
            nbCom = rs.getInt("nbCommande");
        } catch (SQLException ex) {
            System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());
        }
        abd.fermerLienRequete(rs);
        abd.fermerConnexionBase();
        return nbCom;
    }

    private static ArrayList<Integer> lesNumLivraisonDuClient(int idClient) {
        ArrayList<Integer> lesNumLiv = new ArrayList();
        AccesBD abd = new AccesBD("sa", "sa", "localhost", 1433, "LIBRAIRIE");
        ResultSet rs;
        abd.ouvrirConnexionBase();
        abd.ouvrirLienRequete();
        rs = abd.executerRequete("SELECT NUMLIVRAISON\n"
                + "FROM COMMANDES\n"
                + "WHERE IDCLIENT = " + idClient);
        try {
            while (rs.next()) {
                lesNumLiv.add(rs.getInt("NUMLIVRAISON"));
            }
        } catch (SQLException ex) {
            System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());
        }
        abd.fermerLienRequete(rs);
        abd.fermerConnexionBase();
        return lesNumLiv;
    }
    
    public Collection<Adresse> listeAdresseDuClient()
    {
        Collection<Adresse> lAdr = listeAdresseClient.values();
        return lAdr;
    }
    public boolean isEmpty() {
        return listeAdresseClient.isEmpty();
    }    
    
}
