<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mon compte</title>
    </head>
    <body>
        <jsp:include page="/jspHeaderTemplate.jsp" flush="true"/>
        <jsp:include page="/jspArboCategories.jsp" flush="true"/>

        <div class="centrum">
            <table align="center" border='1'> 
                <tr>
                    <td>
                        partie de commande

                    </td>
                </tr>
                <tr>
                    <td>
                        <%=request.getAttribute("creer")%>! 
                    </td>
                </tr>

            </table>
            <table align="center" border='1'>         
                <tr>
                    <td>
                        Nom : 
                    </td>
                    <td>
                        <%=request.getAttribute("compteNom")%>
                    </td>
                </tr>
                <tr>
                    <td>
                        Prénom : 
                    </td>
                    <td>
                        <%=request.getAttribute("comptePrenom")%>
                    </td>
                </tr>
                <tr>
                    <td>
                        Téléphone : 
                    </td>
                    <td>
                        <%=request.getAttribute("compteTel")%>
                    </td>
                </tr>
                <tr>
                    <td>
                        Email : 
                    </td>
                    <td>
                        <%=request.getAttribute("compteEml")%>
                    </td>
                </tr>
       <tr>
            <%if (request.getAttribute("compteRais") != null) {%> 
            <td>
                RaisonSociale : 
            </td>

            <td>
                <%=request.getAttribute("compteRais") == null ? "" : request.getAttribute("compteRais")%>
            </td>
            <%}%>  
        </tr>

            </table>

        </div>

    </body>
</html>