package smoketest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTestNG {
	
	WebDriver driver;
	String browserType = "chrome";
	String URL = "http://sdettraining.com/trguitransactions/accountmanagement.aspx";
	
	@Test
	public void testTNG()
	{
	
		//driver = Utilities.setup.intialize(browserType);
		driver.get(URL);
		boolean lbox = driver.findElement(By.id("MainContent_txtUserName")).isDisplayed();
		boolean pbox = driver.findElement(By.id("MainContent_txtPassword")).isDisplayed();
		Assert.assertTrue(lbox,"email box");
		Assert.assertTrue(pbox,"password box");
		
	}
	@BeforeMethod
	public void setUpTNG()
	{
		driver=Utilities.setup.intialize(browserType);
		System.out.println("Setup Complete");
	}
	@AfterMethod
	public void endTNG()
	{
		driver.close();
		System.out.println("Complete");
	}

}
