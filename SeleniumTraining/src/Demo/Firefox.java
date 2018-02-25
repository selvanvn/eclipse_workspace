package Demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Firefox {

	public static void main(String[] args) {
		
		String uname, email, phone, pwd, country, gender, browserType;
		boolean wemail, memail, oemail;
		uname = "selva";
		email = "dfkfjd@jdj.com";
		phone = "12345";
		pwd ="45678";
		country = "China";
		browserType = "Chrome";
		gender = "female";
		wemail = true;
		WebDriver driver;
		driver = Utilities.setup.intialize(browserType);
		driver.get("http://sdettraining.com/trguitransactions/accountmanagement.aspx");
		WebElement clickButton = 	driver.findElement(By.xpath("html/body/form/div[3]/div[2]/div/div[2]/a"));
		clickButton.click();
		//WebElement
		WebElement nameText = driver.findElement(By.name("ctl00$MainContent$txtFirstName"));
		WebElement emailText = driver.findElement(By.name("ctl00$MainContent$txtEmail"));
		WebElement phoneText = driver.findElement(By.name("ctl00$MainContent$txtHomePhone"));
		WebElement pwdText = driver.findElement(By.cssSelector("input[id='MainContent_txtPassword']"));
		WebElement cpwdText = driver.findElement(By.name("ctl00$MainContent$txtVerifyPassword"));
		WebElement maleRadio = driver.findElement(By.cssSelector("input[name='ctl00$MainContent$Gender'][value='Male']"));
		WebElement femaleRadio = driver.findElement(By.cssSelector("input[name='ctl00$MainContent$Gender'][value='Female']"));
		WebElement weekCheckbox = driver.findElement(By.id("MainContent_checkWeeklyEmail"));
		//WebElement confMsg = driver.findElement(By.id("MainContent_lblTransactionResult"));
		//
		nameText.sendKeys(uname);
		emailText.sendKeys(email);
		phoneText.sendKeys(phone);
		pwdText.sendKeys(pwd);
		cpwdText.sendKeys(pwd);
		//driver.findElement(By.id("MainContent_Male")).click();
		if(gender.equalsIgnoreCase("male"))
		{
		maleRadio.click();
		}
		else 
		{
			femaleRadio.click();
		}
		//Drop Down
		if(wemail)
		{
			if(!weekCheckbox.isSelected())
			{
				weekCheckbox.click();
			}
		}
		else
		{
			if(weekCheckbox.isSelected())
			{
				weekCheckbox.click();
			}
		}
		new Select(driver.findElement(By.id("MainContent_menuCountry"))).selectByVisibleText(country);
		driver.findElement(By.name("ctl00$MainContent$btnSubmit")).click();
		String msg = driver.findElement(By.id("MainContent_lblTransactionResult")).getText();
		String expected = "Customer information added successfully";
		System.out.println(msg);
		if(msg.equals(expected))
		{
			System.out.println("Passed");
		}
		else
		{
			System.out.println("Fail");
		}
		/*if(a=="Customer information added successfully")
		{
		System.out.println("Verified");	
		}
		else
		{
			System.out.println("Fail");
		}*/
		//driver.close();

	}

}
