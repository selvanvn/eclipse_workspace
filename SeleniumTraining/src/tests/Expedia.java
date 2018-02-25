package tests;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Expedia {
	WebDriver driver;
	String browserType = "Chrome";
	String destination = "New York, New York";
	String checkIn = "02/01/2018";
	String checkOut = "02/01/2018";
	String peopleCount ="2";
	String starCount= "star4";
	String searchCount = "7";
	
	@Test
	public void bookHotel() {
		driver.findElement(By.id("tab-hotel-tab-hp")).click();
		driver.findElement(By.id("hotel-destination-hp-hotel")).sendKeys(destination);
		//Check
		//driver.findElement(By.id("hotel-checkin-hp-hotel")).clear();
		driver.findElement(By.id("hotel-checkin-hp-hotel")).sendKeys(checkIn);
		//driver.findElement(By.id("hotel-checkout-hp-hotel")).clear();
		driver.findElement(By.id("hotel-checkout-hp-hotel")).sendKeys(checkOut);
		new Select(driver.findElement(By.xpath("//*[@id=\"gcw-hotel-form-hp-hotel\"]/div[4]/div[4]/label/select"))).selectByValue(peopleCount);
		driver.findElement(By.xpath("//*[@id=\"gcw-hotel-form-hp-hotel\"]/div[9]/label/button")).click();
		//driver.findElement(By.xpath("//*[@id=\"star4-label\"]/span")).click();
		driver.findElement(By.cssSelector("input[name='star'][id='" +starCount+ "']")).click();
		String confirmationMsg = driver.findElement(By.xpath("//*[@id=\"hotelResultTitle\"]/h1")).getText();
		System.out.println("Message" +confirmationMsg);
		//driver.findElement(By.xpath("//*[@id=\"41864\"]/div[2]/div[1]/a")).click();
		driver.findElement(By.xpath("//div[@id=\"resultsContainer\"]/section/article[" +searchCount+ "]/div[2]/div/a")).click();
		
		ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windows.get(1));
		
		//driver.findElement(By.xpath("//*[@id=\"resultsContainer\"]/section/article["+searchCount+"]/div[2]/div/a")).click();
		driver.findElement(By.xpath("//*[@id=\"rooms-and-rates\"]/div/article/table/tbody/tr[2]/td[3]/div/div[1]/button")).click();
		//*[@id="rooms-and-rates"]/div/article/table/tbody/tr[2]/td[3]/div/div[1]/button
	}

	@BeforeMethod
	public void setup() {
		driver = Utilities.setup.intialize(browserType);
		driver.get("https://www.expedia.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void tearDown() {
		//driver.quit();
	}
}
