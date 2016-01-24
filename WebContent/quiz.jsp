<!-- @author : TIONO KEVIN  -->
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>  
<%@ page import="fr.esigelec.quiz.dto.*" %>   
<%@ page import ="java.sql.Timestamp" %>  
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<jsp:include page="/header.jsp"></jsp:include>
<body>
<div class="container">
  <div class="page-header">
    <h2 class="question">CONSULTER LES QUIZ</h2>
  </div>
 
 <%-- 
 //Simulation de données
Timestamp t = new Timestamp(System.currentTimeMillis());

 Quiz q1 = new Quiz("JEE",20,20);
 Quiz q2= new Quiz("HTML",15,20);
 Quiz q3 = new Quiz("ANDROID",10,30);
 q1.setId(1);
q1.setDateDebutQuiz(t);
q2.setDateDebutQuiz(t);
q3.setDateDebutQuiz(t);
 List<Quiz> listeQuiz_de = new ArrayList<Quiz>();
 listeQuiz_de.add(q1);
 listeQuiz_de.add(q2);
 listeQuiz_de.add(q3);
 session.setAttribute("listeQuiz",listeQuiz_de);
 
 
 
 --%>   
 
      
  <table class="table table-striped">
    <thead>
      <tr>
        <th>Libellé</th>
        <th>Questions</th>
        <th>Date</th>
		<th>Jouer</th>
		<th>Resultat</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="quiz" items="${listeQuiz}">
      <tr class="question">
        <td><c:out value="${quiz.libelle}" /></td>
        <td><c:out value="${quiz.questions.size()}" /></td>
        <td><c:out value="${quiz.dateDebutQuiz}" /></td>	
		<td>
		<%-- on affiche le bouton jouer que si le quiz a commencé --%>
		<c:if test="${quiz.dateDebutQuiz != null}" >
			<a href="<%=request.getContextPath()%>/Jouer.do?idQuiz=${quiz.id}"><span class="glyphicon glyphicon-play" aria-hidden="true"></span></a>
		</c:if>
		</td>
		<td>
		<%-- on affiche le bouton resultat que si le quiz a commencé --%>
		<c:if test="${quiz.dateDebutQuiz != null}" >
			<a href="<%=request.getContextPath()%>/Stats.do?idQuiz=${quiz.id}"><span class="glyphicon glyphicon-stats" aria-hidden="true"></span></a>
		</c:if>
		</td>
      </tr>
      </c:forEach> 
    </tbody>
  </table>
</div>
<jsp:include page="/footer.jsp"></jsp:include>