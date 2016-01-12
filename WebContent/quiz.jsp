<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>  
<%@ page import="fr.esigelec.quiz.dto.*" %>   
<%@ page import ="java.sql.Timestamp" %>  
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
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

 Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis()/1000);
 
 Quiz q1 = new Quiz("JEE",currentTimestamp,currentTimestamp,currentTimestamp,20,20);
 Quiz q2= new Quiz("HTML",currentTimestamp,currentTimestamp,currentTimestamp,15,20);
 Quiz q3 = new Quiz("ANDROID",currentTimestamp,currentTimestamp,currentTimestamp,10,30);

 List<Quiz> qu = new ArrayList<Quiz>();
 qu.add(q1);
 qu.add(q2);
 qu.add(q3);
 
 
 
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
	
	
      <% for(int i=0; i<qu.size(); i++){%>

      <tr class="question">
        <td><%=qu.get(i).getLibelle() %></td>
        <td><%=qu.get(i).getEtape() %></td>
        <td><%=qu.get(i).getDateDebutQuiz() %></td>
		<td><a href="#"><img src="Ressources/images/jouer.png" width="8%"/></a></td>
		<td><a href="#"><img src="Ressources/images/stats.png" width="8%"/></a></td>
      </tr>
	<% }%>
    </tbody>
  </table>
</div>
</body>
</html>