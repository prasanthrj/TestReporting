package com.qa.test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;
import com.qa.logging.Reporter;
import com.qa.logging.Results.Status;



public class testRun {
	
	Reporter rs = new Reporter();
	WebDriver browser;
	
	@Test
	public void testHtml() throws IOException
	{
		
		BaseClass baseClass = new BaseClass();
		
	    browser = baseClass.iniBrowser();

		rs.startSummaryReport();
		browser.get("http://www.google.com");
		
		rs.startTestModule("Application");
		rs.setTestCaseID("TES-2");
		rs.startTestCase("Application_UI");
		rs.htmllog("Step1", "Step2", Status.PASS, browser);
		rs.htmllog("Step1", "Step2", Status.PASS, browser);
		rs.htmllog("Step1", "Step2", Status.PASS, browser);
		rs.htmllog("Step1", "Step2", Status.PASS, browser);
		rs.endTestCase();
		
		browser.get("http://www.linkedin.com");
		
		rs.startTestCase("Application_1");
		rs.setTestCaseID("TES-3");
		rs.htmllog("Step1", "Step2", Status.PASS, browser);
		rs.htmllog("Step1", "Step2", Status.PASS, browser);
		rs.htmllog("Step1", "Step2", Status.PASS, browser);
		rs.htmllog("Step1", "Step2", Status.PASS, browser);
		rs.endTestCase();

		browser.get("http://www.facebook.com");
		
		
		rs.setTestCaseID("TES-4");
		rs.startTestCase("Application_2");
		rs.htmllog("Step1", "Step2", Status.PASS, browser);
		rs.htmllog("Step1", "Step2", Status.PASS, browser);
		rs.htmllog("Step1", "Step2", Status.FAIL, browser);
		rs.htmllog("Step1", "Step2", Status.PASS, browser);
		rs.endTestCase();
		
        browser.get("http://www.linkedin.com");
		
        rs.setTestCaseID("TES-5");
		rs.startTestCase("Application_3");
		rs.htmllog("Step1", "Step2", Status.FAIL, browser);
		rs.htmllog("Step1", "Step2", Status.PASS, browser);
		rs.htmllog("Step1", "Step2", Status.FAIL, browser);
		rs.htmllog("Step1", "Step2", Status.PASS, browser);
		rs.endTestCase();

		browser.get("http://www.facebook.com");
		
		
		rs.setTestCaseID("TES-6");
		rs.startTestCase("Application_4");
		rs.htmllog("Step1", "Step2", Status.PASS, browser);
		rs.htmllog("Step1", "Step2", Status.PASS, browser);
		rs.htmllog("Step1", "Step2", Status.FAIL, browser);
		rs.htmllog("Step1", "Step2", Status.PASS, browser);
		rs.endTestCase();
	

		rs.endSummaryReport();
	
	}
	

}
