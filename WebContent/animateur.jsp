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
<body onload="if (!interval) { interval=setInterval(Ecoule, 1000) }">

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
<a href="<%=request.getContextPath()%>/Stats.do"><button id="bouton1" class="btn btn-primary" >Afficher statistiques</button></a>
<a href="<%=request.getContextPath()%>/Reponse.do"><button id="bouton2"  class="btn btn-primary">Afficher bonne réponse</button></a>	
<a href="<%=request.getContextPath()%>/Compteur.do?compteur=<%=count%>"><button id="bouton3" class="btn btn-primary" id="suivant">Question suivante</button></a>

<h1>Question n°<%=count+1%></h1>
</div>
<hr>
<div class="form-center">
  <p class="paraanimateur"><strong>Reste :</strong>  &nbsp;
    <IMG HSPACE=0 NAME="dizaine" SRC="Ressources/images/3.gif">
    <IMG HSPACE=0 NAME="unite" SRC="Ressources/images/0.gif">
    &nbsp;secondes
  </p>
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
</div>
</body>
</html>
