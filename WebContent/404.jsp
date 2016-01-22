<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
    <%@ page import="fr.esigelec.quiz.dto.*,java.util.*" %>
	<jsp:include page='/header.jsp'></jsp:include>

<!DOCTYPE html>
<html>

<body>
<%

	String erreur404 = request.getRequestURI();
	String previousPage = request.getHeader("REFERER");
%>
<div class="container" >

<%-- Début de la partie Erreurs 404 --%>
<div style="display:inline-block; margin-right:40px; margin-top:50px; margin-bottom:50px;vertical-align:top;">
<img src="Ressources/images/warn.png" alt="image_warning" title="page not found">
</div>
<div style="display:inline-block; margin-top:50px; margin-bottom:50px;">
<h1 style="color:red;">Error 404 - Page not found</h1>
<p>Please, click <a href="<%=previousPage %>">here</a> if you want to access to previous page</p>

</div>

<%-- Fin de la partie Erreurs 404 --%>
</div>

</body>
</html>