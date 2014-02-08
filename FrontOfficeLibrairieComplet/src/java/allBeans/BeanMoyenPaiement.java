/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package allBeans;

import allClasses.AccesBD;
import allClasses.ListeModePaiement;
import allClasses.ModePaiement;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author cdi412
 */
public class BeanMoyenPaiement implements Serializable {

    ArrayList<ModePaiement> listeMoyenPaiement;

    public BeanMoyenPaiement() {
        this.listeMoyenPaiement = new ArrayList();
        AccesBD abd = new AccesBD("sa", "sa", "localhost", 1433, "LIBRAIRIE");
        abd.ouvrirConnexionBase();
        listeMoyenPaiement.addAll(ListeModePaiement.remplirVectorModePaiement(abd));
        abd.fermerConnexionBase();
    }

    public ArrayList<ModePaiement> getListeMoyenPaiement() {
        return listeMoyenPaiement;
    }

    public void setListeMoyenPaiement(ArrayList<ModePaiement> listeMoyenPaiement) {
        this.listeMoyenPaiement = listeMoyenPaiement;
    }
}
