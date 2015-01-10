
package UserMgmt;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import jxl.read.biff.BiffException;
import jxl.write.Label;
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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import GlobalVariables.GlobalDecl;

import com.thoughtworks.selenium.Selenium;

public class Change_Password
{
	public static WebDriver driver;
	public static Selenium selenium;
	GlobalDecl GV = new GlobalDecl();
	public String newPassword = "Pravin7may";

	@BeforeTest
	public void before() throws IOException, BiffException, RowsExceededException, WriteException {

		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.setEnableNativeEvents(true);


		//Load Browser with FireBug plugin.
		//Pravin Laptop
		// File file1 = new File("C:\\Documents and Settings\\praveen\\Application Data\\Mozilla\\Firefox\\Profiles\\vd4oz1qy.default\\extensions\\firebug@software.joehewitt.com.xpi");
		//Pravin Office desktop
		File file1 = new File("C:\\Users\\praveen.GYAN\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\isdp7896.default\\extensions\\firebug@software.joehewitt.com.xpi");
		firefoxProfile.addExtension(file1);
		firefoxProfile.setPreference("extensions.firebug.currentVersion", "1.12.4"); // Avoid startup screen



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



		driver = new FirefoxDriver(firefoxProfile);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		selenium = new WebDriverBackedSelenium(driver, GV.baseUrl);
	}

	@Test
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
		//Click on Account Link.
		driver.findElement(By.linkText("Account")).click();
		//Click on Change Password link.
		driver.findElement(By.linkText("Change Password")).click();


		driver.findElement(By.id("oldpass")).sendKeys(GV.Password);
		driver.findElement(By.id("password")).sendKeys(newPassword);
		driver.findElement(By.id("confpass")).sendKeys(newPassword);
		driver.findElement(By.id("submit")).click();

		String successMsg = driver.findElement(By.xpath(".//*[@id='column1']/div/div/div/div")).getText();
		System.out.println("Success Message --->" + successMsg);


		//Click on LOGOUT to Login with Changed Email ID.
		driver.findElement(By.xpath("html/body/div[1]/header/div/nav/ul/li[4]/a/span")).click();

		Actions act2 = new Actions(driver);
		WebElement onElement2 = driver.findElement(By.xpath(".//*[@id='wrapper']/header/div/nav/ul/li[5]/a/span"));
		act2.contextClick(onElement2).perform();
		act2.sendKeys("T").perform();  // If you want the link to open in new tab then use T instead of w
		// Close the Home Tab.
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"w");// close the Home Tab


		//UserID and Password.
		driver.findElement(By.id("email")).sendKeys(GV.Username);
		driver.findElement(By.id("password")).sendKeys(newPassword);
		//Click on Login Button.
		driver.findElement(By.xpath(".//*[@id='submit']")).click();
		//Click on Dashboard
		driver.findElement(By.xpath(".//*[@id='wrapper']/header/div/nav/ul/li[4]/a/span")).click();

		//Click on Account Link.
		driver.findElement(By.linkText("Account")).click();
		//Click on Change Password link.
		driver.findElement(By.linkText("Change Password")).click();

		driver.findElement(By.id("oldpass")).sendKeys(newPassword);
		driver.findElement(By.id("password")).sendKeys(GV.Password);
		driver.findElement(By.id("confpass")).sendKeys(GV.Password);
		driver.findElement(By.id("submit")).click();

		String successMsg2 = driver.findElement(By.xpath(".//*[@id='column1']/div/div/div/div")).getText();
		System.out.println("Success Message --->" + successMsg2);




		//Click on LOGOUT to Login with Changed Email ID.
		driver.findElement(By.xpath("html/body/div[1]/header/div/nav/ul/li[4]/a/span")).click();

		Actions act3 = new Actions(driver);
		WebElement onElement3 = driver.findElement(By.xpath(".//*[@id='wrapper']/header/div/nav/ul/li[5]/a/span"));
		act3.contextClick(onElement3).perform();
		act3.sendKeys("T").perform();  // If you want the link to open in new tab then use T instead of w
		// Close the Home Tab.
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"w");// close the Home Tab


		//UserID and Password.
		driver.findElement(By.id("email")).sendKeys(GV.Username);
		driver.findElement(By.id("password")).sendKeys(newPassword);
		//Click on Login Button.
		driver.findElement(By.xpath(".//*[@id='submit']")).click();

		Thread.sleep(2000);

		String url = driver.getCurrentUrl();

		if (url.contains("login"))
		{
			String err = driver.findElement(By.xpath(".//*[@id='wrapper']/main/div/div/div")).getText();
			String content = err;
			content = content.replace("Login", "");
			content = content.replace("Forgot Password", "");
			content = content.replace("*Email ID", "");
			content = content.replace("*Password", "");
			content = content.replace("\n", ", ");
			content = content.replace(",", " ");
			System.out.println("Error Message---> "+content);
			System.out.println("Old Password is not Accepting... You have to Login with Changed Password only.");
			driver.get(GV.baseUrl +"login");
		}
		//UserID and Password.
		driver.findElement(By.id("email")).sendKeys(GV.Username);
		driver.findElement(By.id("password")).sendKeys(GV.Password);
		//Click on Login Button.
		driver.findElement(By.xpath(".//*[@id='submit']")).click();
		//Click on Dashboard
		driver.findElement(By.xpath(".//*[@id='wrapper']/header/div/nav/ul/li[4]/a/span")).click();
		System.out.println("Login Sucessful with changed Password....");


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
