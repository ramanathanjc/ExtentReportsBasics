package utilities;

import java.util.ArrayList;
import java.util.List;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class DifferentTypeOfLog {

	ExtentReports extent;
	ExtentSparkReporter htmlReport1;
	ExtentTest test;

	public void differentType() {
		htmlReport1 = new ExtentSparkReporter("./TypeOfLogReport.html");
		extent = new ExtentReports();
		htmlReport1.config().setTheme(Theme.STANDARD);
		htmlReport1.config().setDocumentTitle("Different Type Of Log Report");

		extent.attachReporter(htmlReport1);

		// text based log

		test = extent.createTest("Text Based Log");
		test.log(Status.INFO, "Normal Text").log(Status.INFO, "<b>Bold Text</b>")
				.log(Status.INFO, "<i> italic Text</i>").log(Status.INFO, "<strong> Strong Text </strong>");

		// Sample XML Data

		String xmlDataString = "<menu id=\"file\" value=\"File\">\n" + "  <popup>\n"
				+ "    <menuitem value=\"New\" onclick=\"CreateNewDoc()\" />\n"
				+ "    <menuitem value=\"Open\" onclick=\"OpenDoc()\" />\n"
				+ "    <menuitem value=\"Close\" onclick=\"CloseDoc()\" />\n" + "  </popup>\n" + "</menu>";

		extent.createTest("XML Based Log")
				.log(Status.PASS, MarkupHelper.createCodeBlock(xmlDataString, CodeLanguage.XML))
				.info(MarkupHelper.createCodeBlock(xmlDataString, CodeLanguage.XML));

		// Sample Json Data

		String jsonString = "{\"menu\": {\n" + "  \"id\": \"file\",\n" + "  \"value\": \"File\",\n" + "  \"popup\": {\n"
				+ "    \"menuitem\": [\n" + "      {\"value\": \"New\", \"onclick\": \"CreateNewDoc()\"},\n"
				+ "      {\"value\": \"Open\", \"onclick\": \"OpenDoc()\"},\n"
				+ "      {\"value\": \"Close\", \"onclick\": \"CloseDoc()\"}\n" + "    ]\n" + "  }\n" + "}}";
		extent.createTest("Json Based Log")
				.log(Status.INFO, MarkupHelper.createCodeBlock(jsonString, CodeLanguage.JSON))
				.pass(MarkupHelper.createCodeBlock(jsonString, CodeLanguage.JSON));

		// List based Data

		List<String> arrayData = new ArrayList<>();
		arrayData.add("Ram");
		arrayData.add("Ramanathan");
		arrayData.add("Sample");

		extent.createTest("Array/List/Map Based Log")

				// Method 1
				.log(Status.INFO, MarkupHelper.createOrderedList(arrayData))

				// Method 2
				.log(Status.INFO, MarkupHelper.createUnorderedList(arrayData));

		// Highlight the message with colour
		extent.createTest("Highlight Based Log").log(Status.INFO,
				MarkupHelper.createLabel("browser launched successfully", ExtentColor.GREY));

		// Exception handling

		try {

			int i = 5 / 0;
			System.err.println(i);

		} catch (Exception e) {

			extent.createTest("Exception Based Log").info(e);

		}

		extent.flush();

	}

}
