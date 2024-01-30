package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extentReport {
	static ExtentReports extent;

	public static ExtentReports getExtentReport() {
		// Ne pokazuje extent report zato sto vec imamo extent objekat u testu-OneTest
		String extentReportPath = System.getProperty("user.dir") + "\\reports\\extentreport.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(extentReportPath);
		reporter.config().setReportName("Develomon");
		reporter.config().setDocumentTitle("Test Results");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Operating System", "Windows 10");
		extent.setSystemInfo("Tested By", "Vladimir Vasiljevic");
		return extent;

	}
}
