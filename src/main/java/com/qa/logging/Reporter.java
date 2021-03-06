package com.qa.logging;

import java.io.File;
import java.io.IOException;

import org.json.JSONException;

import com.qa.reporting.ApiReporting;
import com.qa.reporting.DataBaseReporting;
import com.qa.reporting.EmailReporting;
import com.qa.reporting.LoggingProperties;
import com.qa.testcomponets.TestFactory;

public class Reporter extends Results {

	// TestCase objTestCase = new TestCase();
	// TestRun objTestRun = new TestRun();
	// TestProperties objTestProperties = new TestProperties();
	// TestSuite objTestSuite = new TestSuite();

	public static String databaseLogger;
	public static String emailLogging;
	public static String apiLogging;
	public static String log4jLogging;
	public static String htmlLogging;
	
	public static final String LOG_PROPERTY_PATH="reportFiles";

	TestFactory objTestfacFactory = new TestFactory();
	Results objResults = new Results();
	DataBaseReporting objDBReports = new DataBaseReporting();
	EmailReporting objEmailRep = new EmailReporting();
	ApiReporting objApiReporting = new ApiReporting();

	/* Read Reporting properties */

	public void loggerModules() {

		LoggingProperties objLoggingProps = new LoggingProperties();
		databaseLogger = objLoggingProps
				.loadLoggingProperties(LOG_PROPERTY_PATH+"//Logging.properties")
				.getProperty("database").toString();
		htmlLogging = objLoggingProps
				.loadLoggingProperties(LOG_PROPERTY_PATH+"//Logging.properties")
				.getProperty("htmllogs").toString();
		log4jLogging = objLoggingProps
				.loadLoggingProperties(LOG_PROPERTY_PATH+"//Logging.properties")
				.getProperty("log4j").toString();
		apiLogging = objLoggingProps
				.loadLoggingProperties(LOG_PROPERTY_PATH+"//Logging.properties")
				.getProperty("api").toString();
		emailLogging = objLoggingProps
				.loadLoggingProperties(LOG_PROPERTY_PATH+"//Logging.properties")
				.getProperty("email").toString();

	}

	/* Execute database reporting */

	public void databaseReporting(TestFactory testFactory)

	{
		if (databaseLogger.equalsIgnoreCase("true"))
			objDBReports.updateTestResult(testFactory);
	}

	/* Trigger Email reporting */

	public void sendEmailReport() {
		if (emailLogging.equalsIgnoreCase("true"))
			objEmailRep.sendEmail();
	}
	
	
	/* Trigger API reporting */

	public void sendAPIReport(TestFactory testFactory) throws IOException, JSONException 
	
	{
		if (apiLogging.equalsIgnoreCase("true"))
			objApiReporting.updateJIRA(testFactory);
			objApiReporting.sendAPI(testFactory);
		    
		
	}

	
	

	/* Start Test Run */

	public void startTestRun()

	{

		loggerModules();
		
		LoggingProperties objLoggingProps = new LoggingProperties();
		objDBReports.createDatabase();
		objTestfacFactory.objTestSuite.setProjectName(objLoggingProps
				.loadLoggingProperties(LOG_PROPERTY_PATH+"//Release.properties").getProperty(
						"projectName"));
		objTestfacFactory.objTestRun.setRelease_ID(objLoggingProps
				.loadLoggingProperties(LOG_PROPERTY_PATH+"//Release.properties").getProperty(
						"releaseID"));
		objTestfacFactory.objTestRun.setBrowser(objLoggingProps
				.loadLoggingProperties(LOG_PROPERTY_PATH+"//Release.properties").getProperty(
						"browser"));
		objTestfacFactory.objTestCase.setTest_Type(objLoggingProps
				.loadLoggingProperties(LOG_PROPERTY_PATH+"//Release.properties").getProperty(
						"browser"));

		System.out.println("Project Name Set to "
				+ objTestfacFactory.objTestSuite.getProjectName());

	}

	// Start Test Module

	public void startSummaryReport() throws IOException {
		startTestRun();
		new File(LOG_PROPERTY_PATH+"//CustomReports/" + objTestfacFactory.objTestRun.getRelease_ID()
				+ "/HTMLResults/").mkdirs();
		objResults.createSummaryHeader(LOG_PROPERTY_PATH+"//CustomReports/"
				+ objTestfacFactory.objTestRun.getRelease_ID()
				+ "/HTMLResults/SummaryHtmlfile.html");

	}

	/* End Summary Reporting */

	public void endSummaryReport() {

		objResults.closeSummary();
		/* Send Email Report after closing Summary */
		sendEmailReport();
		

	}
	
	public void setTestCaseID(String testCaseID)
	{
		objTestfacFactory.objTestCase.setTestCaseID(testCaseID);
	}

	// Start Test case method
	public void startTestCase(String testCaseName) {

		try {

			objTestfacFactory.objTestCase.setTestCase_Name(testCaseName);
			setDirectories();

			objResults
					.createTestCaseHeader(
							objTestfacFactory.objTestProperties.getHtmlResultTargetDirectory()
									+ "\\"
									+ objTestfacFactory.objTestCase
											.getTestCase_Name() + ".html",
							objTestfacFactory.objTestProperties.screenShotDirectory,
							objTestfacFactory.objTestCase,
							objTestfacFactory.objTestRun,
							objTestfacFactory.objTestSuite, objTestfacFactory);

			objResults
					.testcaseheader_consol(objTestfacFactory.objTestProperties.htmlResultTargetDirectory
							+ "\\"
							+ objTestfacFactory.objTestCase.getTestCase_Name()
							+ ".html");

		}

		catch (Exception e) {

		}
	}

	// Start Test Module method

	public void startTestModule(String testModule)

	{

		objTestfacFactory.objTestSuite.setTestModuleName(testModule);

	}

	/* Create All Paths and Folders required for reporting */

	public void setDirectories() throws IOException

	{

		// Create Folders for Html Results
		objTestfacFactory.objTestProperties.setHtmlResultTargetDirectory(null);
		String timestamp = "Run_" + Util.getCurrentDatenTime("MM-dd-yyyy")
				+ "_" + Util.getCurrentDatenTime("hh-mm-ss_a");

		objTestfacFactory.objTestProperties
				.setHtmlResultTargetDirectory(LOG_PROPERTY_PATH+"//CustomReports/"
						+ objTestfacFactory.objTestRun.getRelease_ID()
						+ "/HTMLResults/"
						+ objTestfacFactory.objTestRun.getBrowser() + "/"
						+ objTestfacFactory.objTestSuite.getTestModuleName()
						+ "/"
						+ objTestfacFactory.objTestCase.getTestCase_Name()
						+ "/" + timestamp);
		// Set Summary Path

		// Set Results Path

		new File(
				objTestfacFactory.objTestProperties
						.getHtmlResultTargetDirectory()).mkdirs();

		// Set Screen Shots

		objTestfacFactory.objTestProperties.screenShotDirectory = objTestfacFactory.objTestProperties.htmlResultTargetDirectory
				+ "\\ScreenShots";
		new File(objTestfacFactory.objTestProperties.screenShotDirectory)
				.mkdirs();

	}

	/* */

}
