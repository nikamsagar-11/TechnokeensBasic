package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

public class testGoogle extends  ExtentReportsClass{
	
	private WebDriver driver;
	@Test(priority=1,description="First test Case for checking extent report")
	public void test1() {
		
		System.setProperty("webdriver.gecko.driver", "");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("www.google.com");
		Assert.assertEquals(driver.getTitle(), "Google");
		logger.log(LogStatus.PASS, "Test Case Passed is passTest");
	}
	
	
	
}
