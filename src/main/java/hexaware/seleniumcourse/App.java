package hexaware.seleniumcourse;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobject.HomePage;
import pageobject.LoginRegister;
import pageobject.NewUserPage;

public class App {
	WebDriver driver;
	WebDriverWait wait;
	HomePage home;
	LoginRegister register;
	NewUserPage information;
	
	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
		driver = new ChromeDriver();
		home = new HomePage(driver);
		register = new LoginRegister(driver);
		information = new NewUserPage(driver);
	}
	
	@Test 
	public void test1(){
		// STEP 1
		home.maximizeWindow();
		home.goToHome();
		// STEP 2
		home.loginClick();
		// STEP 3 && 4
		register.createAccount("@yopmail.com");
		// STEP 5
		register.executeScript("window.scrollBy(0,150)");
		register.implicitWait();
		information.setGender();
		information.setFirstName("Juan Ismael");
		information.setLastName("Camacho Cervantes");
		information.setPassword("Password123");
		information.executeScript("window.scrollBy(0,220)");
		information.setDay("1");
		information.setMonth("4");
		information.setYears("2022");
		information.setCompany("Hexapath");
		information.setAddress("2nd Street Jenkins Avenue", "Colony of the hill");
		information.setCity("New Jersey");
		information.executeScript("window.scrollBy(0,600)");
		information.setState("1");
		information.setPostCode("19388");
		information.setOtherInfo("Other info");
		information.setPhoneMobile("83346222424");
		information.setAlias("My address number 1");
		// STEP 6
		information.sendInfo();
		// STEP 7
		information.implicitWait();
		Assert.assertTrue(driver.findElement(By.id("my-account")).isDisplayed());
	}
	
	@Test
	public void test2() {
		// SECOND PART
		// STEP 1
		home.maximizeWindow();
		home.goToHome();
		// STEP 2
		home.loginClick();
		// STEP 3
		register.loginForm("email@@yopmail.com");		
		// STEP 4
		register.implicitWait();
		Assert.assertEquals(register.badMail(), "Invalid email address.");
	}
	
	@Test
	public void test3() {
		//STEP 1
		home.maximizeWindow();
		home.goToHome();
		//STEP 2
		home.loginClick();
		//STEP 3
		register.createAccount("@yopmail.com");
		//STEP 4
		register.executeScript("window.scrollBy(0,150)");
		register.implicitWait();
		information.sendInfo();
		information.implicitWait();
		Assert.assertEquals(information.getErrorCount(), "There are 8 errors");
	}
	
	@AfterMethod
	public void exit() {
		try {
			driver.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
