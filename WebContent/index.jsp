<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Index</title>
<link href="Ressources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
<link href="Ressources/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type ="text/css">
<link href="Ressources/bootstrap/css/style.css" rel="stylesheet" type ="text/css">
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
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
</head>
<body>
<div class="container">
	<h1>Bienvenue</h1>
	<hr>
  <form class="form-horizontal" action="/ConnexionPersonne.do" method="post">
	  <div class="form-center">
		  <div class="form-group">
		    <label for="inputEmail3" class="col-xs-6 col-sm-4 control-label"><span class ="glyphicon glyphicon-user"></span> Adresse mail</label>
		    <div class="col-xs-6 col-sm-4">
		      <input type="email" class="form-control" name="mail" id="inputEmail3" placeholder="nom@domain.com" value="<c:out value=""/>" required>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputPassword3" class="col-xs-6 col-sm-4 control-label"><span class ="glyphicon glyphicon-lock"></span> Mot de passe</label>
		    <div class="col-xs-6 col-sm-4">
		      <input type="password" class="form-control" name="mdp" id="inputPassword3" placeholder="Password" value="<c:out value=""/>" required>
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
		      <button type="submit" class="btn btn-primary">Connexion </button> Ou
			  <a href ="inscription.jsp">Inscription</a>
		    </div>
		  </div>
	  </div>
</form>
</div>
</body>
</html>