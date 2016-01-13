<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
 <%@ page import="fr.esigelec.quiz.dto.*,java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Administrer quiz</title>
<link href="Ressources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
<link href="Ressources/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type ="text/css">
<link href="Ressources/bootstrap/css/style.css" rel="stylesheet" type ="text/css">
<link href="Ressources/fonts/font-awesome.min.css" rel="stylesheet" type ="text/css">
<script src="Ressources/Jquery/jquery.min.js"></script>
<script src="Ressources/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<h1>Administrer les quiz</h1>
	<hr>
	<button class="btn btn-primary"><span class ="glyphicon glyphicon-plus"> </span> Ajouter un Quiz</button>
	<hr>
 <table class="table table-bordered table-hover ">
    <thead>
      <tr>
 	<th>Libellé</th>
 	<th>Date</th>
 	<th>Supprimer</th>
 	<th>Editer</th>
 	<th>(Re)Lancer</th>
      </tr>
    
    </thead>
    <tbody>
    <% List<Quiz> quiz=(List<Quiz>)request.getAttribute("listeQuiz");
						for(Quiz quiz1:quiz){ %>
    <tr>
        <td><%=quiz1.getLibelle()%></td>
        <td><%=quiz1.getDateDebutQuiz()%></td>
        <td><a href="SupprimerQuiz.do?idQuiz=<%=quiz1.getId()%>"><span class="glyphicon glyphicon-remove"></span></a></td>
        <td><a href="#"><span class="glyphicon glyphicon-edit"></span></a></td>
		<td><a href="#"><span class="glyphicon glyphicon-play"></span></a></td>
      </tr>
      <%} %>
	 <tr>
        <td>HTML</td>
		<td>javax.http.servlet.httpServlet</td>
		<td><a href="#"><span class ="glyphicon glyphicon-remove"></span></a></td>
		<td><a href="#"><span class ="glyphicon glyphicon-edit"></span></a></td>
		<td><a href="#"><span class ="glyphicon glyphicon-play"></span></a></td>
      </tr>
      <tr>
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