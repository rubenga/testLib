/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package allClasses;

/**
 *
 * @author cdi412
 */
public class Tva {
    private int idTva;
    private String nomTva;
    private float txTva;

    public Tva(int idTva, String nomTva, float txTva) {
        this.idTva = idTva;
        this.nomTva = nomTva;
        this.txTva = txTva;
    }

    public Tva() {
    }

    public int getIdTva() {
        return idTva;
    }

    public void setIdTva(int idTva) {
        this.idTva = idTva;
    }

    public String getNomTva() {
        return nomTva;
    }

    public void setNomTva(String nomTva) {
        this.nomTva = nomTva;
    }

    public float getTxTva() {
        return txTva;
    }

    public void setTxTva(float txTva) {
        this.txTva = txTva;
    }   

    @Override
    public String toString() {
        return nomTva + " - "+txTva+"%";
    }
    
}
