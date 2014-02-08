/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package allClasses;

import java.util.Date;
import java.util.Vector;

/**
 *
 * @author cdi412
 */
public class Commande {
    private int numCommande;
    private ModePaiement moyenPaiement;
    private Client clt;
    private FraisTransport modeLivraison;
    private Statut statutCommande;
    private Livraison liv;
    private Date dateCommande;
    private String commentaire;
    private Vector<LigneCommande> lc;

    public Commande(int numCommande, ModePaiement moyenPaiement, Client clt, FraisTransport modeLivraison, Statut statutCommande, Livraison liv, Date dateCommande, String commentaire, Vector<LigneCommande> lc) {
        this.numCommande = numCommande;
        this.moyenPaiement = moyenPaiement;
        this.clt = clt;
        this.modeLivraison = modeLivraison;
        this.statutCommande = statutCommande;
        this.liv = liv;
        this.dateCommande = dateCommande;
        this.commentaire = commentaire;
        this.lc = lc;
    }

    public Commande(int numCommande, ModePaiement moyenPaiement, Client clt, FraisTransport modeLivraison, Statut statutCommande, Date dateCommande, String commentaire, Vector<LigneCommande> lc) {
        this.numCommande = numCommande;
        this.moyenPaiement = moyenPaiement;
        this.clt = clt;
        this.modeLivraison = modeLivraison;
        this.statutCommande = statutCommande;
        this.dateCommande = dateCommande;
        this.commentaire = commentaire;
        this.lc = lc;
    }

    public Commande() {
    }

    public int getNumCommande() {
        return numCommande;
    }

    public void setNumCommande(int numCommande) {
        this.numCommande = numCommande;
    }

    public ModePaiement getMoyenPaiement() {
        return moyenPaiement;
    }

    public void setMoyenPaiement(ModePaiement moyenPaiement) {
        this.moyenPaiement = moyenPaiement;
    }

    public Client getClt() {
        return clt;
    }

    public void setClt(Client clt) {
        this.clt = clt;
    }

    public FraisTransport getModeLivraison() {
        return modeLivraison;
    }

    public void setModeLivraison(FraisTransport modeLivraison) {
        this.modeLivraison = modeLivraison;
    }

    public Statut getStatutCommande() {
        return statutCommande;
    }

    public void setStatutCommande(Statut statutCommande) {
        this.statutCommande = statutCommande;
    }

    public Livraison getLiv() {
        return liv;
    }

    public void setLiv(Livraison liv) {
        this.liv = liv;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Vector<LigneCommande> getLc() {
        return lc;
    }

    public void setLc(Vector<LigneCommande> lc) {
        this.lc = lc;
    }

    @Override
    public String toString() {
        return numCommande + ", " + clt + ", " + dateCommande;
    }
    
    
}
