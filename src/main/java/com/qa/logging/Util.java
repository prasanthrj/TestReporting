package com.qa.logging;
 
import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
   
public class Util {
	 
	static Calendar cc = null;	 
 
 
	public static String getCurrentDatenTime(String format) {
		Calendar cal = Calendar.getInstance();
		cc = cal;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cal.getTime());
	}
	
	public static long getLastsetTimeinmili() {
		return cc.getTimeInMillis();
	}
	
	public static String getFormattedTime(long time) {
		long timeMillis = time;
		long time1 = timeMillis / 1000;
		String seconds = Integer.toString((int) (time1 % 60));
		String minutes = Integer.toString((int) ((time1 % 3600) / 60));
		String hours = Integer.toString((int) (time1 / 3600));
		for (int i = 0; i < 2; i++) {
			if (seconds.length() < 2) {
				seconds = "0" + seconds;
			}
			if (minutes.length() < 2) {
				minutes = "0" + minutes;
			}
			if (hours.length() < 2) {
				hours = "0" + hours;
			}
		}
		return hours + ": " + minutes + ": " + seconds;
	}
	
	
	// Updated webdriver function to take Screen shot
	
	public static void takeFailureScreenShot(String path, WebDriver browser )
	{
		
		File scrFile = ((TakesScreenshot)browser).getScreenshotAs(OutputType.FILE);
		DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
		new File(path).mkdirs();
		String destFile = dateFormat.format(new Date()) + ".png";
 
        try {
			FileUtils.copyFile(scrFile, new File(path + "/" + destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
	
	// #############################################################################
	// Function Name : takeScreenShot
	// Description : Function to take screenshot
	// Input Parameters : Path
	// Return Value : None
	// #############################################################################
	public static void takeScreenShot(String path) {
		try {
			// Get the screen size
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Dimension screenSize = toolkit.getScreenSize();
			Rectangle rect = new Rectangle(0, 0, screenSize.width,
					screenSize.height);
			Robot robot = new Robot();
			BufferedImage image = robot.createScreenCapture(rect);
			File file; 

			// Save the screenshot as a png
			// file = new File(path);
			// ImageIO.write(image, "png", file);

			// Save the screenshot as a jpg
			file = new File(path);
			ImageIO.write(image, "jpg", file);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
