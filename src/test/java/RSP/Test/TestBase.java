package RSP.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import Utilities.ConfigRead;

public class TestBase {
	
	public static WebDriver driver = null;
	
	ConfigRead config=new ConfigRead();
	
	@BeforeSuite
	public void setup() throws Exception{
			System.setProperty("webdriver.gecko.driver",config.getFirefox());
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		
			driver.get(config.getApplicatonUrl());
			Thread.sleep(10000);
	}	 
	 @AfterSuite
	 //Test cleanup
	 public void TeardownTest()
	    {
	        TestBase.driver.quit();
	    }
	 

}
