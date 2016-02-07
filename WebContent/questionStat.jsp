<!-- @author : TIONO KEVIN  -->
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.esigelec.quiz.dto.*" %>   
<%@ page import="java.util.*" %>   
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html>
    
    <head>
<jsp:include page="/header.jsp"></jsp:include>
</head>
<body>
<div>
  <h2 class="question">CONSULTER LES QUIZ</h2>
 <hr><br> 
 <p class="question"><strong>Classement </strong> : 1 er / 50 joueurs</p> 
 <p class="question"><strong>Reste</strong> : 0 secondes</p>
 <br>
 <c:out value="${quiz}" />
  <table class="table table-bordered table-hover " style="width:70%">
    <thead>
      <tr >
		<th class="question"><%--=q.getLibelle() --%></th>
		 <th style="text-align:center">%</th>
      </tr>
    </thead>
    <tbody>
	
	  <%-- for(int i=0; i<q.getListePropositions().size(); i++){--%>
      <tr>
        <td ><a href="#"><c:out value="${quiz.question.libelle}" /></a></td>
		<td><c:out value="${quiz.question.libelle}" /></td>

      </tr>
	<%-- }--%>
    </tbody>
  </table>
<jsp:include page="/footer.jsp"></jsp:include>
</div>
</body>
</html>
