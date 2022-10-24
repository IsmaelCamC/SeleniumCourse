package pageobject;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
		passStep("Heading to the home page of Automation Practice.");
	}

	public void loginClick() {
		super.waitElementClickable(signInButton);
		passStep("Clicked 'Sign in' top button.");
		signInButton.click();
	}
	
	public void viewTShirts() {
		Actions builder = new Actions(driver);
		builder.moveToElement(womenSection).perform();
		tShirts.click();
		executeScript(true,"200");
		passStep("Opened T-Shirts menu.");
	}
	
	public String viewFirstElement() {
	waitElementVisible(firstElement);
	String first = firstElement.getText();
	executeScript(true,"-200");
	passStep("Viewing first element.");
	return first;
	}
	
	public void searchElement(String first) {
		searchBar.sendKeys(first);
		submit.click();
		executeScript(true,"200");
		passStep("Searching element on Search box.");
	}
	
	public void elementMore() {
		Actions builder = new Actions(driver);
		builder.moveToElement(firstElement).perform();
		waitElementVisible(elementMore);
		elementMore.click();
		passStep("Clicked on 'More' button on selected element.");
	}
	
	public void addToWishList() {
		Actions builder = new Actions(driver);
		builder.moveToElement(firstElement).perform();
		waitElementVisible(addToWishListButton);
		addToWishListButton.click();
		passStep("Clicked 'Add to Wishlist' button on desired element.");
	}
	
	public String getError() {
		waitElementVisible(fancyBoxError);
		passStep("Succesfully getting error text.");
		return fancyBoxError.getText();
	}
}
