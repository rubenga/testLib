/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package allBeans;

import java.beans.*;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import allClasses.*;

public class BeanLogin implements Serializable {

    private final AccesBD abd = new AccesBD("sa", "sa", "localhost", 1433, "LIBRAIRIE");
    private String nom = null;// cet attribut est pour trouver le nom correspondant à l'email.........
    private String preNom = null;// cet attribut est pour trouver le prenom correspondant à l'email. POUR affiche le compte........
    private String tel = null;// cet attribut est pour trouver la téléphone correspondant à l'email. POUR affiche le compte........
    private String email = null;// cet attribut est pour trouver le courriel nom correspondant à l'email. POUR affiche le compte........
    private String raisonSociale = null;// cet attribut est pour trouver le raisonsoc. correspondant à l'email. POUR affiche le compte........    
    private int idClient = 0;

    public BeanLogin() {
    }

    public boolean validate(String Utilisateur, String MotdePasse) {
        Boolean res = false;
        if (Utilisateur.trim() == null || Utilisateur.isEmpty()) {
            return false;
        }
        if (MotdePasse.trim() == null || MotdePasse.isEmpty()) {
            return false;
        }
        ResultSet rs;
        abd.ouvrirConnexionBase();
        abd.ouvrirLienRequete();
        String query = "";
        query = abd.getQueryEmail() + abd.getSingleQuote() + Utilisateur + abd.getSingleQuote() + abd.getWhereMotDePasse() + abd.getSingleQuote() + MotdePasse + abd.getSingleQuote();

        rs = abd.executerRequete(query);
        try {
            if (rs.next()) {
                res = true;
                renseigneNom(rs.getString("NOMCLIENT"));
                renseignePreNom(rs.getString("PRENOMCLIENT"));
                renseigneTel(rs.getString("TELCLIENT"));
                renseigneEml(rs.getString("EMAILCLIENT"));
                renseigneRaisonSoc(rs.getString("RAISONSOCIALE"));
                renseigneIdClient(rs.getInt("IDCLIENT"));           
            }

        } catch (SQLException ex) {
            System.err.println("Problème de lecture du resultat : " + ex.getMessage());
        }
        abd.fermerConnexionBase();
        return res;
    }

    /* une surcharge -- cette méthode utilisé pour vériifier si l'utilisateur existe avant d'enregistrer ......*/
    public boolean valider(String Utilisateur) {
        Boolean res = false;
        if (Utilisateur.trim() == null || Utilisateur.isEmpty()) {
            return true;
        }
        ResultSet rs;
        abd.ouvrirConnexionBase();
        abd.ouvrirLienRequete();
        String query = "";
        query = abd.getQueryEmail() + abd.getSingleQuote() + Utilisateur + abd.getSingleQuote();
        rs = abd.executerRequete(query);
        try {
            if (rs.next()) {
                res = true;
            }
        } catch (SQLException ex) {
            System.err.println("Problème de lecture du resultat : " + ex.getMessage());
        }
        abd.fermerLienRequete(rs);
        abd.fermerConnexionBase();
        return res;
    }

    /* méthode utilisé pour la création d'une compte */
    public boolean creerCompte(String nomClient, String prenomClient, String mdPasseClient, String telClient, String emailClient, String SiretClient, String RaisonSocClient, String FormeSocClient) {
        int clientId = 0;
        boolean creatOk = false;
        if (valider(emailClient) == true) {
            System.out.println("L'utilisateur existe déjà");
            creatOk = false;
        } else {
            ResultSet rs = null;
            abd.ouvrirConnexionBase();
            abd.ouvrirLienRequete();
            String query = "";
            String query1 = "";
            /* Pour récuperer max identifcateur de la base */
            try {
                query = abd.getQueryIdclientMax();//obligatoire d'avoir une entête
                System.out.println(query);
                rs = abd.executerRequete(query);
                rs.next();
                clientId = rs.getInt("IDCLIENT");
            } catch (SQLException ex) {
                System.err.println("Problème de lecture du resultat : " + ex.getMessage());
            }
            abd.fermerLienRequete(rs);
            abd.ouvrirLienRequete();
            query1 = abd.getQueryInsertClient() + abd.getLeftPar() + (clientId + 1) + abd.getComma() + abd.getSingleQuote() + nomClient + abd.getSingleQuote() + abd.getComma() + abd.getSingleQuote() + prenomClient + abd.getSingleQuote() + abd.getComma() + abd.getSingleQuote() + mdPasseClient + abd.getSingleQuote() + abd.getComma() + abd.getSingleQuote() + telClient + abd.getSingleQuote() + abd.getComma() + abd.getSingleQuote() + emailClient + abd.getSingleQuote() + abd.getComma() + abd.getSingleQuote() + SiretClient + abd.getSingleQuote() + abd.getComma() + abd.getSingleQuote() + RaisonSocClient + abd.getSingleQuote() + abd.getComma() + abd.getSingleQuote() + FormeSocClient + abd.getSingleQuote() + abd.getRightPar();
            creatOk = true;
            System.out.println(query);
            int rs1 = abd.executerRequeteUpdate(query1);
            abd.fermerLienRequete();
            abd.fermerConnexionBase();
        }
        return creatOk;
    }

    //méthode pour faire verification sur email mots de passe
    public boolean verifSaisise(String motPasse, String email) {
        boolean verifOk = false;
        return verifOk;
    }

    //pour récupérer le nom de la base
    public String renseigneNom(String user) {
        nom = user;
        return nom;
    }
    //pour envoyer au controleur

    public String recupNom() {
        return nom;
    }

    //pour récupérer le prénom de la base
    public String renseignePreNom(String prnom) {
        preNom = prnom;
        return preNom;
    }
    //pour envoyer au controleur

    public String recupPreNom() {
        return preNom;
    }

    //pour récupérer le tél de la base
    public String renseigneTel(String telep) {
        tel = telep;
        return tel;
    }
    //pour envoyer au controleur

    public String recupTel() {
        return tel;
    }

    //pour récupérer l'email de la base
    public String renseigneEml(String eml) {
        email = eml;
        return email;
    }
    //pour envoyer au controleur

    public String recupEml() {
        return email;
    }
    //pour récupérer l'email de la base

    public int renseigneIdClient(int idCli) {
        idClient = idCli;
        return idCli;
    }
    //pour envoyer au controleur

    public int recupIdClient() {
        return idClient;
    }

    //pour récupérer la raison sociale de la base
    public String renseigneRaisonSoc(String raisoc) {
        raisonSociale = raisoc;
        return raisonSociale;
    }
    //pour envoyer au controleur

    public String recupRaisonSoc() {
        return raisonSociale;
    }
}
