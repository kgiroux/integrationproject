<!-- Projet d'intégration GSI IR -->
<!-- @uthor TIDJANI ENRIFATH -->
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>
 <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
	<jsp:include page="/header.jsp"></jsp:include>
<div class="container">
<div class="jumbotron" style="margin-top:50px">
	<h1 style="text-align:center">Bienvenue</h1>
	<hr>
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
<jsp:include page="/footer.jsp"></jsp:include>