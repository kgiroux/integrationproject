<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
    <%@ page import="fr.esigelec.quiz.dto.*,java.util.*" %>
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
<script src="Ressources/bootstrap/js/compteur.js"></script>
</head>
<body onload="if (!interval) { interval=setInterval(Ecoule, 1000) }">
<%
int count=Integer.parseInt(request.getParameter("compteur"));

%>
<%
Quiz q;
List<Question> listq;
if(request.getAttribute("quiz")==null){
q=(Quiz)request.getAttribute("quiz");

listq=setToListConverte<Question>();}
//List<Question> listq=q.getListeQuestions();
else{
Question question = new Question("question1");
Proposition pro1 = new Proposition("proposition1faux",false);
Proposition pro2 = new Proposition("proposition2bonne",true);
List<Proposition> list = new LinkedList<Proposition>();
list.add(pro1);
list.add(pro2);

Question question1 = new Question("question2");
Proposition prop11 = new Proposition("proposition11faux",false);
Proposition prop22 = new Proposition("proposition22bonne",true);
List<Proposition> list1 = new LinkedList<Proposition>();
list1.add(prop11);
list1.add(prop22);

listq= new LinkedList<Question>();
listq.add(question);
listq.add(question1);
q = new Quiz();
q.setListeQuestions(listq);
}
%>
<div class="form-center animateur">
<a href="Stats.do?idQuiz=<%=q.getId()%>"><button class="btn btn-primary" >Afficher statistiques</button></a>
<a href="Reponse.do?idQuiz=<%=q.getId()%>"><button class="btn btn-primary">Afficher bonne réponse</button></a>	
<a href="Compteur.do?compteur=<%=count+1%>"><button class="btn btn-primary" id="suivant">Question suivante</button></a>


<h1>Question n°<%=count%></h1>
</div>
<hr>
<div class="form-center">
<p class="paraanimateur"><strong>Reste :</strong>  &nbsp;
 <IMG HSPACE=0 NAME="dizaine" SRC="Ressources/images/3.gif">
		<IMG HSPACE=0 NAME="unite" SRC="Ressources/images/0.gif">
 &nbsp;secondes</p>
			<table id="tab" class="table table-bordered table-hover">
				<thead>
					<tr>
						<th><%=listq.get(count).getLibelle()%></th>
						
					</tr>
				</thead>
				<tbody>
				<%for(int j=0;j<listq.get(count).getListePropositions().size();j++){ %>
					<tr>
						<td><a id="test" href="#"><%=listq.get(count).getListePropositions().get(j).getLibelle()%></a></td>
					</tr>
					<% if(q.getEtape()>1) {%>
					<tr>
						<td>2</td>
					</tr>
					<%} %>
					<% if(q.getEtape()>2) {%>
					<tr>
						<td>3</td>
					</tr>
					<%} %>
					<%} %>
				</tbody>
			</table>
			</div>
</body>
</html>
