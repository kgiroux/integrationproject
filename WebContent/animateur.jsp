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
int count=(int)session.getAttribute("compteur");

%>

<%
Quiz q=(Quiz)session.getAttribute("quiz");
System.out.println(q.toString());
List<Question> listq=q.getListeQuestions();
Question questioncur=listq.get(count);
session.setAttribute("questioncurrente",questioncur);
%>
<div class="form-center animateur">
<a href="<%=request.getContextPath()%>/Stats.do"><button id="bouton1" class="btn btn-primary" >Afficher statistiques</button></a>
<a href="<%=request.getContextPath()%>/Reponse.do"><button id="bouton2"  class="btn btn-primary">Afficher bonne réponse</button></a>	
<a href="<%=request.getContextPath()%>/Compteur.do?compteur=<%=count%>"><button id="bouton3" class="btn btn-primary" id="suivant">Question suivante</button></a>
<script type="text/javascript">
afficher_cacher('bouton1');
afficher_cacher('bouton2');
afficher_cacher('bouton3');
</script>
<h1>Question n°<%=count+1%></h1>
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
						<% if(q.getEtape()>1)%><th>%</th>
						<% if(q.getEtape()>2)%><th>Réponse</th>
						
					</tr>
				</thead>
				<tbody>
				<%for(int j=0;j<listq.get(count).getListePropositions().size();j++){ %>
					<tr>
						<td><a id="test" href="#"><%=listq.get(count).getListePropositions().get(j).getLibelle()%></a></td>
					
									
					
					<% if(q.getEtape()>1) {
						List<Proposition> listpro= (List<Proposition>)session.getAttribute("listpro");
					%>
						<td><%=listpro.get(j).getPourcentage()%></td>
					<%} %>
					<% if(q.getEtape()>2) {
					if(listq.get(count).getListePropositions().get(j).isEstBonneReponse()){%>
						<td class="vert"></td><%} else{%>
						<td class="rouge"></td><%} %>
					<%} %>
					</tr>
					
					<%} %>
				</tbody>
			</table>
			</div>
</body>
</html>
