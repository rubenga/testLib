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
public class ListeModePaiement {
    static Vector<ModePaiement> vModePaiement;

    public static Vector<ModePaiement> remplirVectorModePaiement(AccesBD abd) 
    {
        vModePaiement = new Vector();
        ResultSet rs;
        abd.ouvrirLienRequete();
        rs = abd.executerRequete("SELECT * FROM MODE_PAIEMENT ORDER BY LIBELLEMOYENPAIEMENT");
        try {
            while (rs.next())
            {
                vModePaiement.add(new ModePaiement(rs.getString("CODEMOYENPAIEMENT"), rs.getString("LIBELLEMOYENPAIEMENT")));
            }
        } catch (SQLException ex) {
            System.err.println("Problème de lecture du resultat : "+ex.getMessage());
        }
        abd.fermerLienRequete(rs);
        return vModePaiement;
    }
}
