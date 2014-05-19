package com.qa.test;

import java.io.IOException;
import org.testng.annotations.Test;
import com.qa.logging.Reporter;
import com.qa.logging.Results.Status;

@Test
public class testRun {
	
	Reporter rs = new Reporter();
	
	public void testHtml() throws IOException
	{
		rs.startSummaryReport();
		
		rs.startTestModule("Application");
		rs.startTestCase("Application_UI");
		rs.htmllog("Step1", "Step2", Status.PASS);
		rs.htmllog("Step1", "Step2", Status.PASS);
		rs.htmllog("Step1", "Step2", Status.PASS);
		rs.htmllog("Step1", "Step2", Status.PASS);
		rs.endTestCase();
		
		rs.startTestCase("Application_1");
		rs.htmllog("Step1", "Step2", Status.PASS);
		rs.htmllog("Step1", "Step2", Status.PASS);
		rs.htmllog("Step1", "Step2", Status.FAIL);
		rs.htmllog("Step1", "Step2", Status.PASS);
		rs.endTestCase();
		
		
		rs.startTestCase("Application_2");
		rs.htmllog("Step1", "Step2", Status.PASS);
		rs.htmllog("Step1", "Step2", Status.PASS);
		rs.htmllog("Step1", "Step2", Status.PASS);
		rs.htmllog("Step1", "Step2", Status.PASS);
		rs.endTestCase();
		
		rs.startTestCase("Application_3");
		rs.htmllog("Step1", "Step2", Status.PASS);
		rs.htmllog("Step1", "Step2", Status.PASS);
		rs.htmllog("Step1", "Step2", Status.FAIL);
		rs.htmllog("Step1", "Step2", Status.PASS);
		rs.endTestCase();
		
		
		rs.startTestCase("Application_4");
		rs.htmllog("Step1", "Step2", Status.PASS);
		rs.htmllog("Step1", "Step2", Status.PASS);
		rs.htmllog("Step1", "Step2", Status.PASS);
		rs.htmllog("Step1", "Step2", Status.PASS);
		rs.endTestCase();
		
		
		rs.endSummaryReport();
	
		
	}
	

}
