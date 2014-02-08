package allClasses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import allClasses.*;

public class RequeteListeAuteur {

    AccesBD abd = new AccesBD("sa", "sa", "localhost", 1433, "LIBRAIRIE");
    ResultSet rs;
    Vector<Auteur> v = new Vector();
    Vector vId = new Vector();

    public Vector chargerListe() {

        v.removeAll(v);
        
        abd.ouvrirConnexionBase();
        
        String query = "SELECT IDAUTEUR,NOMAUTEUR,PRENOMAUTEUR FROM Auteurs;";
        abd.ouvrirLienRequete();
        rs = abd.executerRequete(query);
        try {
            while (rs.next()) {
                v.add(new Auteur(
                        rs.getString("prenomauteur"),
                        rs.getString("nomauteur"),
                        Integer.valueOf(rs.getString("idauteur"))));
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

    public Vector getIDAuteur() {

        vId.removeAll(vId);
        
        abd.ouvrirConnexionBase();
        
        String query = "SELECT IDAUTEUR FROM Auteurs;";
        abd.ouvrirLienRequete();
        rs = abd.executerRequete(query);
        try {
            while (rs.next()) {
                vId.add(rs.getString("IDAUTEUR"));
            }
        } catch (SQLException ex) {
            System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());
            return null;
        }
        abd.fermerLienRequete(rs);
        abd.fermerConnexionBase();
        System.out.println("Done!");
        return vId;
    }

    public void insertAuteur(String quer) {

        System.out.println("query = " + quer);
        
        abd.ouvrirConnexionBase();

        String query = "INSERT INTO AUTEURS VALUES (" + quer + ");";
        
        abd.ouvrirLienRequete();
        abd.executerRequeteUpdate(query);

        abd.fermerLienRequete();
        abd.fermerConnexionBase();
        System.out.println("Done!");
    }

    public void supprimerAuteur(String quer) {

        System.out.println("query = " + quer);
        
        abd.ouvrirConnexionBase();
        String query = "DELETE FROM AUTEURS WHERE IDAUTEUR=" + quer + ";";
        abd.ouvrirLienRequete();
        abd.executerRequeteUpdate(query);

        abd.fermerLienRequete();
        abd.fermerConnexionBase();
        System.out.println("Done!");
    }

    public Vector chercherPrenom(String s) {

        vId.removeAll(vId);
        abd.ouvrirConnexionBase();
        String query = "SELECT PRENOMAUTEUR FROM Auteurs where NOMAUTEUR=" + "'" + s + "'" + ";";
        abd.ouvrirLienRequete();
        rs = abd.executerRequete(query);
        try {
            while (rs.next()) {
               vId.add(rs.getString("PRENOMAUTEUR"));
            }
        } catch (SQLException ex) {
            System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());
            return null;
        }
        abd.fermerLienRequete(rs);
        abd.fermerConnexionBase();
        System.out.println("Done!");
        return vId;
    }
}
