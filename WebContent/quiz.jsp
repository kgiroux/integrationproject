<!-- @author : TIONO KEVIN  -->
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>  
<%@ page import="fr.esigelec.quiz.dto.*" %>   
<%@ page import ="java.sql.Timestamp" %>  
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Consulter Quiz</title>
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
  <hr>
 <br> 
 
 <% 
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
 
 
 
 %>   
 
      
  <table class="table table-bordered table-hover ">
    <thead>
      <tr style="background-color:#D8D8D8">
        <th>Libellé</th>
        <th>Questions</th>
        <th>Date</th>
		<th>Jouer</th>
		<th>Resultat</th>
      </tr>
    </thead>
    <tbody>
	
	
<!--
    Hi, Kevin.
    You should use sessionScope to read session scope level attribute.
    - Mincong
 -->
 <c:forEach var="quiz" items="${sessionScope.listeQuiz}">

      <tr class="question">
        <td><c:out value="${quiz.libelle}" /></td>
        <td><c:out value="${quiz.questions.size()}" /></td>
        <td><c:out value="${quiz.dateDebutQuiz}" /></td>
		<td><a href="<%=request.getContextPath()%>/Jouer.do?idQuiz=${quiz.id}"><img src="Ressources/images/jouer.png" width="8%"/></a></td>
		<td><a href="<%=request.getContextPath()%>/Stats.do?idQuiz=${quiz.id}"><img src="Ressources/images/stats.png" width="8%"/></a></td>
      </tr>
      </c:forEach> 
	
    </tbody>
  </table>
</div>
</body>
</html>