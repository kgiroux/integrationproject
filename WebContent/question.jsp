
<!-- @author : TIONO KEVIN & TIDJANI ENRIFATH -->

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.esigelec.quiz.dto.*" %>   
<%@ page import="java.util.*" %>  
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Question</title>
<link href="Ressources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
<link href="Ressources/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type ="text/css">
<!-- <link href="Ressources/bootstrap/css/style.css" rel="stylesheet" type ="text/css"> -->
<link href="Ressources/fonts/font-awesome.min.css" rel="stylesheet" type ="text/css">
<script src="Ressources/Jquery/jquery.min.js"></script>
<script src="Ressources/bootstrap/js/bootstrap.min.js"></script>

<%--compteur affiche que si etape 1 --%>
<c:if test="${quiz.etape==1 }"> 
  <!-- GESTION DU COMPTE A REBOURS -->
  <script>
    /**
     * Ce script est pour gérer le compteur
     * Les variables sont utilisees dans le compteur.js
     * @author mincong-h
     *
     */
    var debut = ${quiz.dateDebutQuestion.time};
    var now = ${currentTimestamp};
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
<div class="container">
  <div class="page-header">
    <p class="h1">
      Question n°<c:out value="${quiz.noQuestionCourante+1}"/>&nbsp;/&nbsp;<c:out value="${quiz.questions.size()}"/>
    </p>
  </div>
  
  
  <%--DEBUT Barre de progression --%>
  <div class="row">
    <div class="col-xs-10 col-xs-offset-1">
      <div class="progress">
        <div class="progress-bar progress-bar-purple" role="progressbar" aria-valuenow="<c:out value="${quiz.noQuestionCourante}"/>" aria-valuemin="1" aria-valuemax="<c:out value="${quiz.questions.size()}"/>"
         style="width:<c:out value="${((quiz.noQuestionCourante+1) * 100 ) / quiz.questions.size()}"/>%">
         <c:out value="${ ((quiz.noQuestionCourante + 1) * 100 ) / quiz.questions.size()}" />%
        </div>
      </div>
    </div>
    <%--FIN Barre de progression --%>
  </div>
 
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
 <div class="row">
 <div class="col-xs-12">
  <p class="question text-center"><strong>Reste</strong> : &nbsp;
 <IMG HSPACE=0 NAME="dizaine" SRC="<%--Ressources/images/3.gif--%>">
		<IMG HSPACE=0 NAME="unite" SRC="<%--Ressources/images/0.gif--%>">
 &nbsp;secondes
 </div>
 </div>
 </c:if>
  <%--FIN compteur --%>
  
  
 

<div class="row">
  <div class="col-xs-12">
    <table class="table table-bordered table-hover ">
      <thead>
	    <tr>
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
  </div><!-- /.col-lg-12 -->
  <div class="col-xs-4 col-xs-offset-4">
    <a href="<%=request.getContextPath() %>/VueQuestion.do" class="btn btn-primary">rafraichir</a>
  </div>
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
    
	  <c:forEach var="personne" items="${classement}" varStatus="loop">
        <tr class="question">
            <td><c:out value="${loop.index + 1}" /></td>
          <td><c:out value="${personne.nom}" /></td>
          <td><c:out value="${personne.prenom}" /></td>
          <td><c:out value="${personne.score}" /></td>
        </tr>
	  </c:forEach>
    </tbody>
  </table>
  </c:if>
  
</div><!-- /.row -->
 <%-- <a id="btn-stats" href="<%=request.getContextPath()%>/VueQuestion.do" class="btn btn-success hidden">Confirmer et voir résultat</a> --%>
</div>
<jsp:include page="/footer.jsp"/>