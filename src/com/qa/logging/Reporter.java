package com.qa.logging;

import java.io.File;
import java.io.IOException;

import com.qa.reporting.LoggingProperties;
import com.qa.testcomponets.TestFactory;


public class Reporter extends Results {
	
//	TestCase objTestCase = new TestCase();
//	TestRun objTestRun = new TestRun();
//	TestProperties objTestProperties = new TestProperties();
//	TestSuite objTestSuite = new TestSuite();
	
	TestFactory objTestfacFactory = new TestFactory();
	Results objResults = new Results();
	
	

	
	
     /* Start Test Run */
	
	public void startTestRun()
	{
		
		LoggingProperties objLoggingProps = new LoggingProperties();
		
		
		objTestfacFactory.objTestSuite.setProjectName
		                               (objLoggingProps.loadLoggingProperties("/Release.properties").getProperty("projectName"));
		objTestfacFactory.objTestRun.setRelease_ID
		                               (objLoggingProps.loadLoggingProperties("/Release.properties").getProperty("releaseID"));
		objTestfacFactory.objTestRun.setBrowser
		                               (objLoggingProps.loadLoggingProperties("/Release.properties").getProperty("browser"));
		
		System.out.println("Project Name Set to "+objTestfacFactory.objTestSuite.getProjectName());
		
	}

	
	// Start Test Module
	
	public void startSummaryReport() throws IOException
	{
		startTestRun();
		
		new File("target/"+objTestfacFactory.objTestRun.getRelease_ID()+"/HTMLResults/").mkdirs();
		
	    objResults.createSummaryHeader("target/"+objTestfacFactory.objTestRun.getRelease_ID()+"/HTMLResults/SummaryHtmlfile.html");
	 	
	}
	
	public void endSummaryReport()
	{
	objResults.closeSummary();
	}
	
    // Start Test case method
	public void startTestCase(String testCaseName){
	
		
	try {
	
		objTestfacFactory.objTestCase.setTestCase_Name(testCaseName);
		setDirectories();
		
		
	    objResults.createTestCaseHeader(objTestfacFactory.objTestProperties.htmlResultTargetDirectory
			                        +"\\"+objTestfacFactory.objTestCase.getTestCase_Name()
			                        +".html", objTestfacFactory.objTestProperties.screenShotDirectory, 
			                        objTestfacFactory.objTestCase,objTestfacFactory.objTestRun,objTestfacFactory.objTestSuite);
	
	objResults.testcaseheader_consol(objTestfacFactory.objTestProperties.htmlResultTargetDirectory
			                        +"\\"+objTestfacFactory.objTestCase.getTestCase_Name()+".html");
	
		}
		
		catch (Exception e)
		{
			
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
		String timestamp = "Run_"+Util.getCurrentDatenTime("MM-dd-yyyy")+"_"+Util.getCurrentDatenTime("hh-mm-ss_a");
		
		objTestfacFactory.objTestProperties.setHtmlResultTargetDirectory("target/"
		                                                +objTestfacFactory.objTestRun.getRelease_ID()+"/HTMLResults/"
				                                        +objTestfacFactory.objTestRun.getBrowser()+"/"
		                                                +objTestfacFactory.objTestSuite.getTestModuleName()+"/"
		                                                +objTestfacFactory.objTestCase.getTestCase_Name()+"/"
		                                                +timestamp);
		// Set Summary Path
		
		
		
		//  Set Results Path
		
		new File(objTestfacFactory.objTestProperties.getHtmlResultTargetDirectory()).mkdirs();
		
		
		// Set Screen Shots
		
		objTestfacFactory.objTestProperties.screenShotDirectory = objTestfacFactory.objTestProperties.htmlResultTargetDirectory+"\\ScreenShots";
		new File(objTestfacFactory.objTestProperties.screenShotDirectory).mkdirs();	
		
	}
	
	/* */
	
	public void loggerModules()
	{
		
		LoggingProperties objLoggingProps = new LoggingProperties();
		String databaseLogging = objLoggingProps.loadLoggingProperties("/Logging.properties").getProperty("database").toString();
		
		
		if(databaseLogging.equalsIgnoreCase("true"))
		{
			System.out.println("database logging set to true");
		}
		
	}
	
}
