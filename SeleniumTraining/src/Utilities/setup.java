package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class setup {

	public static WebDriver intialize(String browserType)
	{
		if(browserType == "Firefox")
		{
			System.setProperty("webdriver.gecko.driver", "D:\\Selenium\\geckodriver.exe");
			return new FirefoxDriver();
		}
		else	
		{
			System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver.exe");
		    return new ChromeDriver();
		}
	}
}
