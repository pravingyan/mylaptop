package RandD;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import GlobalVariables.GlobalDecl;

import com.thoughtworks.selenium.Selenium;

public class NewTABB2 {
	public static WebDriver driver;
	public static Selenium selenium;
	GlobalDecl GV = new GlobalDecl();
	String t;
	@Test
	public void testTabs() throws InterruptedException {




		System.out.println("************************** START OF THE EXECUTION ********************************* ");
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.setEnableNativeEvents(true);

		driver = new FirefoxDriver(firefoxProfile);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		selenium = new WebDriverBackedSelenium(driver, GV.baseUrl);

		driver.get(GV.baseUrl);
		//Click on Login link.

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
	

		driver.findElement(By.linkText("Site Administration")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Users")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Bulk Registration")).click();
		Thread.sleep(1000);

		//driver.findElement(By.xpath(".//*[@idm='image']")).click();

		WebElement file = driver.findElement(By.xpath(".//*[@id='image']")); 
		file.sendKeys(GV.Input_ExcelFile+"bulkuploaduser07.xls" );

		new Select(driver.findElement(By.id("timezone"))).selectByValue("Asia/Dhaka");

		new Select(driver.findElement(By.id("role_id"))).selectByValue("9");

		driver.findElement(By.xpath(".//*[@id='submit']")).click();

	}


}
