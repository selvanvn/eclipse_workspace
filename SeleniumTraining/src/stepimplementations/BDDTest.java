package stepimplementations;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BDDTest {
	
	WebDriver driver;

	 @Given("^login page opens$")
	 public void login_page_opens(){
		 driver = Utilities.setup.intialize("chrome");
			driver.get("http://sdettraining.com/trguitransactions/accountmanagement.aspx");
	 }
	 @When("^right Credentials$")
	 public void right_Credentials()
	 {
		 driver.findElement(By.id("MainContent_txtUserName")).sendKeys("selvanvn@gmail.com");
		 driver.findElement(By.id("MainContent_txtPassword")).sendKeys("Selva@1331");
		 driver.findElement(By.id("MainContent_btnLogin")).click();
	 }
	 @When("^enter email (.*)$")
	 public void enter_email_username(String username)
	 {
		 driver.findElement(By.id("MainContent_txtUserName")).sendKeys(username);
	 }
	    @And("^enter right pwd (.*)$")
	    public void right_pwd_password(String password)
	    {
	    	driver.findElement(By.id("MainContent_txtPassword")).sendKeys(password);
			 driver.findElement(By.id("MainContent_btnLogin")).click();
	    }
	 @Then("^successfully logged in$")
	 public void successfully_logged_in()
	 {
		 Assert.assertTrue(driver.findElement(By.id("conf_message")).getText().contains("success"));
	 }
	 @After
	 public void tearDown()
	 {
		 driver.quit();
	 }
}
