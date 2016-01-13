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
</head>
<body>
<div class="form-center animateur">
<button class="btn btn-primary">Afficher statistiques</button>
<button class="btn btn-primary">Afficher bonne réponse</button>	
<button class="btn btn-primary" id="suivant" onclick="Increment()">Question suivante</button>
<%
//Quiz q=(Quiz)request.getAttribute("quiz");
//List<Question> listq=q.getListeQuestions();
Proposition pro1 = new Proposition("proposition1");
Proposition pro2 = new Proposition("proposition2");
List<Proposition> list = new LinkedList<Proposition>();
list.add(pro1);
list.add(pro2);
Question question = new Question("question1",pro1,list);
Proposition prop11 = new Proposition("proposition11");
Proposition prop22 = new Proposition("proposition22");
List<Proposition> list1 = new LinkedList<Proposition>();
list1.add(prop11);
list1.add(prop22);
Question question1 = new Question("question1",prop11,list1);
List<Question> listq= new LinkedList<Question>();
listq.add(question);
listq.add(question1);
int i=0;
%>
<script>
/*
$("#suivant").click(function(){
		
	i++;
	window.location.reload();
}
		);*/
</script>
<h1>Question n°<%=i%></h1>	
</div>
<hr>
<div class="form-center">
<p class="paraanimateur"><strong>Reste :</strong> 0 seconde</p>
			<table id="tab" class="table table-bordered table-hover">
				<thead>
					<tr>
						<th><%=listq.get(i).getLibelle()%></th>
						
					</tr>
				</thead>
				<tbody>
				<%for(int j=0;j<listq.get(i).getListePropositions().size();j++){ %>
					<tr>
						<td><a id="test" href="#"><%=listq.get(i).getListePropositions().get(j).getLibelle()%></a></td>
					</tr>
					<%} %>
				</tbody>
			</table>
			</div>
</body>
</html>

<script>

$('#suivant').on('click', function() {
	var refresh = window.getElementById('test');
	alert("hello");
	i++
    location.reload();
});
</script>