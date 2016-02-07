<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>


<footer>
<jsp:include page="/mention_legale.jsp"></jsp:include>

SESSION : <br>
<table>
<%Enumeration en=session.getAttributeNames();
while(en.hasMoreElements()){%>
<%String cle=(String)en.nextElement(); %>	

<tr><td><%=cle %></td><td><%=session.getAttribute(cle) %></td></tr>
	
<%
}
%>
</table>


</footer>
