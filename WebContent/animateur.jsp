<%--
@author maxmon13 mincong-h THouet DamienBellenger
 --%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.esigelec.quiz.dto.*,java.util.*" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Animateur</title>
<link href="Ressources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
<link href="Ressources/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type ="text/css">
<link href="Ressources/bootstrap/css/style.css" rel="stylesheet" type ="text/css">
<link href="Ressources/fonts/font-awesome.min.css" rel="stylesheet" type ="text/css">
<script src="Ressources/Jquery/jquery.min.js"></script>
<script src="Ressources/bootstrap/js/bootstrap.min.js"></script>
<script>
/**
 * Ce script est pour gérer le compteur
 * @author mincong-h
 *
 */
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
<body
<%--on declenche le compteur que si on est en etape 1--%>
 <c:if test="${quiz.etape == 1}">
 onload="if (!interval) { interval=setInterval(Ecoule, 1000) }"
 </c:if>
 >

<%
int count=(int)session.getAttribute("compteur");

%>

<%--
Quiz q=(Quiz)session.getAttribute("quiz");
System.out.println(q.toString());
List<Question> listq=q.getListeQuestions();
Question questioncur=listq.get(count);
session.setAttribute("questioncurrente",questioncur);
--%>
<div class="form-center animateur">


<a href="<%=request.getContextPath()%>/VueQuizAdmin.do" class="btn btn-danger" >Liste des quiz</a>


<a href="<%=request.getContextPath()%>/Stats.do" class="btn btn-primary
<c:if test="${quiz.etape != 1}"> disabled</c:if>
" >Afficher %</a>

<a href="<%=request.getContextPath()%>/Reponse.do" class="btn btn-primary
<c:if test="${quiz.etape != 2}"> disabled</c:if>
">Afficher la bonne réponse<br> et nouveau classement</a>	

<%--affichage du bouton pour passer a la question suivante seulement si on est pas a la derniere question --%>
<c:if test="${compteur+1<quiz.questions.size()}">
<a href="<%=request.getContextPath()%>/Compteur.do?compteur=<%=count%>" class="btn btn-primary
<c:if test="${quiz.etape != 3}"> disabled</c:if>
" id="suivant">Question suivante</a>
</c:if>

<h1>Question n°<%=count+1%>/<%=quiz.getQuestions().size() %></h1>

  <%--DEBUT Barre de progression --%>
  <div class="progress">
    <div class="progress-bar progress-bar-purple" role="progressbar" aria-valuenow="<c:out value="${quiz.noQuestionCourante}"/>" aria-valuemin="1" aria-valuemax="<c:out value="${quiz.questions.size()}"/>"
     style="width:<c:out value="${((quiz.noQuestionCourante+1) * 100 ) / quiz.questions.size()}"/>%">
       <c:out value="${ ((quiz.noQuestionCourante + 1) * 100 ) / quiz.questions.size()}" />%
    </div>
</div>

  <%--FIN Barre de progression --%>


</div>
<hr>
<div class="form-center">

<%-- on affiche le compteur que si on est en etape 1 --%>
<c:if test="${quiz.etape == 1}">
  <p class="paraanimateur"><strong>Reste :</strong>  &nbsp;
    <IMG HSPACE=0 NAME="dizaine" SRC="Ressources/images/3.gif">
    <IMG HSPACE=0 NAME="unite" SRC="Ressources/images/0.gif">
    &nbsp;secondes
  </p>
  </c:if>
  <table class="table table-bordered table-hover " style="width:70%">
    <thead>
      <tr>
        <th><c:out value="${question.libelle}" /></th>
        <c:if test="${quiz.etape > 1}"><th>%</th></c:if>
        <c:if test="${quiz.etape > 2}"><th>Réponse</th></c:if>
      </tr>
    </thead>
    <tbody>
        <c:choose>
          <%--
              Etape 1 Jouer
              Dans cette etape, les libelles seront affiches.
          --%>
          <c:when test="${quiz.etape == 1}">
            <c:forEach var="proposition" items="${question.listePropositions}">
              <tr class="question">
                <td><c:out value="${proposition.libelle}" /></td>
              </tr>
            </c:forEach>
          </c:when>
          <%--
              Etape 2 Affichage du pourcentage
              Dans cette etape, les libelles et les pourcentages soront affiches
          --%>
          <c:when test="${quiz.etape == 2}">
            <c:forEach var="proposition" items="${pourcentage}">
              <tr class="question">
                <td><c:out value="${proposition.libelle}" /></td>
                <td><c:out value="${proposition.pourcentage}" />%</td>
              </tr>
            </c:forEach>
          </c:when>
          <%--
              Etape 3 Affichage de la reponse
              Dans cette etape, la bonne response sera affichee aussi.
          --%>
          <c:when test="${quiz.etape == 3}">
            <c:forEach var="proposition" items="${pourcentage}">
              <tr class="question">
                <td><c:out value="${proposition.libelle}" /></td>
                <td><c:out value="${proposition.pourcentage}" />%</td>
                <c:if test="${proposition.estBonneReponse == false}">
                  <td style="background-color:red"></td>
                </c:if>
                <c:if test="${proposition.estBonneReponse == true}">
                  <td style="background-color:green"></td>
                </c:if>
              </tr>
            </c:forEach>
          </c:when>
        </c:choose>
    </tbody>
  </table>
  
  
  <%--affichage du classement seulement en etape 3 --%>
   <c:if test="${quiz.etape==3}">
    <br>
  <h2 class="question">Classement</h2>
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
  
  
  
  
</div>
</body>
</html>
