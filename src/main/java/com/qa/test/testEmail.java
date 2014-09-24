package com.qa.test;

import java.io.IOException;
import org.testng.annotations.Test;
import com.qa.reporting.EmailReporting;
import com.qa.testcomponets.TestFactory;

public class testEmail {
	

	public TestFactory objTs = new TestFactory();
	public EmailReporting objEmailRep = new EmailReporting();
	
	@Test
	public void tet() throws IOException
	{

		
	objEmailRep.sendEmail();
		
	}

}
