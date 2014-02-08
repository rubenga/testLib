/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package allClasses;

import java.io.IOException;

/**
 *
 * @author cdi404
 */
public class ConroleSaisieChamps {

    public static final String DATE_FORMAT = "^\\d{2}/\\d{2}/\\d{4}$";//
    public static final String STRING_NON_NUMERIQUE = "[a-zA-Z\\s'é{0,}è{0,}ç{0,}à{0,}â{0,}]+";
    public static final String STRING_ALPHA_NUMERIQUE = "^[0-9]{1,2}[,]?[ ][A-Za-z\\s]*$";
    public static final String STRING_NUMERIQUE = "[0-9]{1,5}";
    public static final String STRING_NUMERIQUE_FLOTTANT = "^\\d+(?:[.,]\\d{0,2})?$"; //
    public static final String CHAMP_EMAIL = "EmlCli";
    public static final String CHAMP_PASS = "MdpCli";
    public static final String CHAMP_NOM = "nom";
    public static final String CHAMP_TELE = "TelCli";
    public static final String ATT_ERREURS = "errors";
    public static final String ATT_RESULTAT = "result";

    /**
     * Valide l'adresse mail saisie.
     */
    public static void validationEmail(String email) throws Exception {
        if (email != null && email.trim().length() != 0) {
            //([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)
            if (!email.matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$")) {
                throw new Exception("Merci de saisir une adresse mail valide.");
            }
        } else {
            throw new Exception("Merci de saisir une adresse mail.");
        }
    }

    /**
     * Valide les mots de passe saisis.
     */
    public static void validationMotsDePasse(String motDePasse) throws Exception {
        if (motDePasse != null && motDePasse.trim().length() != 0) {
            if (motDePasse.trim().length() < 3) {
                throw new Exception("Les mots de passe doivent contenir au moins 3 caractères.");
            }
        } else {
            throw new Exception("Merci de saisir et confirmer votre mot de passe.");
        }
    }

    /**
     * Valide le nom d'utilisateur saisi.
     */
    public static void validationNom(String nom) throws Exception {
        if (nom != null && nom.trim().length() < 3) {
            throw new Exception("Le nom d'utilisateur doit contenir au moins 3 caractères.");
        }
    }
//validationTelephone

    public static void validationTelephone(String telephone) throws Exception {
        if (telephone != null && telephone.trim().length() != 0) {
            if (!telephone.matches("^(01|02|03|04|05|08|09)[0-9]{8}$")) {
                throw new Exception("Merci de saisir un numéro téléphone valide.");
            }
        } else {
            throw new Exception("Merci de saisir un numéro téléphone.");
        }
    }
}
