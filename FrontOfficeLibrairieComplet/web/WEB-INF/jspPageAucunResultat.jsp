<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Aucun résultat</title>
    </head>
    <body>
        <jsp:include page="/jspHeaderTemplate.jsp" flush="true"/>
        <jsp:include page="/jspArboCategories.jsp" flush="true"/>

        <div class="centrum">
            Aucun résultat<br>

            <a href ="MasterController">Revenir à l'accueil</a>
        </div>
    </body>
</html>