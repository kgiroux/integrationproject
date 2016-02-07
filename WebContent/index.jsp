<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>
 <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
 <!DOCTYPE html>
<html>
<head>
<jsp:include page="header.jsp" />

</head>
<body style="background-color: #03A9F4;">
<div class="container">
  <div class="row" style="margin: 50px 0;">
    <div class="col-xs-12 col-md-6 col-md-offset-3 text-center">
      <img src="<%=request.getContextPath() %>/Ressources/images/quiz-gsi.png" alt="Quiz GSI logo" class="img-rounded img-responsive">
    </div>
  </div>
  <div class="jumbotron" style="background-color: rgba(255, 255, 255, .9); box-shadow: 2px 2px 5px black;">
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
		     <button type="submit" class="btn" style="background-color: #FF4081; color: #FFF">Connexion </button> ou
			  <a href ="<%=request.getContextPath()%>/VueInscription.do">Inscription</a>
		    </div>
		  </div>
	  </div>
    </form>
  </div>
</div>

  <footer>
    <div class="container">
      <hr>
      <p class="text-center" style="color: white;"> Projet d'intégration, GSI-IR &#169; 2016 ESIGELEC</p>
    </div>
  </footer>
  </body>
</html>