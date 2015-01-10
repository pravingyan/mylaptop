package RandD;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DateFormat.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import GlobalVariables.GlobalDecl;


import com.thoughtworks.selenium.Selenium;

public class HandlingPopup {
	public static WebDriver driver;
	public static Selenium selenium;
	GlobalDecl GV = new GlobalDecl();
	public int a, Ch = 0, Le = 0;

	//Enter Course Name to be created.
	public String CourseName = "DemoCourse20nov2013";
	//Enter the Total no.of Chapters to be created.
	int TotaChaptersRequired = 2;
	int TotaLessonsRequired = 1;
	int TotaAssignmentsRequired = 1;
	int TotaAssessmentsRequired = 1;



	@BeforeTest
	public void addUser_Admin() throws IOException, BiffException, RowsExceededException, WriteException {
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.setEnableNativeEvents(true);

		driver = new FirefoxDriver(firefoxProfile);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		selenium = new WebDriverBackedSelenium(driver, GV.baseUrl);

	}
	@Test (priority = 1)
	public void f() throws InterruptedException, RowsExceededException, WriteException, BiffException, IOException {
		System.out.println("************************** START OF THE EXECUTION ********************************* ");
		driver.get(GV.baseUrl);
		// considering that there is only one tab opened in that point.
		String mainBrowserWindow = driver.getWindowHandle();
		System.out.println("mainBrowserWindow Name is: "+ mainBrowserWindow);

		Actions act = new Actions(driver);
		WebElement onElement = driver.findElement(By.xpath(".//*[@id='wrapper']/header/div/nav/ul/li[5]/a"));
		act.contextClick(onElement).perform();
		act.sendKeys("T").perform();  // If you want the link to open in new tab then use T instead of w
		// Close the Home Tab.
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"w");// close the Home Tab


		//UserID and Password.
		driver.findElement(By.id("email")).sendKeys(GV.Username);
		driver.findElement(By.id("password")).sendKeys(GV.Password);
		//Click on Login Button.
		driver.findElement(By.xpath(".//*[@id='submit']")).click();
		//Click on Dashboard
		driver.findElement(By.xpath(".//*[@id='wrapper']/header/div/nav/ul/li[4]/a/span")).click();



		//Click on 'Course Administartion'.
		driver.findElement(By.xpath(".//*[@id='acc3']/li[4]/a")).click();

		// Click on Create a Course
		driver.findElement(By.xpath(".//*[@id='acc3']/li[4]/ul/li[2]/a")).click();
		Thread.sleep(1000);

		//Enter Course Name.
		driver.findElement(By.xpath(".//*[@id='title']")).sendKeys(CourseName);
		driver.findElement(By.xpath(".//*[@id='shortname']")).sendKeys("SeleAutomation");

		driver.findElement(By.xpath(".//*[@id='cat']/a")).click();



		String str[]=selenium.getAllWindowNames();
		System.out.println("Total No.of Windows: "+ str.length);

		for (int i = 0; i < str.length; i++)
		{
			System.out.println("Window Name is : " +i+" = " +str[i]);
		}


		
	  //  driver.switchTo().
		
		//Enter Category Name in Popup Window
		driver.findElement(By.id("name")).sendKeys("Popup Category");

	}
	
	
	@AfterTest
	public void afterTest() throws InterruptedException {
		System.out
		.println("************************** END OF THE EXECUTION ********************************* ");
		System.out.println("");
		Thread.sleep(2000);
		selenium.stop();
		driver.quit();
	}
}