
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Collection"%>
<%@page import="allClasses.ThemePrincipal"%>
<jsp:useBean id="BeanRecherche" scope="application" class="allBeans.BeanRecherche"/>
<link rel="stylesheet" href="css/project-css.css">
<div class="cat">
    <% Collection<ThemePrincipal> col = (Collection) BeanRecherche.collectionArboTheme();%>
    <ul>
        <%for (ThemePrincipal t : col) {%>
        <li><a href="MasterController?souscat=<%=t.getCode()%>"><%= t.getNomTheme()%></a></li>
            <%}%>
    </ul> 
</div>