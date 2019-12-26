package RSP.Test;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import RSP.Pages.DashboardPage;
import RSP.Pages.LoginPage;
import Utilities.ConfigRead;
import Utilities.ReadWriteExcel;
import Utilities.Screenshotutility;

public class LoginTest {

	WebDriver driver = null;
	ConfigRead config = new ConfigRead();

	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.gecko.driver", config.getFirefox());
		driver = new FirefoxDriver();
		driver.manage().window().maximize();

	}

	@Test()
	public void testLogin() throws Exception {
		driver.get(config.getApplicatonUrl());
		LoginPage loginpage = PageFactory.initElements(driver, LoginPage.class);
		loginpage.setEmail("sagar.nikam@technokeens.co");
		loginpage.setPassword("123456");
		loginpage.clickOnLoginButton();
		Thread.sleep(20000);
		DashboardPage dashboardPage=PageFactory.initElements(driver,DashboardPage.class);
		boolean result=dashboardPage.checkLogout();
		if(result==true)
		{
			System.out.println("element found");
			Screenshotutility obj=new Screenshotutility();
    		obj.captureScreenShot(driver);
		}
		else {
			Screenshotutility obj=new Screenshotutility();
    		obj.captureScreenShot(driver);
			System.out.println("Element not present");
		}
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}