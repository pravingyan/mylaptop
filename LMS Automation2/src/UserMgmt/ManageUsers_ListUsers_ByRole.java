package UserMgmt;

import java.io.File;
import java.io.IOException;
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

public class ManageUsers_ListUsers_ByRole {
	public static WebDriver driver;
	public static Selenium selenium;
	//public String baseUrl;
	GlobalDecl GV = new GlobalDecl();
	String role = "Student";
	int a,b,pages;

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



		driver = new FirefoxDriver(firefoxProfile);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		selenium = new WebDriverBackedSelenium(driver, GV.baseUrl);
	}


	@Test
	public void managerUsers_Listing_ByRole() throws InterruptedException, RowsExceededException, WriteException, BiffException, IOException {
		System.out.println("************************** START OF THE EXECUTION ********************************* ");

		driver.get(GV.baseUrl);
		Actions act = new Actions(driver);
		WebElement onElement = driver.findElement(By.xpath(".//*[@id='wrapper']/header/div/nav/ul/li[5]/a/span"));
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
		driver.findElement(By.linkText("Users")).click();
		driver.findElement(By.linkText("Manage Users")).click();

		//Filter only Users by Role (Student)
		driver.findElement(By.xpath(".//*[@id='filter_Role']")).sendKeys(role);
		//Click ENTER button.
		driver.findElement(By.id("filter_email")).sendKeys(Keys.RETURN);

		//Get the Count of Total Pages in the 'User Listing Table'.
		WebElement pagination = driver.findElement(By.id("idf"));
		List <WebElement> pages = pagination.findElements(By.tagName("option"));
		int size = pages.size();      
		System.out.println("Total No.of Pages in User Listing are: " + size);



		int AllUsers = 0;
		//System.out.println(" a value is:" + a);
		for (int i = 1; i <= size; i++) 
		{
			int PageUsers = 0;
			WebElement UserList = driver.findElement(By.xpath(".//*[@id='grid']/table/tbody"));
			java.util.List<WebElement> tr1 = UserList.findElements(By.tagName("tr"));
			System.out.println("");
			System.out.println("No.of Users in Page " + i + " are: " +(tr1.size()- 3));
			PageUsers = (tr1.size()-3);
			AllUsers = (AllUsers + PageUsers);
			for (int j = 4; j <= tr1.size(); j++) {
				String s1 = driver.findElement(By.xpath(".//*[@id='grid']/table/tbody/tr[" + j + "]/td[2]")).getText();
				String s2 = driver.findElement(By.xpath(".//*[@id='grid']/table/tbody/tr[" + j + "]/td[3]")).getText();
				System.out.println((j-3)+ ". Username:- " + s1 + "--------> Email:- " +s2);
			}
			if (i == size) 
			{
				System.out.println("----------------------------------------------------------------");
				System.out.println("TOTAL NO.of USERS ----> " + AllUsers);
				break;
			}
			else
			{
				new Select(driver.findElement(By.id("idf"))).selectByIndex(i);
				selenium.waitForPageToLoad("3000");
			}
		}
		System.out.println("----------------------------------------------------------------");

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

