
<!-- @author : TIONO KEVIN & TIDJANI ENRIFATH -->

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.esigelec.quiz.dto.*" %>   
<%@ page import="java.util.*" %>  
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Question</title>
<link href="Ressources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
<link href="Ressources/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type ="text/css">
<link href="Ressources/bootstrap/css/style.css" rel="stylesheet" type ="text/css">
<link href="Ressources/fonts/font-awesome.min.css" rel="stylesheet" type ="text/css">
<script src="Ressources/Jquery/jquery.min.js"></script>
<script src="Ressources/bootstrap/js/bootstrap.min.js"></script>
<!-- GESTION DU COMPTE A REBOURS -->
<script>
  <%
    // load timestamp du dateDebutQuestion
    Quiz quiz = (Quiz) request.getSession().getAttribute("quiz");
    long t_ms = quiz.getDateDebutQuestion().getTime(); // millisecond
    long t_s = t_ms / 1000; // second
  %>
  // cette variable est utilisee dans le compteur.js
  var timestampDebutQuestion = <%=t_s %>;
</script>
<script src="Ressources/bootstrap/js/compteur.js"></script>
</head>
<body onload="if (!interval) { interval=setInterval(Ecoule, 1000) }">
<div >
  <h2 class="question">CONSULTER LES QUIZ</h2>
  
  
  <a href="<%=request.getContextPath() %>/Jouer.do">rafraichir</a>
  
 <hr><br> 
 <p class="question"><strong>Classement </strong> : 1 er </p>
 <div> 
 
 
 <p class="question"><strong>Reste</strong> : &nbsp;
 <IMG HSPACE=0 NAME="dizaine" SRC="Ressources/images/3.gif">
		<IMG HSPACE=0 NAME="unite" SRC="Ressources/images/0.gif">
 &nbsp;secondes
 </div>
 <br>
 

		<table class="table table-bordered table-hover " style="width:70%">
	    <thead>
	      <tr >
	 <th class="question"><c:out value="${question.libelle}" /></th>
	      </tr>
	    </thead>
	    <tbody>
 <c:forEach var="proposition" items="${question.listePropositions}">
      <tr class="question">
 
        <td><a href="<%=request.getContextPath()%>/Choisir.do?idProposition=${proposition.id}"><c:out value="${proposition.libelle}" /></a></td>

      </tr>
       </c:forEach> 
    </tbody>
  </table>
  <a id="btn-stats" href="<%=request.getContextPath()%>/VueQuestion.do" class="btn btn-success hidden">Confirmer et voir résultat</a>
</div>
<jsp:include page="/footer.jsp"/>