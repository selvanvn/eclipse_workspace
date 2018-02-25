package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
	WebDriver driver;
	@FindBy(linkText="Change password")
	WebElement changePassworkLink;
	
	@FindBy(id="conf_message")
	WebElement confirmText;
	
	public void clickPwd(){
		changePassworkLink.click();
	}
	
	public String confMessage()
	{
		return confirmText.getText();
	}
	public String getTitle(){
		return driver.getTitle();
	}
	
	public String dashboard()
	{
		clickPwd();
		return confMessage();
	}
	
	public DashboardPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

}
