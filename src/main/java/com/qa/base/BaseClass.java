
	
	package com.qa.base;

	import java.io.IOException;
	import java.util.Set;
	import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.ie.InternetExplorerDriver;
	import org.openqa.selenium.remote.DesiredCapabilities;
	import org.openqa.selenium.support.ui.Wait;
	import org.openqa.selenium.support.ui.WebDriverWait;

	import com.qa.logging.Reporter;
	import com.qa.logging.Results.Status;
	import com.qa.reporting.LoggingProperties;



	public class BaseClass {
		
		public WebDriver driver;
		public Reporter logger = new Reporter();
		LoggingProperties objLoggingProps = new LoggingProperties();
		public String currentBrowser ; 
		
		// Initialize webdriver and select browser
		
		
		public WebDriver iniBrowser()
		
		{

			
		 try {  
		    
		     currentBrowser = objLoggingProps
					.loadLoggingProperties("reportFiles//Release.properties").getProperty(
							"browser");
				
			driver = selectBrowser(currentBrowser);
			} catch (IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
			}
			
			// driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);	
			
			return driver;
			
			
		}
		
	    
		// Set  browser environment settings 
		
		public WebDriver setEnvironmentProperties(){
			
			
			return driver;
		}
		
		public WebDriver setDriveroptions()
		{
		
		    driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);	 
	       
		    return driver;
		}
		
		/* Method to select Browser */
		
	     public WebDriver selectBrowser(String browser) throws IOException
		
		{
			
			//SeleniumBase oSeleniumBase = new SeleniumBase();
			//oSeleniumBase.loadEnvData();

			
			try{	
			
			if (browser.equalsIgnoreCase("fireFox"))
			{
				driver = new FirefoxDriver();
			}
			else if (browser.equalsIgnoreCase("internetExplorer"))
			{
				
				System.setProperty("webdriver.ie.driver", "src/test/resources/IEDriverServer.exe");
				driver = new InternetExplorerDriver();	
			
			}
			else if (browser.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
				
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			    ChromeOptions options = new ChromeOptions();
			    options.addArguments("test-type");
			    capabilities.setCapability("chrome.binary","src/test/resources/chromedriver.exe");
			    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				
				//String path = this.getClass().getClassLoader().getResource("chromedriver.exe").getPath();
				//System.setProperty("webdriver.chrome.driver",path.substring(1));
				
				//System.out.println("path "+path.substring(1));
				System.out.println("Crome Driver");
				driver = new ChromeDriver();
				driver.get("http://www.google.com");
				
			}
			
//			    if (browser.equalsIgnoreCase("sauce"))
//			{
//				System.out.println("Starting on Sauce");
//				
//				//abstractDriver objAD = new abstractDriver(driver);
//				//capabillities = DesiredCapabilities.chrome();
//				 capabillities = DesiredCapabilities.firefox();
//				 capabillities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
//				 this.driver = new RemoteWebDriver(
//						  new URL("http://prasanthr:1ed6d489-f86d-440e-9cc9-eed4b3109c84@ondemand.saucelabs.com:80/wd/hub"),capabillities);
//				
				
			}
			
			catch(Exception e)
			{
				logger.htmllog("Error-Start browser "+browser, e.toString(), Status.FAIL, driver);
				logger.htmllog("Script Execution","Script Execution is not started in "+browser,Status.FAIL, driver);
			}
			
			return driver;
		}
	     
	}

	
