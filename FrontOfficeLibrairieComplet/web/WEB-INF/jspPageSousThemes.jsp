<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Collection"%>
<%@page import="allClasses.SousTheme"%>
<jsp:useBean id="MasterBean" scope="application" class="allBeans.BeanRecherche"/>
<jsp:include page="/jspHeaderTemplate.jsp" flush="true"/>

<jsp:include page="/jspArboCategories.jsp" flush="true"/>

<title>Sous-catégories</title>

<div class="centrum">
  <% Collection<SousTheme> col = (Collection) request.getAttribute("themeC");%>

  <%for (SousTheme st : col) {%>
  <ul>
    <li><a href="MasterController?livresparcat=<%=st.getCode()%>"><%= st.getNomTheme()%></a></li>
  </ul>
  <%}%>
</div>