/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package allBeans;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import allClasses.Livre;
import allClasses.ItemLivre;

/**
 *
 * @author cdi412
 */
public class BeanPanier implements Serializable {

    /* Liste des livre du panier */
    HashMap<String, ItemLivre> hashmapLivre;
    /**
     * Creer la liste du pannier
     */
    public BeanPanier() {
        hashmapLivre = new HashMap();
    }

    /**
     * Ajouter un livre +1 dans le panier
     *
     * @param liv : Le livre à ajouter
     */
    /*Livre en paramètre pour pouvoir l'ajouter dans 
     l'hashmapLivre si c'est la première fois */
    public void ajouter(Livre liv) {
        delta(liv, +1);
    }

    /**
     * Enlever un livre -1 dans le panier
     *
     * @param liv : Le livre à enlever
     */
    public void decrementer(Livre liv) {
        delta(liv, -1);
    }

    /**
     * Supprimer un livre du panier - Supprimer le livre et ses quantités des
     * hashmaps et donc du panier
     *
     * @param isbn : l'isbn du livre à supprimer du panier
     */
    public void supprimer(String isbn) {
        /* Supression du titre dans le panier */
        if (hashmapLivre.containsKey(isbn)) {
            hashmapLivre.remove(isbn);
        }
    }

    /**
     * Sert à ajouter ou enlever une quantité d'un livre
     *
     * @param liv : le livre à modifier
     * @param qte : +1 ajoute / -1 enlève
     */
    public void delta(Livre liv, int qte) {
        if (hashmapLivre.containsKey(liv.getIsbn())) {
            hashmapLivre.get(liv.getIsbn()).delta(qte);
            if (hashmapLivre.get(liv.getIsbn()).getQte() < 1) {
                supprimer(liv.getIsbn());
            }
        } else {
            hashmapLivre.put(liv.getIsbn(), new ItemLivre(liv, qte));
        }
    }

    public void modifierQte(String isbn, int qte) {
        if (qte > 0) {
            if (hashmapLivre.containsKey(isbn)) {
                ItemLivre iL = hashmapLivre.get(isbn);
                iL.setQte(qte);
            }
        }
    }

    public float total() {
        float total = 0;
        return total;
    }

    public boolean isEmpty() {
        return hashmapLivre.isEmpty();
    }

    public int nbLivres() {
        int nbLivres = hashmapLivre.size();
        return nbLivres;
    }

    public Collection<ItemLivre> listeLivre() {
        Collection<ItemLivre> c = hashmapLivre.values();
        return c;
    }

    public void vider() {
        hashmapLivre.clear();
    }

    public float montantTotal()
    {
        float montant = 0;
        
        for (ItemLivre l : listeLivre()) {
            montant += l.getPrixTtc()*l.getQte();
        }
        
        return montant;
    }
    
    @Override
    public String toString() {
        String col = "";
        for (ItemLivre il : listeLivre()) {
            col += il.getLivre().getIsbn()+" - "+il.getLivre()+" - "+il.getPrixTtc()+" - "+il.getLivre().getPrixLivre()+"\n";
        }
        return col;
    }
    
    
}
