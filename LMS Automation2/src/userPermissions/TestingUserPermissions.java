
package userPermissions;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Selenium;

import GlobalVariables.GlobalDecl;

public class TestingUserPermissions 
{
	public  WebDriver Empdriver;
	public  Selenium selenium;
	public String baseUrl = "http://50.116.0.186:81/";
	public String Username = "praveenkd@gyanwebsolutions.com";
	//public String Username = "12345@gmail.com";
	public String Password = "Kumar7may";

	GlobalDecl GV = new GlobalDecl();
	@BeforeTest
	public void beforetest() throws BiffException, IOException, RowsExceededException, WriteException, InterruptedException
	{

		/*	System.setProperty("webdriver.ie.driver","C:\\IEDriverServer_Win32_2.39.0\\IEDriverServer.exe");
		DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
		ieCapabilities .setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true); 
		driver = new InternetExplorerDriver(ieCapabilities);*/


		System.setProperty("webdriver.chrome.driver","C:\\chromedriver_win32\\chromedriver.exe");
		Empdriver = new ChromeDriver();



		Empdriver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		selenium = new WebDriverBackedSelenium(Empdriver, baseUrl);
	}


	@Test (priority = 1)
	public void userPermissionsByRole() throws InterruptedException, RowsExceededException, WriteException, BiffException, IOException 
	{



		GV.login();
		String winHandleBefore = GV.driver.getWindowHandle();
		System.out.println("Mozilla Browser--> "+ winHandleBefore);
		GV.driver.findElement(By.xpath("//a[contains(text(),'Site Administration')]")).click();
		GV.driver.findElement(By.xpath("//a[contains(text(),'Site Permissions')]")).click();
		System.out.println("Mozilla Browser Clicked upto Site Permissions");


		Empdriver.get(GV.baseUrl);

		Empdriver.findElement(By.xpath(".//*[@id='wrapper']/header/div/nav/ul/li[5]/a")).click();

		Thread.sleep(3000);

		//Get Parent window handle
		//String winHandleBefore = Empdriver.getWindowHandle();
		System.out.println("WinHandleBefore: "+ winHandleBefore );
		for(String winHandle : Empdriver.getWindowHandles()){
			//Switch to child window
			Empdriver.switchTo().window(winHandle);
		}

		//Do some operation on child window and get child window handle.
		String winHandleAfter = Empdriver.getWindowHandle();
		System.out.println("Chrome Browser----> "+ winHandleAfter );



		//UserID and Password.
		Empdriver.findElement(By.id("email")).sendKeys(Username);
		Empdriver.findElement(By.id("login-password")).sendKeys(Password);
		//Click on Login Button.
		Empdriver.findElement(By.xpath(".//*[@id='submit']")).click();
		//Click on Dashboard
		Empdriver.findElement(By.xpath(".//*[@id='wrapper']/header/div/nav/ul/li[4]/a/span")).click();

		System.out.println("Chrome Browser upto Dashboard only");




		for(String winHandle : GV.driver.getWindowHandles()){
			//Switch to child window
			GV.driver.switchTo().window(winHandle);
		}


		System.out.println("Current Window is: "+GV.driver.getWindowHandle());

		GV.driver.findElement(By.xpath("//a[contains(text(),'Account')]")).click();
		System.out.println("Mozilla Browser Clicked on Account");
		Thread.sleep(2000);
		GV.driver.findElement(By.xpath("//a[contains(text(),'Change Password')]")).click();

		Thread.sleep(2000);
		GV.driver.findElement(By.xpath("//a[contains(text(),'Site Administration')]")).click();
		Thread.sleep(2000);
		GV.driver.findElement(By.xpath("//a[contains(text(),'Site Permissions')]")).click();

		Empdriver.switchTo().defaultContent();
		Empdriver.switchTo().window(winHandleAfter);
		System.out.println("Chrome Browser Clicked on Course Admin");
		Empdriver.findElement(By.xpath("//a[contains(text(),'Course Administration')]")).click();




	}



	@AfterTest
	public void afterTest() throws InterruptedException {
		System.out
		.println("************************** END OF THE EXECUTION ********************************* ");
		System.out.println("");
		Thread.sleep(9000);
		selenium.stop();
		GV.driver.quit();
		Empdriver.quit();
	}
}
