package fr.esigelec.quiz.controleur;

import org.apache.log4j.Logger;

/**
 * TestLogger
 * (could be deleted if no use)
 * @author Wenfeng
 *
 */
public class TestLogger {
	private static final Logger testLogger = Logger.getLogger(TestLogger.class);

	public static void main(String[] args) {
		testLogger.debug("Debug log");
		testLogger.info("Info log");
		testLogger.error("error log");
	}

}
