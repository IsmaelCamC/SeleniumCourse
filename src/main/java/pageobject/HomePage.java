package pageobject;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.AbstractComponents;

public class HomePage extends AbstractComponents{
	WebDriver driver;
	
	@FindBy(xpath = "//*[@id=\"js-app\"]/div[1]/div[1]/div[1]/div/div[2]/div/button")
	WebElement loginButton;
	

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void goToHome() {
		driver.get("https://woodmans-whitelabel.instacart.com/store/woodmans-food-markets/storefront");
		//this.passStep("Cool");
	}

	}
