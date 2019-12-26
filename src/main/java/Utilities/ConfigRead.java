package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ConfigRead {

	String path = null;
	Properties pro;
	private static WebDriver driver=null;
	
	public ConfigRead() {
		
		File src = new File("./Configuration/config.property");
		FileInputStream fis;
		try {
			fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Exception Message      " + e.getMessage());
		}
		
	}
	
	public String getFirefox() {
		String firfox = pro.getProperty("FirefoxDriver");
		return firfox;
	}
	public String getGoogle() {
		String google = pro.getProperty("ChromeDriver");
		return google;
	}


	public String getApplicatonUrl() {
		String url = pro.getProperty("URL");
		return url;
	}
	
	public static void main(String args[]) throws Exception {
		
		ConfigRead config=new ConfigRead();
		//System.setProperty("webdriver.gecko.driver",config.getFirefox());
		//driver=new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver",config.getGoogle());
		driver=new ChromeDriver();
		Thread.sleep(4000);
		driver.get(config.getApplicatonUrl());
		
		driver.close();
		driver.quit();
	}
}
