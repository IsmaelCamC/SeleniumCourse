package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.AbstractComponents;

public class HomePage extends AbstractComponents{
	WebDriver driver;
	
	@FindBy(className = "login")
	WebElement signInButton;
	
	@FindBy(css="a[title='Women']")
	WebElement womenSection;
	
	@FindBy(css="ul[class='submenu-container clearfix first-in-line-xs'] li ul a[title='T-shirts']")
	WebElement tShirts;
	
	@FindBy(css="div[class='right-block'] h5 a")
	WebElement firstElement;
	
	@FindBy(id="search_query_top")
	WebElement searchBar;
	
	@FindBy(css="button[name='submit_search']")
	WebElement submit;
	
	@FindBy(css="a[title='View'] span")
	WebElement elementMore;
	
	@FindBy(css="div[class='wishlist'] a")
	WebElement addToWishListButton;
	
	@FindBy(css=".fancybox-error")
	WebElement fancyBoxError;

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
	
	public void viewTShirts() {
		implicitWait();
		Actions builder = new Actions(driver);
		builder.moveToElement(womenSection).perform();
		tShirts.click();
		executeScript(true,"200");
	}
	
	public String viewFirstElement() {
	waitElementVisible(firstElement);
	String first = firstElement.getText();
	executeScript(true,"-200");
	return first;
	}
	
	public void searchElement(String first) {
		searchBar.sendKeys(first);
		submit.click();
		executeScript(true,"200");
	}
	
	public void elementMore() {
		Actions builder = new Actions(driver);
		builder.moveToElement(firstElement).perform();
		waitElementVisible(elementMore);
		elementMore.click();
	}
	
	public void addToWishList() {
		Actions builder = new Actions(driver);
		builder.moveToElement(firstElement).perform();
		waitElementVisible(addToWishListButton);
		addToWishListButton.click();
	}
	
	public String getError() {
		waitElementVisible(fancyBoxError);
		return fancyBoxError.getText();
	}
}
