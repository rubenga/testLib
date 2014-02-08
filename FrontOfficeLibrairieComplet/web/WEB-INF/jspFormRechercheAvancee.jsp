<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recherche avancée</title>

    </head>
    <body>
        <jsp:include page="/jspHeaderTemplate.jsp" flush="true"/>
        <jsp:include page="/jspArboCategories.jsp" flush="true"/>

        <div class="centrum">
            <!--<form action="jspResultatRecherche.jsp" method="POST">-->
            <form action="MasterController?section=rechercheavancee" method="POST">		  
                <fieldset>
                    <legend>Recherchez votre ouvrage</legend>
                    <label for="auteur" style="display: block; width: 100px; float: left; text-align:right;">
                        Auteur :</label><input type="text" name="auteur" id="auteur"/><br>
                    <label for="titre" style="display: block; width: 100px; float: left; text-align:right;"> 
                        Titre :</label><input type="text" name="titre" id="titre"/><br>
                    <label for="ssTitre" style="display: block; width: 100px; float: left; text-align:right;"> 
                        Sous-Titre :</label><input type="text" name="soustitre" id="ssTitre"/><br>                    
                    <label for="mCle" style="display: block; width: 100px; float: left; text-align:right;">
                        Mot-clé :</label><input type="text" name="keyword" id="mCle"/><br>
                    <label for="isbn" style="display: block; width: 100px; float: left; text-align:right;">
                        ISBN :</label><input type="text" name="isbn" id="isbn"/><br>
                    <label for="editeur" style="display: block; width: 100px; float: left; text-align:right;">
                        Editeur :</label><input type="text" name="editeur" id="editeur"/><br>
                    <label for="dateEd" style="display: block; width: 100px; float: left; text-align:right;">
                        Date d'édition :</label><input type="text" name="edition" id="dateEd"/><br>

                    <label for="lang" style="display: block; width: 100px; float: left; text-align:right;">Langue :</label>
                    <select name="langue" id="lang">
                        <option value="langues">Toutes</option>
                    </select><br>
                    <input type="radio" name="typerecherche" value="and" checked>ET
                    <input type="radio" name="typerecherche" value="or">OU
                    <input type="submit" value="Valider" name="doItAdv" />
                </fieldset>
            </form>
        </div>

    </body>
</html>
