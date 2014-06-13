package com.qa.testcomponets;

public class TestSuite {
	
	public String projectName;
	public String testModuleName;
	public int totalTestCases;
	public int passedTestCases;
	public int failedTestCases;
	public int skippedTestCases;
	
	/* Constructor for Null values */
	
	public TestSuite()
	{
		projectName = null;
		testModuleName = null;
		
	}
	
	
	/* Getters and Setters */
	
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getTestModuleName() {
		return testModuleName;
	}
	public void setTestModuleName(String testModuleName) {
		this.testModuleName = testModuleName;
	}
	public int getTotalTestCases() {
		return totalTestCases;
	}
	public void setTotalTestCases(int totalTestCases) {
		this.totalTestCases = totalTestCases;
	}
	public int getPassedTestCases() {
		return passedTestCases;
	}
	public void setPassedTestCases(int passedTestCases) {
		this.passedTestCases = passedTestCases;
	}
	public int getFailedTestCases() {
		return failedTestCases;
	}
	public void setFailedTestCases(int failedTestCases) {
		this.failedTestCases = failedTestCases;
	}
	public int getSkippedTestCases() {
		return skippedTestCases;
	}
	public void setSkippedTestCases(int skippedTestCases) {
		this.skippedTestCases = skippedTestCases;
	}

	

}
