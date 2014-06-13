package com.qa.testcomponets;

/**
 * @author Prasanth
 *
 */


public class TestProperties {
	
	public String htmlResultTargetDirectory;
	public String screenShotDirectory;
	public String dataBaseProperties;
	public String aPIProperties;
	public String emailProperties;
	public String log4jProperties;
	
	
	/* Constructor Class */
	public TestProperties()
	{
		htmlResultTargetDirectory = null;
		
	}

	/* Getters and Setters */
	public String getHtmlResultTargetDirectory() {
		return htmlResultTargetDirectory;
	}

	public void setHtmlResultTargetDirectory(String htmlResultTargetDirectory) {
		this.htmlResultTargetDirectory = htmlResultTargetDirectory;
	}

	public String getScreenShotDirectory() {
		return screenShotDirectory;
	}

	public void setScreenShotDirectory(String screenShotDirectory) {
		this.screenShotDirectory = screenShotDirectory;
	}


}
