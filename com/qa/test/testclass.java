package com.qa.test;

import org.testng.annotations.Test;

import com.qa.reporting.DataBaseReporting;
import com.qa.testcomponets.TestFactory;

public class testclass {
	
	
	
	public TestFactory objTs = new TestFactory();
	
	@Test
	public void tet()
	{

		objTs.objTestCase.setTestCase_Name("Test Sample 1");
		objTs.objTestSuite.setTestModuleName("Sample Module 1");
		objTs.objTestCase.setTestCase_Status("true");
		objTs.objTestCase.setTest_Type("API Test");
		objTs.objTestRun.setBrowser("Chrome");
		
		DataBaseReporting dsRep = new DataBaseReporting();
		
		//dsRep.createDatabase(objTs);
		dsRep.updateTestResult(objTs);
		
		
		
	}
	

}
