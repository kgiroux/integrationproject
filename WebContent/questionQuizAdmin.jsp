<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Question Quiz Admin</title>
<link href="Ressources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
<link href="Ressources/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type ="text/css">
<link href="Ressources/bootstrap/css/style.css" rel="stylesheet" type ="text/css">
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="Ressources/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<h1>Créer un nouveau quiz</h1>
	<hr>
<div class="container">
	<hr>
	<div class="form-group">
		<label for="libelle" class="col-xs-6 col-sm-2 control-label">Libellé :</label>
		<div class="col-xs-2 col-sm-4">
			<input type="text" class="form-control" name="libelle" id="libelleQuiz" placeholder="JEE,Android,etc">
		</div> 
		   
	</div>
<button  class="btn btn-primary"><span class ="glyphicon glyphicon-plus"> </span> Ajouter une Question</button>		    
<div class="form-center">
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th>Cocher</th>
						<th>Question</th>
						<th>Supprimer</th>
						<th>Editer</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="checkbox" name="check" id="checkbox"></td>
						<td>Question 1</td>
						<td><a href="#"><span class="glyphicon glyphicon-remove"></span></a></td>
						<td><a href="#"><span class="glyphicon glyphicon-edit"></span></a></td>
					</tr>
					<tr>
						<td><input type="checkbox" name="check" id="checkbox"></td>
						<td>Question 2</td>
						<td><a href="#"><span class="glyphicon glyphicon-remove"></span></a></td>
						<td><a href="#"><span class="glyphicon glyphicon-edit"></span></a></td>
					</tr>
					<tr>
						<td><input type="checkbox" name="check" id="checkbox"></td>
						<td>Question 3</td>
						<td><a href="#"><span class="glyphicon glyphicon-remove"></span></a></td>
						<td><a href="#"><span class="glyphicon glyphicon-edit"></span></a></td>
					</tr>
				</tbody>
			</table>
			<button class="btn btn-primary">Créer</button>
   
   <button class="btn btn-primary">Annuler</button>
   <hr>
  </div>
		  
</div>
</body>
</html>