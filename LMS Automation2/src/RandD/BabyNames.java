
package RandD;


import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

import GlobalVariables.GlobalDecl;

public class BabyNames
{
	public static WebDriver driver;
	public static Selenium selenium;
	public String baseUrl= " ";

	@BeforeTest
	public void before() throws IOException, BiffException, RowsExceededException, WriteException 
	{

		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.setEnableNativeEvents(true);

		driver= new FirefoxDriver(firefoxProfile);
		driver.manage().timeouts().implicitlyWait(30000, TimeUnit.SECONDS);
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}
	@Test

	public void addAssessments() throws InterruptedException, RowsExceededException, WriteException, BiffException, IOException 
	{
		System.out.println("************************** START OF THE EXECUTION ********************************* ");
		driver.get(baseUrl);
		selenium.waitForPageToLoad("2000");
		for (int i = 3; i < 25 ; i++) 
			
		{

			System.out.println("************************************************************************");
			driver.findElement(By.xpath(".//*[@id='rgl']/li[" + i + "]/a")).click();
			selenium.waitForPageToLoad("20000");
			
			String name = driver.findElement(By.xpath("html/body/div[2]/div[5]/div[2]")).getText();
			System.out.println("NAMES----->"+name);
			System.out.println("");
			System.out.println("");
		}
	}


}
