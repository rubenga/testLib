/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package allClasses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author cdi412
 */
public class ListeFraisTransport {

    static Vector<FraisTransport> vFraisTransport;

    public static Vector<FraisTransport> remplirVectorFraisTransport(AccesBD abd) {
        vFraisTransport = new Vector();
        ResultSet rs;
        abd.ouvrirLienRequete();
        rs = abd.executerRequete("SELECT * FROM FRAIS_TRANSPORT ORDER BY LIBELLEMODELIVRAISON");
        try {
            while (rs.next()) {
                vFraisTransport.add(new FraisTransport(rs.getInt("IDMODELIVRAISON"), rs.getString("LIBELLEMODELIVRAISON"), rs.getFloat("TARIFMODELIVRAISON")));
            }
        } catch (SQLException ex) {
            System.err.println("Problème de lecture du resultat : " + ex.getMessage());
        }
        abd.fermerLienRequete(rs);
        return vFraisTransport;
    }
}
