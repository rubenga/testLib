/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package allClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author cdi414
 */
public class RequeteEditeur {
    
    AccesBD abd = new AccesBD("sa", "sa", "localhost", 1433, "LIBRAIRIE");
    ResultSet rs;
    Vector<Editeur> v = new Vector();
    
    public Vector chargerListeEditeur(){
     
        v.removeAll(v);
        abd.ouvrirConnexionBase();
        String query = "SELECT IDEDITEUR,NOMEDITEUR FROM EDITEURS;";
        abd.ouvrirLienRequete();
        rs = abd.executerRequete(query);
        try {
            while (rs.next()) {
                v.add(new Editeur(
                        rs.getString("NOMEDITEUR"),
                        Integer.valueOf(rs.getString("IDEDITEUR"))));                  
            }
        } catch (SQLException ex) {
            System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());
            return null;
        }
        abd.fermerLienRequete(rs);
        abd.fermerConnexionBase();
        System.out.println("Done!");
        return v;
    }

    public void insertEditeur(String quer) {

        System.out.println("query = " + quer);
        abd.ouvrirConnexionBase();
        String query = "INSERT INTO EDITEURS VALUES ("+ quer +")";
        abd.ouvrirLienRequete();
        abd.executerRequeteUpdate(query);
        abd.fermerLienRequete();
        abd.fermerConnexionBase();
        System.out.println("Done!");
    }
    
    public void supprimerEditeur(int re) {

        System.out.println("query = " + re);
        abd.ouvrirConnexionBase();
        String query = "DELETE FROM EDITEURS WHERE IDEDITEUR ='"+ re+"'";
        abd.ouvrirLienRequete();
        abd.executerRequeteUpdate(query);
        abd.fermerLienRequete();
        abd.fermerConnexionBase();
        System.out.println("Done!");
    }
    
    public Vector chargerListe() {

        v.removeAll(v);
        
        abd.ouvrirConnexionBase();
        
        String query = "SELECT IDEDITEUR,NOMEDITEUR FROM EDITEURS;";
        abd.ouvrirLienRequete();
        rs = abd.executerRequete(query);
        try {
            while (rs.next()) {
                v.add(new Editeur(                      
                        rs.getString("nomediteur"),
                        Integer.valueOf(rs.getString("idediteur"))));
            }
        } catch (SQLException ex) {
            System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());
            return null;
        }
        abd.fermerLienRequete(rs);
        abd.fermerConnexionBase();
        System.out.println("Done!");
        return v;
    }
    
}
