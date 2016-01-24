<%@page import="org.hamcrest.core.IsNull"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ page import="fr.esigelec.quiz.dto.*,java.util.*" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<jsp:include page="/header.jsp"/>
<div class="container">
  <div class="page-header">
	<h1>Administrer les quiz</h1>
  </div>
  <a href="<%=request.getContextPath()%>/VueQuestionQuizAdmin.do"><button class="btn btn-success"><span class ="glyphicon glyphicon-plus"> </span> Ajouter un Quiz</button></a>
  <a href="<%=request.getContextPath()%>/"><button class="btn btn-primary pull-right"> Déconnexion</button></a>
  <hr>
  <div class="row">
    <c:if test="${listeQuiz != null}">
      <div class="col-xs-12">
        <table class="table table-bordered table-hover">
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
            <c:forEach var="quiz" items="${listeQuiz}">
              <tr>
                <td>${quiz.libelle}</td>
                <td>${quiz.dateDebutQuiz}</td>
                <td><a href="<%=request.getContextPath()%>/SupprimerQuiz.do?idQuiz=${quiz.id}"><span class="glyphicon glyphicon-remove"></span></a></td>
                <td>
                  <!--<c:if test="${quiz.dateFinQuiz != null}"> -->
                    <a href="<%=request.getContextPath()%>/EditerQuiz.do?idQuiz=${quiz.id}"><span class="glyphicon glyphicon-edit"></span></a>
                  <!--</c:if> -->
                </td>
                <td><a href="<%=request.getContextPath()%>/JouerAdmin.do?idQuiz=${quiz.id}"><span class="glyphicon glyphicon-play"></span></a></td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </c:if>
  </div>
</div>
<jsp:include page="/footer.jsp"></jsp:include>