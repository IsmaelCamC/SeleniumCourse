package hexaware.seleniumcourse;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
//ItestListener interface
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import TestComponents.BaseTest;

public class Listeners extends BaseTest implements ITestListener {

	//ExtentTest test;
	//ExtentReports extent = ExtentReporterNG.getReportObject();
	//ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public void onTestSuccess(ITestResult result) {
		getExtentTest().get().log(Status.PASS, "Test Passed");
	}

	public void onTestFailure(ITestResult result) {
		getExtentTest().get().fail(result.getThrowable());
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		String filepath = null;
		try {
			filepath = getScreenshot(result.getMethod().getMethodName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		getExtentTest().get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
	}

	public void onFinish(ITestContext arg0) {
		getExtent().flush();
	}

	@SuppressWarnings("unchecked")
	public void onTestStart(ITestResult result) {
		setTest(getExtent().createTest(result.getMethod().getMethodName()));
		getExtentTest().set(getTest());
		/*
		String browser = null;
		try {
			browser = (String) result.getTestClass().getRealClass().getField("browser").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		System.out.println(browser);*/
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}
	
	public void step(String info) {
		getExtentTest().get().log(Status.INFO, info);
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub

	}


	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	
	

}
