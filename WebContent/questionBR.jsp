<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="fr.esigelec.quiz.dto.*" %>   
 
   <%@ page import="java.util.*" %>  

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
	q.setList(l);
	
	Personne p = new Personne();
	Personne pe1 = new Personne("TIONO","Kevin","tionokevin@live.fr","kev87dc",0);
	pe1.setId(1);
	Personne pe2 = new Personne("TIONO","Sandra","tionosady@live.fr","sand08",0);
	pe2.setId(2);
	Personne pe3 = new Personne("JACKSON","Michael","mj.thebest@live.fr","mjforever",0);
	pe3.setId(3);
	Personne pe4 = new Personne("HOLLANDE","François","f.hollande@live.fr","cuculolo",0);
	pe4.setId(4);
	Personne pe5 = new Personne("KOUBOURA","Anastasie","anaskoub@live.fr","tatiouf",0);
	pe5.setId(5);
	Personne pe6 = new Personne("LI","Jet","jet.li@live.fr","hongfeihung",0);
	pe6.setId(6);
	
	List<Personne> le = new ArrayList<Personne>();
	le.add(pe1);
	le.add(pe2);
	le.add(pe3);
	le.add(pe4);
	le.add(pe5);
	le.add(pe6);
	
	%>
  <center><table class="table table-bordered table-hover " style="width:70%">
    <thead>
      <tr >
 <th class="question">De quelle classe doit hériter une servlet ?</th>
 <th class="question">%</th>
 <th class="question">R&eacute;sultat</th>
      </tr>
    </thead>
    <tbody>
	
	
      <% for(int i=0; i<q.getList().size(); i++){%>

      <tr class="question">
        <td><a href="#"><%=q.getList().get(i).getLibelle().toString()%></a></td><td>30%</td><td style="background-color:red"></td>

      </tr>
	<%  }%>
    </tbody>
  </table></cennter><br>
  <h2 class="question">RESULTATS</h2>
  <p style="color:#D8D8D8">_________________________________________________________________________________________________________________________________________________________</p>
 <br>
 <center><table class="table table-bordered table-hover " style="width:70%">
    <thead>
      <tr style="background-color:#D8D8D8">
        <th class="question">N°</th>
        <th class="question">Nom</th>
        <th class="question">Prenom</th>
		<th class="question">Score</th>
	
      </tr>
    </thead>
    <tbody>
	
	<% for(int i=0; i<le.size(); i++){%>
      <tr class="question">
      <td><%=le.get(i).getId() %></td>
        <td><%=le.get(i).getNom() %></td>
        <td><%=le.get(i).getPrenom() %></td>
        <td>30</td>
		

      </tr>
	<%  }%>
    </tbody>
  </table></center>
</div>

</body>
</html>