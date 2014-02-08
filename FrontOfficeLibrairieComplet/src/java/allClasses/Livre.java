/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package allClasses;

import allClasses.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
/**
 *
 * @author cdi404
 */
public class Livre 
{
private String isbn;
private ThemePrincipal  themeLivre;
private SousTheme sousThemeLivre;
private Editeur editeurLivre;
private Auteur auteurLivre;    
private Tva tvaLivre;
private String titreLivre;
private String sousTitre;
private String LangueLivre;
private int nombrePageLivre;
private Date dateEditionLIvre;
private String imageLivre;
private String resumeLivre;
private float prixLivre;
private float poidsLivre;

    //private ListeMotcles listeMotsCles;
    //private ListeMotcles listeMotsCles;
    public Livre() 
    {
    }

    

   public Livre(String titreLivre) {
        this.titreLivre = titreLivre;
    }

    

    //public Livre(String isbn, ThemePrincipal themeLivre, SousTheme sousThemeLivre, Editeur editeurLivre, Auteur auteurLivre, Tva tvaLivre, String titreLivre, String sousTitre, String LangueLivre, int nombrePageLivre, Date dateEditionLIvre, String imageLivre, String resumeLivre, float prixLivre, Evennement evennementLivre, ListeMotcles listeMotsCles) 

    public Livre(String isbn, ThemePrincipal themeLivre, SousTheme sousThemeLivre, Editeur editeurLivre, Auteur auteurLivre, Tva tvaLivre, String titreLivre, String sousTitre, String LangueLivre, int nombrePageLivre, Date dateEditionLIvre, String imageLivre, String resumeLivre, float prixLivre, float poidsLivre) 
    {
        this.isbn = isbn;
        this.themeLivre = themeLivre;
        this.sousThemeLivre = sousThemeLivre;
        this.editeurLivre = editeurLivre;
        this.auteurLivre = auteurLivre;
        this.tvaLivre = tvaLivre;
        this.titreLivre = titreLivre;
        this.sousTitre = sousTitre;
        this.LangueLivre = LangueLivre;
        this.nombrePageLivre = nombrePageLivre;
        this.dateEditionLIvre = dateEditionLIvre;
        this.imageLivre = imageLivre;
        this.resumeLivre = resumeLivre;
        this.prixLivre = prixLivre;
        this.poidsLivre = poidsLivre;
    }

   /* public Livre(String isbn, ThemePrincipal themeLivre, SousTheme sousThemeLivre, Auteur auteurLivre, Tva tvaLivre, String titreLivre, String sousTitre, String LangueLivre, int nombrePageLivre, Date dateEditionLIvre, String imageLivre, String resumeLivre, float prixLivre, float poidsLivre) 
    {
        this.isbn = isbn;
        this.themeLivre = themeLivre;
        this.sousThemeLivre = sousThemeLivre;
        this.auteurLivre = auteurLivre;
        this.tvaLivre = tvaLivre;
        this.titreLivre = titreLivre;
        this.sousTitre = sousTitre;
        this.LangueLivre = LangueLivre;
        this.nombrePageLivre = nombrePageLivre;
        this.dateEditionLIvre = dateEditionLIvre;
        this.imageLivre = imageLivre;
        this.resumeLivre = resumeLivre;
        this.prixLivre = prixLivre;
        this.poidsLivre = poidsLivre;
    }*/

    public String getIsbn() {
        return isbn;
    }

    public ThemePrincipal getThemeLivre() {
        return themeLivre;
    }

    public SousTheme getSousThemeLivre() {
        return sousThemeLivre;
    }

    public Auteur getAuteurLivre() {
        return auteurLivre;
    }

    public Tva getTvaLivre() {
        return tvaLivre;
    }

    public String getTitreLivre() {
        return titreLivre;
    }

    public String getSousTitre() {
        return sousTitre;
    }

    public String getLangueLivre() {
        return LangueLivre;
    }

    public int getNombrePageLivre() {
        return nombrePageLivre;
    }

    public Date getDateEditionLIvre() {
        return dateEditionLIvre;
    }

    public String getImageLivre() {
        return imageLivre;
    }

    public String getResumeLivre() {
        return resumeLivre;
    }

    public float getPrixLivre() {
        return prixLivre;
    }

    public float getPoidsLivre() {
        return poidsLivre;
    }

    public Editeur getEditeurLivre() {
        return editeurLivre;
    }
    
    
    @Override
    public String toString() 
    {
        //return "Livre{" + "isbn=" + isbn + ", themeLivre=" + themeLivre + ", sousThemeLivre=" + sousThemeLivre + ", editeurLivre=" + "ajouter editeur" + ", auteurLivre=" + auteurLivre + ", tvaLivre=" + tvaLivre + ", titreLivre=" + titreLivre + ", sousTitre=" + sousTitre + ", LangueLivre=" + LangueLivre + ", LANGUES=" + LANGUES + ", nombrePageLivre=" + nombrePageLivre + ", dateEditionLIvre=" + dateEditionLIvre + ", imageLivre=" + imageLivre + ", resumeLivre=" + resumeLivre + ", prixLivre=" + prixLivre + ", poidsLivre=" + poidsLivre + ", evennementLivre=" + "evennementLivre" + ", listeMotsCles=" + "listeMotsCles" + '}';
        return titreLivre;
    }

}
