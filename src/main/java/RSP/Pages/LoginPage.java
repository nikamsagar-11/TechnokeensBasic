package RSP.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {


	WebDriver driver = null;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH,using="//input[@id='username']")
	@CacheLookup
	private WebElement username;
	
	@FindBy(how = How.XPATH,using="//input[contains(@id,'password')]")
	@CacheLookup
	private WebElement password;
	
	
	@FindBy(how = How.XPATH,using="//button[@type='submit']")
	@CacheLookup
	private WebElement login;
	
	@FindBy(how = How.XPATH,using="//div[text()='User not found. Please check username or password.']")
	private WebElement loginFailedError;	
	
	
	 public void setEmail(String strEmail){
	 username.sendKeys(strEmail);
	 }
	
	 public void setPassword(String strPassword){
	 password.sendKeys(strPassword);
	 }
	
	 public void clickOnLoginButton(){
	 login.click();
	 } 
	 
	 public boolean erorLogin() {
		 
		 return isElementPresent(loginFailedError);
	 }
	 
	 public boolean isElementPresent(WebElement ele)
	    {
	       boolean ElementPresent=false;
	       try {
	           if(ele.isDisplayed())
	           {
	               ElementPresent=true;
	           }
	       }
	       catch(Exception e)
	       {
	           System.out.println("Element is not present " +e);
	       }
	       return ElementPresent;
	    }
   
	
}
