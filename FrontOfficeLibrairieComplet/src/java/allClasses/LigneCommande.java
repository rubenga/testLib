/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package allClasses;

import allClasses.Livre;

/**
 *
 * @author cdi412
 */
public class LigneCommande {
    private Livre liv;
    private float prixHt;
    private int quantite;
    private float tvaCommande;

    public LigneCommande(Livre liv, float prixHt, int quantite, float tvaCommande) {
        this.liv = liv;
        this.prixHt = prixHt;
        this.quantite = quantite;
        this.tvaCommande = tvaCommande;
    }

    public LigneCommande() {
    }

    public Livre getLiv() {
        return liv;
    }

    public void setLiv(Livre liv) {
        this.liv = liv;
    }

    public float getPrixHt() {
        return prixHt;
    }

    public void setPrixHt(float prixHt) {
        this.prixHt = prixHt;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getTvaCommande() {
        return tvaCommande;
    }

    public void setTvaCommande(float tvaCommande) {
        this.tvaCommande = tvaCommande;
    }
    
}
