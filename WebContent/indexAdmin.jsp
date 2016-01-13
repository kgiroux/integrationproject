<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index Admin</title>
<link href="Ressources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
<link href="Ressources/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type ="text/css">
<link href="Ressources/bootstrap/css/style.css" rel="stylesheet" type ="text/css">
<link href="Ressources/fonts/font-awesome.min.css" rel="stylesheet" type ="text/css">
<script src="Ressources/Jquery/jquery.min.js"></script>
<script src="Ressources/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<h1>Administration</h1>
	<hr>
  <form class="form-horizontal" action="ConnexionAdministrateur" method="post">
	  <div class="form-center">
		  <div class="form-group">
		    <label for="inputEmail3" class="col-xs-6 col-sm-4 control-label"><span class ="glyphicon glyphicon-user"></span> Adresse mail</label>
		    <div class="col-xs-6 col-sm-4">
		      <input type="email" class="form-control" name="mail" id="inputEmail3" placeholder="nom@domain.com">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputPassword3" class="col-xs-6 col-sm-4 control-label"><span class ="glyphicon glyphicon-lock"></span> Mot de passe</label>
		    <div class="col-xs-6 col-sm-4">
		      <input type="password" class="form-control" name="mdp" id="inputPassword3" placeholder="Password">
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-xs-6">
		      <button type="submit" class="btn btn-primary">Connexion</button>
			</div>
		  </div>
	  </div>
</form>
</div>
</body>
</html>