package commonFunction;

import org.openqa.selenium.By;
import org.testng.Reporter;

import util.AppUtil;

public class FunctionLibrary extends AppUtil {
	public void adminLogin(String User,String Pass)
	{
		driver.findElement(By.xpath(conpro.getProperty("ObjUser"))).sendKeys(User);
		driver.findElement(By.xpath(conpro.getProperty("ObjPass"))).sendKeys(Pass);
		driver.findElement(By.xpath(conpro.getProperty("Objlogin"))).click();	
	}
	public boolean isAdminDisplayed()
	{
		if(driver.findElement(By.xpath(conpro.getProperty("ObjAdmin"))).isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean isErrorMessageDisplayed()
	{
		String Err_Mess=driver.findElement(By.xpath(conpro.getProperty("ObjError"))).getText().toLowerCase();
		if(Err_Mess.contains("invalid")||Err_Mess.contains("empty"))
		{
			Reporter.log(Err_Mess, true);
			return true;
		}
		else
		{
			return false;
		}
	}
	public void adminLogout()
	{
		driver.findElement(By.xpath(conpro.getProperty("ObjWelcome"))).click();
		driver.findElement(By.xpath(conpro.getProperty("ObjLogout"))).click();
		
	}
	

}
