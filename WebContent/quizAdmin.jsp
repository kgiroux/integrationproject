<%@page import="org.hamcrest.core.IsNull"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
 <%@ page import="fr.esigelec.quiz.dto.*,java.util.*" %>
<jsp:include page="/header.jsp"></jsp:include>
<div class="container">
  <div class="page-header">
	<h1>Administrer les quiz</h1>
  </div>
  <a href="<%=request.getContextPath()%>/VueQuestionQuizAdmin.do"><button class="btn btn-primary"><span class ="glyphicon glyphicon-plus"> </span> Ajouter un Quiz</button></a>
  <hr>
  <div class="row">
    <table class="table table-bordered table-hover col-xs-12">
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
      		}
						%>
      </tbody>
    </table>
  </div>
</div>
<jsp:include page="/footer.jsp"></jsp:include>