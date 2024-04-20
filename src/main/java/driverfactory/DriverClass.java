package driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.Status;

import utilities.ExtentGen;

public class DriverClass {

	public WebDriver driver;
	ExtentGen extentReport = new ExtentGen();

	public void initDriver(String browserName) {
		extentReport.reportStart();
		String methodName = new Exception().getStackTrace()[0].getMethodName();
		// String className = new Exception().getStackTrace()[0].getClassName();

		ExtentGen.test = ExtentGen.extent.createTest(methodName, "Driver Test");

		if (browserName == "chrome") {
			driver = new ChromeDriver();
			ExtentGen.test.log(Status.INFO, "Chrome Driver Started Successfully");

		} else if (browserName == "firefox") {
			driver = new FirefoxDriver();
			ExtentGen.test.log(Status.INFO, "FireFox Driver Started Successfully");

		} else {
			System.out.println("Incorrect browser name : " + browserName);
			ExtentGen.test.log(Status.FAIL, "Driver Not Started: Check Browser Name Spelling " + browserName);

		}

		driver.get("https://www.google.co.in");
		ExtentGen.test.pass("URL Launched");
	}

	public void tearDown() {

		driver.quit();
		ExtentGen.test.pass("Browser Closed Successfully");
		extentReport.stopReport();
	}

}
