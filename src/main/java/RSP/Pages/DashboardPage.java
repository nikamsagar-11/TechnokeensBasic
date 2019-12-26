package RSP.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
	
	WebDriver driver = null;

	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH,using="//img[@alt='RenoSystem']")
	private WebElement logo;
	
	@FindBy(how = How.XPATH,using="//a[contains(.,'Logout')]")
	private WebElement logout;
	

	@FindBy(how = How.XPATH,using="//a[contains(.,'Notifications']")
	private WebElement notification;
	
	
	@FindBy(how = How.XPATH,using="//button[contains(@title,'Add Property')]")
	private WebElement addProperty;
	
	
	public boolean checkLogout() {
		return isElementPresent(logout);
}
	public void logout() {
			logout.click();
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
