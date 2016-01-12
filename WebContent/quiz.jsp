<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="fr.esigelec.quiz.dto.*" %>   
    <%@ page import ="java.sql.Timestamp" %>  
   <%@ page import="java.util.*" %>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Consulter Quiz</title>
<link href="Ressources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
<link href="Ressources/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type ="text/css">
<link href="Ressources/bootstrap/css/style.css" rel="stylesheet" type ="text/css">
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</head>
<body>
<div >
  <h2 class="question">CONSULTER LES QUIZ</h2>
  <p style="color:#D8D8D8">_________________________________________________________________________________________________________________________________________________________</p>
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