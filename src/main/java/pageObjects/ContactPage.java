package pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class ContactPage {
	WebDriver driver;
	public ContactPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy (xpath = "//input[@value='Send']")
	private WebElement SendButton;
	@FindBy (xpath = "(//div[@class='field field--text field--text-error'])[4]")
	private WebElement warning;
	
	public WebElement SendButton() {
		return SendButton;
	}
	public WebElement warning() {
		return warning;
	}

}
