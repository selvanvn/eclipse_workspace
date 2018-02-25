package tests;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

@RunWith(value = Parameterized.class)
public class NewAccountDDT {
	WebDriver driver;
	String uname, email, phone, pwd, country, gender, msgactual, msgexpected;
	boolean wemail, memail, oemail;
	WebElement nameText, emailText, phoneText, pwdText, cpwdText, maleRadio, femaleRadio, weekCheckbox, monthCheckbox,
			ocasionCheckbox;

	@Test
	public void TestMethod1() {
		System.out.println("NEW RECORD " + uname + " " + email + " " + phone + " " + country + " " + gender);
		defineWebElements();
		// WebElement confMsg =
		// driver.findElement(By.id("MainContent_lblTransactionResult"));
		nameText.sendKeys(uname);
		emailText.sendKeys(email);
		phoneText.sendKeys(phone);
		pwdText.sendKeys(pwd);
		cpwdText.sendKeys(pwd);
		// driver.findElement(By.id("MainContent_Male")).click();
		if (gender.equalsIgnoreCase("male")) {
			maleRadio.click();
		} else {
			femaleRadio.click();
		}
		// Drop Down
		if (wemail) {
			if (!weekCheckbox.isSelected()) {
				weekCheckbox.click();
			}
		} else {
			if (weekCheckbox.isSelected()) {
				weekCheckbox.click();
			}
		}
		if (memail) {
			if (!monthCheckbox.isSelected()) {
				monthCheckbox.click();
			}
		} else {
			if (monthCheckbox.isSelected()) {
				monthCheckbox.click();
			}
		}
		if (oemail) {
			if (!ocasionCheckbox.isSelected()) {
				ocasionCheckbox.click();
			}
		} else {
			if (ocasionCheckbox.isSelected()) {
				ocasionCheckbox.click();
			}
		}
		new Select(driver.findElement(By.id("MainContent_menuCountry"))).selectByVisibleText(country);
		driver.findElement(By.name("ctl00$MainContent$btnSubmit")).click();
		msgactual = driver.findElement(By.id("MainContent_lblTransactionResult")).getText();
		System.out.println(msgactual);
		msgexpected = "Customer information added successfully";
		/*
		 * if(msgactual.equalsIgnoreCase(msgexpected)) {
		 * System.out.println("pass"); }
		 */
		Assert.assertEquals(msgactual, msgexpected);
	}

	@Parameters
	public static List<String[]> getParam() {
		return Utilities.CSV.get("D:\\Selenium\\UserAccounts.csv");
	}

	public NewAccountDDT(String uname, String email, String phone, String gender, String pwd, String country,
			String wemail, String memail, String oemail) {

		this.uname = uname;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.pwd = pwd;
		this.country = country;
		if (wemail == "TRUE") {
			this.wemail = true;
		} else {
			this.wemail = false;
		}
		if (memail == "TRUE") {
			this.memail = true;
		} else {
			this.memail = false;
		}
		if (oemail == "TRUE") {
			this.oemail = true;
		} else {
			this.oemail = false;
		}

	}

	@Before
	public void intializefunc() {
		driver = Utilities.setup.intialize("chrome");
		driver.get("http://sdettraining.com/trguitransactions/accountmanagement.aspx");
		WebElement clickButton = driver.findElement(By.xpath("html/body/form/div[3]/div[2]/div/div[2]/a"));
		clickButton.click();
	}

	@After
	public void finish() {
		driver.close();
	}

	public void defineWebElements() {
		nameText = driver.findElement(By.name("ctl00$MainContent$txtFirstName"));
		emailText = driver.findElement(By.name("ctl00$MainContent$txtEmail"));
		phoneText = driver.findElement(By.name("ctl00$MainContent$txtHomePhone"));
		pwdText = driver.findElement(By.cssSelector("input[id='MainContent_txtPassword']"));
		cpwdText = driver.findElement(By.name("ctl00$MainContent$txtVerifyPassword"));
		maleRadio = driver.findElement(By.cssSelector("input[name='ctl00$MainContent$Gender'][value='Male']"));
		femaleRadio = driver.findElement(By.cssSelector("input[name='ctl00$MainContent$Gender'][value='Female']"));
		weekCheckbox = driver.findElement(By.id("MainContent_checkWeeklyEmail"));
		monthCheckbox = driver.findElement(By.id("MainContent_checkMonthlyEmail"));
		ocasionCheckbox = driver.findElement(By.id("MainContent_checkUpdates"));
	}

}