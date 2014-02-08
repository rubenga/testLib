<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Compte créé</title>
  </head>
  <body>
	<jsp:include page="/jspHeaderTemplate.jsp" flush="true"/>

	<jsp:include page="/jspArboCategories.jsp" flush="true"/>

        <div class="centrum">
	  <h1 align="center">BIENVENUE CHEZ EAST WEST BOOK SHOP -- VOTRE COMPTE EST BIEN CREE</h1>
	  <table align="center" border='1'> 
		<tr>
		  <td>
			<br /> 
			Bonjour <%=request.getAttribute("creer")%>! 
		  </td>
		</tr>
		<tr>
		  <td>
			<A HREF="MasterController?JspAccueil.jsp"> Acceuil</A>    
		  </td>
		</tr>
		<tr>
		  <td>
			<A HREF="MasterController?section=moncompte">Consultation Votre Compte </A>     
		  </td>
		</tr>
	  </table>
	</div>
  </body>
</html>













