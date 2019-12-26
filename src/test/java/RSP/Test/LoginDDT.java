package RSP.Test;

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

	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.gecko.driver", config.getFirefox());
		driver = new FirefoxDriver();
		driver.manage().window().maximize();

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
		
		
		LoginPage loginpage = PageFactory.initElements(driver, LoginPage.class);
		loginpage.setEmail(username);
		loginpage.setPassword(password);
		loginpage.clickOnLoginButton();
		Thread.sleep(20000);
		DashboardPage dashboardPage=PageFactory.initElements(driver,DashboardPage.class);
		Thread.sleep(5000);
		boolean result=dashboardPage.checkLogout();
		
		if(result==true)
		{
			String tResult="Pass";
			LoginDDT obj=new LoginDDT();
			obj.result(tResult, DataSet);
			System.out.println("User is logged in successfully with valid credentials");
			dashboardPage.logout();
			Thread.sleep(10000);
		}
		else 
		{
			boolean fresult=loginpage.erorLogin();
			if(fresult==true)
			{
				String tResult="Pass";
				LoginDDT obj=new LoginDDT();
				obj.result(tResult, DataSet);
				System.out.println("User failed to login with invalid credentials");
			}
			else
			{
				String tResult="Failed";
				LoginDDT obj=new LoginDDT();
				obj.result(tResult, DataSet);
				Screenshotutility srcObj=new Screenshotutility();
				srcObj.captureScreenShot(driver,srcPath);
				System.out.println("Failed to login");
			
			}						
		}
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

	@DataProvider(name = "data")
	public Object[][] getUserData() throws Exception {

		ReadWriteExcel rws = new ReadWriteExcel();

		String filePath = "D:\\Automation-Workspace\\Wrokspace\\Technokeens.Nashik\\src\\main\\java\\TestData\\userLogin.xlsx";

		Object loginData[][] = rws.getUserData(filePath, "sheet1");

		return loginData;
	}

	public void result(String result,int DR) throws Exception {
		
		ReadWriteExcel rws = new ReadWriteExcel();
		
		System.out.println("the result of test case is  " +result);
		String filePath = "D:\\Automation-Workspace\\Wrokspace\\Technokeens.Nashik\\src\\main\\java\\TestData\\userLogin.xlsx";

		rws.WriteCell(filePath, "sheet1", result,DR);
	}

}
