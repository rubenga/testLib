package allClasses;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.*;

public class AccesBD {

    SQLServerDataSource ds;
    Connection con;
    String sQuery;
    Statement stmt;
    //REQUÊTES    
    private String leftQuote = "'%";
    private String rightQuote = "%'";
    private String singleQuote = "'";
    private String leftPar = "(";
    private String rightPar = ")";
    private String comma = ", ";
    private String genSelect = "SELECT l.ISBN, l.CODETHEME, l.CODESOUSTHEME, l.IDTVA, t.IDTVA,t.LIBELLETVA, t.TAUXTVA,"
            + " l.IDEDITEUR, l.IDAUTEUR, l.TITRELIVRE, l.SOUSTITRE, l.LANGUELIVRE,"
            + " l.NBPAGELIVRE, l.DATEEDITIONLIVRE, l.IMAGELIVRE, l.RESUMELIVRE,"
            + " l.PRIXLIVRE, l.POIDSLIVRE, e.NOMEDITEUR, a.PRENOMAUTEUR,l.CODETHEME,"
            + " l.CODESOUSTHEME, th.LIBELLETHEME, sth.LIBELLESOUSTHEME, a.NOMAUTEUR";
    private String genFrom = " From LIVRES l, EDITEURS e, AUTEURS a, TVA t,"
            + " THEMES th,SOUS_THEMES sth ";
    private String genWhere = " where l.IDEDITEUR=e.IDEDITEUR AND l.IDAUTEUR=a.IDAUTEUR AND th.CODETHEME = l.CODETHEME "
            + " AND t.IDTVA = l.IDTVA"
            + " AND l.CODESOUSTHEME = sth.CODESOUSTHEME AND a.IDAUTEUR = l.IDAUTEUR";
    private String queryArboThemes = "SELECT CODETHEME, LIBELLETHEME FROM THEMES ORDER BY LIBELLETHEME";
    private String querySousThemes = "SELECT T.CODETHEME, T.LIBELLETHEME, ST.LIBELLESOUSTHEME, ST.CODESOUSTHEME "
            + "FROM SOUS_THEMES ST, THEMES T "
            + "WHERE T.CODETHEME = ST.CODETHEME AND T.CODETHEME LIKE ";
    private String queryEmail = "SELECT * FROM CLIENTS WHERE EMAILCLIENT = ";
    private String queryIdclientMax = "SELECT MAX(IDCLIENT)[IDCLIENT] FROM CLIENTS";
    private String queryEvenements = "SELECT DISTINCT E.IDEVENEMENT, E.LIBELLEEVENEMENT, E.TXREDUCTION, E.DATEDEBUT, E.DATEFIN FROM EVENEMENT E, PROMOUVOIR P WHERE E.IDEVENEMENT = P.IDEVENEMENT AND E.DATEFIN > GETDATE()";
    private String queryEvaluation = "SELECT * FROM EVALUER WHERE IDCLIENT = ";
	private String queryInsertClient = "INSERT INTO CLIENTS VALUES";
	private String queryInsertComment = "INSERT INTO EVALUER VALUES";
    private String queryComments = "SELECT * FROM EVALUER WHERE ISBN = ";
//	private String queryEmailMonCompte = "SELECT NOMCLIENT, PRENOMCLIENT, TELCLIENT, EMAILCLIENT, RAISONSOCIALE FROM CLIENTS WHERE EMAILCLIENT = ";    
    private String selectMotCle = ", TROUVER tr, MOTSCLEFS mc ";
    private String selectEvent = ", PROMOUVOIR p, EVENEMENT ev ";
    private String orderBySousThemes = " ORDER BY ST.LIBELLESOUSTHEME";
    private String orderByLivres = " ORDER BY l.TITRELIVRE";
    private String whereAuteur = " AND a.NOMAUTEUR LIKE ";
    private String whereTitre = " AND l.TITRELIVRE LIKE ";
    private String whereMotCle = " AND tr.ISBN = l.ISBN AND mc.IDMOTCLE = tr.IDMOTCLE AND mc.LEMOTCLE LIKE ";
    private String whereIsbn = " AND l.ISBN LIKE ";
	private String whereIsbnEquals = " AND ISBN = ";
    private String whereEditeur = " AND e.NOMEDITEUR LIKE ";
    private String whereEdition = " AND l.DATEEDITIONLIVRE LIKE ";
    private String whereSousTheme = " AND l.CODESOUSTHEME LIKE ";
    private String whereMotDePasse = " AND MOTDEPASSECLIENT = ";
    private String whereEvenement = " AND P.IDEVENEMENT = EV.IDEVENEMENT AND P.ISBN = L.ISBN AND P.IDEVENEMENT = ";

    /**
     * Création du dataSource pour la connexion
     *
     * @param utilisateur
     * @param motDePasse
     * @param server : nom du server
     * @param port
     * @param nomBaseDonne
     */
    public AccesBD(String utilisateur, String motDePasse, String server, int port, String nomBaseDonne) {
        ds = new SQLServerDataSource();
        ds.setUser(utilisateur);
        ds.setPassword(motDePasse);
        ds.setServerName(server);
        ds.setPortNumber(port);
        ds.setDatabaseName(nomBaseDonne);
    }

    /**
     * Ouvrir la connexion à la base de données
     */
    public void ouvrirConnexionBase() {
        try {
            con = ds.getConnection();
        } catch (SQLServerException ex) {
            System.err.println("Connexion : " + ex.getMessage());
            System.exit(0);
        }
    }

    /**
     * Executer une requete qui renvoi un ou plusieurs resultats
     *
     * @param query : la requête sql
     * @return ResultSet avec le resultat de la requête
     */
    public ResultSet executerRequete(String query) {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.err.println("Requête SQL " + ex.getErrorCode() + " : " + ex.getMessage());
        }
        return rs;
    }

    /**
     * Ouvre un lien vers la base de données (Statement)
     */
    public void ouvrirLienRequete() {
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            System.err.println("Ouverture Lien Requête SQL " + ex.getErrorCode() + " : " + ex.getMessage());
        }
    }

    /**
     * Ouvre un lien vers la base de données (Statement) avec des options pour
     * parcourir le ResultSet
     *
     * @param optionOuverture
     * @param optionOuverture2
     */
    public void ouvrirLienRequete(int optionOuverture, int optionOuverture2) {
        try {
            stmt = con.createStatement(optionOuverture, optionOuverture2);
        } catch (SQLException ex) {
            System.err.println("Ouverture Lien Requête SQL " + ex.getErrorCode() + " : " + ex.getMessage());
        }
    }

    /**
     * Execute une requête pour modifier une table (INSERT, UPDATE, DELETE)
     *
     * @param query
     * @return Renvoi le nombre de lignes affectées
     */
    public int executerRequeteUpdate(String query) {
        int result = 0;
        try {
            result = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println("Requête SQL " + ex.getErrorCode() + " : " + ex.getMessage());
        }
        return result;
    }

    /**
     * Execute une transaction pour modifier une table (INSERT, UPDATE, DELETE)
     *
     * @return Renvoi le nombre de lignes affectées
     */
    public int[] executerRequeteTransaction() {
        int result[] = {0};
        try {
            result = stmt.executeBatch();
            stmt.clearBatch();
        } catch (SQLException ex) {
            System.err.println("Requête SQL " + ex.getErrorCode() + " : " + ex.getMessage());
        }
        return result;
    }
    
    public void addBatch(String sql) {
        try {
            stmt.addBatch(sql);
        } catch (SQLException ex) {
            System.err.println("Batch SQL " + ex.getErrorCode() + " : " + ex.getMessage());
        }
    }

    /**
     * Fermer le ResultSet et le Statement
     *
     * @param rs : le ResultSet utilisé
     */
    public void fermerLienRequete(ResultSet rs) {
        try {
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            System.err.println("Fermeture Lien Requête SQL " + ex.getErrorCode() + " : " + ex.getMessage());
        }
    }

    /**
     * Fermer le Statement
     */
    public void fermerLienRequete() {
        try {
            stmt.close();
        } catch (SQLException ex) {
            System.err.println("Fermeture Lien Requête SQL " + ex.getErrorCode() + " : " + ex.getMessage());
        }
    }

    /**
     * Fermer la connection à la base de données
     */
    public void fermerConnexionBase() {
        try {
            con.close();
        } catch (SQLException ex) {
            System.err.println("Fermeture connexion : " + ex.getMessage());
        }
    }

    public Connection getCon() {
        return con;
    }

    public String getLeftQuote() {
        return leftQuote;
    }

    public String getRightQuote() {
        return rightQuote;
    }

    public String getSingleQuote() {
        return singleQuote;
    }

    public String getLeftPar() {
        return leftPar;
    }

    public String getRightPar() {
        return rightPar;
    }

    public String getComma() {
        return comma;
    }

    public String getGenSelect() {
        return genSelect;
    }

    public String getGenFrom() {
        return genFrom;
    }

    public String getGenWhere() {
        return genWhere;
    }

    public String getQueryArboThemes() {
        return queryArboThemes;
    }

    public String getQuerySousThemes() {
        return querySousThemes;
    }

    public String getQueryEmail() {
        return queryEmail;
    }

    public String getQueryIdclientMax() {
        return queryIdclientMax;
    }

    public String getQueryInsertClient() {
        return queryInsertClient;
    }

    public String getSelectMotCle() {
        return selectMotCle;
    }

    public String getOrderBySousThemes() {
        return orderBySousThemes;
    }

    public String getOrderByLivres() {
        return orderByLivres;
    }

    public String getWhereAuteur() {
        return whereAuteur;
    }

    public void setWhereAuteur(String whereAuteur) {
        this.whereAuteur = whereAuteur;
    }

    public String getWhereTitre() {
        return whereTitre;
    }

    public String getWhereMotCle() {
        return whereMotCle;
    }

    public String getWhereIsbn() {
        return whereIsbn;
    }

    public String getWhereEditeur() {
        return whereEditeur;
    }

    public String getWhereEdition() {
        return whereEdition;
    }

    public String getWhereSousTheme() {
        return whereSousTheme;
    }

    public String getWhereMotDePasse() {
        return whereMotDePasse;
    }

    public String getQueryEvenements() {
        return queryEvenements;
    }

    public String getSelectEvent() {
        return selectEvent;
    }

    public String getWhereEvenement() {
        return whereEvenement;
    }

    public String getQueryComments() {
        return queryComments;
    }

  public String getQueryInsertComment() {
	return queryInsertComment;
  }

  public String getQueryEvaluation() {
	return queryEvaluation;
  }

  public String getWhereIsbnEquals() {
	return whereIsbnEquals;
  }
    
    
}
