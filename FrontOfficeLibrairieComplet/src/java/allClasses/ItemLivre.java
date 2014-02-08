package allClasses;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemLivre {

    Livre livre;
    int qte;
    float prixTtc; /* Prix TTC avec réduction */


    public ItemLivre(Livre livre, int qte) {
        this.livre = livre;
        this.qte = qte;
        this.prixTtc = prixTtcReduc(livre);
    }

    public ItemLivre(Livre livre) {
        this.livre = livre;
        this.qte = 0;
        this.prixTtc = prixTtcReduc(livre);
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    /**
     * Ajouter supprimer un élément
     *
     * @param d : +1 ou -1
     */
    public void delta(int d) {
        qte += d;
    }

    public float getPrixTtc() {
        return prixTtc;
    }

    public static float prixTtcReduc(Livre liv) {
        float prix = 0;

        AccesBD abd = new AccesBD("sa", "sa", "localhost", 1433, "LIBRAIRIE");
        ResultSet rs;
        abd.ouvrirConnexionBase();

        try {
            CallableStatement cstmt = abd.getCon().prepareCall("{call TauxPromotionLivre(?,?)}");
            cstmt.setString("ISBN", liv.getIsbn());
            cstmt.registerOutParameter("txPromo", java.sql.Types.FLOAT);
            cstmt.execute();
            prix = liv.getPrixLivre() * (1 - (cstmt.getFloat("txPromo") / 100)) * (1 + (liv.getTvaLivre().getTxTva() / 100));
            
            cstmt.close();
        } catch (SQLException ex) {
            System.err.println("Procédure stockée : " + ex.getMessage());
            prix = liv.getPrixLivre()* (1 + (liv.getTvaLivre().getTxTva() / 100));
        }
        abd.fermerConnexionBase();
        
        return prix;
    }
}
