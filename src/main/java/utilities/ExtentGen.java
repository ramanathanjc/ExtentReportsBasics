package utilities;

import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentGen {

	public static ExtentReports extent;
	public static ExtentSparkReporter htmlReport;
	public static ExtentTest test;

	public void reportStart() {

		/*
		 * creating blank HTML file with name of test.html
		 */
		htmlReport = new ExtentSparkReporter("./driver.html");
		final File CONF = new File("config/extentreportconfig.xml");
		
		try {
			htmlReport.loadXMLConfig(CONF);
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		/*
		 * creating ExtentReports method to add scenarios
		 */

		extent = new ExtentReports();

		/*
		 * passing our HTML file to report
		 */

		extent.attachReporter(htmlReport);

	}

	public void stopReport() {
		/*
		 * it will create a report if we miss this method we won't get report
		 */
		extent.flush();

	}

}
