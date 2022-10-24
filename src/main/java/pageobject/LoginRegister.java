package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.AbstractComponents;

public class LoginRegister extends AbstractComponents {
	WebDriver driver;
	
	@FindBy (id="email_create")
	WebElement emailCreateTextBox;
	
	@FindBy (id="SubmitCreate")
	WebElement submitInformation;
	
	@FindBy(id="email")
	WebElement emailAddressSignIn;
	
	@FindBy(id="passwd")
	WebElement password;
	
	@FindBy(id="SubmitLogin")
	WebElement submitLogin;
	
	@FindBy(xpath="//*[@id=\"center_column\"]/div[1]/ol/li")
	WebElement errorWarning;
	
	public LoginRegister(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void createAccount(String email) {
		super.waitElementVisible(emailCreateTextBox);
		emailCreateTextBox.sendKeys(super.randomNumbers()+email);
		super.waitElementVisible(submitInformation);
		submitInformation.click();
		passStep("Successfully submited email and clicked on 'Register' button.");
	}

	public void loginForm(String email, String passwd) {
		emailAddressSignIn.sendKeys(email);
		password.sendKeys(passwd);
		submitLogin.click();
		passStep("Successfully submited email and clicked on 'Login' button.");
	}
	
	public String badMail() {
		return errorWarning.getText();
	}

	
}
