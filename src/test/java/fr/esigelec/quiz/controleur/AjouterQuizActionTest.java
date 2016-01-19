package fr.esigelec.quiz.controleur;

import java.io.File;

import fr.esigelec.quiz.dto.Personne;
import servletunit.struts.MockStrutsTestCase;

public class AjouterQuizActionTest extends MockStrutsTestCase {

	public AjouterQuizActionTest(String testName) {
		super(testName);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setConfigFile("/WEB-INF/struts-config.xml");
		setContextDirectory(new File("WebContent"));
		setRequestPathInfo("/AjouterQuiz");
	}
	
	public void testWithoutAuthSession_ExpectedBackToLoginPage() {
		actionPerform();
		assertEquals(null, getSession().getAttribute("personne"));
		verifyForwardPath("/indexAdmin.jsp");
		verifyActionErrors(new String[] {"err.session.auth.notfound"});
	}
	
	public void testSuccessfulAjouter() {
		Personne p = new Personne();
		p.setDroits(Personne.ADMIN);
		getSession().setAttribute("personne", p);
//		request.setAttribute("listeQuestions", o);
		
	}
	
	public void testWithoutParameter_ExpectedBackToLastPage() {
//		setConfigFile("/WEB-INF/struts-config.xml");
//		setRequestPathInfo("/AjouterQuiz");
//		try {
//			setUp();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Personne p = new Personne();
		p.setDroits(Personne.ADMIN);
		getSession().setAttribute("personne", p);
		actionPerform();
		assertNotNull(getSession().getAttribute("personne"));
		assertNull(request.getParameter("listeQuestions"));
		assertNull(request.getParameter("libelleQuiz"));
//		verifyForward("/erreur.jsp");
		verifyActionErrors(new String[] {"err.inputs.null"});
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	
	
}
