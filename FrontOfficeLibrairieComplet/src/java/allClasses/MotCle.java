/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package allClasses;

/**
 *
 * @author cdi412
 */
public class MotCle {
    private int idMotCle;
    private String leMotCle;

    public MotCle(int idMotCle, String leMotCle) {
        this.idMotCle = idMotCle;
        this.leMotCle = leMotCle;
    }

    public MotCle() {
    }

    public int getIdMotCle() {
        return idMotCle;
    }

    public void setIdMotCle(int idMotCle) {
        this.idMotCle = idMotCle;
    }

    public String getLeMotCle() {
        return leMotCle;
    }

    public void setLeMotCle(String leMotCle) {
        this.leMotCle = leMotCle;
    }

    @Override
    public String toString() {
        return leMotCle;
    }
    
    
}
