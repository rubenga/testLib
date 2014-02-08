<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Logged</title>
  </head>
  <body>
	<jsp:include page="/jspHeaderTemplate.jsp" flush="true"/>  
	<jsp:include page="/jspArboCategories.jsp" flush="true"/>
        <div class="centrum">
	  <h1 align="center">BIENVENUE CHEZ EAST WEST BOOK SHOP</h1>
	  <table align="center"> 
		<tr>
		  <td>
			<br /> 
			Bonjour <%=request.getAttribute("login")%>!
		  </td>
		</tr>

		<tr>
		  <td>
			<A HREF="MasterController?section=moncompte">Consultation Votre Compte </A>     
		  </td>
		</tr>

		<tr>
		  <td>
			<br /> <br />                    
			<A HREF="MasterController?LoginForm.jsp"> Pour quitter veuillez suivre ce lien : Deconnexion</A> 
		  </td>

		</tr>
	  </table>
	</div>
  </body>
</html>













