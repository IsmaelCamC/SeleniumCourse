package pageobject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import resources.AbstractComponents;

public class NewUserPage extends AbstractComponents {
	WebDriver driver;

	@FindBy(id = "id_gender1")
	WebElement genderRadio;

	@FindBy(xpath = "//*[@id=\"customer_firstname\"]")
	WebElement customerFirstName;

	@FindBy(id = "customer_lastname")
	WebElement customerLastName;

	@FindBy(id = "email")
	WebElement email;

	@FindBy(id = "passwd")
	WebElement passwd;

	@FindBy(id = "days")
	WebElement selectDays;

	@FindBy(id = "months")
	WebElement selectMonths;

	@FindBy(id = "years")
	WebElement selectYears;

	@FindBy(id = "company")
	WebElement company;

	@FindBy(id = "address1")
	WebElement address1Txt;

	@FindBy(id = "address2")
	WebElement address2Txt;

	@FindBy(id = "city")
	WebElement cityTxt;

	@FindBy(id = "id_state")
	WebElement id_state;

	@FindBy(id = "postcode")
	WebElement postcode;

	@FindBy(id = "other")
	WebElement otherInfo;

	@FindBy(id = "phone_mobile")
	WebElement phone_mobile;

	@FindBy(id = "alias")
	WebElement alias;

	@FindBy(id = "submitAccount")
	WebElement submitButton;

	@FindBy(css = "div[class='alert alert-danger'] p")
	WebElement errorCount;

	@FindAll(@FindBy(css = "//*[@id=\"center_column\"]/div/ol/li"))
	List<WebElement> getErrorsXpath;

	
	public NewUserPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void setGender() {
		super.waitElementVisible(genderRadio);
		genderRadio.click();
	}

	public void setFirstName(String firstName) {
		customerFirstName.sendKeys(firstName);
	}

	public void setLastName(String lastName) {
		customerLastName.sendKeys(lastName);
	}

	public void setPassword(String password) {
		email.click();
		passwd.sendKeys(password);
	}

	public void setDay(String day) {
		Select days = new Select(selectDays);
		days.selectByValue(day);
	}

	public void setMonth(String monthNumber) {
		Select month = new Select(selectMonths);
		month.selectByValue(monthNumber);
	}

	public void setYears(String yearNumber) {
		Select years = new Select(selectYears);
		years.selectByValue(yearNumber);
	}

	public void setCompany(String companyName) {
		company.sendKeys(companyName);
	}

	public void setAddress(String address1, String address2) {
		address1Txt.sendKeys(address1);
		address2Txt.sendKeys(address2);
	}

	public void setCity(String city) {
		cityTxt.sendKeys(city);
	}

	public void setState(String state) {
		Select stateSelect = new Select(id_state);
		stateSelect.selectByValue(state);
	}

	public void setPostCode(String post) {
		postcode.sendKeys(post);
	}

	public void setOtherInfo(String other) {
		otherInfo.sendKeys(other);
	}

	public void setPhoneMobile(String phone) {
		phone_mobile.sendKeys(phone);
	}

	public void setAlias(String aliasAddress) {
		alias.sendKeys(aliasAddress);
	}

	public void sendInfo() {
		submitButton.click();
	}

	public String getErrorCount() {
		return errorCount.getText();
	}

	public ArrayList getErrors() {
		super.waitElementVisible(errorCount);
		ArrayList<String> errorsText = new ArrayList();
		for (WebElement getError:getErrorsXpath) {
			getError.click();
			errorsText.add(getError.findElement(By.tagName("li")).getAttribute("innerHTML"));
			System.out.println(getError.getText());
		}
		return errorsText;
	}
	
}
