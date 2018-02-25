package smoketest;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AtagTest {
	WebDriver driver;
	String browserType = "chrome";
	String URL = "http://sdettraining.com/trguitransactions/accountmanagement.aspx";
	boolean flag = false;
	
	@Test
	public void testATag()
	{
	
		//driver = Utilities.setup.intialize(browserType);
		driver.get(URL);
		List <WebElement> aElements = driver.findElements(By.tagName("a"));
		
		for(WebElement aElement : aElements)
		{
			System.out.println("The Element is " +aElement.getText()+ "Has A Tag");
			if(aElement.getText().equals("Create Account"))
			{
				flag = true;
			}
				
		}
		
		Assert.assertTrue(flag);
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
