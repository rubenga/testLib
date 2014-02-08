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
public class ListeStatut {
    static Vector<Statut> vStatut;

    public static Vector<Statut> remplirVectorStatut(AccesBD abd) 
    {
        vStatut = new Vector();
        ResultSet rs;
        abd.ouvrirLienRequete();
        rs = abd.executerRequete("SELECT * FROM STATUTS ORDER BY LIBELLESTATUT");
        try {
            while (rs.next())
            {
                vStatut.add(new Statut(rs.getString("CODESTATUT"), rs.getString("LIBELLESTATUT")));
            }
        } catch (SQLException ex) {
            System.err.println("Problème de lecture du resultat : "+ex.getMessage());
        }
        abd.fermerLienRequete(rs);
        return vStatut;
    }
}
