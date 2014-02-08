/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package allBeans;

import allClasses.AccesBD;
import allClasses.FraisTransport;
import allClasses.ListeFraisTransport;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author cdi412
 */
public class BeanLivraison implements Serializable {

    ArrayList<FraisTransport> listeFp;

    public BeanLivraison() {
        this.listeFp = new ArrayList();
        AccesBD abd = new AccesBD("sa", "sa", "localhost", 1433, "LIBRAIRIE");
        abd.ouvrirConnexionBase();
        listeFp.addAll(ListeFraisTransport.remplirVectorFraisTransport(abd));
        abd.fermerConnexionBase();
    }

    public ArrayList<FraisTransport> getListeFp() {
        return listeFp;
    }

    public void setListeFp(ArrayList<FraisTransport> listeFp) {
        this.listeFp = listeFp;
    }

    @Override
    public String toString() {
       String col = "";
        for (FraisTransport ft : listeFp) {
            col += ft.getLibelle()+" - "+ft.getTarif()+" - "+ft.getIdentifiant()+"\n";
        }
        return col;
    }
    
}
