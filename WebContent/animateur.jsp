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
<jsp:include page="header.jsp"></jsp:include>


<%--compteur affiche que si etape 1 --%>
<c:if test="${quiz.etape == 1}"> 
  <script>
    /**
     * Ce script est pour gérer le compteur
     * Les variables sont utilisees dans le compteur.js
     * @author mincong-h
     *
     */
    var debut = ${quiz.dateDebutQuestion.time};
    var now = <%=System.currentTimeMillis()%>;
  </script>
  <script src="Ressources/compteur.js"></script>
</c:if>
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
<!-- <div class="form-center animateur"> -->
<div class="container">

  <div class="row" style="margin-top:10px">
    <div class="col-sm-3">
      <a href="<%=request.getContextPath()%>/VueQuizAdmin.do" class="btn btn-danger btn-block">Liste des quiz</a>
    </div>
    <div class="col-sm-3">
      <a href="<%=request.getContextPath()%>/Stats.do" class="btn btn-primary btn-block <c:if test="${quiz.etape != 1}"> disabled</c:if>">Ratio de réponse</a>
    </div>
    <div class="col-sm-3">
      <a href="<%=request.getContextPath()%>/Reponse.do" class="btn btn-primary btn-block <c:if test="${quiz.etape != 2}"> disabled</c:if>">Réponse et classement</a>	
    </div>
    <div class="col-sm-3">
      <%--affichage du bouton pour passer a la question suivante seulement si on est pas a la derniere question --%>
      <c:if test="${compteur+1<quiz.questions.size()}">
        <a href="<%=request.getContextPath()%>/Compteur.do?compteur=<%=count%>" class="btn btn-primary btn-block <c:if test="${quiz.etape != 3}"> disabled</c:if>" id="suivant">Question suivante</a>
      </c:if>
    </div>
  </div>
  <div class="page-header">
    <h1>Question n°<%=count+1%>&nbsp;/&nbsp;<%=((Quiz) session.getAttribute("quiz")).getQuestions().size() %></h1>
    <%--DEBUT Barre de progression --%>
    <div class="progress">
      <div class="progress-bar progress-bar-purple" role="progressbar" aria-valuenow="<c:out value="${quiz.noQuestionCourante}"/>" aria-valuemin="1" aria-valuemax="<c:out value="${quiz.questions.size()}"/>"
       style="width:<c:out value="${((quiz.noQuestionCourante+1) * 100 ) / quiz.questions.size()}"/>%">
       <c:out value="${ ((quiz.noQuestionCourante + 1) * 100 ) / quiz.questions.size()}" />%
      </div>
    </div>
     
    <%--FIN Barre de progression --%>
    <div><span id="messageWebSockets"></span></div>
  </div><!-- /.row -->

  <div class="row">
    <%-- on affiche le compteur que si on est en etape 1 --%>
    <c:if test="${quiz.etape == 1}">
      <div class="col-xs-12">
        <p class="paraanimateur text-center"><strong>Reste :</strong>  &nbsp;
          <IMG HSPACE=0 NAME="dizaine" SRC="Ressources/images/3.gif">
          <IMG HSPACE=0 NAME="unite" SRC="Ressources/images/0.gif">
          &nbsp;secondes
        </p>
      </div>
    </c:if>
    <div class="col-xs-12">
      <table class="table table-bordered table-hover">
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
    </div><!-- /.col -->
    
    <%--affichage du classement seulement en etape 3 --%>
    <c:if test="${quiz.etape==3}">
      <div class="col-xs-12">
        <div class="page-header"> 
          <h2 class="question">Classement</h2>
        </div>
      </div>
      <div class="col-xs-12">
        <table class="table table-bordered table-hover">
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
      </div>
    </c:if>
  </div>
</div>



<jsp:include page="/footer.jsp"/>
</body>
</html>
