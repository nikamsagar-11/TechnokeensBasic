package Utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Screenshotutility {
	
	private static WebDriver driver;

	public void captureScreenShot(WebDriver ldriver,String path) throws Exception {
		
		Screenshotutility.driver=ldriver;
		// Take screenshot and store as a file format
		File src = ((TakesScreenshot) ldriver).getScreenshotAs(OutputType.FILE);
		Thread.sleep(6000);
		try {

			// now copy the screenshot to desired location using copyFile method
			FileUtils.copyFile(src, new File(path+"\\"+driver.getTitle()+timestamp()+ ".png"));
		} catch (IOException e)

		{
			System.out.println(e.getMessage());
		}
	}
	public String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
}

	public static void main(String args[]) throws Exception {

		String path="D:\\Automation-Workspace\\Wrokspace\\Technokeens.Nashik\\Screenshot";
		  ConfigRead config = new ConfigRead();
		  
		  System.setProperty("webdriver.gecko.driver", config.getFirefox());
		  
		  driver=new  FirefoxDriver();
		  
		  driver.get(config.getApplicatonUrl());
		  
		  Thread.sleep(8000); 
		  Screenshotutility screesnshots = new Screenshotutility();
		  screesnshots.captureScreenShot(driver,path);
		  
		  driver.quit();
		 
	}

	
}
