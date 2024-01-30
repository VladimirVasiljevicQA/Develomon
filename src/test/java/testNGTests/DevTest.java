package testNGTests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.ContactPage;
import resources.Base;

public class DevTest extends Base {
	public WebDriver driver;
	Logger log;

	@BeforeMethod
	public void open() throws IOException {
		log = LogManager.getLogger(DevTest.class.getName());
		log.debug("Test contactTest started");
		driver = initDriver();
		log.debug("driver is started");
		driver.get(prop.getProperty("url"));
		log.debug("app is open on contact page");
	}

	@Test(dataProvider = "getData")
	public void contactTest(String message) {
		ContactPage ContactPage = new ContactPage(driver);

		ContactPage.SendButton().click();
		log.debug("click on the contact button");

		String actualMess = ContactPage.warning().getText();

		log.info("check warning message under field where message is required");
		try {
			if (!message.equalsIgnoreCase(actualMess)) {
				// System.out.println("Strings are not equal");
				log.debug("proper warning message is displayed");
			} else {

				throw new Exception();
			}
		} catch (Exception e) {
			// System.out.println("Strings are eqaul " + e);
			log.error("wrong warning message is displayed " + e);
		}

		Assert.assertNotEquals(actualMess, message);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
		log.debug("Browser is closed");
	}

}
