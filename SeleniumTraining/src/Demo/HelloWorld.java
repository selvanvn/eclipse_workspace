package Demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloWorld {
	
public static void main(String[] args)
{

	System.out.println("Naveen is shit");
	System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver.exe");
	WebDriver driver= new ChromeDriver();
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
	
}
