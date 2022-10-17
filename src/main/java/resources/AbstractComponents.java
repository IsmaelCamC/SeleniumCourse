package resources;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {
	WebDriver driver;
	WebDriverWait wait;

	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public void waitElementClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitElementVisible(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public int randomNumbers() {
		int max = 9999;
		int min = 0001;
		int number = (int) Math.floor(Math.random()*(max-min+1)+min);
		return number;
	}
	
	public void executeScript(Boolean vertical,String script) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		if (vertical==true) {
			js.executeScript("window.scrollBy(0,"+script+")", "");
		} else {
			js.executeScript("window.scrollBy("+script+",0)", "");
		}
		
	}
	
	public void implicitWait() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void maximizeWindow() {
		driver.manage().window().maximize();
	}
}
