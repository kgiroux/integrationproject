<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ page import="fr.esigelec.quiz.dto.*,java.util.*" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<jsp:include page="/header.jsp"/>
<div class="container">
  <div class="page-header">
    <h1>Sauvegarder un quiz</h1>
  </div>
  <!--
      Error messages 
   -->
  <form method="POST" action="<%=request.getContextPath()%>/AjouterQuiz.do">
    <div class="form-group">
      <c:if test="${listeQuestions == null}">
        <div class="jumbotron">
          <h1>Ooops!!</h1>
          <p>Pas de question dans la base de données.<br>Créez la première question.</p>
          <a href="<%=request.getContextPath()%>/VueQuestionAdmin.do" class="btn btn-primary">
            <span class ="glyphicon glyphicon-plus"></span> Ajouter une Question
          </a>
        </div>
      </c:if>
      <c:if test="${listeQuestions != null}">
        <div class="row">
          <div class="col-xs-4 col-sm-2">
            <label for="libelleQuiz" class="control-label">Libellé :</label>
          </div>
          <div class="col-xs-12 col-sm-4">
            <input type="text" class="form-control" name="libelleQuiz" id="libelleQuiz" placeholder="Titre de votre quiz">
          </div>
          <div class="col-md-4">
            <a href="<%=request.getContextPath()%>/VueQuestionAdmin.do" class="btn btn-success">
              <span class ="glyphicon glyphicon-plus"></span> Ajouter une Question
            </a>
          </div>
          <div class="col-xs-12">
            <p class="text-danger h4"><html:errors property="err.inputs"/></p>
            <hr>
          </div>
          <div class="col-xs-12">
            <table class="table table-bordered table-hover">
              <thead>
                <tr>
                  <th>Cocher</th>
                  <th>Question</th>
                  <th><p class="hidden-xs">Supprimer</p></th>
                  <th><p class="hidden-xs">Editer</p></th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="question" items="${listeQuestions}">
                  <tr class="question">
                    <td><input type="checkbox" name="questionId" value="${question.id}"></td>
                    <td><c:out value="${question.libelle}" /></td>
                    <td>
                      <a href="<%=request.getContextPath()%>/SupprimerQuestion.do?idQuestion=${question.id}">
                        <span class="glyphicon glyphicon-remove"></span>
                      </a>
                    </td>
                    <td>
                      <a href="<%=request.getContextPath()%>/EditionQuestion.do?idQuestion=${question.id}">
                        <span class="glyphicon glyphicon-edit"></span>
                      </a>
                    </td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
          <div class="col-xs-6 col-md-3 col-lg-2">
            <button type="submit" class="btn btn-primary btn-block">Sauvegarder</button>
          </div>
          <div class="col-xs-6 col-md-3 col-lg-2">
            <a href="<%=request.getContextPath()%>/VueQuizAdmin.do" class="btn btn-default btn-block">Annuler</a>
          </div>
        </div>
      </c:if>
    </div>	    	
  </form>
</div>  
<jsp:include page="/footer.jsp"></jsp:include>