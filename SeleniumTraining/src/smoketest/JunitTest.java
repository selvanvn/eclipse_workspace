package smoketest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;



public class JunitTest {
	
	WebDriver driver;
	String browserType = "chrome";
	String URL = "http://sdettraining.com/trguitransactions/accountmanagement.aspx";
	
	@Test
	public void pageTitleTest()
	{
		System.out.println("test");
	driver.get(URL);
	String atitle = driver.getTitle();
	String etitle = "SDET Training | Account Management";
	Assert.assertEquals(atitle, etitle);
	}
	
	@Before
	public void setUp()
	{
		driver=Utilities.setup.intialize(browserType);
		System.out.println("Setup Complete");
	}
	@After
	public void end()
	{
		driver.close();
		System.out.println("Complete");
	}
}
