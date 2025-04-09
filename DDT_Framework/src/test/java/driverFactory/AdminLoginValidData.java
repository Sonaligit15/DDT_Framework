package driverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunction.FunctionLibrary;
import util.AppUtil;
import util.ExcelFileUtil;

public class AdminLoginValidData extends AppUtil{
	String inputpath="./FileInput/TestData.xlsx/";
	String outputpath="./FileOutput/ValidLoginResults.xlsx/";
	String TCSheet="ValidLoginData";
	ExtentReports reports;
	ExtentTest logger;
	@Test
	public void startTest() throws Throwable
	{
		reports=new ExtentReports("./target/ValidData.html");
		ExcelFileUtil xl=new ExcelFileUtil(inputpath);
		int rc=xl.rowCount(TCSheet);
		Reporter.log("No of rows are::"+rc,true);
		for(int i=1;i<=rc;i++)
		{
			logger=reports.startTest("Valid Login Data");
			String user=xl.getCellData(TCSheet, i, 0);
			String pass=xl.getCellData(TCSheet, i, 1);
			logger.log(LogStatus.INFO,user+"  "+pass);
			FunctionLibrary lp =new FunctionLibrary();
			lp.adminLogin(user, pass);
			boolean res=lp.isAdminDisplayed();
			if(res)
			{
				xl.setCellData(TCSheet, i, 2, "Pass",outputpath);
				logger.log(LogStatus.PASS, "Valid Credentials");
			}
			else
			{
				xl.setCellData(TCSheet, i, 2, "Pass",outputpath);
				logger.log(LogStatus.FAIL, "InValid Credentials");
			}
			reports.endTest(logger);
			reports.flush();
			lp.adminLogout();

		}

	}



}
