package com.qa.reporting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.qa.testcomponets.TestFactory;

public class DataBaseReporting {
	
	
		
		   final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		   final String DB_URL = "jdbc:mysql://localhost/";

		   //  Database credentials
		   final String USER = "root";
		   final String PASS = "root";
		   
		   
		   public void createDatabase(TestFactory objTestFactory) 
		   
		   {
			   
		   Connection conn = null;
		   Statement stmt = null;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");
	
		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      System.out.println("Connected database successfully...");
		      
		      //STEP 4: Execute a query
		      System.out.println("Inserting records into the table...");
		      stmt = conn.createStatement();
		      
		      String sql = "CREATE DATABASE LSIAutomation";
		      stmt.executeUpdate(sql);
		      System.out.println("Database created successfully...");
		      
		      stmt.execute("use LSIAutomation");
		      
		    //STEP 4: Execute a query
		      System.out.println("Creating table in given database...");
		      
		      sql = "CREATE TABLE TESTRUN " +
		                   "(id INTEGER not NULL, " +
		    		       " testType VARCHAR(25)"+
		                   " testModule VARCHAR(255), " + 
		                   " testcase VARCHAR(255), " + 
		                   " result VARCHAR(255), " + 
		                   " run INTEGER, " + 
		                   " browser VARCHAR(25), " + 
		                   " PRIMARY KEY ( id ))"; 
		      stmt.executeUpdate(sql);
		      
		      sql = "CREATE TABLE SUITE " +
	          "(id INTEGER not NULL, " +
	          " runName VARCHAR(255), " + 
	          " PRIMARY KEY ( id ))"; 
		      stmt.executeUpdate(sql);
		      
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Goodbye!");
		 }
		   
		  /* Create Test Run*/
		   
		   public void createTestRun()
		  
		   {   
//			   Connection conn = null;
//			   Statement stmt = null;
//			   
//			   try
//			   
//			   {
//			   //STEP 2: Register JDBC driver
//			      Class.forName("com.mysql.jdbc.Driver");
	//
//			      //STEP 3: Open a connection
//			      System.out.println("Connecting to a selected database...");
//			      conn = DriverManager.getConnection(DB_URL, USER, PASS);
//			      System.out.println("Connected database successfully...");
//			      
//			      String sql;
//			      
//			      stmt = conn.createStatement();
//			      stmt.execute("use LSIAutomation");
//			      
//			    //STEP 4: Execute a query
//			      System.out.println("Creating table in given database...");
//			      sql = "INSERT INTO SUITE VALUES('1','test')";  
//			      stmt.executeUpdate(sql);
//			      sql = "Select MAX(pkey) from SUITE";
//			      ResultSet rs = stmt.executeQuery(sql);
//			      
//			      int maxPkey=0;
//			      while(rs.next())
//			      {
//			    	 maxPkey = rs.getInt("pkey");
//			    	
//			      }
//			      
//			      sql = "INSERT INTO SUITE " +
//			                   "VALUES("+maxPkey+",'TestRun"+Util.getLastsetTimeinmili()+"')";
//			      stmt.executeUpdate(sql);
//			      
//			      oTD.runId = oTD.runId+1;
//			      System.out.println("Inserted records into the table...");
	//
//			   }catch(SQLException se){
//			      //Handle errors for JDBC
//			      se.printStackTrace();
//			   }catch(Exception e){
//			      //Handle errors for Class.forName
//			      e.printStackTrace();
//			   }finally{
//			      //finally block used to close resources
//			      try{
//			         if(stmt!=null)
//			            conn.close();
//			      }catch(SQLException se){
//			      }// do nothing
//			      try{
//			         if(conn!=null)
//			            conn.close();
//			      }catch(SQLException se){
//			         se.printStackTrace();
//			      }//end finally try
//			   }//end try
			   System.out.println("Goodbye!");
		   }
		   
		   /* Update Test Result */
		   
		   public void updateTestResult(TestFactory objTestFactory)
			  
		   {   
			   Connection conn = null;
			   Statement stmt = null;
			   
			   try
			   
			   {
			   //STEP 2: Register JDBC driver
			      Class.forName("com.mysql.jdbc.Driver");
	
			      //STEP 3: Open a connection
			      System.out.println("Connecting to a selected database...");
			      conn = DriverManager.getConnection(DB_URL, USER, PASS);
			      System.out.println("Connected database successfully...");
			      
			      String sql;
			      
			      stmt = conn.createStatement();
			      stmt.execute("use LSIAutomation");
			      
			    //STEP 4: Execute a query
			      System.out.println("Creating table in given database...");
			      
//			      sql = "INSERT INTO testrun " +
//			                   "VALUES("+oTD.getRunId()+",'"+oTD.getTestModuleName()+"','"+oTD.getTestCaseName()+"', '"+Results.testCaseResult+"', 1,'"+oTD.browser+"')";
//			      stmt.executeUpdate(sql);
//			      
//			      oTD.runId = oTD.runId+1;
			      
			      sql = "INSERT INTO testrun " +
                  "VALUES('1','"
			    		   +objTestFactory.objTestSuite.testModuleName+"','"
                           +objTestFactory.objTestCase.getTestCase_Name()+"', '"
			    		   +objTestFactory.objTestCase.getTestCase_Status()+"', 1,'"
                           +objTestFactory.objTestRun.browser+"')";
			      
                  stmt.executeUpdate(sql);
     
			    		      
			      System.out.println("Inserted records into the table...");
	
			   }catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            conn.close();
			      }catch(SQLException se){
			      }// do nothing
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try
			   System.out.println("Test Run creation successful!");
		   }
		   

}
