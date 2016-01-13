<!-- Projet d'intégration GSI IR -->
<!-- @uthor TIDJANI ENRIFATH -->
<%@ page language="java" contentType="text/html; charset=utf-8"
<<<<<<< HEAD
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
=======
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
>>>>>>> 82ef3e6b56b4471f9df4b1eecfc727178647d143
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index</title>
<link href="Ressources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
<link href="Ressources/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type ="text/css">
<link href="Ressources/bootstrap/css/style.css" rel="stylesheet" type ="text/css">
<<<<<<< HEAD
<script type="text/javascript" src="Ressources/js/jquery-1.12.0.min.js"></script>
<script src="Ressources/bootstrap/js/bootstrap.min.js"></script>
<script src="Ressources/js/formValidation.min.js"></script>
<script src="Ressources/js/bootstrap.min.js"></script>
<script src="Ressources/js/script.js"></script>
=======
<link href="Ressources/fonts/font-awesome.min.css" rel="stylesheet" type ="text/css">
<script src="Ressources/Jquery/jquery.min.js"></script>
<script src="Ressources/bootstrap/js/bootstrap.min.js"></script>
<script>
$(document).ready(function() {
    $('#InscriptionForm').formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            mail: {
                validators: {
                    notEmpty: {
                        message: 'le champ email ne doit pas être vide'
                    },
                    emailAddress: {
                        message: 'votre email n est pas valide '
                    }
                }
            },
            mdp: {
                validators: {
                    notEmpty: {
                        message: 'le mot de passe est obligatoire'
                    }
                }
            }
        }
    });
});
</script>
>>>>>>> 82ef3e6b56b4471f9df4b1eecfc727178647d143
</head>
<body>
<div class="container">
	<h1>Bienvenue</h1>
	<hr>
<<<<<<< HEAD
  <form id="loginForm" class="form-horizontal" method="post" action="<%=request.getContextPath()%>/ConnexionPersonne.do">
=======
  <form class="form-horizontal" action="<%=request.getContextPath()%>/ConnexionPersonne.do" method="post">
>>>>>>> 82ef3e6b56b4471f9df4b1eecfc727178647d143
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
		  <span class="col-xs-6 col-sm-4"></span>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-xs-6">
		      <div class="checkbox">
		        <label>
		          <input type="checkbox"> Remember me
		        </label>
		      </div>
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-xs-6">
<<<<<<< HEAD
		      <button type="submit" class="btn btn-primary" name="signup">Connexion </button> Ou
			  <a href ="inscription.jsp">Inscription</a>
=======
		      <button type="submit" class="btn btn-primary">Connexion </button> ou
			  <a href ="<%=request.getContextPath()%>/VueInscription.do">Inscription</a>
>>>>>>> 82ef3e6b56b4471f9df4b1eecfc727178647d143
		    </div>
		  </div>
	  </div>
</form>
</div>
</body>
</html>