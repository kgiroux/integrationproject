<!-- Projet d'intégration GSI IR -->
<!-- @uthor TIDJANI ENRIFATH -->
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>
 <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<jsp:include page="/header.jsp"></jsp:include>
<div class="container">
	<h1 >Bienvenue</h1>
	<hr>
  <form id="loginForm" class="form-horizontal" method="post" action="<%=request.getContextPath()%>/ConnexionPersonne.do">
	  <div class="form-center">
		  <div class="form-group">
		    <label for="inputEmail3" class="col-xs-6 col-sm-4 control-label"><span class ="glyphicon glyphicon-user"></span> Adresse mail</label>
		    <div class="col-xs-6 col-sm-4">
		      <input type="email" value="a@a.a" class="form-control" name="mail" id="inputEmail3" placeholder="nom@domain.com">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputPassword3" class="col-xs-6 col-sm-4 control-label"><span class ="glyphicon glyphicon-lock"></span> Mot de passe</label>
		    <div class="col-xs-6 col-sm-4">
		      <input type="password" value="a" class="form-control" name="password" id="inputPassword3" placeholder="Password">
		    </div>
		  </div>
		  <span class="col-xs-6 col-sm-4"></span>
		  <div class="form-group">
		    <div class="col-xs-6 col-sm-4">
		      <div class="checkbox">
		        <label>
		          <input type="checkbox"> Remember me
		        </label>
		      </div>
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-3 col-xs-6">
		     <button type="submit" class="btn btn-primary">Connexion </button> ou
			  <a href ="<%=request.getContextPath()%>/VueInscription.do">Inscription</a>
		    </div>
		  </div>
	  </div>
</form>
</div>
<jsp:include page="/footer.jsp"></jsp:include>