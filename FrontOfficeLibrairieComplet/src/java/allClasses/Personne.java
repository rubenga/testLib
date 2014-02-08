
package allClasses;


public class Personne {
    
    private String nom;
    private int identifiant;

    public Personne() {
    }

    public Personne(String nom, int identifiant) {
        this.nom = nom;
        this.identifiant = identifiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    

    @Override
    public String toString() {
        return nom;
    }
    
    
    
}
