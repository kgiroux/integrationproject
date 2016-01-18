<!-- @author : TIONO KEVIN  -->
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.esigelec.quiz.dto.*" %>   
<%@ page import="java.util.*" %>   
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QuestionStat</title>
<link href="Ressources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
<link href="Ressources/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type ="text/css">
<link href="Ressources/bootstrap/css/style.css" rel="stylesheet" type ="text/css">
<link href="Ressources/fonts/font-awesome.min.css" rel="stylesheet" type ="text/css">
<script src="Ressources/Jquery/jquery.min.js"></script>
<script src="Ressources/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
<div >
  <h2 class="question">CONSULTER LES QUIZ</h2>
 <hr><br> 
 <p class="question"><strong>Classement </strong> : 1 er / 50 joueurs</p> 
 <p class="question"><strong>Reste</strong> : 0 secondes</p>
 <br>
 <%
	
	Proposition p1 = new Proposition("javax.http.servlet.HttpServlet");
	Proposition p2 = new Proposition("javax.servlet.HttpServlet");
	Proposition p3 = new Proposition("java.http.servlet.ServletHttp");
	Proposition p4 = new Proposition("javax.http.servlet.ServletHttp");

	List<Proposition> l=new ArrayList<Proposition>();
	l.add(p1);
	l.add(p2);
	l.add(p3);
	l.add(p4);
	Question q = new Question();
	q.setLibelle("De quelle classe doit hériter une servlet ?");
	q.setId(1);
	q.setBonneReponse(p1);
	q.setListePropositions(l);
	%>
  <table class="table table-bordered table-hover " style="width:70%">
    <thead>
      <tr >
<th class="question"><%=q.getLibelle() %></th>
 <th style="text-align:center">%</th>
      </tr>
    </thead>
    <tbody>
	
	  <% for(int i=0; i<q.getListePropositions().size(); i++){%>
      <tr>
        <td ><a href="#">j<%=q.getListePropositions().get(i).getLibelle().toString()%></a></td>
		<td>30 %</td>

      </tr>
	<%  }%>
    </tbody>
  </table>
</div>

</body>
</html>