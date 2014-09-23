/**
 * 
 */

package com.qa.testcomponets;

/**
 * @author Prasanth
 *
 */


public class TestCase {
	
	private String testCase_Name;
	private String testCase_Status;
	private String test_Steps;
	private String test_Type;
	private String testCase_Description;
	private String testCaseID;
	
	/* Constructor withi Null values */
	
	public TestCase()
	
	{
		testCase_Name=null;
		testCase_Status=null;
		test_Steps=null;
		test_Type=null;
		testCaseID=null;
		
	}
	
	
	/* Getters and Setters */
	
	
	public String getTestCaseID() {
		return testCaseID;
	}
	public void setTestCaseID(String testCase_ID) {
		this.testCaseID = testCase_ID;
	}
	
	
	public String getTestCase_Name() {
		return testCase_Name;
	}
	public void setTestCase_Name(String testCase_Name) {
		this.testCase_Name = testCase_Name;
	}
	public String getTestCase_Status() {
		return testCase_Status;
	}
	public void setTestCase_Status(String testCase_Status) {
		this.testCase_Status = testCase_Status;
	}
	public String getTest_Steps() {
		return test_Steps;
	}
	public void setTest_Steps(String test_Steps) {
		this.test_Steps = test_Steps;
	}
	public String getTest_Type() {
		return test_Type;
	}
	public void setTest_Type(String test_Type) {
		this.test_Type = test_Type;
	}


	public String getTestCase_Description() {
		return testCase_Description;
	}


	public void setTestCase_Description(String testCase_Description) {
		this.testCase_Description = testCase_Description;
	}

}
