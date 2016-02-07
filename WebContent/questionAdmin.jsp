<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="header.jsp"/>
</head>
<body>
<div class="container">
  <div class="page-header">
	<h1>Sauvegarder une question</h1>
  </div>
  <div class="row">
    <div class="col-xs-12">
      <p class="text-danger h4"><html:errors property="err.inputs"/></p>
    </div>
  </div>
		<form class="form-horizontal" method="post" action="<%=request.getContextPath()%>/EnregistrerQuestion.do">
			<br>
			<fieldset>
				<div class="form-group">
				  <label class="col-md-2 control-label" for="libelleQuestion">Question : </label>  
				  <div class="col-md-8">
				  <input id="libelleQuestion" name="libelleQuestion" placeholder="question" class="form-control input-md" required="" type="text">    
				  </div>
				</div>
				<div class="form-group">
				  <label class="col-md-2 control-label" for="bonneReponse">Bonne Réponse : </label>  
				  <div class="col-md-8">
				  <input id="bonneReponse" name="bonneReponse" placeholder="la bonne réponse" class="form-control input-md" required="" type="text">    
				  </div>
				</div>
				<div class="form-group">
				  <label class="col-md-2 control-label" for="p1">Mauvaise Proposition 1 : </label>  
				  <div class="col-md-8">
				  <input id="p1" name="p1" placeholder="proposition 1" class="form-control input-md" required="" type="text">    
				  </div>
				</div>
				<div class="form-group">
				  <label class="col-md-2 control-label" for="p2">Mauvaise Proposition 2 : </label>  
				  <div class="col-md-8">
				  <input id="p2" name="p2" placeholder="propostion 2" class="form-control input-md"  type="text">    
				  </div>
				</div>
				<div class="form-group">
				  <label class="col-md-2 control-label" for="p3">Mauvaise Proposition 3 : </label>  
				  <div class="col-md-8">
				  <input id="p3" name="p3" placeholder="proposition 3" class="form-control input-md"  type="text">    
				  </div>
				</div>
				<div class="form-group">
				  <label class="col-md-2 control-label" for="p4">Mauvaise Proposition 4 : </label>  
				  <div class="col-md-8">
				  <input id="p4" name="p4" placeholder="proposition 4" class="form-control input-md"  type="text">    
				  </div>
				</div>
				<div class="form-group">
				  <label class="col-md-2 control-label" for="p5">Mauvaise Proposition 5 : </label>  
				  <div class="col-md-8">
				  <input id="p5" name="p5" placeholder="proposition 5" class="form-control input-md" type="text">    
				  </div>
				</div>
				<div class="form-group">
				  <label class="col-md-2 control-label" for="p6">Mauvaise Proposition 6 : </label>  
				  <div class="col-md-8">
				  <input id="p6" name="p6" placeholder="proposition 6" class="form-control input-md" type="text">    
				  </div>
				</div>
				<div class="form-group">
				  <label class="col-md-2 control-label" for="p7">Mauvaise Proposition 7 : </label>  
				  <div class="col-md-8">
				  <input id="p7" name="p7" placeholder="proposition 7" class="form-control input-md" type="text">    
				  </div>
				</div>
				
				<div class="form-group">
				  <label class="col-md-2 control-label" for=""></label>
				  <div class="col-md-6">
				    <button type="submit" class="btn btn-success">Sauvegarder</button>
				    <button type="reset" class="btn btn-warning">Rétablir</button>
				    <a href="<%=request.getContextPath()%>/VueQuestionQuizAdmin.do" class="btn btn-default">Annuler</a>
				  </div>
				</div>
			</fieldset>
		</form>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>