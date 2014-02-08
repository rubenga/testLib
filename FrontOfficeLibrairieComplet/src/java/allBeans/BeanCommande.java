/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package allBeans;

import allClasses.ItemLivre;
import allClasses.LigneCommande;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author cdi412
 */
public class BeanCommande implements Serializable {
    ArrayList<LigneCommande> articleCommande;

    public BeanCommande() {
        articleCommande = new ArrayList();
    }

    public BeanCommande(BeanPanier bp) {
        articleCommande = new ArrayList();
        for (ItemLivre il : bp.listeLivre()) {
            LigneCommande lc = new LigneCommande(il.getLivre(), il.getPrixTtc()*(100/(il.getLivre().getTvaLivre().getTxTva()+100)), il.getQte(), il.getLivre().getTvaLivre().getTxTva());
            articleCommande.add(lc);
        }
    }

    public ArrayList<LigneCommande> getArticleCommande() {
        return articleCommande;
    }

    public void setArticleCommande(ArrayList<LigneCommande> articleCommande) {
        this.articleCommande = articleCommande;
    }

    @Override
    public String toString() {
        String col = "";
        for (LigneCommande lc : articleCommande) {
            col += lc.getLiv().getIsbn()+" - "+lc.getPrixHt()+" - "+lc.getQuantite()+" - "+lc.getTvaCommande()+"\n";
        }
        return col;
    }
}
