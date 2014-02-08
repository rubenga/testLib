/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package allClasses;

import allClasses.*;
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
public class RequeteLivres {

    AccesBD abd = new AccesBD("sa", "sa", "localhost", 1433, "LIBRAIRIE");
    ResultSet rs;
    private Vector<LivreInfos> liv=new Vector();
    private Vector vi = new Vector();
    Req r = new Req();

    public Vector chargerCombo(int id) {

        liv.removeAll(liv);
        abd.ouvrirConnexionBase();
        String query = "SELECT ISBN,codetheme,codesousTheme,IDediteur,IDauteur,IDtva,titrelivre,soustitre,"
                + "languelivre,nbpagelivre,dateeditionlivre,resumelivre,prixlivre,"
                + "poidslivre FROM LIVRES where IDAUTEUR ='" + id + "';";
        System.out.println("query = " + query);
        abd.ouvrirLienRequete();
        rs = abd.executerRequete(query);
        try {
            while (rs.next()) {
                /*liv.add(new LivreInfos(
                        rs.getString("ISBN"),
                        (ThemePrincipal) (rs.getObject("codetheme")),
                        (SousTheme) (rs.getObject("codesousTheme")),
                        (Editeur) (rs.getObject("IDediteur")),
                        (Auteur) (rs.getObject("IDauteur")),
                        (Tva) (rs.getObject("IDtva")),
                        rs.getString("titrelivre"),
                        rs.getString("soustitre"),
                        rs.getString("languelivre"),
                        Integer.valueOf(rs.getString("nbpagelivre")),
                        rs.getDate("dateeditionlivre"),                     
                        rs.getString("resumelivre"),
                        Float.valueOf(rs.getString("prixlivre")),
                        Float.valueOf(rs.getString("poidslivre"))));*/
            }
        } catch (SQLException ex) {
            System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());
            return null;
        }
        abd.fermerLienRequete(rs);
        abd.fermerConnexionBase();
        System.out.println("Done!");
        return liv;
    }

    public Req reqCharger(int id) {


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Oops:Driver:" + ex.getMessage());
            return null;
        }
        Connection connexion = null;
        try {
            connexion = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;"
                    + "databaseName=LIBRAIRIE;user=sa;password=sa");
        } catch (SQLException ex) {
            System.err.println("Oops:Connection:" + ex.getErrorCode() + ":" + ex.getMessage());
            return null;
        }

        // SELECT ISBN,codetheme,codesousTheme,IDauteur,IDtva,titrelivre,soustitre,languelivre,"
        //       + "nbpagelivre,dateeditionlivre,imagelivre,resumelivre,prixlivre,poidslivre

        String query = "SELECT titrelivre, idauteur FROM Livres where IDAUTEUR=" + id + ";";
        System.out.println("query = " + query);
        try {
            Statement stmt = connexion.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                r.setIdauteur(Integer.valueOf(rs.getString("IDauteur")));
                r.setTitre(rs.getString("titrelivre"));

                // vi.add(rs.getObject("IDauteur").toString());
                //System.out.println("id dans vect req = " + rs.getObject("IDauteur").toString());
                //vi.add(rs.getString("titrelivre").toString());
                //System.out.println("titre dans vect req = " + rs.getString("titrelivre").toString());

                //rs.getString("ISBN"),
                //(ThemePrincipal)(rs.getObject("codetheme")),
                //(SousTheme)(rs.getObject("codesousTheme")),
                // rs.getObject("IDauteur").toString(),
                //(Tva)(rs.getObject("IDtva")),
                //  rs.getString("titrelivre").toString()
                // rs.getString("soustitre"),
                //rs.getString("langue"),
                //Integer.valueOf(rs.getString("nbpageslivre")),
                //rs.getDate("dateeditionlivre"),
                //rs.getString("imagelivre"),
                //rs.getString("resumelivre"),
                //Float.valueOf(rs.getString("prixlivre")),
                //Float.valueOf(rs.getString("poidslivre"))
                //  );
            }

            stmt.close();
        } catch (SQLException ex) {
            System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());
            return null;
        }

        try {
            connexion.close();
        } catch (SQLException ex) {
            System.err.println("Oops:Close:" + ex.getErrorCode() + ":" + ex.getMessage());
            return null;
        }

        System.out.println("Done!");

        return r;
    }
}
