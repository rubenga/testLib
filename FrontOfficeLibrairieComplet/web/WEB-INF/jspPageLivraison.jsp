<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Commande</title>
        <!-- <link rel="stylesheet" type="text/css" href="css.css" media="all"/>-->
    </head>
    <body>
        <jsp:include page="/jspHeaderTemplate.jsp" flush="true"/>
        <h1>Finaliser la commande</h1><br>
        <form method="POST" action="MasterController">            
            <input type="hidden" name="section" value="paiementCommande"/>
            <table>
                <tr>
                    <td>
                        Adresse Facturation<br><br>
                        <c:if test="${!empty listeAdresse}" var="test" scope="session">
                            <c:forEach var="adr" items="${listeAdresse}">
                                <input type="radio" name="adrFact" value="${adr.identifiant}" checked>
                                ${adr.nom}<br>
                                ${adr.rue}<br>
                                ${adr.codePostal}, ${adr.ville}<br>
                                ${adr.pays}<br>
                            </c:forEach>
                            <br>
                        </c:if>
                    </td>
                    <td>
                        Adresse Livraison<br><br>
                        <c:if test="${!empty listeAdresse}" var="test" scope="session">
                            <c:forEach var="adr" items="${listeAdresse}">
                                <input type="radio" name="adrLiv" value="${adr.identifiant}" checked>
                                ${adr.nom}<br>
                                ${adr.rue}<br>
                                ${adr.codePostal}, ${adr.ville}<br>
                                ${adr.pays}<br>
                            </c:forEach>
                            <br>
                        </c:if>
                    </td>
                </tr>
            </table>
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
                <c:forEach var="lc" items="${maCommande.articleCommande}">
                    <tr>
                        <td>
                            ${lc.liv.titreLivre}<br>            
                            ${lc.liv.sousTitre}<br>
                            ${lc.liv.auteurLivre.nom}, ${lc.liv.auteurLivre.prenom}
                        </td>
                        <td>
                            ${lc.quantite}
                        </td>
                        <td>
                            <fmt:formatNumber value="${lc.prixHt*(1+(lc.tvaCommande/100))}"  maxFractionDigits="2" /> €
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="2">TOTAL</td><td><fmt:formatNumber value="${montantTotal}"  maxFractionDigits="2" /> €</td>
                </tr>
            </table>

            <jsp:include page="jspFormOptionLivraison.jsp" flush="true"/>
            <fieldset>
                <legend>Moyen de paiements :</legend>
                <c:forEach var="moyenPaiement" items="${mesPaiements}">
                    <c:if test="${moyenPaiement.code}=='CB'" var="test" scope="session">
                        <input type="radio" name="typepaiement" value="${moyenPaiement.code}" checked>${moyenPaiement.libelle}<br>
                    </c:if>
                </c:forEach>
                Numéro carte : <input type="text" name="numCard"><br>
                Date d'expiration : <select name="mois">
                    <c:forEach var="i" begin="1" end="12" step="1">
                        <option value="${i}">${i}                            
                        </c:forEach>
                </select>
                <select name="annee">
                    <c:forEach var="i" begin="2014" end="2060" step="1">
                        <option value="${i}">${i}                            
                        </c:forEach>
                </select><br>
                Cryptogramme : <input type="text" name="crypto">
            </fieldset>
            <input type="submit" name="validCommande" value="Payer"/>
        </form>
        <a href="MasterController?section=panier">Retour Panier</a>
    </div>
</body>
</html>
