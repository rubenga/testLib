<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Collection"%>
<%@page import="allClasses.Evenement"%>
<jsp:useBean id="BeanRecherche" scope="application" class="allBeans.BeanRecherche"/>


<% Collection<Evenement> col = (Collection) BeanRecherche.collectionEvenements();%>
<div id="arbo">
    <ul>
        <%for (Evenement e : col) {%>
        <li>-<%=e.getTauxReduction()%>% sur notre collection <a href="MasterController?eventid=<%=e.getIdEvenement()%>"><%=e.getNomEvenement()%></a></li>
            <%}%>
    </ul> 
</div>