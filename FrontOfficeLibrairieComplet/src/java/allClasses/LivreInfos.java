/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package allClasses;

import allClasses.*;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author cdi414
 */
public class LivreInfos {

    private String isbn;
    private ThemePrincipal themeLivre;
    private SousTheme sousThemeLivre;
    private Editeur editeurLivre;
    private Auteur auteurLivre;
    private Tva tvaLivre;
    private String titreLivre;
    private String sousTitre;
    private String LangueLivre;
    private int nombrePageLivre;
    private Date dateEditionLivre;
    private String resumeLivre;
    private float prixLivre;
    private float poidsLivre;

    public LivreInfos() {
    }

    public LivreInfos(String isbn, ThemePrincipal themeLivre, SousTheme sousThemeLivre, Editeur editeurLivre, Auteur auteurLivre, Tva tvaLivre, String titreLivre, String sousTitre, String LangueLivre, int nombrePageLivre, Date dateEditionLivre, String resumeLivre, float prixLivre, float poidsLivre) {
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
        this.dateEditionLivre = dateEditionLivre;
        this.resumeLivre = resumeLivre;
        this.prixLivre = prixLivre;
        this.poidsLivre = poidsLivre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public ThemePrincipal getThemeLivre() {
        return themeLivre;
    }

    public void setThemeLivre(ThemePrincipal themeLivre) {
        this.themeLivre = themeLivre;
    }

    public SousTheme getSousThemeLivre() {
        return sousThemeLivre;
    }

    public void setSousThemeLivre(SousTheme sousThemeLivre) {
        this.sousThemeLivre = sousThemeLivre;
    }

    public Editeur getEditeurLivre() {
        return editeurLivre;
    }

    public void setEditeurLivre(Editeur editeurLivre) {
        this.editeurLivre = editeurLivre;
    }

    public Auteur getAuteurLivre() {
        return auteurLivre;
    }

    public void setAuteurLivre(Auteur auteurLivre) {
        this.auteurLivre = auteurLivre;
    }

    public Tva getTvaLivre() {
        return tvaLivre;
    }

    public void setTvaLivre(Tva tvaLivre) {
        this.tvaLivre = tvaLivre;
    }

    public String getTitreLivre() {
        return titreLivre;
    }

    public void setTitreLivre(String titreLivre) {
        this.titreLivre = titreLivre;
    }

    public String getSousTitre() {
        return sousTitre;
    }

    public void setSousTitre(String sousTitre) {
        this.sousTitre = sousTitre;
    }

    public String getLangueLivre() {
        return LangueLivre;
    }

    public void setLangueLivre(String LangueLivre) {
        this.LangueLivre = LangueLivre;
    }

    public int getNombrePageLivre() {
        return nombrePageLivre;
    }

    public void setNombrePageLivre(int nombrePageLivre) {
        this.nombrePageLivre = nombrePageLivre;
    }

    public Date getDateEditionLivre() {
        return dateEditionLivre;
    }

    public void setDateEditionLivre(Date dateEditionLivre) {
        this.dateEditionLivre = dateEditionLivre;
    }

    public String getResumeLivre() {
        return resumeLivre;
    }

    public void setResumeLivre(String resumeLivre) {
        this.resumeLivre = resumeLivre;
    }

    public float getPrixLivre() {
        return prixLivre;
    }

    public void setPrixLivre(float prixLivre) {
        this.prixLivre = prixLivre;
    }

    public float getPoidsLivre() {
        return poidsLivre;
    }

    public void setPoidsLivre(float poidsLivre) {
        this.poidsLivre = poidsLivre;
    }

}
