<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Inscription</title>
<link href="Ressources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
<link href="Ressources/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type ="text/css">
<link href="Ressources/bootstrap/css/style.css" rel="stylesheet" type ="text/css">
<script src="Ressources/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container">
	<h1>Inscription</h1>
	<hr>
  <form id="inscriptionForm" class="form-horizontal" method="post" action="/Inscrire.do" >
	  <div class="form-center">
		  <div class="form-group">
		    <label for="inputEmail3" class="col-xs-6 col-sm-4 control-label"><span class ="glyphicon glyphicon-user"></span> Adresse mail</label>
		    <div class="col-xs-6 col-sm-4">
		      <input type="email" class="form-control" name="mail" placeholder="nom@domain.com" value="<c:out value=""/>">
		    </div>
		    <span class="col-xs-6 col-sm-4"></span>
		  </div>
		  <div class="form-group">
		    <label for="inputPassword3" class="col-xs-6 col-sm-4 control-label"><span class ="glyphicon glyphicon-lock"></span> Mot de passe</label>
		    <div class="col-xs-6 col-sm-4">
		      <input type="password" class="form-control" name="mdp" placeholder="Password" value="<c:out value=""/>">
		    </div>
		  </div>
		  <div class="form-group">
		  	<label for="inputPassword3" class="col-xs-6 col-sm-4 control-label"></label>
		    <div class="col-xs-6 col-sm-4">
		      <input type="password" class="form-control" name="reMdp" placeholder="Re-Password" value="<c:out value=""/>">
		    </div>
		     <span class="col-xs-6 col-sm-4"></span>
		  </div>
		  <div class="form-group">
		    <label for="nom" class="col-xs-6 col-sm-4 control-label">Nom</label>
		    <div class="col-xs-6 col-sm-4">
		      <input type="text" class="form-control" name="nom" placeholder="Nom" value="<c:out value=""/>">
		    </div>
		     <span class="col-xs-6 col-sm-4"></span>
		  </div>
		  <div class="form-group">
		    <label for="prenom" class="col-xs-6 col-sm-4 control-label">Prenom</label>
		    <div class="col-xs-6 col-sm-4">
		      <input type="text" class="form-control" name="prenom" placeholder="Prenom" value="<c:out value=""/>">
		    </div>
		     <span class="col-xs-6 col-sm-4"></span>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-xs-6">
		      <button type="submit" class="btn btn-primary">S'inscrire</button>
		      <button type="Reset" class="btn btn-primary">Annuler</button>
		    </div>
		  </div>
	  </div>
</form>
<script>
$(document).ready(function() {
    $('#inscriptionForm').formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            nom: {
                row: '.col-xs-6 col-sm-4',
                validators: {
                    notEmpty: {
                        message: 'le nom est obligatoire'
                    },
			        stringLength: {
			            min: 2,
			            max: 30,
			            message: 'le nom doit être compris entre 2 et 30 caractères'
			        },
			        regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: 'le nom ne doit pas contenir des  chiffres, points et des underscore'
                    }

                }
            },
            prenom: {
                row: '.col-xs-6 col-sm-4',
                validators: {
                    notEmpty: {
                        message: 'le prénom est obligatoire'
                    },
                    stringLength: {
                        min: 2,
                        max: 30,
                        message: 'le prénom doit être compris entre 2 et 30 caractères'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: 'le prénom ne doit pas contenir des  chiffres, points et des underscore et $'
                    }
                }
            },
            mail: {
            	row: '.col-xs-6 col-sm-4',
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
            	row: '.col-xs-6 col-sm-4',
                validators: {
                    notEmpty: {
                        message: 'le mot de passe est obligatoire'
                    },
                    different: {
                        field: 'nom',
                        message: 'le mot de passe doit être différent du nom'
                    },
                    different: {
                        field: 'prenom',
                        message: 'le mot de passe doit être différent du prenom'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: ''
                    }
                }
            },
            reMdp: {
            	row: '.col-xs-6 col-sm-4',
                validators: {
                    notEmpty: {
                        message: 'le mot de passe est obligatoire'
                    },
                    identical: {
                        field: 'mdp',
                        message: 'les mots de passe doivent être identiques'
                    }
                }
            }
            
        }
    });
});
</script>
</div>
</body>
</html>