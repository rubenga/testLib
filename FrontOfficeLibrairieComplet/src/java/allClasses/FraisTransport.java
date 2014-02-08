/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package allClasses;

/**
 *
 * @author cdi412
 */
public class FraisTransport {
    private int identifiant;
    private String libelle;
    private float tarif;

    public FraisTransport(int identifiant, String libelle, float tarif) {
        this.identifiant = identifiant;
        this.libelle = libelle;
        this.tarif = tarif;
    }

    public FraisTransport(int identifiant) {
        this.identifiant = identifiant;
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public float getTarif() {
        return tarif;
    }

    public void setTarif(float tarif) {
        this.tarif = tarif;
    }

    @Override
    public String toString() {
        return libelle + " - " + tarif;
    }
    
    
}
