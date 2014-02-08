<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <jsp:include page="/jspHeaderTemplate.jsp" flush="true"/>
        <jsp:include page="/jspArboCategories.jsp" flush="true"/>
        <div class="centrum">
            <h1 align='center'>LIBRAIRIE EN LIGNE</h1>
            <form method='POST' action='MasterController?section=login'>
                <table align="center">
                    <tr>
                        <td>
                            * Adresse e-mail : <input type='text' name='user' size="20" value="<%=request.getAttribute("login") == null ? "" : request.getAttribute("login")%>" >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            * Mot de passe : <input type="password" name="password" size="20">
                        </td>
                    </tr>
                </table>
                <table align="center">   
                    <tr>
                        <td>
                            <INPUT TYPE='SUBMIT' NAME='doIt' VALUE='Valider'/>
                        </td>
                        <td>
                            <INPUT TYPE='SUBMIT' NAME='Reset' VALUE='Erase'/>                        
                        </td>
                    </tr>
                </table>
            </form>
            <font color="red" style="text-align: center">
            <%=request.getAttribute("msg") == null ? "" : request.getAttribute("msg")%>
            </font>   
            <table align="center" border="0">           
                <tr>
                    <td>
                        <br />
                        <A HREF='MasterController?section=creer'>Créer un compte</A>  
                        <br />

                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>





