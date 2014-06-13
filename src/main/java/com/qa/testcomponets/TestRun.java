package com.qa.testcomponets;


/**
 * @author Prasanth
 *
 */


public class TestRun {
	
	public String environment;
	public String test_URL;
	public String browser;
	public String release_ID;
	public String testRun_ID;
	public String operating_System;
	
	/* Constructor set to Null */
	
	
	public TestRun()
	{
		 environment = null;
		 test_URL = null;
		 browser = null;
		 release_ID = null;
		 testRun_ID = null ;
		 operating_System = null; 
	}
	
	/* Getters and Setters for Test Run */
	public String getEnvironment() {
		return environment;
	}
	
	
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	
	
	public String getTest_URL() {
		return test_URL;
	}
	
	
	public void setTest_URL(String test_URL) {
		this.test_URL = test_URL;
	}
	
	
	public String getBrowser() {
		return browser;
	}
	
	
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	
	
	public String getRelease_ID() {
		return release_ID;
	}
	
	
	public void setRelease_ID(String release_ID) {
		this.release_ID = release_ID;
	}
	
	
	public String getTestRun_ID() {
		return testRun_ID;
	}
	
	
	public void setTestRun_ID(String testRun_ID) {
		this.testRun_ID = testRun_ID;
	}
	
	public String getOperating_System() {
		return operating_System;
	}
	
	
	public void setOperating_System(String operating_System) {
		this.operating_System = operating_System;
	}
	

}
