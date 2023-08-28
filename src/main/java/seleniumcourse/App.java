package seleniumcourse;

import java.io.IOException;

import org.testng.annotations.*;
import TestComponents.BaseTest;
import pageobject.HomePage;


public class App extends BaseTest{
	HomePage home;
	
	//Listeners listener;
	
	@BeforeMethod
	public void setup() throws IOException {
		driver = initializeDriver("chrome");
		home = new HomePage(driver);
	
	}

	@Test (retryAnalyzer = Retry.class)
	public void test1() {
		//Declare browser to use
		home.goToHome();
		
	}


	@AfterMethod
	public void exit() {
		driver.close();
	}
}
