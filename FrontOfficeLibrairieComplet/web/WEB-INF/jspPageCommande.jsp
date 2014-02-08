<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Commande</title>
        <!--<link rel="stylesheet" type="text/css" href="css.css" media="all"/>-->
    </head>
    <body>
        <jsp:include page="/jspHeaderTemplate.jsp" flush="true"/>

        <jsp:include page="/jspArboCategories.jsp" flush="true"/>


        <div class="centrum">
            <h1>Commande !</h1><br>
            <table border="1">
                <tr>
                    <td>
                        Description du Livre
                    </td>
                    <td>
                        Quantité
                    </td>
                    <td>
                        Prix total
                    </td>
                </tr>
                <c:forEach var="l" items="${listeLivre}">
                    <tr>
                        <td>
                            ${l.livre.titreLivre}<br>            
                            ${l.livre.sousTitre}<br>
                            ${l.livre.auteurLivre.nom}, ${l.livre.auteurLivre.prenom}
                        </td>
                        <td>
                            ${l.qte}
                        </td>
                        <td>
                            <fmt:formatNumber value="${l.prixTtc*l.qte}"  maxFractionDigits="2" /> €
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="2">TOTAL</td><td><fmt:formatNumber value="${montantTotal}"  maxFractionDigits="2" /> €</td>
                </tr>
            </table>
            <a href="MasterController?section=finCommande">Livraison</a>
            <a href="MasterController?section=viePanier">Retour Panier</a>
        </div>

    </body>
</html>
