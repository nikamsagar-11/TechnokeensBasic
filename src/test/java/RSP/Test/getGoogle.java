package RSP.Test;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class getGoogle {
	
	@Test
	public void testGoogle() {
		 // Here we need to create logger instance so we need to pass Class name for 
	     //which  we want to create log file in my case Google is classname
		

		System.setProperty("webdriver.gecko.driver", "D:\\Automation-Workspace\\Wrokspace\\Driver\\geckodriver.exe");
		
	         Logger logger=Logger.getLogger("Google");
	 
	       // configure log4j properties file
	       PropertyConfigurator.configure("./src/log4j.properties");
	 
	 
	        // Open browser
	        WebDriver driver = new FirefoxDriver();
	        logger.info("Browser Opened");
	      
	        // Set implicit wait
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        logger.info("Implicit wait given");
	      
	 
	        // Load application
	        driver.get("https://www.google.co.in/");
	        logger.info("Url opened");
	      
	 
	        // type Selenium
	        driver.findElement(By.name("q")).sendKeys("Selenium");
	        logger.info("keyword type");           
	    }
}
