package Utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Screenshotutility {
	  static WebDriver dirver=null;

	public void captureScreenShot(WebDriver ldriver,String path) throws Exception {

		// Take screenshot and store as a file format
		File src = ((TakesScreenshot) ldriver).getScreenshotAs(OutputType.FILE);
		Thread.sleep(6000);
		try {

			DateFormat dateFormat = new SimpleDateFormat("yyyy");
			DateFormat dateFormat1 = new SimpleDateFormat("MM");
			DateFormat dateFormat2 = new SimpleDateFormat("DD");
			
			Date date = new Date();

			String Year = dateFormat.format(date);
			String month = dateFormat1.format(date);
			String date1 = dateFormat2.format(date);
			// now copy the screenshot to desired location using copyFile method
			FileUtils.copyFile(src, new File(path+"\\"+dirver.getTitle() + Year+"_"+month + "_"+date1					+ ".png"));
		} catch (IOException e)

		{
			System.out.println(e.getMessage());
		}
	}

	public static void main(String args[]) throws Exception {

		String path="D:\\Automation-Workspace\\Wrokspace\\Technokeens.Nashik\\Screenshot";
		  ConfigRead config = new ConfigRead();
		  
		  System.setProperty("webdriver.gecko.driver", config.getFirefox());
		  
		  dirver = new FirefoxDriver();
		  
		  dirver.get(config.getApplicatonUrl());
		  
		  Thread.sleep(8000); 
		  Screenshotutility screesnshots = new Screenshotutility();
		  screesnshots.captureScreenShot(dirver,path);
		  
		  dirver.quit();
		 
	}

	
}
