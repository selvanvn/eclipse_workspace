package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;

public class Login {
	WebDriver driver;

@Test
	public void loginTestPOM() {
		driver = Utilities.setup.intialize("chrome");
		driver.get("http://sdettraining.com/trguitransactions/accountmanagement.aspx");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("selvanvn@gmail.com", "Selva@1331");
		//loginPage.setUsername("selvanvn@gmail.com");
		//loginPage.setPassword("Selva@1331");
		//loginPage.clickSubmit();
		DashboardPage dashboardPage = new DashboardPage(driver);
		String confM = dashboardPage.dashboard();
		//String confM = dashboardPage.confMessage();
		//String logM = dashboardPage.getTitle();
		//dashboardPage.clickPwd();
		Assert.assertTrue(confM.contains("success"));
		//Assert.assertTrue(logM.contains("SDET"));
		
		driver.quit();
	}
}
