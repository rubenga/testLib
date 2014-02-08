<%@page import="java.text.SimpleDateFormat"%>
<%@page import="allClasses.Livre"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<title>Résultats</title>
<jsp:include page="/jspHeaderTemplate.jsp" flush="true"/>

<jsp:include page="/jspArboCategories.jsp" flush="true"/>

<div class="centrum">
  <% int i = 0;
	Collection<Livre> col = (Collection) request.getAttribute("collectionC");

	for (Livre liv : col) {%>
  <fieldset>
	<div class="result">
	  <%= ++i%>
	  <div class="image">
		<a href="?paramIsbn=<%=liv.getIsbn()%>"><img src="<%=liv.getImageLivre()%>" alt="<%=liv.getTitreLivre()%>_<%=liv.getIsbn()%>" height="150" width="100"></a>
	  </div>

	  <div class="data">
              <a href="?paramIsbn=<%=liv.getIsbn()%>"><%=liv.getTitreLivre()%></a>
              <a href="?paramIsbn=<%=liv.getIsbn()%>"><%=(liv.getSousTitre() == null) ? "" : liv.getSousTitre()%> </a><br>
              <%=liv.getAuteurLivre()%><br>
              <%=liv.getEditeurLivre()%><br>
		ISBN : <%=liv.getIsbn()%><br>
		<%  SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); %>
		<% String dat = dateFormat.format(liv.getDateEditionLIvre());%>
		Edition : <%=dat%>
	  </div>

	  <div class="resume">
		<%=liv.getResumeLivre().substring(0, 50)%><a href="?paramIsbn=<%=liv.getIsbn()%>">...</a>
	  </div>

	  <div class="sale">
		<%=liv.getPrixLivre() + (liv.getPrixLivre() * liv.getTvaLivre().getTxTva() / 100)%> EUR TTC<br>
		<%=liv.getPrixLivre()%> EUR HT
	  </div>            
	</div>
	<br>
  </fieldset>				
  <% }
  %>
</div>