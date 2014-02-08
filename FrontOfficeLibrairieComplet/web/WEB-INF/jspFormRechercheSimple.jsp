<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
  <head>
	<link rel="stylesheet" href="css/project-css.css">
  </head>
  <body>
      <div class="centrum">
	  <form action="MasterController?section=recherchesimple" method="POST">
		<fieldset>
		  <legend>Recherchez votre ouvrage</legend>
		  <label for="auteur" style="display: block; width: 70px; float: left; text-align:right;">Auteur :</label><input type="text" name="auteur" id="auteur"/><br>
		  <label for="titre" style="display: block; width: 70px; float: left; text-align:right;"> Titre :</label><input type="text" name="titre" id="titre"/><br>
		  <label for="mCle" style="display: block; width: 70px; float: left; text-align:right;">Mot-clé :</label><input type="text" name="keyword" id="mCle"/><br>
		  <label for="isbn" style="display: block; width: 70px; float: left; text-align:right;">ISBN :</label><input type="text" name="isbn" id="isbn"/><br>
		  <input type="submit" value="Valider" name="doIt"/>
		  <!--<a href="jspFormRechercheAvancee.jsp">Recherche avancée</a>-->
		  <a href="MasterController?adv=y">Recherche avancée</a><br>
		  <input type="radio" name="typerecherche" value="and" checked>ET
		  <input type="radio" name="typerecherche" value="or">OU<br>
		</fieldset>
	  </form>
      </div>

  </body>
</html>
