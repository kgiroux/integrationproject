package fr.esigelec.quiz.controleur;

import servletunit.struts.MockStrutsTestCase;

public class AjouterQuizActionTest extends MockStrutsTestCase {
	

	public AjouterQuizActionTest() {
		super();
	}

	public AjouterQuizActionTest(String testName) {
		super(testName);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setRequestPathInfo("/AjouterQuiz");
	}
	
	public void testWithoutAuthSession_ExpectedBackToLoginPage() {
		actionPerform();
		assertEquals(null, getSession().getAttribute("personne"));
		verifyForwardPath("/indexAdmin.jsp");
		verifyActionMessages(new String[] {"err.session.auth.notfound"});
	}
	
	public void testTest() {
		assertEquals(1, 1);
	}
	

}
