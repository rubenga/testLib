<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="css/project-css.css">

<div id="header">
    <div id="bannerd">
        <a href="MasterController"><img src="banner.jpg" alt="banner" id="banner"></a>
    </div>  

    <div id="navi">
        <ul>
            <li style="display: inline-block;"><a href="MasterController?section=<%=session.getAttribute("userlogged") == "Déconnexion" ? "deconnect" : "login"%>"><%=session.getAttribute("userlogged") == "Déconnexion" ? "Déconnexion" : "Login"%></a></li>
            <li style="display: inline-block;"><a href="MasterController?section=panier">Panier</a></li>
            <li style="display: inline-block;"><a href="MasterController?section=moncompte">Mon compte</a></li>
        </ul>
    </div>
</div>


<!-- 
<table id = "login"  cellspacing="0" cellpadding="0" border="0" align="right">
        <tr>
                <td>
                        <a id="sign-on" href="/servlet/LoginDirector?cm_sp=TopNav-_-Home-_-Login">Ouvrir une session</a>
                </td>
                <td width="20px">

                </td>
                <td>
                        <a href="/servlet/MembersMenuPL?cm_sp=TopNav-_-Home-_-MMM">Votre compte</a>
                </td>
                <td width="20px">

                </td>

                <td>
                        <a  href="/servlet/ShopBasketPL?cm_sp=TopNav-_-Home-_-SB">Panier</a>
                </td>
                <td width="20px">

                </td>

                <td>
                        <a href="/aide-acheteur/?cm_sp=TopNav-_-Home-_-Help">Aide</a>
                </td>
        </tr>
</table>
-->