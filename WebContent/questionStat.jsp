<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>QuestionStat</title>
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
 <p style="text-align:center"><strong>Reste</strong> : 0 secondes</p>
 <br>
  <center><table class="table table-bordered table-hover " style="width:70%">
    <thead>
      <tr style="background-color:#D8D8D8;">
 <th style="text-align:center">De quelle classe doit hériter une servlet ?</th>
 <th style="text-align:center">%</th>
      </tr>
    </thead>
    <tbody>
	
	<%
	for(int i=0; i<4; i++){
	%>
      <tr style="text-align:center">
        <td ><a href="#">javax.http.servlet.httpServlet</a></td>
		<td>30 %</td>

      </tr>
	<%  }%>
    </tbody>
  </table></center>
</div>

</body>
</html>