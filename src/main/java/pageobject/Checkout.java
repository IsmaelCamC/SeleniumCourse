package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import resources.AbstractComponents;

public class Checkout extends AbstractComponents {
	WebDriver driver;

	@FindBy(id = "quantity_wanted")
	WebElement quantityText;

	@FindBy(id = "group_1")
	WebElement sizeSelect;

	@FindBy(id = "color_14")
	WebElement color;

	@FindBy(css = "button[name='Submit'] span")
	WebElement buttonCart;

	@FindBy(css = "a[title='Proceed to checkout'] span")
	WebElement proceedCheckout;

	@FindBy(xpath = "//*[@id=\"center_column\"]/p[2]/a[1]/span")
	WebElement nextStepSummary;

	@FindBy(css = "button[name='processAddress']")
	WebElement nextStepAddress;

	@FindBy(xpath = "//*[@id=\"uniform-cgv\"]/span")
	WebElement checkboxAgree;

	@FindBy(xpath = "//*[@id=\"form\"]/p/button/span")
	WebElement nextStepShipping;

	@FindBy(css = "a[class='cheque']")
	WebElement selectPayment;

	@FindBy(xpath="//*[@id=\"cart_navigation\"]/button")
	WebElement nextStepFinal;

	@FindBy(css = "span[class='price']")
	WebElement priceConfirmationOrder;
	
	@FindBy(xpath="/html/body/div/div[2]/div/div[3]/div/div[2]/table/tbody/tr/td[6]/span")
	WebElement priceToPayCheckout1;
	
	@FindBy(css="i[class='icon-plus']")
	WebElement add1;

	public Checkout(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void changeQuantity(String quantity) {
		quantityText.clear();
		quantityText.sendKeys(quantity);
		passStep("Changed quantity correctly.");
	}

	public void changeSize(String value) {
		Select size = new Select(sizeSelect);
		size.selectByValue(value);
		passStep("Changed size correctly.");
	}

	public void changeColor() {
		color.click();
		passStep("Changed color correctly.");
	}

	public void addToCart() {
		buttonCart.click();
		passStep("Item added to cart.");
	}

	public void proceedCheckout() {
		waitElementVisible(proceedCheckout);
		proceedCheckout.click();
		passStep("Clicked on 'Proceed to checkout' correctly");
	}

	public void nextStepSummary() {
		executeScript(true, "800");
		waitElementVisible(nextStepSummary);
		nextStepSummary.click();
	}

	public void nextStepAddress() {
		executeScript(true, "800");
		waitElementVisible(nextStepAddress);
		nextStepAddress.click();
	}

	public void nextStepShipping() {
		executeScript(true, "300");
		waitElementVisible(checkboxAgree);
		checkboxAgree.click();
		nextStepShipping.click();
	}

	public void nextStepPayment() {
		executeScript(true, "800");
		waitElementVisible(selectPayment);
		selectPayment.click();
	}

	public void nextStepFinal() {
		executeScript(true, "800");
		waitElementVisible(nextStepFinal);
		nextStepFinal.click();
		passStep("Clicked next on each step.");
	}

	public String priceConfirmation() {
		executeScript(true, "300");
		waitElementVisible(priceConfirmationOrder);
		return priceConfirmationOrder.getText();
	}
	
	
	public String priceToPayCheckout(String text) {
		waitElementUpdateText(priceToPayCheckout1,"$33.02");
		return priceToPayCheckout1.getText();
	}
	
	public String priceToPayCheckout() {
		executeScript(true,"300");
		waitElementVisible(priceToPayCheckout1);
		return priceToPayCheckout1.getText();
	}
	
	public void addOne() {
		add1.click();
		passStep("Added one more item.");
	}
}
