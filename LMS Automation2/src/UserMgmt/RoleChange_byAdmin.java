package UserMgmt;

import java.io.File;
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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import GlobalVariables.GlobalDecl;

import com.thoughtworks.selenium.Selenium;

public class RoleChange_byAdmin {
	public static WebDriver driver;
	public static Selenium selenium;
	GlobalDecl GV = new GlobalDecl();
	int a, b, c;

	@BeforeTest
	public void before() throws IOException, BiffException, RowsExceededException, WriteException {

		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.setEnableNativeEvents(true);

/*
		//Load Browser with FireBug plugin.
		//Pravin Laptop
		// File file1 = new File("C:\\Documents and Settings\\praveen\\Application Data\\Mozilla\\Firefox\\Profiles\\vd4oz1qy.default\\extensions\\firebug@software.joehewitt.com.xpi");
		//Pravin Office desktop
		File file1 = new File("C:\\Users\\praveen.GYAN\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\isdp7896.default\\extensions\\firebug@software.joehewitt.com.xpi");
		firefoxProfile.addExtension(file1);
		firefoxProfile.setPreference("extensions.firebug.currentVersion", "1.12.5"); // Avoid startup screen



		//Load 'FirePath' plugin to identify 'xPath of an element'.
		//Pravin Laptop
		//File file2 = new File("C:\\Documents and Settings\\praveen\\Application Data\\Mozilla\\Firefox\\Profiles\\vd4oz1qy.default\\extensions\\FireXPath@pierre.tholence.com.xpi");
		//Pravin Office Desktop
		File file2 = new File("C:\\Users\\praveen.GYAN\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\isdp7896.default\\extensions\\FireXPath@pierre.tholence.com.xpi");
		firefoxProfile.addExtension(file2);
		firefoxProfile.setPreference("extensions.FireXPath.currentVersion", "0.9.7");



		//Load Selenium IDE plugin to Record the scenarios...
		//Pravin Laptop
		//File file3 = new File("C:\\Documents and Settings\\praveen\\Application Data\\Mozilla\\Firefox\\Profiles\\vd4oz1qy.default\\extensions\\{a6fd85ed-e919-4a43-a5af-8da18bda539f}.xpi");
		//Pravin Office Desktop
		File file3 = new File("C:\\Users\\praveen.GYAN\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\isdp7896.default\\extensions\\{a6fd85ed-e919-4a43-a5af-8da18bda539f}.xpi");
		firefoxProfile.addExtension(file3);
		firefoxProfile.setPreference("extensions.{a6fd85ed-e919-4a43-a5af-8da18bda539f}.currentVersion", "2.4.0");



		//Load Java Formatters for Slenium IDE plugin.
		//Pravin Laptop
		//File file4 = new File("C:\\Documents and Settings\\praveen\\Application Data\\Mozilla\\Firefox\\Profiles\\vd4oz1qy.default\\extensions\\javaformatters@seleniumhq.org.xpi");
		//Pravin Office Desktop
		File file4 = new File("C:\\Users\\praveen.GYAN\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\isdp7896.default\\extensions\\javaformatters@seleniumhq.org.xpi");
		firefoxProfile.addExtension(file4);
		firefoxProfile.setPreference("extensions.javaformatters@seleniumhq.org.currentVersion", "2.4.0");
*/


		driver = new FirefoxDriver(firefoxProfile);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		selenium = new WebDriverBackedSelenium(driver, GV.baseUrl);
	}


	@Test 
	public void RoleChange_byAdmin() throws InterruptedException, RowsExceededException, WriteException, BiffException, IOException {
		System.out.println("************************** START OF THE EXECUTION ********************************* ");

		driver.get(GV.baseUrl);
		Actions act = new Actions(driver);
		WebElement onElement = driver.findElement(By.xpath(".//*[@id='wrapper']/header/div/nav/ul/li[5]/a/span"));
		act.contextClick(onElement).perform();
		act.sendKeys("T").perform();  // If you want the link to open in new tab then use T instead of w
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"w");// close the Home Tab


		//UserID and Password.
		driver.findElement(By.id("email")).sendKeys(GV.Username);
		driver.findElement(By.id("password")).sendKeys(GV.Password);
		//Click on Login Button.
		driver.findElement(By.xpath(".//*[@id='submit']")).click();
		//Click on Dashboard
		driver.findElement(By.xpath(".//*[@id='wrapper']/header/div/nav/ul/li[4]/a/span")).click();

		driver.findElement(By.linkText("Site Administration")).click();
		driver.findElement(By.linkText("Users")).click();
		driver.findElement(By.linkText("Manage Users")).click();

		//Email id is sent to email filter
		driver.findElement(By.xpath(".//*[@id='filter_email']")).sendKeys(GV.Username);
		driver.findElement(By.xpath(".//*[@id='filter_email']")).sendKeys(Keys.RETURN);

		//Tick the Checkbox
		driver.findElement(By.xpath(".//*[@id='grid']/table/tbody/tr[4]/td[1]/input")).click();

		//Click to EDIT the User
		driver.findElement(By.xpath(".//*[@id='grid']/table/tbody/tr[4]/td[8]/a[1]")).click();

		//Change the Role to 'Student'.
		new Select(driver.findElement(By.id("role_id"))).selectByVisibleText("Student");
		//Click on 'EDIT ACCOUNT' button
		driver.findElement(By.id("submit")).click();


		boolean b = driver.findElement(By.xpath(".//*[@id='column1']/div/div/div/div")).getText() != null;

		if (b) 
		{
			String msg = driver.findElement(By.xpath(".//*[@id='column1']/div/div/div[1]/div")).getText();
			System.out.println("User Role Changed to 'Super Admin'--->" + msg);
		} 
		else
		{
			System.out.println("User Role hasn't Changed , Pls check the Validations");
		}







		//Click on LOGOUT to Login 
		driver.findElement(By.xpath("html/body/div[1]/header/div/nav/ul/li[4]/a/span")).click();

		Actions act2 = new Actions(driver);
		WebElement onElement2 = driver.findElement(By.xpath(".//*[@id='wrapper']/header/div/nav/ul/li[5]/a/span"));
		act2.contextClick(onElement2).perform();
		act2.sendKeys("T").perform();  // If you want the link to open in new tab then use T instead of w
		// Close the Home Tab.
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"w");// close the Home Tab


		//UserID and Password.
		driver.findElement(By.id("email")).sendKeys(GV.Username);
		driver.findElement(By.id("password")).sendKeys(GV.Password);
		//Click on Login Button.
		driver.findElement(By.xpath(".//*[@id='submit']")).click();
		//Click on Dashboard
		driver.findElement(By.xpath(".//*[@id='wrapper']/header/div/nav/ul/li[4]/a/span")).click();

	
		String s = driver.findElement(By.xpath(".//*[@id='acc3']/li[3]/a")).getText();
		System.out.println("String S = " + s);
		if (s.contains("Site Administration"))
		{
			System.out.println("User role hasn't been changed, There is a Bug....");
		}
		else
		{
			System.out.println("User role has been changed from SuperAdmin to Student");
		}










		//Click on LOGOUT to Login 
		driver.findElement(By.xpath("html/body/div[1]/header/div/nav/ul/li[4]/a/span")).click();

		Actions act3 = new Actions(driver);
		WebElement onElement3 = driver.findElement(By.xpath(".//*[@id='wrapper']/header/div/nav/ul/li[5]/a/span"));
		act3.contextClick(onElement3).perform();
		act3.sendKeys("T").perform();  // If you want the link to open in new tab then use T instead of w
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
		driver.findElement(By.linkText("Users")).click();
		driver.findElement(By.linkText("Manage Users")).click();


		driver.findElement(By.xpath(".//*[@id='filter_email']")).sendKeys(GV.Username);
		driver.findElement(By.xpath(".//*[@id='filter_email']")).sendKeys(Keys.RETURN);

		//Tick the Checkbox
		driver.findElement(By.xpath(".//*[@id='grid']/table/tbody/tr[4]/td[1]/input")).click();

		//Click to EDIT the User
		driver.findElement(By.xpath(".//*[@id='grid']/table/tbody/tr[4]/td[8]/a[1]")).click();

		//Change the Role to 'Student'.
		new Select(driver.findElement(By.id("role_id"))).selectByValue("1");
		//Click on 'EDIT ACCOUNT' button
		driver.findElement(By.id("submit")).click();


		boolean b2 = driver.findElement(By.xpath(".//*[@id='column1']/div/div/div/div")).getText() != null;

		if (b2) 
		{
			String msg = driver.findElement(By.xpath(".//*[@id='column1']/div/div/div[1]/div")).getText();
			System.out.println("User Role Changed to 'Super Admin'--->" + msg);
		} 
		else
		{
			System.out.println("User Role hasn't Changed , Pls check the Validations");
		}








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

