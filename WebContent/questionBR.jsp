<!-- @author : TIONO KEVIN & HUANG Mincong -->
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.esigelec.quiz.dto.*" %>   
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<jsp:include page="/header.jsp" />
<div >
  <h2 class="question">CONSULTER LES QUIZ</h2>
 <hr><br> 
 <%
	// TODO: change to JSTL if possible
	List<Personne> personnes = (List<Personne>) session.getAttribute("classement");
	Personne joueur = (Personne) session.getAttribute("personne");
	// cherche joueur's rank
	int rank = -1;
	int size = personnes.size();
	for(int i = 0; i < size; i++) {
	    if (personnes.get(i).getId() == joueur.getId()) {
	        int index = i;    // index starts at 0
	        rank = index + 1; // rank starts at 1
	    }
	}
 %>
 <p class="question"><strong>Classement </strong> : <%=rank %> / <%=size %> joueurs</p> 
 <!-- <p class="question"><strong>Reste</strong> : 0 secondes</p> -->
 <br>
 <%--
	
	Proposition p1 = new Proposition("javax.http.servlet.HttpServlet");
	Proposition p2 = new Proposition("javax.servlet.HttpServlet");
	Proposition p3 = new Proposition("java.http.servlet.ServletHttp");
	Proposition p4 = new Proposition("javax.http.servlet.ServletHttp");

	List<Proposition> l=new ArrayList<Proposition>();
	l.add(p1);
	l.add(p2);
	l.add(p3);
	l.add(p4);
	Question q = new Question();
	q.setLibelle("De quelle classe doit hériter une servlet ?");
	q.setId(1);
	q.setBonneReponse(p1);
	q.setListePropositions(l);
	
	Personne p = new Personne();
	Personne pe1 = new Personne(-1, "TIONO","Kevin","tionokevin@live.fr","kev87dc",0);
	pe1.setId(1);
	Personne pe2 = new Personne(-1, "TIONO","Sandra","tionosady@live.fr","sand08",0);
	pe2.setId(2);
	Personne pe3 = new Personne(-1, "JACKSON","Michael","mj.thebest@live.fr","mjforever",0);
	pe3.setId(3);
	Personne pe4 = new Personne(-1, "HOLLANDE","François","f.hollande@live.fr","cuculolo",0);
	pe4.setId(4);
	Personne pe5 = new Personne(-1, "KOUBOURA","Anastasie","anaskoub@live.fr","tatiouf",0);
	pe5.setId(5);
	Personne pe6 = new Personne(-1, "LI","Jet","jet.li@live.fr","hongfeihung",0);
	pe6.setId(6);
	
	List<Personne> le = new ArrayList<Personne>();
	le.add(pe1);
	le.add(pe2);
	le.add(pe3);
	le.add(pe4);
	le.add(pe5);
	le.add(pe6);
	
	--%>
  <table class="table table-bordered table-hover " style="width:70%">
    <thead>
      <tr>
        <th class="question"><c:out value="${question.libelle}" /></th>
        <th class="question">%</th>
        <th class="question">Résultat</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="proposition" items="${question.listePropositions}">
        <tr class="question">
          <td><c:out value="${proposition.libelle}" /></td>
          <td><c:out value="${proposition.pourcentage}" />%</td>
          <c:if test="${proposition.estBonneReponse == false}"><td style="background-color:red"></td></c:if>
          <c:if test="${proposition.estBonneReponse == true}"><td style="background-color:green"></td></c:if>
        </tr>
      </c:forEach>
    </tbody>
  </table>
  <br>
  <h2 class="question">RESULTATS</h2>
  <hr>
  <br>
  <table class="table table-bordered table-hover " style="width:70%">
    <thead>
      <tr style="background-color:#D8D8D8">
        <th class="question">N°</th>
        <th class="question">Nom</th>
        <th class="question">Prénom</th>
		<th class="question">Score</th>
      </tr>
    </thead>
    <tbody>
	  <c:forEach var="personne" items="${classement}">
        <tr class="question">
          <td><c:out value="${personne.id}" /></td>
          <td><c:out value="${personne.nom}" /></td>
          <td><c:out value="${personne.prenom}" /></td>
          <td><c:out value="${personne.score}" /></td>
        </tr>
	  </c:forEach>
    </tbody>
  </table>
  <a href="${pageContext.request.contextPath}/Jouer.do" class="btn btn-primary">question suivante</a>
</div>
<jsp:include page="/footer.jsp" />