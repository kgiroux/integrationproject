<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>QuestionBR</title>
<link href="Ressources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
<link href="Ressources/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type ="text/css">
<link href="Ressources/bootstrap/css/style.css" rel="stylesheet" type ="text/css">
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</head>
<body>
<div >
  <h2 style="text-align:center">CONSULTER LES QUIZ</h2>
  <p style="color:#D8D8D8">_________________________________________________________________________________________________________________________________________________________</p>
 
 <br> 
 <p style="text-align:center"><strong>Classement</strong> : 1 er / 60 joueurs</p> 
 <p style="text-align:center"><strong>Reste</strong> : 0 secondes</p>
 <br>
  <cennter><table class="table table-bordered table-hover " style="width:70%">
    <thead>
      <tr style="background-color:#D8D8D8;">
 <th style="text-align:center">De quelle classe doit hériter une servlet ?</th>
 <th style="text-align:center">%</th>
 <th style="text-align:center">R&eacute;sultat</th>
      </tr>
    </thead>
    <tbody>
	
	
      <tr style="text-align:center">
        <td ><a href="#">javax.http.servlet.httpServlet</a></td>
		<td>30 %</td>
		<td style="background-color:red"></td>
      </tr>
<tr style="text-align:center">
        <td ><a href="#">javax.http.servlet.httpServlet</a></td>
		<td>30 %</td>
		<td style="background-color:green"></td>
      </tr>
	  <tr style="text-align:center">
        <td ><a href="#">javax.http.servlet.httpServlet</a></td>
		<td>30 %</td>
		<td style="background-color:red"></td>
      </tr>
	  <tr style="text-align:center">
        <td ><a href="#">javax.http.servlet.httpServlet</a></td>
		<td>30 %</td>
		<td style="background-color:red"></td>
      </tr>
    </tbody>
  </table></cennter><br>
  <h2 style="text-align:center">RESULTATS</h2>
  <p style="color:#D8D8D8">_________________________________________________________________________________________________________________________________________________________</p>
 <br>
 <center><table class="table table-bordered table-hover " style="width:70%">
    <thead>
      <tr style="background-color:#D8D8D8">
        <th style="text-align:center">N°</th>
        <th style="text-align:center">Nom</th>
        <th style="text-align:center">Prenom</th>
		<th style="text-align:center">Score</th>
	
      </tr>
    </thead>
    <tbody>
	
	<%
	for(int i=0; i<4; i++){
	%>
      <tr style="text-align:center">
        <td><?php echo $i+1 ?></td>
        <td>Tiono</td>
        <td>Kevin</td>
		<td>90</td>

      </tr>
	<%  }%>
    </tbody>
  </table></center>
</div>

</body>
</html>