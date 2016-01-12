<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="fr.esigelec.quiz.dto.*" %>   
 
   <%@ page import="java.util.*" %>   
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Question</title>
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
 <hr><br> 
 <p class="question"><strong>Classement </strong> : 1 er / 50 joueurs</p> 
 <p class="question"><strong>Reste</strong> : 0 secondes</p>
 <br>
	
	<%
	
	Proposition p1 = new Proposition("javax.http.servlet.HttpServlet");
	Proposition p2 = new Proposition("javax.servlet.HttpServlet");
	Proposition p3 = new Proposition("java.http.servlet.ServletHttp");
	Proposition p4 = new Proposition("javax.http.servlet.ServletHttp");

	List<Proposition> l=new ArrayList<Proposition>();
	l.add(p1);
	l.add(p2);
	l.add(p3);
	l.add(p4);
	Question q = new Question();
	q.setLibelle("De quelle classe doit heriter une servlet ?");
	q.setId(1);
	q.setBonneReponse(p1);
	q.setListePropositions(l);
	%>
		<center><table class="table table-bordered table-hover " style="width:70%">
	    <thead>
	      <tr >
	 <th class="question"><%=q.getLibelle() %></th>
	      </tr>
	    </thead>
	    <tbody>
	    <% for(int i=0; i<q.getListePropositions().size(); i++){%>

      <tr class="question">
        <td><a href="#"><%=q.getListePropositions().get(i).getLibelle().toString()%></a></td>

      </tr>
	<%  }%>
    </tbody>
  </table></center>
</div>
</body>
</html>