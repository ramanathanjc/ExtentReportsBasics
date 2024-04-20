package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {

	ExtentReports extent;
	ExtentSparkReporter htmlReport;
	ExtentTest test;

	public void reportStart() {

		/*
		 * creating blank HTML file with name of test.html
		 */
		htmlReport = new ExtentSparkReporter("./test.html");
		htmlReport.config().setTheme(Theme.DARK);
		htmlReport.config().setDocumentTitle("MyReport");

		/*
		 * creating ExtentReports method to add scenarios
		 */

		extent = new ExtentReports();

		/*
		 * passing our HTML file to report
		 */

		extent.attachReporter(htmlReport);

		/*
		 * Creating new test with Pass Test name ExtentTest object created with test
		 * with help of test object name we can create multiple test and test status
		 * (Pass/Fail/Info/Skip/Warning)
		 */

		test = extent.createTest("Pass Test");

		// below methods we can use to create tests (Pass/Fail/Info/Skip/Warning)

		// Method 1 with status and string value
		test.log(Status.PASS, "Login Successfully");
		// Method 2 with direct string
		test.pass("Login Successfully");

		ExtentTest test1 = extent.createTest("Fail Test");
		test1.log(Status.FAIL, "Login Failed");
		test1.fail("Login Failed");

		ExtentTest test2 = extent.createTest("INFO Test");
		test2.log(Status.INFO, "Chrome Driver Started  Successfully");
		test2.info("Chrome Driver Started  Successfully");

		ExtentTest test3 = extent.createTest("Skip Test");
		test3.log(Status.SKIP, "Skip Test");
		test3.skip("Skip Test");

		ExtentTest test4 = extent.createTest("Warning Test");
		test4.log(Status.WARNING, "Warning Test");
		test4.warning("Warning Test");

		/*
		 * it will create a report if we miss this method we won't get report
		 */
		extent.flush();

		/**
		 * This will automatically open our report once test is completed.
		 */
		try {
			Desktop.getDesktop().browse(new File("./test.html").toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
