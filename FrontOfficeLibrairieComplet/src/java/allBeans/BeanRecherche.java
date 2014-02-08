/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package allBeans;

import allClasses.AccesBD;
import allClasses.Auteur;
import allClasses.Editeur;
import allClasses.Evaluation;
import allClasses.Evenement;
import allClasses.Livre;
import allClasses.SousTheme;
import allClasses.ThemePrincipal;
import allClasses.Tva;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class BeanRecherche implements Serializable {

  //accès
  private final AccesBD abd = new AccesBD("sa", "sa", "localhost", 1433, "LIBRAIRIE");

  private boolean baut = false;
  private boolean btit = false;
  private boolean bmot = false;
  private boolean bisb = false;
  private boolean bedi = false;
  private boolean bedo = false;
  private boolean existe = false;
  private String whereAut;
  private String whereTit;
  private String whereMot;
  private String selectMot;
  private String whereIsb;
  private String whereEdi;
  private String whereEdo;

  private static String ISBN="";
//  private static String userComment="";
//  private static String note="";  
  
  //affichage de l'arborescence de thèmes
  public Collection<ThemePrincipal> collectionArboTheme() {
	Collection<ThemePrincipal> ct = new ArrayList<>();
	ResultSet rs;
	abd.ouvrirConnexionBase();
	abd.ouvrirLienRequete();
	rs = abd.executerRequete(abd.getQueryArboThemes());
	System.out.println(abd.getQueryArboThemes());
	try {
	  while (rs.next()) {
		ThemePrincipal tp = new ThemePrincipal(rs.getString("LIBELLETHEME"), rs.getString("CODETHEME"));
//                SousTheme st = new SousTheme(tp, rs.getString("LIBELLESOUSTHEME"), rs.getString("CODESOUSTHEME"));
		ct.add(tp);
	  }
	} catch (SQLException ex) {
	  System.err.println("Problème de lecture du resultat : " + ex.getMessage());
	}
	abd.fermerLienRequete(rs);
	abd.fermerConnexionBase();

	return ct;
  }

  //affichage de l'arborescence des sous-thèmes
  public Collection<SousTheme> collectionArboSousThemes(String theme) {
	Collection<SousTheme> cst = new ArrayList<>();
	String query = abd.getQuerySousThemes() + abd.getLeftQuote() + theme + abd.getRightQuote() + abd.getOrderBySousThemes();
	System.out.println(query);
	ResultSet rs;
	abd.ouvrirConnexionBase();
	abd.ouvrirLienRequete();
	rs = abd.executerRequete(query);
	System.out.println(query);
	try {
	  while (rs.next()) {
		ThemePrincipal tp = new ThemePrincipal(rs.getString("LIBELLETHEME"), rs.getString("CODETHEME"));
		SousTheme st = new SousTheme(tp, rs.getString("LIBELLESOUSTHEME"), rs.getString("CODESOUSTHEME"));
		cst.add(st);
	  }
	} catch (SQLException ex) {
	  System.err.println("Problème de lecture du resultat : " + ex.getMessage());
	}
	abd.fermerLienRequete(rs);
	abd.fermerConnexionBase();
	return cst;
  }

  //retourne une série de livres à partir d'un code sous-thème
  public Collection<Livre> collectionLivresParSousTheme(String codeSousCat) {
	Collection<Livre> cl = new ArrayList<>();
	String query = abd.getGenSelect() + abd.getGenFrom() + abd.getGenWhere() + abd.getWhereSousTheme() + abd.getLeftQuote() + codeSousCat + abd.getRightQuote() + abd.getOrderByLivres();
	ResultSet rs;
	abd.ouvrirConnexionBase();
	abd.ouvrirLienRequete();
	rs = abd.executerRequete(query);
	System.out.println(query);
	try {
	  while (rs.next()) {
		ThemePrincipal tp = new ThemePrincipal(rs.getString("LIBELLETHEME"), rs.getString("CODETHEME"));
		Livre l = new Livre(rs.getString("ISBN"), tp, new SousTheme(tp, rs.getString("LIBELLESOUSTHEME"),
				rs.getString("CODESOUSTHEME")), new Editeur(rs.getString("NOMEDITEUR"), rs.getInt("IDEDITEUR")),
				new Auteur(rs.getString("PRENOMAUTEUR"), rs.getString("NOMAUTEUR"), rs.getInt("IDAUTEUR")),
				new Tva(rs.getInt("IDTVA"), rs.getString("LIBELLETVA"), rs.getFloat("TAUXTVA")),
				rs.getString("TITRELIVRE"), rs.getString("SOUSTITRE"), rs.getString("LANGUELIVRE"),
				rs.getInt("NBPAGELIVRE"), rs.getDate("DATEEDITIONLIVRE"), rs.getString("IMAGELIVRE"),
				rs.getString("RESUMELIVRE"), rs.getFloat("PRIXLIVRE"), rs.getFloat("POIDSLIVRE"));
		cl.add(l);
	  }
	} catch (SQLException ex) {
	  System.err.println("Problème de lecture du resultat : " + ex.getMessage());
	}
	abd.fermerLienRequete(rs);
	abd.fermerConnexionBase();
	return cl;
  }

  //affichage de la liste des évènements en cours
  public Collection<Evenement> collectionEvenements() {
	Collection<Evenement> ce = new ArrayList<>();
	ResultSet rs;
	abd.ouvrirConnexionBase();
	abd.ouvrirLienRequete();
	rs = abd.executerRequete(abd.getQueryEvenements());
	System.out.println(abd.getQueryEvenements());
	try {
	  while (rs.next()) {
		Evenement eve = new Evenement(rs.getInt("IDEVENEMENT"), rs.getString("LIBELLEEVENEMENT"), rs.getFloat("TXREDUCTION"), rs.getDate("DATEDEBUT"), rs.getDate("DATEFIN"));
		ce.add(eve);
	  }
	} catch (SQLException ex) {
	  System.err.println("Problème de lecture du resultat : " + ex.getMessage());
	}
	abd.fermerLienRequete(rs);
	abd.fermerConnexionBase();

	return ce;
  }

  //retourne une série de livres à partir d'un évènement
  public Collection<Livre> collectionLivresParEvenement(String idEvent) {
	Collection<Livre> cl = new ArrayList<>();
	String query = abd.getGenSelect() + abd.getGenFrom() + abd.getSelectEvent() + abd.getGenWhere() + abd.getWhereEvenement() + Integer.parseInt(idEvent) + abd.getOrderByLivres();
	ResultSet rs;
	abd.ouvrirConnexionBase();
	abd.ouvrirLienRequete();
	rs = abd.executerRequete(query);
	System.out.println(query);
	try {
	  while (rs.next()) {
		ThemePrincipal tp = new ThemePrincipal(rs.getString("LIBELLETHEME"), rs.getString("CODETHEME"));
		Livre l = new Livre(rs.getString("ISBN"), tp, new SousTheme(tp, rs.getString("LIBELLESOUSTHEME"),
				rs.getString("CODESOUSTHEME")), new Editeur(rs.getString("NOMEDITEUR"), rs.getInt("IDEDITEUR")),
				new Auteur(rs.getString("PRENOMAUTEUR"), rs.getString("NOMAUTEUR"), rs.getInt("IDAUTEUR")),
				new Tva(rs.getInt("IDTVA"), rs.getString("LIBELLETVA"), rs.getFloat("TAUXTVA")),
				rs.getString("TITRELIVRE"), rs.getString("SOUSTITRE"), rs.getString("LANGUELIVRE"),
				rs.getInt("NBPAGELIVRE"), rs.getDate("DATEEDITIONLIVRE"), rs.getString("IMAGELIVRE"),
				rs.getString("RESUMELIVRE"), rs.getFloat("PRIXLIVRE"), rs.getFloat("POIDSLIVRE"));
		cl.add(l);
	  }
	} catch (SQLException ex) {
	  System.err.println("Problème de lecture du resultat : " + ex.getMessage());
	}
	abd.fermerLienRequete(rs);
	abd.fermerConnexionBase();
	return cl;
  }

  //retourne une série de commentaires à partir d'un livre
  public Collection<Evaluation> collectionCommentaires(String isb) {
	Collection<Evaluation> ceva = new ArrayList<>();
	ResultSet rs;
	abd.ouvrirConnexionBase();
	abd.ouvrirLienRequete();
	rs = abd.executerRequete(abd.getQueryComments() + abd.getSingleQuote() + isb + abd.getSingleQuote());
	System.out.println(abd.getQueryComments() + abd.getSingleQuote() + isb + abd.getSingleQuote());
	try {
	  while (rs.next()) {
		Evaluation eva = new Evaluation(rs.getString("ISBN"), rs.getInt("IDCLIENT"), new Livre(), rs.getString("COMMENTAIRE"), rs.getInt("EVALUATION"));
		ceva.add(eva);
	  }
	} catch (SQLException ex) {
	  System.err.println("Problème de lecture du resultat : " + ex.getMessage());
	}
	abd.fermerLienRequete(rs);
	abd.fermerConnexionBase();

	return ceva;
  }

  //retourne une série de commentaires à partir d'un livre
  public boolean insererCommentaire(String isb, int id, String comment, int note) {

	if(comment.contains("'")){
	  comment = comment.replace("'", "''");
	}

	boolean commented =false;
	
	int result = 0;
	abd.ouvrirConnexionBase();
	abd.ouvrirLienRequete();
	ResultSet rs;
	rs = abd.executerRequete(abd.getQueryEvaluation() + id + abd.getWhereIsbnEquals() + abd.getSingleQuote() + isb + abd.getSingleQuote());
	System.out.println("VERIF: \n"+abd.getQueryEvaluation() + id + abd.getWhereIsbnEquals() + abd.getSingleQuote() + isb + abd.getSingleQuote());
//	String query = abd.getQueryInsertComment() + abd.getLeftPar() + id + isb + comment + note + abd.getRightPar();
//	System.out.println(rs);
	try {
	  while (rs.next()) {
		commented = true;
	  }	  
	  if(!commented){
	  result = abd.executerRequeteUpdate(abd.getQueryInsertComment() + abd.getLeftPar() + id + ", " + abd.getSingleQuote() + isb + abd.getSingleQuote() + ", " + abd.getSingleQuote() + comment + abd.getSingleQuote() + ", " + note + abd.getRightPar());	
	  System.out.println("Result : "+result);
	  System.out.println("INSERT: \n"+abd.getQueryInsertComment() + abd.getLeftPar() + id + ", " + abd.getSingleQuote() + isb + abd.getSingleQuote() + ", " + abd.getSingleQuote() + comment + abd.getSingleQuote() + ", " + note + abd.getRightPar());
	  }
	} catch (SQLException ex) {
	  System.err.println("Problème de lecture du resultat : " + ex.getMessage());
	}
	abd.fermerLienRequete(rs);
	abd.fermerConnexionBase();
	
	return commented;
  }

  //retourne 1 livre à partir de l'isbn
  public Livre ficheProduit(String isb) {
	Livre lib = new Livre();
	ResultSet rs;
	abd.ouvrirConnexionBase();
	String query = abd.getGenSelect() + abd.getGenFrom() + abd.getGenWhere() + abd.getWhereIsbn() + abd.getLeftQuote() + isb + abd.getRightQuote();
	abd.ouvrirLienRequete();
	rs = abd.executerRequete(query);
	System.out.println(query);
	try {
	  while (rs.next()) {
		ThemePrincipal tp = new ThemePrincipal(rs.getString("LIBELLETHEME"), rs.getString("CODETHEME"));
		lib = new Livre(rs.getString("ISBN"), tp, new SousTheme(tp, rs.getString("LIBELLESOUSTHEME"),
				rs.getString("CODESOUSTHEME")), new Editeur(rs.getString("NOMEDITEUR"), rs.getInt("IDEDITEUR")),
				new Auteur(rs.getString("PRENOMAUTEUR"), rs.getString("NOMAUTEUR"), rs.getInt("IDAUTEUR")),
				new Tva(rs.getInt("IDTVA"), rs.getString("LIBELLETVA"), rs.getFloat("TAUXTVA")),
				rs.getString("TITRELIVRE"), rs.getString("SOUSTITRE"), rs.getString("LANGUELIVRE"),
				rs.getInt("NBPAGELIVRE"), rs.getDate("DATEEDITIONLIVRE"), rs.getString("IMAGELIVRE"),
				rs.getString("RESUMELIVRE"), rs.getFloat("PRIXLIVRE"), rs.getFloat("POIDSLIVRE"));

	  }
	} catch (SQLException ex) {
	  System.err.println("Problème de lecture du resultat : " + ex.getMessage());
	}
	abd.fermerLienRequete(rs);
	abd.fermerConnexionBase();
	return lib;
  }

  //recherche de livres par saisie utilisateur : recherche simple, somme logique
  public Collection<Livre> collectionRechercheSimpleOR(String aut, String tit, String isb, String mot) {
	Collection<Livre> cOR = new ArrayList<>();

	if (!aut.isEmpty()) {
	  baut = true;
	  whereAut = abd.getWhereAuteur() + abd.getLeftQuote() + aut + abd.getRightQuote();
	} else {
	  whereAut = "";
	}
	if (!tit.isEmpty()) {
	  btit = true;
	  whereTit = abd.getWhereTitre() + abd.getLeftQuote() + tit + abd.getRightQuote();
	} else {
	  whereTit = "";
	}
	if (!mot.isEmpty()) {
	  bmot = true;
	  whereMot = abd.getWhereMotCle() + abd.getLeftQuote() + mot + abd.getRightQuote();
	  selectMot = abd.getSelectMotCle();
	} else {
	  whereMot = "";
	  selectMot = "";
	}
	if (!isb.isEmpty()) {
	  bisb = true;
	  whereIsb = abd.getWhereIsbn() + abd.getLeftQuote() + isb + abd.getRightQuote();
	} else {
	  whereIsb = "";
	}

	if (!baut && !btit && !bmot && !bisb) {
	  cOR = null;
	} else {
	  abd.ouvrirConnexionBase();

	  String genQueryAcMotCLe = abd.getGenSelect() + abd.getGenFrom() + selectMot + abd.getGenWhere();

	  if (baut) {
		String query1 = abd.getGenSelect() + abd.getGenFrom() + abd.getGenWhere() + whereAut + abd.getOrderByLivres();
		ResultSet rs1;
		abd.ouvrirLienRequete();
		rs1 = abd.executerRequete(query1);
		System.out.println("1 " + query1);
		try {
		  while (rs1.next()) {
			ThemePrincipal tp = new ThemePrincipal(rs1.getString("LIBELLETHEME"), rs1.getString("CODETHEME"));
			Livre l = new Livre(rs1.getString("ISBN"), tp, new SousTheme(tp, rs1.getString("LIBELLESOUSTHEME"),
					rs1.getString("CODESOUSTHEME")), new Editeur(rs1.getString("NOMEDITEUR"), rs1.getInt("IDEDITEUR")),
					new Auteur(rs1.getString("PRENOMAUTEUR"), rs1.getString("NOMAUTEUR"), rs1.getInt("IDAUTEUR")),
					new Tva(rs1.getInt("IDTVA"), rs1.getString("LIBELLETVA"), rs1.getFloat("TAUXTVA")),
					rs1.getString("TITRELIVRE"), rs1.getString("SOUSTITRE"), rs1.getString("LANGUELIVRE"),
					rs1.getInt("NBPAGELIVRE"), rs1.getDate("DATEEDITIONLIVRE"), rs1.getString("IMAGELIVRE"),
					rs1.getString("RESUMELIVRE"), rs1.getFloat("PRIXLIVRE"), rs1.getFloat("POIDSLIVRE"));
			if (cOR.isEmpty()) {
			  cOR.add(l);
			} else {
			  for (Livre li : cOR) {
				if (l.getIsbn().equals(li.getIsbn())) {
				  existe = true;
				  break;
				} else {
				  existe = false;
				}
			  }
			  if (!existe) {
				cOR.add(l);
			  }
			}
		  }
		} catch (SQLException ex) {
		  System.err.println("Problème de lecture du resultat : " + ex.getMessage());
		}
		abd.fermerLienRequete(rs1);
	  }

	  if (btit) {
		String query2 = abd.getGenSelect() + abd.getGenFrom() + abd.getGenWhere() + whereTit + abd.getOrderByLivres();
		ResultSet rs2;
		abd.ouvrirLienRequete();
		rs2 = abd.executerRequete(query2);
		System.out.println("2 " + query2);
		try {
		  while (rs2.next()) {
			ThemePrincipal tp = new ThemePrincipal(rs2.getString("LIBELLETHEME"), rs2.getString("CODETHEME"));
			Livre l = new Livre(rs2.getString("ISBN"), tp, new SousTheme(tp, rs2.getString("LIBELLESOUSTHEME"),
					rs2.getString("CODESOUSTHEME")), new Editeur(rs2.getString("NOMEDITEUR"), rs2.getInt("IDEDITEUR")),
					new Auteur(rs2.getString("PRENOMAUTEUR"), rs2.getString("NOMAUTEUR"), rs2.getInt("IDAUTEUR")),
					new Tva(rs2.getInt("IDTVA"), rs2.getString("LIBELLETVA"), rs2.getFloat("TAUXTVA")),
					rs2.getString("TITRELIVRE"), rs2.getString("SOUSTITRE"), rs2.getString("LANGUELIVRE"),
					rs2.getInt("NBPAGELIVRE"), rs2.getDate("DATEEDITIONLIVRE"), rs2.getString("IMAGELIVRE"),
					rs2.getString("RESUMELIVRE"), rs2.getFloat("PRIXLIVRE"), rs2.getFloat("POIDSLIVRE"));
			if (cOR.isEmpty()) {
			  cOR.add(l);
			} else {
			  for (Livre li : cOR) {
				if (l.getIsbn().equals(li.getIsbn())) {
				  existe = true;
				  break;
				} else {
				  existe = false;
				}
			  }
			  if (!existe) {
				cOR.add(l);
			  }
			}
		  }
		} catch (SQLException ex) {
		  System.err.println("Problème de lecture du resultat : " + ex.getMessage());
		}
		abd.fermerLienRequete(rs2);
	  }

	  if (bisb) {
		String query3 = abd.getGenSelect() + abd.getGenFrom() + abd.getGenWhere() + whereIsb + abd.getOrderByLivres();
		ResultSet rs3;
		abd.ouvrirLienRequete();
		rs3 = abd.executerRequete(query3);
		System.out.println("3 " + query3);
		try {
		  while (rs3.next()) {
			ThemePrincipal tp = new ThemePrincipal(rs3.getString("LIBELLETHEME"), rs3.getString("CODETHEME"));
			Livre l = new Livre(rs3.getString("ISBN"), tp, new SousTheme(tp, rs3.getString("LIBELLESOUSTHEME"),
					rs3.getString("CODESOUSTHEME")), new Editeur(rs3.getString("NOMEDITEUR"), rs3.getInt("IDEDITEUR")),
					new Auteur(rs3.getString("PRENOMAUTEUR"), rs3.getString("NOMAUTEUR"), rs3.getInt("IDAUTEUR")),
					new Tva(rs3.getInt("IDTVA"), rs3.getString("LIBELLETVA"), rs3.getFloat("TAUXTVA")),
					rs3.getString("TITRELIVRE"), rs3.getString("SOUSTITRE"), rs3.getString("LANGUELIVRE"),
					rs3.getInt("NBPAGELIVRE"), rs3.getDate("DATEEDITIONLIVRE"), rs3.getString("IMAGELIVRE"),
					rs3.getString("RESUMELIVRE"), rs3.getFloat("PRIXLIVRE"), rs3.getFloat("POIDSLIVRE"));
			if (cOR.isEmpty()) {
			  cOR.add(l);
			} else {
			  for (Livre li : cOR) {
				if (l.getIsbn().equals(li.getIsbn())) {
				  existe = true;
				  break;
				} else {
				  existe = false;
				}
			  }
			  if (!existe) {
				cOR.add(l);
			  }
			}
		  }
		} catch (SQLException ex) {
		  System.err.println("Problème de lecture du resultat : " + ex.getMessage());
		}
		abd.fermerLienRequete(rs3);
	  }

	  if (bmot) {
		String query4 = genQueryAcMotCLe + whereMot + abd.getOrderByLivres();
		ResultSet rs4;
		abd.ouvrirLienRequete();
		rs4 = abd.executerRequete(query4);
		System.out.println("4 " + query4);
		try {
		  while (rs4.next()) {
			ThemePrincipal tp = new ThemePrincipal(rs4.getString("LIBELLETHEME"), rs4.getString("CODETHEME"));
			Livre l = new Livre(rs4.getString("ISBN"), tp, new SousTheme(tp, rs4.getString("LIBELLESOUSTHEME"),
					rs4.getString("CODESOUSTHEME")), new Editeur(rs4.getString("NOMEDITEUR"), rs4.getInt("IDEDITEUR")),
					new Auteur(rs4.getString("PRENOMAUTEUR"), rs4.getString("NOMAUTEUR"), rs4.getInt("IDAUTEUR")),
					new Tva(rs4.getInt("IDTVA"), rs4.getString("LIBELLETVA"), rs4.getFloat("TAUXTVA")),
					rs4.getString("TITRELIVRE"), rs4.getString("SOUSTITRE"), rs4.getString("LANGUELIVRE"),
					rs4.getInt("NBPAGELIVRE"), rs4.getDate("DATEEDITIONLIVRE"), rs4.getString("IMAGELIVRE"),
					rs4.getString("RESUMELIVRE"), rs4.getFloat("PRIXLIVRE"), rs4.getFloat("POIDSLIVRE"));
			if (cOR.isEmpty()) {
			  cOR.add(l);
			} else {
			  for (Livre li : cOR) {
				if (l.getIsbn().equals(li.getIsbn())) {
				  existe = true;
				  break;
				} else {
				  existe = false;
				}
			  }
			  if (!existe) {
				cOR.add(l);
			  }
			}
		  }
		} catch (SQLException ex) {
		  System.err.println("Problème de lecture du resultat : " + ex.getMessage());
		}
		abd.fermerLienRequete(rs4);
	  }
	  abd.fermerConnexionBase();
	}
	return cOR;
  }

  //recherche de livres par saisie utilisateur : recherche simple, produit logique
  public Collection<Livre> collectionRechercheSimpleAND(String aut, String tit, String isb, String mot) {

	Collection<Livre> cAND = new ArrayList<>();

	if (!aut.isEmpty()) {
	  baut = true;
	  whereAut = abd.getWhereAuteur() + abd.getLeftQuote() + aut + abd.getRightQuote();
	} else {
	  whereAut = "";
	}
	if (!tit.isEmpty()) {
	  btit = true;
	  whereTit = abd.getWhereTitre() + abd.getLeftQuote() + tit + abd.getRightQuote();
	} else {
	  whereTit = "";
	}
	if (!mot.isEmpty()) {
	  bmot = true;
	  whereMot = abd.getWhereMotCle() + abd.getLeftQuote() + mot + abd.getRightQuote();
	  selectMot = abd.getSelectMotCle();
	} else {
	  whereMot = "";
	  selectMot = "";
	}
	if (!isb.isEmpty()) {
	  bisb = true;
	  whereIsb = abd.getWhereIsbn() + abd.getLeftQuote() + isb + abd.getRightQuote();
	} else {
	  whereIsb = "";
	}

	if (!baut && !btit && !bmot && !bisb) {
	  cAND = null;
	} else {
	  ResultSet rs;
	  abd.ouvrirConnexionBase();
	  String query = abd.getGenSelect() + abd.getGenFrom() + selectMot + abd.getGenWhere() + whereAut + whereTit + whereIsb + whereMot + abd.getOrderByLivres();
	  abd.ouvrirLienRequete();
	  rs = abd.executerRequete(query);
	  System.out.println(query);
	  try {
		while (rs.next()) {
		  ThemePrincipal tp = new ThemePrincipal(rs.getString("LIBELLETHEME"), rs.getString("CODETHEME"));
		  Livre l = new Livre(rs.getString("ISBN"), tp, new SousTheme(tp, rs.getString("LIBELLESOUSTHEME"),
				  rs.getString("CODESOUSTHEME")), new Editeur(rs.getString("NOMEDITEUR"), rs.getInt("IDEDITEUR")),
				  new Auteur(rs.getString("PRENOMAUTEUR"), rs.getString("NOMAUTEUR"), rs.getInt("IDAUTEUR")),
				  new Tva(rs.getInt("IDTVA"), rs.getString("LIBELLETVA"), rs.getFloat("TAUXTVA")),
				  rs.getString("TITRELIVRE"), rs.getString("SOUSTITRE"), rs.getString("LANGUELIVRE"),
				  rs.getInt("NBPAGELIVRE"), rs.getDate("DATEEDITIONLIVRE"), rs.getString("IMAGELIVRE"),
				  rs.getString("RESUMELIVRE"), rs.getFloat("PRIXLIVRE"), rs.getFloat("POIDSLIVRE"));
		  if (cAND.isEmpty()) {
			cAND.add(l);
		  } else {
			for (Livre li : cAND) {
			  if (l.getIsbn().equals(li.getIsbn())) {
				existe = true;
				break;
			  } else {
				existe = false;
			  }
			}
			if (!existe) {
			  cAND.add(l);
			}
		  }
		}
	  } catch (SQLException ex) {
		System.err.println("Problème de lecture du resultat : " + ex.getMessage());
	  }
	  abd.fermerLienRequete(rs);
	  abd.fermerConnexionBase();
	}

	return cAND;
  }

  //recherche de livres par saisie utilisateur : recherche avancée, produit logique
  public Collection<Livre> collectionRechercheAvanceeAND(String aut, String tit, String isb, String mot, String edi, String edo) {

	Collection<Livre> cAND = new ArrayList<>();

	if (!aut.isEmpty()) {
	  baut = true;
	  whereAut = abd.getWhereAuteur() + abd.getLeftQuote() + aut + abd.getRightQuote();
	} else {
	  whereAut = "";
	}
	if (!tit.isEmpty()) {
	  btit = true;
	  whereTit = abd.getWhereTitre() + abd.getLeftQuote() + tit + abd.getRightQuote();
	} else {
	  whereTit = "";
	}
	if (!mot.isEmpty()) {
	  bmot = true;
	  whereMot = abd.getWhereMotCle() + abd.getLeftQuote() + mot + abd.getRightQuote();
	  selectMot = abd.getSelectMotCle();
	} else {
	  whereMot = "";
	  selectMot = "";
	}
	if (!isb.isEmpty()) {
	  bisb = true;
	  whereIsb = abd.getWhereIsbn() + abd.getLeftQuote() + isb + abd.getRightQuote();
	} else {
	  whereIsb = "";
	}
	if (!edi.isEmpty()) {
	  bedi = true;
	  whereEdi = abd.getWhereEditeur() + abd.getLeftQuote() + edi + abd.getRightQuote();
	} else {
	  whereEdi = "";
	}
	if (!edo.isEmpty()) {
	  bedo = true;
	  whereEdo = abd.getWhereEdition() + abd.getLeftQuote() + edo + abd.getRightQuote();
	} else {
	  whereEdo = "";
	}

	if (!baut && !btit && !bmot && !bisb && !bedi && !bedo) {
	  cAND = null;
	} else {
//	  String genQuery = genSelect+genFrom + selectMotCle + genWhere;
	  ResultSet rs1;
	  abd.ouvrirConnexionBase();
	  String query = abd.getGenSelect() + abd.getGenFrom() + selectMot + abd.getGenWhere() + whereAut + whereTit + whereIsb + whereMot + whereEdi + whereEdo + abd.getOrderByLivres();
	  abd.ouvrirLienRequete();
	  rs1 = abd.executerRequete(query);
	  System.out.println(query);
	  try {
		while (rs1.next()) {
		  ThemePrincipal tp = new ThemePrincipal(rs1.getString("LIBELLETHEME"), rs1.getString("CODETHEME"));
		  Livre l = new Livre(rs1.getString("ISBN"), tp, new SousTheme(tp, rs1.getString("LIBELLESOUSTHEME"),
				  rs1.getString("CODESOUSTHEME")), new Editeur(rs1.getString("NOMEDITEUR"), rs1.getInt("IDEDITEUR")),
				  new Auteur(rs1.getString("PRENOMAUTEUR"), rs1.getString("NOMAUTEUR"), rs1.getInt("IDAUTEUR")),
				  new Tva(rs1.getInt("IDTVA"), rs1.getString("LIBELLETVA"), rs1.getFloat("TAUXTVA")),
				  rs1.getString("TITRELIVRE"), rs1.getString("SOUSTITRE"), rs1.getString("LANGUELIVRE"),
				  rs1.getInt("NBPAGELIVRE"), rs1.getDate("DATEEDITIONLIVRE"), rs1.getString("IMAGELIVRE"),
				  rs1.getString("RESUMELIVRE"), rs1.getFloat("PRIXLIVRE"), rs1.getFloat("POIDSLIVRE"));
		  if (cAND.isEmpty()) {
			cAND.add(l);
		  } else {
			for (Livre li : cAND) {
			  if (l.getIsbn().equals(li.getIsbn())) {
				existe = true;
				break;
			  } else {
				existe = false;
			  }
			}
			if (!existe) {
			  cAND.add(l);
			}
		  }
		}
	  } catch (SQLException ex) {
		System.err.println("Problème de lecture du resultat : " + ex.getMessage());
	  }
	  abd.fermerLienRequete(rs1);
	  abd.fermerConnexionBase();
	}
	return cAND;
  }

  //recherche de livres par saisie utilisateur : recherche avancée, somme logique
  public Collection<Livre> collectionRechercheAvanceeOR(String aut, String tit, String isb, String mot, String edi, String edo) {

	Collection<Livre> cOR = new ArrayList<>();

	if (!aut.isEmpty()) {
	  baut = true;
	  whereAut = abd.getWhereAuteur() + abd.getLeftQuote() + aut + abd.getRightQuote();
	} else {
	  whereAut = "";
	}
	if (!tit.isEmpty()) {
	  btit = true;
	  whereTit = abd.getWhereTitre() + abd.getLeftQuote() + tit + abd.getRightQuote();
	} else {
	  whereTit = "";
	}
	if (!mot.isEmpty()) {
	  bmot = true;
	  whereMot = abd.getWhereMotCle() + abd.getLeftQuote() + mot + abd.getRightQuote();
	  selectMot = abd.getSelectMotCle();
	} else {
	  whereMot = "";
	  selectMot = "";
	}
	if (!isb.isEmpty()) {
	  bisb = true;
	  whereIsb = abd.getWhereIsbn() + abd.getLeftQuote() + isb + abd.getRightQuote();
	} else {
	  whereIsb = "";
	}
	if (!edi.isEmpty()) {
	  bedi = true;
	  whereEdi = abd.getWhereEditeur() + abd.getLeftQuote() + edi + abd.getRightQuote();
	} else {
	  whereEdi = "";
	}
	if (!edo.isEmpty()) {
	  bedo = true;
	  whereEdo = abd.getWhereEdition() + abd.getLeftQuote() + edo + abd.getRightQuote();
	} else {
	  whereEdo = "";
	}

	if (!baut && !btit && !bmot && !bisb && !bedi && !bedo) {
	  cOR = null;
	} else {
	  abd.ouvrirConnexionBase();
//	  String genQueryAcMotCLe = genSelect + genFrom + selectMotCle + genWhere;

	  if (baut) {
		String query1 = abd.getGenSelect() + abd.getGenFrom() + abd.getGenWhere() + whereAut + abd.getOrderByLivres();
		ResultSet rs1;
		abd.ouvrirLienRequete();
		rs1 = abd.executerRequete(query1);
		System.out.println("1 " + query1);
		try {
		  while (rs1.next()) {
			ThemePrincipal tp = new ThemePrincipal(rs1.getString("LIBELLETHEME"), rs1.getString("CODETHEME"));
			Livre l = new Livre(rs1.getString("ISBN"), tp, new SousTheme(tp, rs1.getString("LIBELLESOUSTHEME"),
					rs1.getString("CODESOUSTHEME")), new Editeur(rs1.getString("NOMEDITEUR"), rs1.getInt("IDEDITEUR")),
					new Auteur(rs1.getString("PRENOMAUTEUR"), rs1.getString("NOMAUTEUR"), rs1.getInt("IDAUTEUR")),
					new Tva(rs1.getInt("IDTVA"), rs1.getString("LIBELLETVA"), rs1.getFloat("TAUXTVA")),
					rs1.getString("TITRELIVRE"), rs1.getString("SOUSTITRE"), rs1.getString("LANGUELIVRE"),
					rs1.getInt("NBPAGELIVRE"), rs1.getDate("DATEEDITIONLIVRE"), rs1.getString("IMAGELIVRE"),
					rs1.getString("RESUMELIVRE"), rs1.getFloat("PRIXLIVRE"), rs1.getFloat("POIDSLIVRE"));
			if (cOR.isEmpty()) {
			  cOR.add(l);
			} else {
			  for (Livre li : cOR) {
				if (l.getIsbn().equals(li.getIsbn())) {
				  existe = true;
				  break;
				} else {
				  existe = false;
				}
			  }
			  if (!existe) {
				cOR.add(l);
			  }
			}
		  }
		} catch (SQLException ex) {
		  System.err.println("Problème de lecture du resultat : " + ex.getMessage());
		}
		abd.fermerLienRequete(rs1);
	  }
	  System.out.println(cOR);

	  if (btit) {
		String query2 = abd.getGenSelect() + abd.getGenFrom() + abd.getGenWhere() + whereTit + abd.getOrderByLivres();
		ResultSet rs2;
		abd.ouvrirLienRequete();
		rs2 = abd.executerRequete(query2);
		System.out.println("2 " + query2);
		try {
		  while (rs2.next()) {
			ThemePrincipal tp = new ThemePrincipal(rs2.getString("LIBELLETHEME"), rs2.getString("CODETHEME"));
			Livre l = new Livre(rs2.getString("ISBN"), tp, new SousTheme(tp, rs2.getString("LIBELLESOUSTHEME"),
					rs2.getString("CODESOUSTHEME")), new Editeur(rs2.getString("NOMEDITEUR"), rs2.getInt("IDEDITEUR")),
					new Auteur(rs2.getString("PRENOMAUTEUR"), rs2.getString("NOMAUTEUR"), rs2.getInt("IDAUTEUR")),
					new Tva(rs2.getInt("IDTVA"), rs2.getString("LIBELLETVA"), rs2.getFloat("TAUXTVA")),
					rs2.getString("TITRELIVRE"), rs2.getString("SOUSTITRE"), rs2.getString("LANGUELIVRE"),
					rs2.getInt("NBPAGELIVRE"), rs2.getDate("DATEEDITIONLIVRE"), rs2.getString("IMAGELIVRE"),
					rs2.getString("RESUMELIVRE"), rs2.getFloat("PRIXLIVRE"), rs2.getFloat("POIDSLIVRE"));
			if (cOR.isEmpty()) {
			  cOR.add(l);
			} else {
			  for (Livre li : cOR) {
				if (l.getIsbn().equals(li.getIsbn())) {
				  existe = true;
				  break;
				} else {
				  existe = false;
				}
			  }
			  if (!existe) {
				cOR.add(l);
			  }
			}
		  }
		} catch (SQLException ex) {
		  System.err.println("Problème de lecture du resultat : " + ex.getMessage());
		}
		abd.fermerLienRequete(rs2);
	  }
	  System.out.println(cOR);

	  if (bisb) {
		String query3 = abd.getGenSelect() + abd.getGenFrom() + abd.getGenWhere() + whereIsb + abd.getOrderByLivres();
		ResultSet rs3;
		abd.ouvrirLienRequete();
		rs3 = abd.executerRequete(query3);
		System.out.println("3 " + query3);
		try {
		  while (rs3.next()) {
			ThemePrincipal tp = new ThemePrincipal(rs3.getString("LIBELLETHEME"), rs3.getString("CODETHEME"));
			Livre l = new Livre(rs3.getString("ISBN"), tp, new SousTheme(tp, rs3.getString("LIBELLESOUSTHEME"),
					rs3.getString("CODESOUSTHEME")), new Editeur(rs3.getString("NOMEDITEUR"), rs3.getInt("IDEDITEUR")),
					new Auteur(rs3.getString("PRENOMAUTEUR"), rs3.getString("NOMAUTEUR"), rs3.getInt("IDAUTEUR")),
					new Tva(rs3.getInt("IDTVA"), rs3.getString("LIBELLETVA"), rs3.getFloat("TAUXTVA")),
					rs3.getString("TITRELIVRE"), rs3.getString("SOUSTITRE"), rs3.getString("LANGUELIVRE"),
					rs3.getInt("NBPAGELIVRE"), rs3.getDate("DATEEDITIONLIVRE"), rs3.getString("IMAGELIVRE"),
					rs3.getString("RESUMELIVRE"), rs3.getFloat("PRIXLIVRE"), rs3.getFloat("POIDSLIVRE"));
			if (cOR.isEmpty()) {
			  cOR.add(l);
			} else {
			  for (Livre li : cOR) {
				if (l.getIsbn().equals(li.getIsbn())) {
				  existe = true;
				  break;
				} else {
				  existe = false;
				}
			  }
			  if (!existe) {
				cOR.add(l);
			  }
			}
		  }
		} catch (SQLException ex) {
		  System.err.println("Problème de lecture du resultat : " + ex.getMessage());
		}
		abd.fermerLienRequete(rs3);
	  }
	  System.out.println(cOR);

	  if (bmot) {
//		String query4 = genQueryAcMotCLe + queryMotCle + orderBy;
		String query4 = abd.getGenSelect() + abd.getGenFrom() + selectMot + abd.getGenWhere() + whereMot + abd.getOrderByLivres();
		ResultSet rs4;
		abd.ouvrirLienRequete();
		rs4 = abd.executerRequete(query4);
		System.out.println("4 " + query4);
		try {
		  while (rs4.next()) {
			ThemePrincipal tp = new ThemePrincipal(rs4.getString("LIBELLETHEME"), rs4.getString("CODETHEME"));
			Livre l = new Livre(rs4.getString("ISBN"), tp, new SousTheme(tp, rs4.getString("LIBELLESOUSTHEME"),
					rs4.getString("CODESOUSTHEME")), new Editeur(rs4.getString("NOMEDITEUR"), rs4.getInt("IDEDITEUR")),
					new Auteur(rs4.getString("PRENOMAUTEUR"), rs4.getString("NOMAUTEUR"), rs4.getInt("IDAUTEUR")),
					new Tva(rs4.getInt("IDTVA"), rs4.getString("LIBELLETVA"), rs4.getFloat("TAUXTVA")),
					rs4.getString("TITRELIVRE"), rs4.getString("SOUSTITRE"), rs4.getString("LANGUELIVRE"),
					rs4.getInt("NBPAGELIVRE"), rs4.getDate("DATEEDITIONLIVRE"), rs4.getString("IMAGELIVRE"),
					rs4.getString("RESUMELIVRE"), rs4.getFloat("PRIXLIVRE"), rs4.getFloat("POIDSLIVRE"));
			if (cOR.isEmpty()) {
			  cOR.add(l);
			} else {
			  for (Livre li : cOR) {
				if (l.getIsbn().equals(li.getIsbn())) {
				  existe = true;
				  break;
				} else {
				  existe = false;
				}
			  }
			  if (!existe) {
				cOR.add(l);
			  }
			}
		  }
		} catch (SQLException ex) {
		  System.err.println("Problème de lecture du resultat : " + ex.getMessage());
		}
		abd.fermerLienRequete(rs4);
	  }
	  System.out.println(cOR);

	  if (bedi) {
		String query5 = abd.getGenSelect() + abd.getGenFrom() + abd.getGenWhere() + whereEdi + abd.getOrderByLivres();
		ResultSet rs5;
		abd.ouvrirLienRequete();
		rs5 = abd.executerRequete(query5);
		System.out.println("5 " + query5);
		try {
		  while (rs5.next()) {
			ThemePrincipal tp = new ThemePrincipal(rs5.getString("LIBELLETHEME"), rs5.getString("CODETHEME"));
			Livre l = new Livre(rs5.getString("ISBN"), tp, new SousTheme(tp, rs5.getString("LIBELLESOUSTHEME"),
					rs5.getString("CODESOUSTHEME")), new Editeur(rs5.getString("NOMEDITEUR"), rs5.getInt("IDEDITEUR")),
					new Auteur(rs5.getString("PRENOMAUTEUR"), rs5.getString("NOMAUTEUR"), rs5.getInt("IDAUTEUR")),
					new Tva(rs5.getInt("IDTVA"), rs5.getString("LIBELLETVA"), rs5.getFloat("TAUXTVA")),
					rs5.getString("TITRELIVRE"), rs5.getString("SOUSTITRE"), rs5.getString("LANGUELIVRE"),
					rs5.getInt("NBPAGELIVRE"), rs5.getDate("DATEEDITIONLIVRE"), rs5.getString("IMAGELIVRE"),
					rs5.getString("RESUMELIVRE"), rs5.getFloat("PRIXLIVRE"), rs5.getFloat("POIDSLIVRE"));
			if (cOR.isEmpty()) {
			  cOR.add(l);
			} else {
			  for (Livre li : cOR) {
				if (l.getIsbn().equals(li.getIsbn())) {
				  existe = true;
				  break;
				} else {
				  existe = false;
				}
			  }
			  if (!existe) {
				cOR.add(l);
			  }
			}
		  }
		} catch (SQLException ex) {
		  System.err.println("Problème de lecture du resultat : " + ex.getMessage());
		}
		abd.fermerLienRequete(rs5);
	  }
	  System.out.println(cOR);

	  if (bedo) {
		String query6 = abd.getGenSelect() + abd.getGenFrom() + abd.getGenWhere() + whereEdo + abd.getOrderByLivres();
		ResultSet rs6;
		abd.ouvrirLienRequete();
		rs6 = abd.executerRequete(query6);
		System.out.println("6 " + query6);
		try {
		  while (rs6.next()) {
			ThemePrincipal tp = new ThemePrincipal(rs6.getString("LIBELLETHEME"), rs6.getString("CODETHEME"));
			Livre l = new Livre(rs6.getString("ISBN"), tp, new SousTheme(tp, rs6.getString("LIBELLESOUSTHEME"),
					rs6.getString("CODESOUSTHEME")), new Editeur(rs6.getString("NOMEDITEUR"), rs6.getInt("IDEDITEUR")),
					new Auteur(rs6.getString("PRENOMAUTEUR"), rs6.getString("NOMAUTEUR"), rs6.getInt("IDAUTEUR")),
					new Tva(rs6.getInt("IDTVA"), rs6.getString("LIBELLETVA"), rs6.getFloat("TAUXTVA")),
					rs6.getString("TITRELIVRE"), rs6.getString("SOUSTITRE"), rs6.getString("LANGUELIVRE"),
					rs6.getInt("NBPAGELIVRE"), rs6.getDate("DATEEDITIONLIVRE"), rs6.getString("IMAGELIVRE"),
					rs6.getString("RESUMELIVRE"), rs6.getFloat("PRIXLIVRE"), rs6.getFloat("POIDSLIVRE"));
			if (cOR.isEmpty()) {
			  cOR.add(l);
			} else {
			  for (Livre li : cOR) {
				if (l.getIsbn().equals(li.getIsbn())) {
				  existe = true;
				  break;
				} else {
				  existe = false;
				}
			  }
			  if (!existe) {
				cOR.add(l);
			  }
			}
		  }
		} catch (SQLException ex) {
		  System.err.println("Problème de lecture du resultat : " + ex.getMessage());
		}
		abd.fermerLienRequete(rs6);
	  }
	  System.out.println(cOR);

	  abd.fermerConnexionBase();
	}
	return cOR;
  }

  public static void setISBN(String ISBN) {
	BeanRecherche.ISBN = ISBN;
  }

  public static String getISBN() {
	return ISBN;
  }

//  public static String getUserComment() {
//	return userComment;
//  }
//
//  public static void setUserComment(String userComment) {
//	BeanRecherche.userComment = userComment;
//  }
//
//  public static String getNote() {
//	return note;
//  }
//
//  public static void setNote(String note) {
//	BeanRecherche.note = note;
//  }


}
