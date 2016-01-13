<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<!DOCTYPE html>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><bean:message key="erreur.globale.titre" /></title>
<link href="Ressources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
<link href="Ressources/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type ="text/css">
<link href="Ressources/bootstrap/css/style.css" rel="stylesheet" type ="text/css">
<link href="Ressources/fonts/font-awesome.min.css" rel="stylesheet" type ="text/css">
<script src="Ressources/Jquery/jquery.min.js"></script>
<script src="Ressources/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" style="text-align:center; padding:50px;">
<div>
<img src="ressources/images/warn.png" alt="warning" title="avertissement" />
</div>
<div >
<h1><bean:message key="erreur.globale.title"/></h1>
<p><bean:message key="erreur.globale.message"/> click <a href="#">here</a> if you want to go back to index</p>
</div>
</div>

</body>
</html:html>
