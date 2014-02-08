<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panier</title>
    </head>
    <body>
        <jsp:include page="/jspHeaderTemplate.jsp" flush="true"/>
        <jsp:include page="/jspArboCategories.jsp" flush="true"/>

        <div class="centrum">
            <h1>Panier !</h1><br>

            <c:forEach var="l" items="${listeLivre}">
                <form action="MasterController" method="POST">
                    ${l.livre.titreLivre}<br>
                    ${l.livre.sousTitre}<br>
                    ${l.livre.auteurLivre.nom}, ${l.livre.auteurLivre.prenom}<br>
                    *************************  <fmt:formatNumber value="${l.prixTtc}"  maxFractionDigits="2" /> €<br>
                    <input type="hidden" name="section" value="viePanier"/>
                    <input type="submit" name="add" value="+"/> ${l.qte}
                    <input type="hidden" name="addHid" value="${l.livre.isbn}" />
                    <input type="submit" name="dec" value="-"/>
                    <input type="hidden" name="decHid" value="${l.livre.isbn}" />
                    <input type="submit" name="del" value="X"/>
                    <input type="hidden" name="delHid" value="${l.livre.isbn}" />
                    <br>        
                </form>
            </c:forEach>
            ************************* <fmt:formatNumber value="${montantTotal}"  maxFractionDigits="2" /> €<br>

            <a href="MasterController?section=viePanier&clear=">Vider Panier</a>
            <a href="MasterController?section=validerPanier">Commander</a>
        </div>
    </body>
</html>