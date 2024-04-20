package test;

import org.testng.annotations.Test;

import utilities.DifferentTypeOfLog;
import utilities.ExtentReport;

public class testng {

	ExtentReport report = new ExtentReport();
	DifferentTypeOfLog dl = new DifferentTypeOfLog();

	@Test
	public void reportTest() {

		report.reportStart();

	}

	@Test
	public void differentTypeTest() {
		dl.differentType();
	}
}
