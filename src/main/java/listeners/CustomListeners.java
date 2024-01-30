package listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.Base;
import utilities.extentReport;

public class CustomListeners extends Base implements ITestListener {
	ExtentReports eReport = extentReport.getExtentReport();
	ExtentTest extenttest;
	ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
		String testname=result.getName();
	    extenttest = eReport.createTest(testname);
	    extentTestThread.set(extenttest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testname=result.getInstanceName();
		//extenttest.log(Status.PASS, testname +" TEST PASS");
		extentTestThread.get().log(Status.PASS, testname +" TEST PASS");
	}

	
	@Override
	public void onTestFailure(ITestResult result) {
		//extenttest.fail(result.getThrowable());
        extentTestThread.get().fail(result.getThrowable());
		
       
       WebDriver driver=null;
       String testMethodName = result.getName();
	    	
			try {
				driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		
		try {
			String screenshotFilePath = takeSreenshot(testMethodName,driver);
			extentTestThread.get().addScreenCaptureFromPath(screenshotFilePath, testMethodName);
		} catch (IOException e) {
			
			e.printStackTrace();
		}                   
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	
	}

	@Override
	public void onStart(ITestContext context) {
		
	}

	@Override
	public void onFinish(ITestContext context) {
		eReport.flush();
		//mandatory to write, to get report
	}

}
