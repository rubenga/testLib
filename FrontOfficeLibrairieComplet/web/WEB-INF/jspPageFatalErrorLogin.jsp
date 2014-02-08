<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Erreur</title>
    </head>
    <body>

        <jsp:include page="/jspHeaderTemplate.jsp" flush="true"/>
        <jsp:include page="/jspArboCategories.jsp" flush="true"/>
        <div class="centrum">
            <h1>Fatal Error!</h1>
            <%=request.getAttribute("fatalerror")%>
        </div>

    </body>
</html>











