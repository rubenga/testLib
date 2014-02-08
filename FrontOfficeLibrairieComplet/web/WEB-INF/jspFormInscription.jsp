<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inscription</title>
    </head>
    <body>
        <jsp:include page="/jspHeaderTemplate.jsp" flush="true"/>
        <jsp:include page="/jspArboCategories.jsp" flush="true"/>
        <div class="centrum">
            <center><h1><b>Créer Votre Compte Librairie</b></h1></center>
            <form method='POST' action='MasterController?section=creer'>    
                <table  border="0" cellpadding="0" cellspacing="0" align="center">
                    <tr>
                        <td>
                            Nom :
                        </td>
                        <td>
                            <input type="text" name="NomCli" value="<%=request.getAttribute("creer") == null ? "" : request.getAttribute("creer")%>" size="20">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Prénom : 
                        </td>
                        <td>
                            <input type="text" name="PrenomCli" size="20" value="${param.PrenomCli}">
                        </td>
                    </tr>           
                    <tr>
                        <td>
                            Mot de Passe <span style="color: red">*</span>:
                        </td>
                        <td>
                            <input type="password" name="MdpCli" size="20"><span class="erreur">${errors['MdpCli']}</span>
                        </td>
                    </tr>                      
                    <tr>
                        <td>
                            Téléphone :
                        </td>
                        <td>
                            <input type="text" name="TelCli" size="20" value="${param.TelCli}"> <span class="erreur">${errors['TelCli']}</span>
                        </td>
                    </tr>           
                    <tr>
                        <td>
                            E-mail <span style="color: red">*</span>:
                        </td>
                        <td>
                            <input type="email" name="EmlCli" value="${param.EmlCli}"/> <span class="erreur">${errors['EmlCli']}</span>
                        </td>
                    </tr>    
                    <tr>
                        <td>
                            Siret :
                        </td>
                        <td>
                            <input type="text" name="Siret" size="20" value="${param.Siret}"> 
                        </td>
                    </tr>    
                    <tr>
                        <td>
                            Raison Sociale :
                        </td>
                        <td>
                            <input type="text" name="RaisonSociale" size="20" value="${param.RaisonSociale}">
                        </td>
                    </tr>    
                    <tr>
                        <td>
                            Forme Sociale :
                        </td>
                        <td>
                            <input type="text" name="FormeSociale" size="20">
                        </td>
                    </tr>    
                </table>
                <table  border="0" cellpadding="0" cellspacing="0" align="center">
                    <tr>
                        <td><br/></td>
                    </tr>
                    <tr>
                        <td>
                            <INPUT TYPE='SUBMIT' NAME='valid' VALUE='Valider'/>
                        </td>
                        <td width="20px">
                        </td>
                        <td>
                            <INPUT TYPE='SUBMIT' NAME='Refresh' VALUE='Effacer'/>                        
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
