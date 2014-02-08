/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package allClasses;

/**
 *
 * @author cdi412
 */
public class Langue {
    private String pays;
    private String code;

    public Langue(String pays, String code) {
        this.pays = pays;
        this.code = code;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return pays;
    }
       
}
