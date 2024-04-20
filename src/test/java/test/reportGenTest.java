package test;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import driverfactory.DriverClass;
import utilities.ExtentGen;

public class reportGenTest {

	DriverClass driverobj = new DriverClass();

	@BeforeTest
	public void setup() {
		driverobj.initDriver("firefox");
	}

	@Test(priority = 1)
	public void googleTest() {
		String pageTitle = driverobj.driver.getTitle();

		String methodName = new Exception().getStackTrace()[0].getMethodName();
		ExtentGen.test = ExtentGen.extent.createTest(methodName, "Title Verification");

		if (pageTitle.contains("Google")) {

			Assert.assertEquals(pageTitle, "Google");
			ExtentGen.test.log(Status.PASS, "Received Expected Title :" + pageTitle);

		} else {
			ExtentGen.test.log(Status.FAIL, "Actucal Title is :" + pageTitle);
		}

	}
    @Test(priority = 2)
	public void getURL() {

		String curl = driverobj.driver.getCurrentUrl();
		//below method will create new test 
		String methodName = new Exception().getStackTrace()[0].getMethodName();
		ExtentGen.test = ExtentGen.extent.createTest(methodName, "Current URL Verification");

		if (curl.contains(".google.")) {
			ExtentGen.test.log(Status.PASS, "Received Expected URL :" + curl);
		} else {
			ExtentGen.test.log(Status.FAIL, "Actucal URL is :" + curl);
		}

	}

	@AfterTest
	public void down() {
		driverobj.tearDown();

	}

}
