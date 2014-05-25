package com.qa.reporting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import com.qa.testcomponets.TestFactory;

public class ApiReporting {
	
	public static String URL; 
	public static String ContentType;
	
	public void getURL()

	{
		
		Properties objLogging = new LoggingProperties().loadLoggingProperties("C://LSIReports//Properties//Database.properties");
		URL = objLogging.getProperty("API_URL");
		ContentType = objLogging.getProperty("ContentType");
		
		
	}

		 
		// http://localhost:8080/RESTfulExample/json/product/post
		public void sendAPI(TestFactory objTestfacFactory)
		
		{
	 
		  try {
			  
			getURL();
			  
			  
			System.out.println("API Report"+objTestfacFactory.objTestCase.getTestCase_Name()
					            +"Status"+objTestfacFactory.objTestCase.getTestCase_Status());
			
			String input = "{\"testcase_name\":\""+objTestfacFactory.objTestCase.getTestCase_Name()+"\",";
			       input+= "\"testcase_module\":\""+objTestfacFactory.objTestSuite.getTestModuleName()+"\",";
			       input+= "\"test_case_ID\":\""+objTestfacFactory.objTestCase.getTestCase_Name()+"\",";
			       input+= "\"testcase_status\":\""+objTestfacFactory.objTestCase.getTestCase_Status()+"\",";
			       input+= "\"cycle\":\""+objTestfacFactory.objTestRun.getRelease_ID()+"\",";
			       input+= "\"project\":\""+objTestfacFactory.objTestSuite.projectName+"\",";
			       input+= "\"testcase_type\":\""+objTestfacFactory.objTestCase.getTest_Type()+"\",";
			       input+= "\"browser\":\""+objTestfacFactory.objTestRun.browser+"\"}";
	 
			URL url = new URL(URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", ContentType);
	 
			//String input = "{\"module_name\": \"ModuleName2\",\"test_case_name\": \"Test case Name\"}";
	 
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
	 
			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
			}
	 
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
	 
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
	 
			conn.disconnect();
	 
		  } catch (MalformedURLException e) {
	 
			e.printStackTrace();
	 
		  } catch (IOException e) {
	 
			e.printStackTrace();
	 
		 }
	 
		}
}
