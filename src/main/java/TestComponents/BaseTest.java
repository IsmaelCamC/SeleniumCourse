package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import hexaware.seleniumcourse.ExtentReporterNG;
import hexaware.seleniumcourse.Listeners;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	String browserName;
	public static ExtentTest test;
	public ExtentReports extent = ExtentReporterNG.getReportObject();
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	public WebDriver driver;
	
	public WebDriver initializeDriver(String browserName) throws IOException {
		// Properties
		/*Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//resources//globaldata.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");*/
		
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().clearDriverCache().setup();
			
			 driver = new ChromeDriver();
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			// FIREFOX
		} else if (browserName.equalsIgnoreCase("edge")) {
			// EDGE
		}
		driver.manage().window().maximize();
		return driver;		 
	}
	
	public String getScreenshot(String testCaseName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}

	public ThreadLocal<ExtentTest> getExtentTest() {
		return extentTest;
	}

	public void setExtentTest(ThreadLocal<ExtentTest> extentTest) {
		BaseTest.extentTest = extentTest;
	}

	public ExtentReports getExtent() {
		return extent;
	}

	public void setExtent(ExtentReports extent) {
		this.extent = extent;
	}

	public ExtentTest getTest() {
		return test;
	}

	public void setTest(ExtentTest test) {
		this.test = test;
	}
	
	//public ThreadLocal<ExtentTest> getTest() {
		//return listener.getTest();
	//}

	
}
