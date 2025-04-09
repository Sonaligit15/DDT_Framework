package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunction.FunctionLibrary;
import util.AppUtil;
import util.ExcelFileUtil;

public class AdminLoginInvalidData extends AppUtil {
	String inputpath="FileInput/TestData.xlsx";
	String outputpath="FileOutput/InvalidloginResults.xlsx";
	ExtentReports reports;
	ExtentTest logger;
	String TCSheet="InvalidLoginData";
	@Test
	public void startTest() throws Throwable
	{
		reports=new ExtentReports("./target/InValiddata.html");
	    ExcelFileUtil xl=new ExcelFileUtil(inputpath);
	    int rc=xl.rowCount(TCSheet);
	    Reporter.log("No.of rows are::"+rc,true);
	    for(int i=1;i<=rc;i++)
	    {
	    	logger=reports.startTest("Invalid Login Data");
	    	logger.assignAuthor("Sonali");
	    	logger.assignCategory("Invalid Data");
	    	String user=xl.getCellData(TCSheet, i, 0);
	    	String pass=xl.getCellData(TCSheet, i, 1);
	    	FunctionLibrary lp=new FunctionLibrary();
	    	lp.adminLogin(user, pass);
	    	boolean res=lp.isErrorMessageDisplayed();
	    	if(res)
	    	{
	    		File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    		FileUtils.copyFile(screenshot,new File("./target/Screenshot/"+i+" "+"invalidlogin.png"));
	    		xl.setCellData(TCSheet, i, 2, "Pass", outputpath);
	    		logger.log(LogStatus.PASS, "Invalid Credentials");
	    		logger.addScreenCapture("./target/Screenshot/"+i+" "+"invalidlogin.png");
	    	}
	    	else
	    	{
	    		xl.setCellData(TCSheet, i, 2, "Fail", outputpath);
	    		logger.log(LogStatus.FAIL, "Valid Credentials");
	    	}
	    	reports.endTest(logger);
	    	reports.flush();
	    	
	    	
	    }
	    
	}
	

}
