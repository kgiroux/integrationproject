<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Administrer quiz</title>
<link href="Ressources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
<link href="Ressources/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type ="text/css">
<link href="Ressources/bootstrap/css/style.css" rel="stylesheet" type ="text/css">
<link href="Ressources/fonts/font-awesome.min.css" rel="stylesheet" type ="text/css">
<script src="Ressources/Jquery/jquery.min.js"></script>
<script src="Ressources/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<h1>Ajouter une question</h1>
	<hr>
		<form class="container form-horizontal contform" method="post" action="<%=request.getContextPath()%>/EnregistrerQuestion.do">
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
				  <label class="col-md-2 control-label" for="p1">Proposition 1 : </label>  
				  <div class="col-md-8">
				  <input id="p1" name="p1" placeholder="proposition 1" class="form-control input-md" required="" type="text">    
				  </div>
				</div>
				<div class="form-group">
				  <label class="col-md-2 control-label" for="p2">Proposition 2 : </label>  
				  <div class="col-md-8">
				  <input id="p2" name="p2" placeholder="propostion 2" class="form-control input-md" required="" type="text">    
				  </div>
				</div>
				<div class="form-group">
				  <label class="col-md-2 control-label" for="p3">Proposition 3 : </label>  
				  <div class="col-md-8">
				  <input id="p3" name="p3" placeholder="proposition 3" class="form-control input-md" required="" type="text">    
				  </div>
				</div>
				<div class="form-group">
				  <label class="col-md-2 control-label" for="p4">Proposition 4 : </label>  
				  <div class="col-md-8">
				  <input id="p4" name="p4" placeholder="proposition 4" class="form-control input-md" required="" type="text">    
				  </div>
				</div>
				<div class="form-group">
				  <label class="col-md-2 control-label" for="p5">Proposition 5 : </label>  
				  <div class="col-md-8">
				  <input id="p5" name="p5" placeholder="proposition 5" class="form-control input-md" required="" type="text">    
				  </div>
				</div>
				<div class="form-group">
				  <label class="col-md-2 control-label" for="p6">Proposition 6 : </label>  
				  <div class="col-md-8">
				  <input id="p6" name="p6" placeholder="proposition 6" class="form-control input-md" required="" type="text">    
				  </div>
				</div>
				<div class="form-group">
				  <label class="col-md-2 control-label" for="p7">Proposition 7 : </label>  
				  <div class="col-md-8">
				  <input id="p7" name="p7" placeholder="proposition 7" class="form-control input-md" required="" type="text">    
				  </div>
				</div>
				<div class="form-group">
				  <label class="col-md-2 control-label" for="p7">Proposition 8 : </label>  
				  <div class="col-md-8">
				  <input id="p7" name="p7" placeholder="proposition 7" class="form-control input-md" required="" type="text">    
				  </div>
				</div>
				<div class="form-group">
				  <label class="col-md-2 control-label" for=""></label>
				  <div class="col-md-6">
				    <button type="submit" class="btn btn-primary">Ajouter</button>
				    <button type="reset" class="btn btn-primary">Rétablir</button>
				    <a href="/quiz/questionQuizAdmin.jsp"><button type="button" class="btn btn-primary">Annuler</button></a>
				  </div>
				</div>
				<hr>
			</fieldset>
		</form>
	
	<br>
</body>
</html>