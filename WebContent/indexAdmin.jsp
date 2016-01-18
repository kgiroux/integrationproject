<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<jsp:include page="/header.jsp"></jsp:include>
<div class="container">
	<h1>Administration</h1>
	<hr>
  <form class="form-horizontal" action="ConnexionAdministrateur.do" method="post">
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
<jsp:include page="/footer.jsp"></jsp:include>