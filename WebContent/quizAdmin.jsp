<%@page import="org.hamcrest.core.IsNull"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
 <%@ page import="fr.esigelec.quiz.dto.*,java.util.*" %>
<jsp:include page="/header.jsp"></jsp:include>
	<h1>Administrer les quiz</h1>
	<hr>
	 <a href="<%=request.getContextPath()%>/VueQuestionQuizAdmin.do"><button class="btn btn-primary"><span class ="glyphicon glyphicon-plus"> </span> Ajouter un Quiz</button></a>
	<hr>
 <table class="table table-bordered table-hover ">
    <thead>
      <tr>
 	<th>Libellé</th>
 	<th>Date</th>
 	<th>Supprimer</th>
 	<th>Editer</th>
 	<th>(Re)Lancer</th>
      </tr>
    
    </thead>
    <tbody>
    <% 
    List<Quiz> quiz = null;
		if(request.getAttribute("listeQuiz")!=null) {
		quiz = (List<Quiz>)request.getAttribute("listeQuiz");
	} 
	else {
		List<Question> listq = null; 
		Question question = new Question("question1");
		Proposition pro1 = new Proposition("proposition1faux",false);
		Proposition pro2 = new Proposition("proposition2bonne",true);
		question.getPropositions().add(pro1);
		question.getPropositions().add(pro2);
		Question question1 = new Question("question2");
		Proposition prop11 = new Proposition("proposition11faux",false);
		Proposition prop22 = new Proposition("proposition22bonne",true);
		question.getPropositions().add(prop11);
		question.getPropositions().add(prop22);
		listq= new LinkedList<Question>();
		listq.add(question);
		listq.add(question1);
		Quiz q = new Quiz();
		q.setListeQuestions(listq);
		q.setId(0);
		q.setLibelle("bouh");
	    //Quiz quiz1 = q;
	    quiz = new LinkedList<Quiz>();
		quiz.add(q);
	}
						for(Quiz quiz1:quiz){ 
						%>
    <tr>
        <td><%=quiz1.getLibelle()%></td>
        <td><%=quiz1.getDateDebutQuiz()%></td>
        <td><a href="<%=request.getContextPath()%>/SupprimerQuiz.do?idQuiz=<%=quiz1.getId()%>"><span class="glyphicon glyphicon-remove"></span></a></td>
        <%if(quiz1.getDateFinQuiz() == null) {%> 
        <td><a href="<%=request.getContextPath()%>/EditerQuiz.do?idQuiz=<%=quiz1.getId()%>"><span class="glyphicon glyphicon-edit"></span></a></td>
        <%} else {%>
        <td></td>
        <%} %>
		<td><a href="<%=request.getContextPath()%>/JouerAdmin.do?idQuiz=<%=quiz1.getId()%>"><span class="glyphicon glyphicon-play"></span></a></td>
      </tr>
      <%
      }
						%>
	 </tbody>
  </table>	
<jsp:include page="/footer.jsp"></jsp:include>