<%@page import="allBeans.BeanRecherche"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<fieldset>
  <legend>Evaluez cet article :</legend>
  <form action="MasterController?section=comment" method="POST">
	<textarea name="usercomment" rows="5" cols="20"><%= (session.getAttribute("comment") != null) ? session.getAttribute("comment") : "Exprimez-vous"%> </textarea>
	<select name="note" size="1">
	  <option>5
	  <option>4
	  <option>3
	  <option>2
	  <option>1
	  <option>0
	</select>	  
	<input type="submit" value="Valider" name="doItC"/>
  </form>
</fieldset>