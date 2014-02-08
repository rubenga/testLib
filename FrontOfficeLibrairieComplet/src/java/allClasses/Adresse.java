/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package allClasses;

/**
 *
 * @author cdi412
 */
public class Adresse {
    private int identifiant;
    private String nom;
    private String rue;
    private String codePostal;
    private String ville;
    private String pays;


    public Adresse() {
    }

    public Adresse(int identifiant, String nom, String rue, String codePostal, String ville, String pays) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
    }


    public Adresse(int identifiant, String rue, String codePostal, String ville, String pays) {
        this.identifiant = identifiant;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
    }


    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @Override
    public String toString() {
        return nom + ", " + rue + ", " + codePostal + ", " + pays;
    }
    
    
}
