/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package allClasses;

import java.util.Date;

/**
 *
 * @author cdi412
 */
public class Livraison {
    private int numLivraison;
    private Adresse adresseLivraison;
    private Adresse adresseFacture;
    private int numFacture;
    private Date dateFacture;
    private Date dateLivraison;

    public Livraison(int numLivraison, Adresse adresseLivraison, Adresse adresseFacture, int numFacture, Date dateFacture, Date dateLivraison) {
        this.numLivraison = numLivraison;
        this.adresseLivraison = adresseLivraison;
        this.adresseFacture = adresseFacture;
        this.numFacture = numFacture;
        this.dateFacture = dateFacture;
        this.dateLivraison = dateLivraison;
    }

    public Livraison() {
    }

    public int getNumLivraison() {
        return numLivraison;
    }

    public void setNumLivraison(int numLivraison) {
        this.numLivraison = numLivraison;
    }

    public Adresse getAdresseLivraison() {
        return adresseLivraison;
    }

    public void setAdresseLivraison(Adresse adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

    public Adresse getAdresseFacture() {
        return adresseFacture;
    }

    public void setAdresseFacture(Adresse adresseFacture) {
        this.adresseFacture = adresseFacture;
    }

    public int getNumFacture() {
        return numFacture;
    }

    public void setNumFacture(int numFacture) {
        this.numFacture = numFacture;
    }

    public Date getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(Date dateFacture) {
        this.dateFacture = dateFacture;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }
    
}
