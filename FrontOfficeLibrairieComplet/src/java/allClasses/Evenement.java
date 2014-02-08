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
public class Evenement 
{
    private int idEvenement;
    private String nomEvenement;
    private float tauxReduction;
    private Date dateDebut;
    private Date dateFin;
    
    public Evenement(){
    }

    public Evenement(int idEvenement, String nomEvenement, float tauxReduction, Date dateDebut, Date dateFin){
        this.idEvenement = idEvenement;
        this.nomEvenement = nomEvenement;
        this.tauxReduction = tauxReduction;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return nomEvenement;
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    public String getNomEvenement() {
        return nomEvenement;
    }

    public void setNomEvenement(String nomEvenement) {
        this.nomEvenement = nomEvenement;
    }

    public float getTauxReduction() {
        return tauxReduction;
    }

    public void setTauxReduction(float tauxReduction) {
        this.tauxReduction = tauxReduction;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
}
