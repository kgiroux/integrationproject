<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.esigelec.quiz.dto.*,java.util.*" %>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<jsp:include page="/header.jsp"></jsp:include>
<h1>Créer un nouveau quiz</h1>
	<hr>
<div class="container">
	<hr>
	<form method="POST" action="<%=request.getContextPath()%>/AjouterQuiz.do">
	<div class="form-group">
		<label for="libelle" class="col-xs-6 col-sm-2 control-label">Libellé :</label>
		<div class="col-xs-2 col-sm-4">
			<input type="text" class="form-control" name="libelleQuiz" id="libelleQuiz" placeholder="JEE,Android,etc">
		</div> 
		   
	</div>
 <a href="<%=request.getContextPath()%>/VueQuestionAdmin.do"><button  class="btn btn-primary"><span class ="glyphicon glyphicon-plus"> </span> Ajouter une Question</button></a>		    
<div class="form-center">
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th>Cocher</th>
						<th>Question</th>
						<th>Supprimer</th>
						<th>Editer</th>
					</tr>
				</thead>
				<tbody>
				<% List<Question> listq = null; 
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
				//List<Question> q=(List<Question>)request.getAttribute("listeQuestions");
						for(Question q1:listq){ %>
					<tr>
						<td><input type="checkbox" name="<%=q1.getId()%>" id="<%=q1.getId()%>" >
						</td>
						<td><%=q1.getLibelle()%></td>
						<td><a href="<%=request.getContextPath()%>/EditionQuestion.do" ><span class="glyphicon glyphicon-remove"></span></a></td>
						<td><a href="<%=request.getContextPath()%>/SupprimerQuestion.do?idQuestion=<%= q1.getId()%>"><span class="glyphicon glyphicon-edit"></span></a></td>
					</tr>
						<%} %>
				</tbody>
			</table>
			<button class="btn btn-primary">Créer</button>
   </form>
   			<a href="<%=request.getContextPath()%>/VueQuizAdmin.do"><button class="btn btn-primary">Annuler</button></a>
   <hr>
  </div>  
</div>
<jsp:include page="/footer.jsp"></jsp:include>