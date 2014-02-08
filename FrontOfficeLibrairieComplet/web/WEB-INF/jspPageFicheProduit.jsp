<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Fiche produit</title>
	<link rel="stylesheet" href="css/project-css.css">
  </head>
  <body>

	<jsp:include page="/jspHeaderTemplate.jsp" flush="true"/>
	<jsp:include page="/jspArboCategories.jsp" flush="true"/>

	<div class="centrum">

	  <div id="image">
		<img id="coverImage"  alt="${livre.getTitreLivre()}_${livre.getIsbn()}" src="${livre.getImageLivre()}">
	  </div>

	  <div id="data">
		<table>
		  <tr><td >Titre : </td><td><strong>${livre.getTitreLivre()}</strong></td></tr>
		  <tr><td >Sous-Titre : </td><td>${livre.getSousTitre()}</td></tr>
		  <tr><td >ISBN :</td><td>${livre.getIsbn()}</td></tr>		  
		  <tr><td >Auteur : </td><td>${livre.getAuteurLivre()}</td></tr>
		  <tr><td >Thématique : </td><td>${livre.getThemeLivre()}</td></tr>
		  <tr><td >Sous-thème : </td><td>${livre.getSousThemeLivre()}</td></tr>
		  <tr><td >Publication : </td><td>${livre.getDateEditionLIvre()}</td></tr>
		  <tr><td >Editeur : </td><td>${livre.getEditeurLivre()}</td></tr>                                              
		  <tr><td >Langue : </td><td>${livre.getLangueLivre()}</td></tr>    
		  <tr><td >Nombre de pages  : </td><td>${livre.getNombrePageLivre()}</td></tr>                                                
		  <tr><td >Poids : </td><td>${livre.getPoidsLivre()} g</td></tr>
		  <tr><td >Tva : </td><td>${livre.getTvaLivre()}</td></tr>  
		</table>                 
	  </div>

	  <div id="sale">
		<strong><fmt:formatNumber value="${prixlivre}"  maxFractionDigits="2" /> EUR TTC</strong><br>
		<a href="MasterController?section=viePanier&add=''&addHid=${livre.getIsbn()}"><img alt="ajout_panier" src="panier.jpg"></a>
	  </div>  

	  <div id="resume">
		${livre.getResumeLivre()}
	  </div>    

	  <div>
		<fieldset>
		  <legend>Commentaires :</legend>
		  
			<c:forEach var="com" items="${commentaires}">
			  <strong>
			  Note : ${com.getNote()} sur 5<br>
			  </strong>
			  <em>
			  ${com.getReview()}<br>
			  </em>
			</c:forEach>
		  
		</fieldset>
	  </div>
	  <div>
		<jsp:include page="jspFormCommentaire.jsp" flush="true"/>
	  </div>
	</div>

  </body>
</html>