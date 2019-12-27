package RSP.Test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RSP.Pages.DashboardPage;
import RSP.Pages.LoginPage;
import Utilities.ConfigRead;
import Utilities.ReadWriteExcel;
import Utilities.Screenshotutility;

public class LoginDDT {

	WebDriver driver = null;
	ConfigRead config = new ConfigRead();
	public int DataSet=-1;
	String srcPath="D:\\Automation-Workspace\\Wrokspace\\Technokeens.Nashik\\src\\test\\java\\RSP\\screenshot\\";
	Logger logger=Logger.getLogger("Login test cases while sending data from Excel sheet");
	
	
	
	@BeforeMethod
	@BeforeTest
	public void setUp() {
		
		PropertyConfigurator.configure("./src/log4j.properties");
		System.setProperty("webdriver.gecko.driver", config.getFirefox());
		driver = new FirefoxDriver();
		logger.info("Browser Opened");
		driver.manage().window().maximize();
		logger.info("window is maximized");

	}

	@Test(dataProvider="data")
	public void testLogin(String uname, String pass,String rs) throws Exception {
		
		DataSet++;
		driver.get(config.getApplicatonUrl());
	
		String username;
		String password;
		if(uname==null) {
			System.out.println("value is null");
			username="";
		}
		else
			username=uname.toString();
			if(pass==null) {
				System.out.println("value is null");
				password=" ";
			}
			else
				password=pass.toString();
			if(rs==null) {
				System.out.println("do nothing");
			}
			logger.info("user readed the data");
		
		LoginPage loginpage = PageFactory.initElements(driver, LoginPage.class);
		loginpage.setEmail(username);
		loginpage.setPassword(password);
		loginpage.clickOnLoginButton();
		Thread.sleep(20000);
		logger.info("Test clicked on the login button");
		DashboardPage dashboardPage=PageFactory.initElements(driver,DashboardPage.class);
		Thread.sleep(5000);
		boolean result=dashboardPage.checkLogout();
		logger.info("Test verifying the result and moves to write result");
		if(result==true)
		{
			logger.info("User is logged in successfully to system");
			String tResult="Pass";
			LoginDDT obj=new LoginDDT();
			obj.result(tResult, DataSet);
			
			dashboardPage.logout();
			Thread.sleep(10000);
		}
		else 
		{
			
			boolean fresult=loginpage.erorLogin();
			if(fresult==true)
			{
				logger.info("User not found on the system with credentials");
				String tResult="Pass";
				LoginDDT obj=new LoginDDT();
				obj.result(tResult, DataSet);
				System.out.println("User failed to login with invalid credentials");
			}
			else
			{
				logger.info("User failed to login system");
				String tResult="Failed";
				LoginDDT obj=new LoginDDT();
				obj.result(tResult, DataSet);
				Screenshotutility srcObj=new Screenshotutility();
				srcObj.captureScreenShot(driver,srcPath);
				System.out.println("Failed to login");
			
			}						
		}
	}

	@AfterMethod
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

	@DataProvider(name = "data")
	public Object[][] getUserData() throws Exception {

		ReadWriteExcel rws = new ReadWriteExcel();

		String filePath = "D:\\Automation-Workspace\\Wrokspace\\Technokeens.Nashik\\src\\main\\java\\TestData\\userLogin.xlsx";
		logger.info("user redirected towards the read excel file class to read data in object form");
		Object loginData[][] = rws.getUserData(filePath, "sheet1");
		logger.info("user redirected towards the read excel file class to read data in object form");
		return loginData;
		
	}

	public void result(String result,int DR) throws Exception {
		
		ReadWriteExcel rws = new ReadWriteExcel();
		logger.info("the is redirecting towards printng the result "+result);
		String filePath = "D:\\Automation-Workspace\\Wrokspace\\Technokeens.Nashik\\src\\main\\java\\TestData\\userLogin.xlsx";

		rws.WriteCell(filePath, "sheet1", result,DR);
		logger.info("the result has been written on the row"+result);
	}

}
