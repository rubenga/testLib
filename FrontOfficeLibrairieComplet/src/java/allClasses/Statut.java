/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package allClasses;

/**
 *
 * @author cdi412
 */
public class Statut {
    private String code;
    private String libelle;

    public Statut() {
    }

    public Statut(String code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return libelle;
    }
}
