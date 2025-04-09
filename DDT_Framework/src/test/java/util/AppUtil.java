package util;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class AppUtil {
public static	WebDriver driver;
public	static Properties conpro;
	@BeforeMethod
	public static void setUp()throws Throwable
	{
		conpro=new Properties();
		conpro.load(new FileInputStream("./PropertyFiles/Environment.properties/"));
		if( conpro.getProperty("Browser").equalsIgnoreCase("Chrome"))
		{
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.get(conpro.getProperty("Url"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			
		}
		else if(conpro.getProperty("Browser").equalsIgnoreCase("Chrome"))
		{
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.get(conpro.getProperty("Url"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			
		}
		else
		{
			throw new IllegalArgumentException("Browser is not matching");
		}
	}
		@AfterMethod
		public static void tearDowm()
		{
			driver.quit();
		}
		
	}