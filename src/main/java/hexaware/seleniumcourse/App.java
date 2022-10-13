package hexaware.seleniumcourse;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class App {

	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
	}
	
	@Test 
	public void test1(){
		WebDriver driver = new ChromeDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.manage().window().maximize();
		// STEP 1
		driver.get("http://automationpractice.com/index.php");
		// STEP 2
		WebElement signInButton = driver.findElement(By.className("login"));
		wait.until(ExpectedConditions.elementToBeClickable(signInButton));
		signInButton.click();
		// STEP 3
		WebElement emailCreateTextBox = driver.findElement(By.id("email_create"));
		wait.until(ExpectedConditions.visibilityOf(emailCreateTextBox));
		emailCreateTextBox.sendKeys("email1234@yopmail.com");
		// STEP 4
		WebElement submitInformation = driver.findElement(By.id("SubmitCreate"));
		wait.until(ExpectedConditions.visibilityOf(submitInformation));
		submitInformation.click();
		// STEP 5
		js.executeScript("window.scrollBy(0,150)", "");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("id_gender1")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("id_gender1"))));
		WebElement customerFirstName = driver.findElement(By.xpath("//*[@id=\"customer_firstname\"]"));
		customerFirstName.sendKeys("Juan Ismael");
		driver.findElement(By.id("customer_lastname")).sendKeys("Camacho Cervantes");
		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("passwd")).sendKeys("password123");
		js.executeScript("window.scrollBy(0,220)", "");
		Select days = new Select(driver.findElement(By.id("days")));
		days.selectByValue("1");
		Select months = new Select(driver.findElement(By.id("months")));
		months.selectByValue("4");
		Select years = new Select(driver.findElement(By.id("years")));
		years.selectByValue("2022");
		driver.findElement(By.id("company")).sendKeys("Hexapath");
		driver.findElement(By.id("address1")).sendKeys("2nd Street Jenkins Avenue");
		driver.findElement(By.id("address2")).sendKeys("Colony of the hill");
		driver.findElement(By.id("city")).sendKeys("New Jersey");
		js.executeScript("window.scrollBy(0,600)", "");
		Select stateSelect = new Select(driver.findElement(By.id("id_state")));
		stateSelect.selectByValue("1");
		driver.findElement(By.id("postcode")).sendKeys("19388");
		driver.findElement(By.id("other")).sendKeys("Other info");
		driver.findElement(By.id("phone_mobile")).sendKeys("83346222424");
		driver.findElement(By.id("alias")).sendKeys("My address number 1");
		// STEP 6
		driver.findElement(By.id("submitAccount")).click();
		// STEP 7
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Assert.assertTrue(driver.findElement(By.id("my-account")).isDisplayed());
	}
	
	@AfterClass
	public void exit(WebDriver driver) {
		driver.close();
	}
}
