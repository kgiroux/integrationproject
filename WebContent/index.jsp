<!-- Projet d'intégration GSI IR -->
<!-- @uthor TIDJANI ENRIFATH -->
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>
 <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quiz Game</title>
<link href="Ressources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
<link href="Ressources/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type ="text/css">
<!-- <link href="Ressources/bootstrap/css/style.css" rel="stylesheet" type ="text/css"> -->
<link href="Ressources/fonts/font-awesome.min.css" rel="stylesheet" type ="text/css">
<script src="Ressources/Jquery/jquery.min.js"></script>
<script src="Ressources/bootstrap/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #1976D2;">
<div class="container">
  <div class="row" style="margin: 50px 0;">
    <div class="col-xs-12 col-md-6 col-md-offset-3 text-center">
      <img src="<%=request.getContextPath() %>/Ressources/images/quiz-gsi.png" alt="Quiz GSI logo" class="img-rounded img-responsive">
    </div>
  </div>
  <div class="jumbotron" style="background-color: rgba(255, 255, 255, .5); box-shadow: 2px 2px 5px black;">
	<!-- <h1 style="text-align:center">Bienvenue</h1>

	<hr> -->
  <form id="loginForm" class="form-horizontal" method="post" action="<%=request.getContextPath()%>/ConnexionPersonne.do">
	  <div class="form-center">
		  <div class="form-group">
		    <label for="inputEmail3" class="col-sm-4 control-label"><span class ="glyphicon glyphicon-user"></span> Adresse mail</label>
		    <div class="col-sm-6 col-lg-5">
		    <input type="email" value="j@j.j" class="form-control " name="mail" id="inputEmail3" placeholder="nom@domain.com">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputPassword3" class="col-sm-4 control-label"><span class ="glyphicon glyphicon-lock"></span> Mot de passe</label>
		    <div class="col-sm-6 col-lg-5">
		      <input type="password" value="j" class="form-control" name="password" id="inputPassword3" placeholder="Password">
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-10 col-lg-8">
		      <div class="checkbox">
		        <label>
		          <input type="checkbox"> Remember me
		        </label>
		      </div>
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-10 col-lg-8">
		     <button type="submit" class="btn btn-primary">Connexion </button> ou
			  <a href ="<%=request.getContextPath()%>/VueInscription.do">Inscription</a>
		    </div>
		  </div>
	  </div>
    </form>
  </div>
</div>
</body>
  <footer>
    <div class="container">
      <hr>
      <p class="text-center" style="color: white;"> Projet d'intégration, GSI-IR &#169; 2016 ESIGELEC</p>
    </div>
  </footer>
</html>