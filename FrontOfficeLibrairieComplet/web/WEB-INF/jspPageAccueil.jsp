<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Librairie</title>
	<link rel="stylesheet" href="css/project-css.css">
  </head>
  <body>

	<jsp:include page="/jspHeaderTemplate.jsp" flush="true"/>
	<jsp:include page="/jspArboCategories.jsp" flush="true"/>

	<div class="centrum">
	  <jsp:include page="/MasterController?section=recherchesimple" flush="true"/>
	  <jsp:include page="/jspListeEvenements.jsp" flush="true"/>

	</div>


  </body>
</html>
