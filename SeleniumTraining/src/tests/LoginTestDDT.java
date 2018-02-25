package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTestDDT {
	//String uname,email,pwd;
	WebDriver driver;
	@Test(dataProvider="getData")
	public void TestMethod2(String uname,String email,String pwd)
	{		
		driver.get("https://en.wikipedia.org/w/index.php?title=Special:UserLogin&returnto=Login");
		driver.findElement(By.name("wpName")).sendKeys("selvanvn");
		driver.findElement(By.name("wpPassword")).sendKeys("Selva@1331");
		driver.findElement(By.name("wploginattempt")).click();
		String a = driver.findElement(By.id("firstHeading")).getText();
		System.out.println("Confirmation :" +a);
		String title = driver.getTitle();
		System.out.println(title);
		/*if(title == "Login - Wikipedia")
		{
		System.out.println(title);
		}*/
		driver.close();
	}
	@BeforeMethod
	public void setup()
	{
		driver= Utilities.setup.intialize("Firefox");
	}
	@AfterMethod
	public void end()
	{
		driver.quit();
	}
	@DataProvider
	public String[][] getData()
	{
		return Utilities.Excel.get("D:\\Selenium\\UserLogin.xls");
	}

}
