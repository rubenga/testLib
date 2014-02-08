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
public class ListeTva {
    
    static Vector<Tva> vTva = new Vector();

    public static Vector<Tva> remplirVectorTva(AccesBD abd) 
    {
        ResultSet rs;
        abd.ouvrirLienRequete();
        rs = abd.executerRequete("SELECT * FROM TVA ORDER BY LIBELLETVA");
        try {
            while (rs.next())
            {
                vTva.add(new Tva(rs.getInt("IDTVA"), rs.getString("LIBELLETVA"), rs.getFloat("TAUXTVA")));
            }
        } catch (SQLException ex) {
            System.err.println("Problème de lecture du resultat : "+ex.getMessage());
        }
        abd.fermerLienRequete(rs);
        return vTva;
    }
}
