<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div>
    <fieldset>
        <legend>Informations de livraison :</legend>
        <c:forEach var="livraison" items="${mesLivraisons}">
            <input type="radio" name="typetransport" value="${livraison.identifiant}" checked>${livraison.libelle} / ${livraison.tarif} €<br>
       </c:forEach>
            <br>Détails de livraison<br>
            <textarea name="usercomment" rows="5" cols="20"></textarea>
    </fieldset>
</div>