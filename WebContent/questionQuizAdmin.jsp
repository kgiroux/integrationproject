<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.esigelec.quiz.dto.*,java.util.*" %>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<jsp:include page="/header.jsp"></jsp:include>
<h1>Créer un nouveau quiz</h1>
	<hr>
<div class="container">
	<hr>
	<div class="form-group">
		<label for="libelle" class="col-xs-6 col-sm-2 control-label">Libellé :</label>
		<div class="col-xs-2 col-sm-4">
			<input type="text" class="form-control" name="libelleQuiz" id="libelleQuiz" placeholder="JEE,Android,etc">
		</div> 
		   
	</div>
 <a href="VueQuestionAdmin.do"><button  class="btn btn-primary"><span class ="glyphicon glyphicon-plus"> </span> Ajouter une Question</button></a>		    
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
				<% List<Question> questions = null; 
				List<Question> q=(List<Question>)request.getAttribute("listeQuestions");
						for(Question q1:q){ %>
					<tr>
						<td><input type="checkbox" name="check" id="checkbox" value="<%=q1.getId()%>">
						</td>
						<td><%=q1.getLibelle()%></td>
						<td><a href="EditerQuestion(<% q1.getId();%>)"><span class="glyphicon glyphicon-remove"></span></a></td>
						<td><a href="SupprimerQuestion(<% q1.getId();%>)"><span class="glyphicon glyphicon-edit"></span></a></td>
					</tr>
						<%} %>
				</tbody>
			</table>
		<script>	
		function getAttributes(){
		$("input[type='checkbox']:checked").each(
			questions.add(q1)
			);
			var libelle = $('#libelleQuiz').val();
			request.setAttribute("libelleQuiz", libelle);
			request.setAttribute("listeQuestionsQuiz", questions);
		}
			</script>
			<a href="AjouterQuiz.do"><button onClick="getAttributes()" class="btn btn-primary">Créer</button></a>
   
   <a href="VueQuizAdmin.do"><button class="btn btn-primary">Annuler</button></a>
   <hr>
  </div>  
</div>
<jsp:include page="/footer.jsp"></jsp:include>