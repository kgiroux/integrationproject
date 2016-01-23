<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.esigelec.quiz.dto.*,java.util.*" %>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<jsp:include page="/header.jsp"></jsp:include>
<% if ((String)session.getAttribute("listeQuestions") != null) {
	session.setAttribute("idQuiz", (int)session.getAttribute("idQuiz"));
}%>
<h1>Sauvegarder un quiz</h1>
	<hr>
<div class="container">
	<hr>
	<form method="POST" action="<%=request.getContextPath()%>/AjouterQuiz.do">
	<div class="form-group">
		<label for="libelle" class="col-xs-6 col-sm-2 control-label">Libellé :</label>
		<div class="col-xs-2 col-sm-4">
				<% if  ((List<Question>)session.getAttribute("listeQuestions") != null) {
						String libelleQuiz = (String)session.getAttribute("libelleQuiz");
						%><input type="text" class="form-control" name="libelleQuiz" id="libelleQuiz" value="<%= libelleQuiz%>"><%
					}
					else %><input type="text" class="form-control" name="libelleQuiz" id="libelleQuiz" placeholder="JEE,Android,etc"><%
					%>
		</div> 
		   
	</div>
 <a href="<%=request.getContextPath()%>/VueQuestionAdmin.do"><button type="button"  class="btn btn-primary"><span class ="glyphicon glyphicon-plus"> </span> Ajouter une Question</button></a>		    
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
				<% 				
				List<Question> qtotale= null;
				List<Question> listeQuestionQuiz = null;
				if  ((List<Question>)session.getAttribute("listeQuestions") != null && (List<Question>)session.getAttribute("listeQuestionsQuiz") == null) {	
					qtotale = (List<Question>)session.getAttribute("listeQuestions");
					for(Question q1:qtotale){ %>
					<tr>
						<td><input type="checkbox" name="questionId" value="<%=q1.getId()%>"></td>
						<td><%=q1.getLibelle()%></td>
						<td><a href="<%=request.getContextPath()%>/SupprimerQuestion.do?idQuestion=<%= q1.getId()%>" ><span class="glyphicon glyphicon-remove"></span></a></td>
						<td><a href="<%=request.getContextPath()%>/EditionQuestion.do"><span class="glyphicon glyphicon-edit"></span></a></td>
					</tr>
						<%} 
						}
				if  ((List<Question>)session.getAttribute("listeQuestions") != null && (List<Question>)session.getAttribute("listeQuestionsQuiz") != null) {	
					qtotale = (List<Question>)session.getAttribute("listeQuestions");
					System.out.println(qtotale.toString());
					listeQuestionQuiz = (List<Question>)session.getAttribute("listeQuestionsQuiz");
					for(Question q1:qtotale){ %>
						<tr>
				
						<td><input type="checkbox" id="<%=q1.getId()%>" name="questionId11" value="<%=q1.getId()%>"></td>
						<td><%=q1.getLibelle()%></td>
						<td><a href="<%=request.getContextPath()%>/SupprimerQuestion.do?idQuestion=<%= q1.getId()%>" ><span class="glyphicon glyphicon-remove"></span></a></td>
						<td><a href="<%=request.getContextPath()%>/EditionQuestion.do"><span class="glyphicon glyphicon-edit"></span></a></td>
					</tr>
					<%	for (Question qq1:listeQuestionQuiz) {%>
					<%		if (qq1.equals(q1)) { %>
						<script>
							var el=document.getElementById("<%=q1.getId()%>");
							el.setAttribute("checked", "checked");
						</script>
						
						<% } %>
						<%} 
					}
				}
						%>
				</tbody>
			</table>
			<button type="submit" class="btn btn-primary">Sauvegarder</button>
   </form>
   
   			<a href="<%=request.getContextPath()%>/VueQuizAdmin.do"><button type="button" class="btn btn-primary">Annuler</button></a>
   <hr>
  </div>  
</div>
<jsp:include page="/footer.jsp"></jsp:include>