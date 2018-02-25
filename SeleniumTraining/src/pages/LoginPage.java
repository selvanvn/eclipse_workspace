package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	@FindBy(id = "MainContent_txtUserName")
	WebElement usernameBox;

	@FindBy(id = "MainContent_txtPassword")
	WebElement passwordBox;

	@FindBy(id = "MainContent_btnLogin")
	WebElement submitButton;

	public void setUsername(String username) {
		usernameBox.sendKeys(username);
	}

	public void setPassword(String password) {
		passwordBox.sendKeys(password);
	}

	public void clickSubmit() {
		submitButton.click();
	}

	public void login(String username, String password) {
		setUsername(username);
		setPassword(password);
		clickSubmit();
	}

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
