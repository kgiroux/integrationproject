<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrer quiz</title>
<link href="Ressources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
<link href="Ressources/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type ="text/css">
<link href="Ressources/bootstrap/css/style.css" rel="stylesheet" type ="text/css">
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
	<h1 style="text-align:center">Administrer les quiz</h1>
	<hr>
	<button style="text-align:center" class="btn btn-primary"><span class ="glyphicon glyphicon-plus"> </span> Ajouter un Quiz</button>
	<hr>
 <table class="table table-bordered table-hover ">
    <thead>
      <tr style="background-color:#D8D8D8;">
 	<th style="text-align:center">Libellé</th>
 	<th style="text-align:center">Date</th>
 	<th style="text-align:center">Supprimer</th>
 	<th style="text-align:center">Editer</th>
 	<th style="text-align:center">(Re)Lancer</th>
      </tr>
    
    </thead>
    <tbody>
    <tr style="text-align:center">
        <td>JEE</td>
        <td>javax.http.servlet.httpServlet</td>
        <td><a href="#"><span class="glyphicon glyphicon-remove"></span></a></td>
        <td><a href="#"><span class="glyphicon glyphicon-edit"></span></a></td>
		<td><a href="#"><span class="glyphicon glyphicon-play"></span></a></td>
      </tr>
	 <tr style="text-align:center">
        <td>HTML</td>
		<td>javax.http.servlet.httpServlet</td>
		<td><a href="#"><span class ="glyphicon glyphicon-remove"></span></a></td>
		<td><a href="#"><span class ="glyphicon glyphicon-edit"></span></a></td>
		<td><a href="#"><span class ="glyphicon glyphicon-play"></span></a></td>
      </tr>
      <tr style="text-align:center">
        <td>Android</td>
		<td>javax.http.servlet.httpServlet</td>
		<td><a href="#"><span class ="glyphicon glyphicon-remove"></span></a></td>
		<td><a href="#"><span class ="glyphicon glyphicon-edit"></span></a></td>
		<td><a href="#"><span class ="glyphicon glyphicon-play"></span></a></td>
      </tr>
    </tbody>
  </table>	
  

</body>
</html>