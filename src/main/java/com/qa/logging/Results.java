package com.qa.logging;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import net.sourceforge.htmlunit.corejs.javascript.ObjToIntMap;

import org.openqa.selenium.WebDriver;

import com.qa.testcomponets.TestCase;
import com.qa.testcomponets.TestFactory;
import com.qa.testcomponets.TestRun;
import com.qa.testcomponets.TestSuite;

	public class Results  {
		
		/**
		 * Script Name   : <b>HTMLResults</b>
		 * Generated     : <b>Oct 16, 2012</b>
		 * Description   : Functional Test Script
		 *
		 * @author 222464
		 */
		 	
		 public static String SummaryHtmlfile = null;
		 public static String TestCaseHtmlfile = null;	 
		 static int Pass = 0;
		 public static int TC_Pass=0;
		 public static int TC_Fail=0;
		 static int S_No=1;
		 public static int Fail = 0;
		 static long start = 0;
		 static long end =0;
		 static String duration ="";
		 static int overallPass = 0;
		 static int overallFail = 0;
		 public static int stepNo = 1;
		 static int inc =1;
		 static String h1color="";
		 static String h2color="";
		 static String fontColor="";	 
		 public static String screenshotName=null;
		 public static String ScreenShotPath=null;
		 public static String TC_ScreenShotPath=null;
		 public static String ScreenShotPath1=null;
		 
		
		 public static String FinalTC_Status="PASS_FAIL";
		 public static boolean testCaseResult = true;
		 
		 public static String testCaseName=null;
		 public static String testModule=null;
		 private static TestFactory testFactory;
		 
		 
		 
			public enum Status {
			    PASS,FAIL,DONE
			}
			
		 	
		 public void updateResult( boolean testResult)
		 {
			 
			 
			 if(testResult == false)
			 {
				 testCaseResult = false;
			 }
			 
			 if(testResult && testCaseResult!=false)
			 {
				 testCaseResult = true;
			 } 
		 }

		 
	//#############################################################################
		//Function Name    	: setColorsforResults
		//Description     	: Function to set the requited colors for results
		//Input Parameters 	: None
		//Return Value    	: None		
	//#############################################################################

			private  void setColorsforResults()
			{
				String theme = "CLASSIC";
				if(theme.equals("AUTUMN"))
				{
					h1color="#7E5D56";
		            h2color="#EDE9CE";
		            fontColor="#F6F3E4";
				}
				else if (theme.equalsIgnoreCase("OLIVE"))
				{
					h1color="#686145";
			        h2color="#EDE9CE";
			        fontColor="#E8DEBA";
				}
				else if (theme.equalsIgnoreCase("CLASSIC"))
				{
			        h1color="#687C7D";
			        h2color="#C6D0D1";
			        fontColor="#EDEEF0";
				}
				else if (theme.equalsIgnoreCase("RETRO"))
				{
					h1color="#CE824E";
					h2color="#F3DEB1";
					fontColor="#F8F1E7";
				}
				else if (theme.equalsIgnoreCase("MYSTIC"))
				{
					 h1color="#4D7C7B";
		             h2color="#FFFFAE";
		             fontColor="#FAFAC5" ;
				}
				else if (theme.equalsIgnoreCase("SERENE"))
				{
					h1color="#7B597A";
		            h2color="#ADE0FF";
		            fontColor="#C5AFC6";
				}
				else if (theme.equalsIgnoreCase("REBEL"))
				{
					h1color="#953735";
		            h2color="#A6A6A6";
		            fontColor="#D9D9D9";
					
				}
				else if (theme.equalsIgnoreCase("qwe"))
				{
					h1color="#12579D";
			        h2color="#BCE1FB";
			        fontColor="#FFFFFF" ;
				}
				else
				{
					h1color="#B3D9FF";
			        h2color="#CCD9FF";
			        fontColor="#8A4117";
				}
			}
			
			 //#############################################################################
			//Function Name    	: createTestCaseHeader
			//Description     	: Function to create the testcase header
			//Input Parameters 	: TestCaseHtmlfile,Screenshotpath
			//Return Value    	: void		
			//Date Created		: 
			//#############################################################################

			
			public void createTestCaseHeader(String TestCaseHtmlfile,String Screenshotpath, TestCase objTestCase, TestRun objTestRun, TestSuite objTestSuite, TestFactory objTestfacFactory)
			{
				testFactory = objTestfacFactory;
				
				testCaseName = objTestCase.getTestCase_Name();
				testModule =  objTestSuite.getTestModuleName();
				
				testCaseResult=true;
				inc=1;
				ScreenShotPath = Screenshotpath;
				Results.TestCaseHtmlfile = TestCaseHtmlfile;
				FileOutputStream out; // declare a file output object
		        PrintStream p; // declare a print stream object
		        setColorsforResults();
		        try
		        {
			        // Create a new file output stream
			        // connected to "myfile.txt"
			        out = new FileOutputStream(TestCaseHtmlfile);
			
			        // Connect print stream to the output stream
			        p = new PrintStream(out);
			      
			        String header="<html><head><title>Test Case Automation Execution Results</title>";
			        header +="</head><Body>"+
					"<p align = center><table border=1 bordercolor=#000000 id=table1 width=1000 height=100 >"+
					
					"<tr><td COLSPAN = 6 bgcolor = "+h1color+">";
			        header+="<p align=center><font color="+fontColor+" size=4 face= Copperplate Gothic Bold>"+objTestSuite.projectName+" Test Result </font><font face= Copperplate Gothic Bold></font> </p>";
					//header+="<p align=center><font color="+fontColor+" size=4 face= Copperplate Gothic Bold>"+DriverScript.testcase+" Automation Execution Results </font><font face= Copperplate Gothic Bold></font> </p>";
					header +="</td></tr>"+
					"<td COLSPAN = 6 bgcolor = "+h1color+">"+
					"<p align=justify><b><font color =FFCC00 size=2 face= Verdana>DATE:"+ Util.getCurrentDatenTime("dd MMMMM yyyy")+
					"</td></tr>";
					header+="<tr bgcolor="+h2color+">"+
					"<td><b>Test Module  </b> "  +objTestSuite.getTestModuleName()+"</td>"+
					"<td><b>Test Case ID  </b>"+ objTestCase.getTestCase_Name()+"</td>"+ 
					"<td><b>Test Case Name  </b> "  +objTestCase.getTestCase_Name()+"</td>"+
					"<td><b>Browser </b>" +objTestRun.getBrowser()+"</td>"+
					"<td><b>Time  </b></td></tr>";	
					
			        p.println (header);
			
			        p.close();
			        start = Util.getLastsetTimeinmili();  //Used to calculate the total time taken to execute testcase    
		        }
		        catch (Exception ex)
		        {
		        	System.err.println ("Error writing to file");
		            ex.printStackTrace();
		        }
			}
			
			//Header for creating individual header.
			public  void createTestCaseHeader1(String TestCaseHtmlfile,String Screenshotpath,String TC_id,String TC_Desc)
			{
				inc=1;
				ScreenShotPath = Screenshotpath;
				Results.TestCaseHtmlfile = TestCaseHtmlfile;
				FileOutputStream out; // declare a file output object
		        PrintStream p; // declare a print stream object
		        setColorsforResults();
		        try
		        {
			        // Create a new file output stream
			        // connected to "myfile.txt"
			        out = new FileOutputStream(TestCaseHtmlfile);
			
			        // Connect print stream to the output stream
			        p = new PrintStream(out);
			      
			        String header="<html><head><title>Test Case Automation Execution Results</title>";
			        header +="</head><Body>"+
					"<p align = center><table border=1 bordercolor=#000000 id=table1 width=1000 height=100 >"+
					
					"<tr><td COLSPAN = 6 bgcolor = "+h1color+">";
			        header+="<p align=center><font color="+fontColor+" size=4 face= Copperplate Gothic Bold>"+TC_id+":"+TC_Desc+"</font><font face= Copperplate Gothic Bold></font> </p>";
					//header+="<p align=center><font color="+fontColor+" size=4 face= Copperplate Gothic Bold>"+DriverScript.testcase+" Automation Execution Results </font><font face= Copperplate Gothic Bold></font> </p>";
					header +="</td></tr>"+
					"<tr>"+
					"<td COLSPAN = 6 bgcolor = "+h1color+">"+
					"<p align=justify><b><font color="+fontColor+"size=2 face= Verdana>DATE:"+ Util.getCurrentDatenTime("dd MMMMM yyyy")+
					"</td></tr>";
//					header+="<tr bgcolor="+h2color+">"+
//					"<td><b>Step No</b></td>"+
//					"<td><b>Step Name</b></td>"+
//					"<td><b>Description</b></td>"+
//					"<td><b>Status</b></td>"+
//					"<td><b>Time</b></td>"+
//					"</tr>";	
					
			        p.println (header);
			
			        p.close();
			        start = Util.getLastsetTimeinmili();  //Used to calculate the total time taken to execute testcase    
		        }
		        catch (Exception ex)
		        {
		        	System.err.println ("Error writing to file");
		            ex.printStackTrace();
		        }
			}
			
			/* public  void testcaseheader(String TestCaseHtmlfile)
			{
				BufferedWriter bw = null;
				 String row = null;
			      
			         try {
			        	 	bw = new BufferedWriter(new FileWriter(TestCaseHtmlfile, true)); 
					        
							String header="<tr> </tr> <tr ><td><b>TestCase Id: "+driverScript.TC_ID+"</b></td>"+
							//"<td><b>"+driverScript.TC_ID+"</b></td>"+
							"</tr>"+
							"<tr >"+
							"<td><b>Description:"+driverScript.Description+"</b></td>"+
							"</tr>"+
							"<tr >"+
							"<td><b>Execution Start Time: "+Util.getCurrentDatenTime("dd MMMMM yyyy")+"</b></td></tr>";
							
							header+="<tr bgcolor="+h2color+">"+
							"<td><b>Step No</b></td>"+
							"<td><b>Step Name</b></td>"+
							"<td><b>Description</b></td>"+
							"<td><b>Status</b></td>"+
							"<td><b>Time</b></td>"+
							"</tr>";	
							
							 bw.write(header);
							 bw.newLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					finally
				      {
				    	  try
				    	  {
				    		  bw.close();
				    	  }
				    	  catch (Exception e)
				    	  {
				    		  
				    	  }
				      }

			}   */
			
			public  void testcaseheader_consol(String TestCaseHtmlfile)
			{
				BufferedWriter bw = null;
				 String row = null;
			      
			         try {
			        	 	bw = new BufferedWriter(new FileWriter(TestCaseHtmlfile, true)); 
					        
							String header="<tr bgcolor="+h2color+">"+
							"<td><b>S.No</b></td>"+
							"<td><b>Test Description</b></td>"+
							"<td><b>Actual Result</b></td>"+
							"<td><b>Status</b></td>"+
							"<td><b>Time</b></td>"+
							"</tr>";	
							
							 bw.write(header);
							 bw.newLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					finally
				      {
				    	  try
				    	  {
				    		  bw.close();
				    	  }
				    	  catch (Exception e)
				    	  {
				    		  
				    	  }
				      }

			}
			
			
			
			 //#############################################################################
			//Function Name    	: createSummaryHeader
			//Description     	: Function to  create the summary header
			//Input Parameters 	: SummaryHtmlfile
			//Return Value    	: None		
			//Date Created		: 
			//#############################################################################

			public void createSummaryHeader(String SummaryHtmlfile)
			{
				Results.SummaryHtmlfile = SummaryHtmlfile;
				
				FileOutputStream out; // declare a file output object
		        PrintStream p; // declare a print stream object
		        setColorsforResults();

		        try
		        {
		            // Create a new file output stream
		            out = new FileOutputStream(SummaryHtmlfile);

		            // Connect print stream to the output stream
		            p = new PrintStream( out );
		            String header="<html><head><title>Test Automation Summary </title>";
		            header +="</head><Body>"+
					"<p align = center><table border=2 bordercolor=#000000 id=table1 width=900 height=31 bordercolorlight=#000000>"+
					"<tr><td COLSPAN = 6 bgcolor = "+h1color+">";
		            header+= "<p align=center><font color="+fontColor+" size=4 face= Copperplate Gothic Bold> Automation Execution Results </font><font face= Copperplate Gothic Bold></font> </p>";
					//header+= "<p align=center><font color="+fontColor+" size=4 face= Copperplate Gothic Bold>"+Util.getValue("ProjectName", "CRAFT Project")+" Automation Execution Results </font><font face= Copperplate Gothic Bold></font> </p>";
					header +="</td></tr>"+
					"<tr>"+
					"<td COLSPAN = 6 bgcolor = "+h1color+">"+
					"<p align=justify><b><font color="+fontColor+" size=2 face= Verdana>DATE:"+ Util.getCurrentDatenTime("dd MMMMM yyyy")+
					"</td></tr>";
					header+="<tr bgcolor="+h2color+"><td><b>S.No </b></td>"+
					
					"<td><b>Test Case ID / Name </b>	</td>"+
					"<td><b>Test Description</b></td>"+
					"<td><b>Status</b>	</td>"+
					"<td><b>Time</b>	</td>"+
					"</tr>";
					
					p.println (header);

		            p.close();	
		        }
		        catch (Exception ex)
		        {
		        	System.err.println ("Error writing to file");
		            ex.printStackTrace();
		        	
		        }
			
			}
			

			 //#############################################################################
			//Function Name    	: addRowtoSummary
			//Description     	: Function to add a row to summary
			//Input Parameters 	: None
			//Return Value    	: None		
			//Date Created		: 
			//#############################################################################

			public  void addRowtoSummary()
			{
				 BufferedWriter bw = null;
				 String row = null;
			      try {
			         bw = new BufferedWriter(new FileWriter(SummaryHtmlfile, true));
			         String status = "";
				     if (Fail>0)
				     {
				     	overallFail++;
				     	status = "FAIL";
				     }
				     else if (Pass > 0)
				     {
				     	overallPass++;
				     	status = "PASS";
				     }
				     else
				     {
				     	status = "DONE";
				     }
			         //row = "<tr><td><a href='" + DriverScript.testcase +".html" + "'" + "target=" + "about_blank" + ">"+DriverScript.testcase+"</a></td><td>"+DriverScript.desc+"</td><td>"+duration+"</td><td>"+status+"</td></tr>";
				     
			         Pass =0;
			         Fail =0;
			         bw.write(row);
			         bw.newLine();
			         
			      }
			      catch (Exception e)
			      {
			    	  
			      }
			      finally
			      {
			    	  try
			    	  {
			    		  bw.close();
			    	  }
			    	  catch (Exception e)
			    	  {
			    		  
			    	  }
			      }
			}
			
			 //#############################################################################
			//Function Name    	: addRowtoTestCase
			//Description     	: Function to add row to testcase
			//Input Parameters 	: strStepName, strDescription,strStatus
			//Return Value    	: None		
			//Date Created		: 
			//#############################################################################

			public void htmllog(String strStepName,String strDescription,Status strStatus, WebDriver browser)
			{
				 
				 if (strStatus.equals(Status.PASS))
				 {
					Pass++;
					updateResult(true);
				 }
				 
				 if (strStatus.equals(Status.FAIL))
				 {
					 updateResult(false);
					Fail++;
					FinalTC_Status = "FAIL";
					//String testCaseNameAlone = TestCaseHtmlfile.substring(TestCaseHtmlfile.lastIndexOf("\\"),TestCaseHtmlfile.length()-1);
					screenshotName = "Screenshot";
					//ScreenShotPath = DriverScript.ScreenshotsPath+ "\\"+screenshotName;
					ScreenShotPath1 = ScreenShotPath+ "\\"+screenshotName;
					//ScreenShotPath1 = screenshotName;
					
					//Util.takeScreenShot(ScreenShotPath1);
					
					Util.takeFailureScreenShot(ScreenShotPath1, browser);
					inc++;
					
				 }
				 BufferedWriter bw = null;
				 String row = null;
			      try {
			         bw = new BufferedWriter(new FileWriter(TestCaseHtmlfile, true));
			        // row = "<tr><td>"+DriverScript.iteration+"</td><td>"+DriverScript.subiteration+"</td><td>"+strStepName+"</td><td>"+strDescription+"</td><td>"+strStatus+"</td><td>"+Util.getcurrentdate("H:mm:ss")+"</td></tr>";
			       
			         row = "<tr><td>"+stepNo+"</td><td>"+strStepName+"</td><td>"+strDescription+"</td><td>";
			         stepNo++;
		        	 if (strStatus.equals(Status.FAIL))
		        	 {
		        		// int i = DriverScript.subiteration -1; 
		        		 //+ DriverScript.testcase +" "+DriverScript.iteration+" "+i+(inc-1)+".jpg" 
		        		 row+="<a href='.\\Screenshots\\"+screenshotName+"'"+"target=" + "about_blank" + "><font color = red><B>"+strStatus+"</B></font>";
		        	 } 
		        	 else if (strStatus.equals(Status.PASS))
		        	 {
		        		 row+="<font color = green><B>"+strStatus+"</B></font>";
		        		 
		        	 }
		        	 else
		        	 {
		        		 row+="<b>"+strStatus+"</b>";
		        	 }
		        	 row+="</td><td>"+Util.getCurrentDatenTime("H:mm:ss")+"</td></tr>";
			         bw.write(row);
			         bw.newLine();
			         
			      }
			      catch (Exception e)
			      {
			    	  
			      }
			      finally
			      {
			    	  try
			    	  {
			    		  bw.close();
			    	  }
			    	  catch (Exception e)
			    	  {
			    		  
			    	  }
			      }

			}  
			
			
			 //#############################################################################
			//Function Name    	: End Test Case template
			//Description     	: Function to add summary to test case
			//Input Parameters 	: strStepName, strDescription,strStatus
			//Return Value    	: None		
			//Date Created		: 
			//#############################################################################

			public void endTestCase()
			{
				
				
				 BufferedWriter bw = null;
				 String row = "";
				 
			      try {
			    	  
			         bw = new BufferedWriter(new FileWriter(TestCaseHtmlfile, true));
		        	
			         System.out.println("Total Result "+ testCaseResult);
		        	 if (testCaseResult)
		        		 
		        	 {	 
		        		 updateSummaryCount(Status.PASS);
		        		 testFactory.objTestCase.setTestCase_Status("PASS");
		        		 
		        		 row+="<tr><td COLSPAN = 6 bgcolor = #687C7D><p align=center><b><font color=white size=2 face= Verdana> PASSED </td></tr>"; 
		        	 }
		        	 else
		        	 {
		        		 updateSummaryCount(Status.FAIL);
		        		 testFactory.objTestCase.setTestCase_Status("FAIL");
		        		 
		        		 row+="<tr><td COLSPAN = 6 bgcolor = #687C7D><p align=center><b><font color=red size=2 face= Verdana> FAILED </td></tr>";
		        	 }
		        	
			         bw.write(row);
			         
			         Reporter rs = new Reporter();
			         rs.databaseReporting(testFactory);
			         rs.sendAPIReport(testFactory);
			        
			 		
			       
			      }
			      catch (Exception e)
			      {
			    	 
			      }
			      finally
			      {
			    	  try
			    	  {
			    		  bw.close();
			    	  }
			    	  catch (Exception e)
			    	  {
			    		  
			    	  }
			      }

			}
			
			
			//* Update Test case status to test suite *//
			
			
			public void updateSummaryCount(Status strStatus)
			{
				
				 if (strStatus.equals(Status.PASS))
				 {
					Testcasestatus(SummaryHtmlfile, testModule, testCaseName, Status.PASS);
					TC_Pass++;
					overallPass++;
				 }
				 if (strStatus.equals(Status.FAIL))
				 {
					 Testcasestatus(SummaryHtmlfile, testModule , testCaseName, Status.FAIL); 
					TC_Fail++;
					overallFail++;
								
				 }
				S_No++;
				
			}
			
			// *   This is currently not used * //
			

			public  void Testcasestatus(String path,String TC_id,String TC_Desc,Status strStatus)
			{
				 
				 BufferedWriter bw = null;
				 String row = null;
			      try {
			         bw = new BufferedWriter(new FileWriter(path, true));
			        // row = "<tr><td>"+DriverScript.iteration+"</td><td>"+DriverScript.subiteration+"</td><td>"+strStepName+"</td><td>"+strDescription+"</td><td>"+strStatus+"</td><td>"+Util.getcurrentdate("H:mm:ss")+"</td></tr>";
			       
			         row = "<tr><td>"+S_No+"</td><td>"+testModule+"</td><td>"+testCaseName+"</td><td>";
			         stepNo++;
		        	 if (strStatus.equals(Status.FAIL))
		        	 {
		        		// int i = DriverScript.subiteration -1; 
		        		 //+ DriverScript.testcase +" "+DriverScript.iteration+" "+i+(inc-1)+".jpg" 
		        		 row+="<a href= "+Results.TestCaseHtmlfile+"><font color = red><B>"+strStatus+"</B></font>";
		        	 } 
		        	 else if (strStatus.equals(Status.PASS))
		        	 {
		        		 //row+="<font color = green><B>"+strStatus+"</B></font>";
		        		 
		        		 row+="<a href="+Results.TestCaseHtmlfile+"><font color = green><B>"+strStatus+"</B></font>";
		        		 
		        	 }
		        	 else
		        	 {
		        		 row+="<b>"+strStatus+"</b>";
		        	 }
		        	 row+="</td><td>"+Util.getCurrentDatenTime("H:mm:ss")+"</td></tr>";
			         bw.write(row);
			         bw.newLine();
			         
			      }
			      catch (Exception e)
			      {
			    	  
			      }
			      finally
			      {
			    	  try
			    	  {
			    		  bw.close();
			    	  }
			    	  catch (Exception e)
			    	  {
			    		  
			    	  }
			      }

			}
			
			public  void Final_Count(String TestCaseHtmlfile)
			{
				BufferedWriter bw = null;
				 String row = null;
			      
			         try {
			        	 	bw = new BufferedWriter(new FileWriter(TestCaseHtmlfile, true)); 
					        
							String Footer=""+
							"<tr><td><b>Total count : "+(TC_Pass+TC_Fail)+"</b></td></tr>"+
							"<tr><td><b><font color = green>Total Pass : "+TC_Pass+"</font></b></td></tr>"+
							"<tr><td><b><font color = red>Total Fail : "+TC_Fail+"</font></b></td></tr>";
							
								
							
							 bw.write(Footer);
							 bw.newLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					finally
				      {
				    	  try
				    	  {
				    		  bw.close();
				    	  }
				    	  catch (Exception e)
				    	  {
				    		  
				    	  }
				      }

			}

			 //#############################################################################
			//Function Name    	: insertIteration
			//Description     	: Function to insert Iteration
			//Input Parameters 	: iteration
			//Return Value    	: None		
			//Date Created	: 
			//#############################################################################

			public  void insertIteration(String iteration)
			{
				BufferedWriter bw = null;
				try {
			         bw = new BufferedWriter(new FileWriter(TestCaseHtmlfile, true));
			         
			         String insertiter=  "<tr><td COLSPAN = 6> <center><b>Iteration: "+ iteration+
			        	 "</b></center></td></tr>";
			        
			         bw.write(insertiter);
			         stepNo=1;
			         
				}catch (Exception e)
				{
					
				}
				finally
			      {
			    	  try
			    	  {
			    		  bw.close();
			    	  }
			    	  catch (Exception e)
			    	  {
			    		  
			    	  }
			      }
				
			}

			 //#############################################################################
			//Function Name    	: closeSummary
			//Description     	: Function to close the summary file
			//Input Parameters 	: None
			//Return Value    	: None		
			//Date Created	: 
			//#############################################################################

			public  void closeSummary()
			{
				BufferedWriter bw = null;
				try {
			         bw = new BufferedWriter(new FileWriter(SummaryHtmlfile, true));
			         String closetags = "";
			         closetags+=  "<tr><center> <B><td COLSPAN = 3> <font color = green>Passed:</font> "+overallPass+"</td><td  COLSPAN = 3><font color = red>Failed:</font> "+overallFail;
			         overallPass =0;
			         overallFail =0;
					 closetags+="</b></center></td></tr></Table></Body></HTML>";
			         bw.write(closetags);
				}
				catch (Exception e)
				{
					
				}
				finally
			      {
			    	  try
			    	  {
			    		  bw.close();
			    	  }
			    	  catch (Exception e)
			    	  {
			    		  
			    	  }
			      }
			}

			 //#############################################################################
			//Function Name    	: closeTestCase
			//Description     	: Function to close testcase
			//Input Parameters 	: None
			//Return Value    	: None		
			//Date Created		: 
			//#############################################################################

			public  void closeTestCase()
			{
				end = Util.getLastsetTimeinmili();
				long dur = end - start;
				duration = Util.getFormattedTime(dur);
				
				BufferedWriter bw = null;
				try {
			         bw = new BufferedWriter(new FileWriter(TestCaseHtmlfile, true));
			         String closetags = "";
			         closetags+=  "<tr><td COLSPAN = 6> <center><B>Total Duration: "+ duration
		        	 +"</B></center></td></tr><tr><td COLSPAN = 5 align = right><B><font color = green align = right>PASS:</font>"+Pass+" </b></td></tr><tr><td COLSPAN = 5 align = right><B><font color = red>FAIL:</font> "+Fail;
			         closetags+="</B></td></tr></Table></Body></HTML>";
			         bw.write(closetags);
			         
				}catch (Exception e)
				{
					
				}
				finally
			      {
			    	  try
			    	  {
			    		  bw.close();
			    	  }
			    	  catch (Exception e)
			    	  {
			    		  
			    	  }
			      }
			}

	}
