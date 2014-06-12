package com.qa.reporting;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.qa.logging.Reporter;


public class LoggingProperties {
	
	boolean setLog4j,setHtml,setDBreport = false;

	/****** Load logging properties from setup *********/ 
	
	
	public Properties loadLoggingProperties(String propertiesPath)
	{
		/*   Load Logging Properties */
		
		Properties loggingProperties = new Properties();
		try {
			//loggingProperties.load(BaseWrapper.class.getResourceAsStream("/Logging.properties"));
			loggingProperties.load(new FileInputStream(propertiesPath));
		} catch (IOException e) {
			System.out.println("Error while importing logging properties from path "+propertiesPath );
			e.printStackTrace();
		}
		
		return loggingProperties;
		
	}
	
	/****** Load logging properties for database *********/ 
	
	public Properties loadDatabaseProperties(String propertiesPath)
	{
          /*   Load Database logging Properties */
		
		Properties loggingDatabaseProperties = new Properties();
		try {
			loggingDatabaseProperties.load(Reporter.class.getResourceAsStream("/Database.properties"));
		} catch (IOException e) {
			System.out.println("Error while importing database properties");
			e.printStackTrace();
		}
		
		return loggingDatabaseProperties;
		
	}
	
    /****** Load logging properties *********/ 
	
	public Properties loadLogProperties(String propertiesPath)
	{
          /*   Load Database logging Properties */
		
		Properties loggingDatabaseProperties = new Properties();
		try {
			loggingDatabaseProperties.load(Reporter.class.getResourceAsStream("/Database.properties"));
		} catch (IOException e) {
			System.out.println("Error while importing database properties");
			e.printStackTrace();
		}
		
		return loggingDatabaseProperties;
		
	}
	

}
