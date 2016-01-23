
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

<%--compteur affiche que si etape 1 --%>
<c:if test="${quiz.etape==1 }"> 
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
</c:if>

</head>
<body 

<%--compteur géré que si etape 1 --%>
<c:if test="${quiz.etape==1 }"> 
onload="if (!interval) { interval=setInterval(Ecoule, 1000) }"
</c:if>
>
<div>
  <h2 class="question">Question n°<c:out value="${quiz.noQuestionCourante+1}"/>/<c:out value="${quiz.questions.size()}"/></h2>
  
  
  
  <%--DEBUT Barre de progression --%>
  <div class="progress">
    <div class="progress-bar progress-bar-purple" role="progressbar" aria-valuenow="<c:out value="${quiz.noQuestionCourante}"/>" aria-valuemin="1" aria-valuemax="<c:out value="${quiz.questions.size()}"/>"
     style="width:<c:out value="${((quiz.noQuestionCourante+1) * 100 ) / quiz.questions.size()}"/>%">
       <c:out value="${ ((quiz.noQuestionCourante + 1) * 100 ) / quiz.questions.size()}" />%
    </div>
</div>

  <%--FIN Barre de progression --%>
  
  <a href="<%=request.getContextPath() %>/VueQuestion.do" class="btn btn-primary">rafraichir</a>
  
 <hr><br> 
 
 <%--affichage de la position du joueur dans le classement seulement en etape 3 --%>
 <c:if test="${quiz.etape==3}">
 <%
	
	List<Personne> personnes = (List<Personne>) session.getAttribute("classement");
	Personne joueur = (Personne) session.getAttribute("personne");
	// cherche joueur's rank
	int rank = -1;
	int size = personnes.size();
	for(int i = 0; i < size; i++) {
	    if (personnes.get(i).getId() == joueur.getId()) {
	        int index = i;    // index starts at 0
	        rank = index + 1; // rank starts at 1
	    }
	}
	
 %>
 <p class="question"><strong>Classement </strong> : <%=rank %> / <%=size %> joueurs</p> 
 </c:if>
 
 
 
 <%--DEBUT compteur affiche que si etape 1 --%>
<c:if test="${quiz.etape==1 }"> 
 <div> 
  <p class="question"><strong>Reste</strong> : &nbsp;
 <IMG HSPACE=0 NAME="dizaine" SRC="Ressources/images/3.gif">
		<IMG HSPACE=0 NAME="unite" SRC="Ressources/images/0.gif">
 &nbsp;secondes
 </div>
 </c:if>
  <%--FIN compteur --%>
  
  
 <br>


		<table class="table table-bordered table-hover " style="width:70%">
	    <thead>
	      <tr >
	 <th class="question"><c:out value="${question.libelle}" /></th>
	 <c:if test="${quiz.etape>=2}">
	  <th class="question">%</th>
	  </c:if>
	   <c:if test="${quiz.etape==3}">
        <th class="question">Réponse</th>
        </c:if>
	      </tr>
	    </thead>
	    <tbody>
				<%-- lignes permettant de choisir --%>
				<c:if test="${quiz.etape==1}">
					<c:forEach var="proposition" items="${question.listePropositions}">
						<tr class="question">
							<td><a <c:if test="${idProposition==proposition.id}"> style="color:red;font-weight:bold"</c:if>
								href="<%=request.getContextPath()%>/Choisir.do?idProposition=${proposition.id}"><c:out
										value="${proposition.libelle}" /></a></td>
						</tr>
					</c:forEach>
				</c:if>
				<%-- lignes permettant de voir les resultats en fonction des etapes --%>
				<c:if test="${quiz.etape!=1}">
					<c:forEach var="proposition" items="${question.listePropositions}">
						<tr class="question">
							<td><c:out value="${proposition.libelle}" /></td>
							<c:if test="${quiz.etape>=2}">
								<td><c:out value="${proposition.pourcentage}" />%</td>
							</c:if>
							<c:if test="${quiz.etape==3}">
								<c:if test="${proposition.estBonneReponse == false}">
									<td style="background-color: red"></td>
								</c:if>
								<c:if test="${proposition.estBonneReponse == true}">
									<td style="background-color: green"></td>
								</c:if>
							</c:if>
						</tr>
					</c:forEach>
				</c:if>



			</tbody>
  </table>
  
  <%--afficahge du calssement si etape 3 --%>
  <c:if test="${quiz.etape==3}">
   <h2 class="question">CLASSEMENT</h2>
  <hr>
  <br>
  <table class="table table-bordered table-hover " style="width:70%">
    <thead>
      <tr style="background-color:#D8D8D8">
        <th class="question">N°</th>
        <th class="question">Nom</th>
        <th class="question">Prénom</th>
		<th class="question">Score</th>
      </tr>
    </thead>
    <tbody>
	  <c:forEach var="personne" items="${classement}">
        <tr class="question">
          <td><c:out value="${personne.id}" /></td>
          <td><c:out value="${personne.nom}" /></td>
          <td><c:out value="${personne.prenom}" /></td>
          <td><c:out value="${personne.score}" /></td>
        </tr>
	  </c:forEach>
    </tbody>
  </table>
  </c:if>
  
  
  
 <%-- <a id="btn-stats" href="<%=request.getContextPath()%>/VueQuestion.do" class="btn btn-success hidden">Confirmer et voir résultat</a> --%>
</div>
<jsp:include page="/footer.jsp"/>