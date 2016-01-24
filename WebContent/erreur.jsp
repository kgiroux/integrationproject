
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<jsp:include page="/header.jsp"></jsp:include>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<html:html>
<body>
<div class="container" style="text-align:center; padding:50px;">
<div>
<img src="Ressources/images/warn.png" alt="warning" title="avertissement" />
</div>
<div >
<h1><bean:message key="erreur.globale.title"/></h1>
<p><bean:message key="erreur.globale.message"/> click <a href="<%=request.getContextPath() %>">here</a> if you want to go back to index</p>
</div>
</div>

</body>
</html:html>
