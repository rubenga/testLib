/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package allClasses;


/**
 *
 * @author cdi412
 */
public class Client extends Auteur{
    private String motDePasse;
    private String telephone;
    private String email;
    private String siret;
    private String raisonSociale;
    private String formeJuridique;
        
    public Client() {
    }

    public Client(String prenom, String nom, int id) {
        super(prenom, nom, id);
    }    

    public Client(String motDePasse, String email, String prenom, String nom, int id) {
        super(prenom, nom, id);
        this.motDePasse = motDePasse;
        this.email = email;
    }

    public Client(String motDePasse, String telephone, String email, String prenom, String nom, int id) {
        super(prenom, nom, id);
        this.motDePasse = motDePasse;
        this.telephone = telephone;
        this.email = email;
    }

    public Client(String motDePasse, String telephone, String email, String siret, String raisonSociale, String formeJuridique, String prenom, String nom, int id) {
        super(prenom, nom, id);
        this.motDePasse = motDePasse;
        this.telephone = telephone;
        this.email = email;
        this.siret = siret;
        this.raisonSociale = raisonSociale;
        this.formeJuridique = formeJuridique;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getFormeJuridique() {
        return formeJuridique;
    }

    public void setFormeJuridique(String formeJuridique) {
        this.formeJuridique = formeJuridique;
    }

}
