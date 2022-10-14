package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.AbstractComponents;

public class HomePage extends AbstractComponents{
	WebDriver driver;
	
	@FindBy(className = "login")
	WebElement signInButton;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void goToHome() {
		driver.get("http://automationpractice.com/index.php");
	}

	public void loginClick() {
		super.waitElementClickable(signInButton);
		signInButton.click();
	}
	
}
