package hexaware.seleniumcourse;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import pageobject.Checkout;
import pageobject.HomePage;
import pageobject.LoginRegister;
import pageobject.NewUserPage;

public class App extends BaseTest{
	HomePage home;
	LoginRegister register;
	NewUserPage information;
	Checkout checkout;
	
	@BeforeMethod
	public void setup() throws IOException {
		driver = initializeDriver();
		home = new HomePage(driver);
		register = new LoginRegister(driver);
		information = new NewUserPage(driver);
		checkout = new Checkout(driver);
	}

	@Test (retryAnalyzer = Retry.class)
	public void test1() {
		// STEP 1
		home.goToHome();
		// STEP 2
		home.loginClick();
		// STEP 3 && 4
		register.createAccount("@yopmail.com");
		// STEP 5
		register.executeScript(true, "150");
		information.setGender();
		information.setFirstName("Juan Ismael");
		information.setLastName("Camacho Cervantes");
		information.setPassword("Password123");
		information.executeScript(true, "220");
		information.setDay("1");
		information.setMonth("4");
		information.setYears("2022");
		information.setCompany("Hexapath");
		information.setAddress("2nd Street Jenkins Avenue", "Colony of the hill");
		information.setCity("New Jersey");
		information.executeScript(true, "600");
		information.setState("1");
		information.setPostCode("19388");
		information.setOtherInfo("Other info");
		information.setPhoneMobile("83346222424");
		// STEP 6
		information.sendInfo();
		// STEP 7
		Assert.assertTrue(driver.findElement(By.id("my-account")).isDisplayed());
	}

	@Test
	public void test2() {
		// SECOND PART
		// STEP 1
		home.goToHome();
		// STEP 2
		home.loginClick();
		// STEP 3
		register.loginForm("email@@yopmail.com", "");
		// STEP 4
		Assert.assertEquals(register.badMail(), "Invalid email address.");
	}

	@Test
	public void test3() {
		// STEP 1
		home.goToHome();
		// STEP 2
		home.loginClick();
		// STEP 3
		register.createAccount("@yopmail.com");
		// STEP 4
		register.executeScript(true, "150");
		information.sendInfo();
		Assert.assertEquals(information.getErrorCount(), "There are 8 errors");
	}

	@Test
	public void test4() {
		// STEP 1
		home.goToHome();
		// STEP 2
		home.loginClick();
		// STEP 3
		register.createAccount("@yopmail.com");
		// STEP 4
		register.executeScript(true, "150");
		information.setFirstName("Juan Ismael");
		information.setLastName("Camacho Cervantes");
		information.setPassword("Password123");
		information.executeScript(true, "220");
		information.setAddress("2nd Street Jenkins Avenue", "Colony of the hill");
		information.setCity("New Jersey");
		information.executeScript(true, "600");
		information.setState("1");
		information.setPostCode("HI HI HI HI HI HI HI");
		information.setPhoneMobile("HI HI HI HI HI");
		// STEP 5
		information.sendInfo();
		Assert.assertEquals(information.getErrorCount(), "There are 3 errors");
	}

	@Test
	public void test5() {
		// STEP 1
		home.goToHome();
		// STEP 2 & 3
		home.viewTShirts();
		// STEP 4
		String shirtsElement = home.viewFirstElement();
		// STEP 5
		home.searchElement(shirtsElement);
		// STEP 6
		String searchedElement = home.viewFirstElement();
		Assert.assertEquals(shirtsElement, searchedElement);
	}

	@Test
	public void test6() {
		// STEP 1
		home.goToHome();
		// STEP 2 & 3
		home.loginClick();
		register.loginForm("email@yopmail.com", "password123");
		home.viewTShirts();
		// STEP 4
		home.viewFirstElement();
		// STEP 5&6
		home.elementMore();
		// STEP 7
		checkout.changeQuantity("2");
		// STEP 8
		checkout.changeSize("3");
		// STEP 9
		checkout.changeColor();
		// STEP 10
		checkout.addToCart();
		// STEP11
		checkout.proceedCheckout();
		// STEP 12
		checkout.nextStepSummary();
		checkout.nextStepAddress();
		checkout.nextStepShipping();
		checkout.nextStepPayment();
		checkout.nextStepFinal();
		// STEP 13
		Assert.assertEquals(checkout.priceConfirmation(), "$36.42");
	}

	@Test
	public void test7() {
		// STEP 1
		home.goToHome();
		// STEP 2 & 3
		home.viewTShirts();
		//STEP 4 & 5
		home.addToWishList();
		//STEP 6
		Assert.assertEquals(home.getError(), "You must be logged in to manage your wishlist.");
	}
	
	@Test
	public void test8() {
		// STEP 1
		home.goToHome();
		// STEP 2 & 3
		home.loginClick();
		register.loginForm("email@yopmail.com", "password123");
		home.viewTShirts();
		// STEP 4
		home.viewFirstElement();
		// STEP 5&6
		home.elementMore();
		// STEP 7
		checkout.changeQuantity("1");
		// STEP 8
		checkout.changeSize("2");
		// STEP 9
		checkout.changeColor();
		//STEP 10
		checkout.addToCart();
		//STEP 11
		checkout.proceedCheckout();
		Assert.assertEquals(checkout.priceToPayCheckout(), "$16.51");
		//STEP 12
		checkout.addOne();
		Assert.assertEquals(checkout.priceToPayCheckout("$33.02"), "$33.02");
	}

	@AfterMethod
	public void exit() {
		driver.close();
	}
}
