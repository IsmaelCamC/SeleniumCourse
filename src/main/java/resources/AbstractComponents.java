package resources;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

//import org.apache.tools.ant.util.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;


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
	
	public void waitElementUpdateText(WebElement element,String text) {
		wait.until(ExpectedConditions.textToBePresentInElement(element, text));
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
	
}
