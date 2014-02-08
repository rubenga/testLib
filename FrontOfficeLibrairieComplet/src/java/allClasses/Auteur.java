
package allClasses;



public class Auteur extends Personne{
    
    private String prenom;

    public Auteur() {
    }

    public Auteur(String prenom,String nom,int id) {
       super(nom,id);
        this.prenom = prenom;       
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return super.toString()+" "+prenom;
    }
    
}

