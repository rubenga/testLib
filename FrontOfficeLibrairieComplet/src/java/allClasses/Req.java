/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package allClasses;

/**
 *
 * @author cdi414
 */
public class Req {
    private int idauteur;
    private String titre;

    public Req() {
    }

    public Req(int idauteur, String titre) {
        this.idauteur = idauteur;
        this.titre = titre;
    }

    public int getIdauteur() {
        return idauteur;
    }

    public void setIdauteur(int idauteur) {
        this.idauteur = idauteur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    
}
