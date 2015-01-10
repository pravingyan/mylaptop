package UserMgmt;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.Alert;
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

public class AddCustom_UserFields_WF
{
	public static WebDriver driver;
	public static Selenium selenium;
	GlobalDecl GV = new GlobalDecl();
	public int rownum;
	public String sb;

	@BeforeTest
	public void addUser_Admin() throws IOException, BiffException, RowsExceededException, WriteException {
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.setEnableNativeEvents(true);

		driver = new FirefoxDriver(firefoxProfile);
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
		selenium = new WebDriverBackedSelenium(driver, GV.baseUrl);

	}
	@Test 
	public void userFields() throws InterruptedException, RowsExceededException, WriteException, BiffException, IOException {
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
		driver.findElement(By.id("login-password")).sendKeys(GV.Password);
		//Click on Login Button.
		driver.findElement(By.xpath(".//*[@id='submit']")).click();
		//Click on Dashboard
		driver.findElement(By.xpath(".//*[@id='wrapper']/header/div/nav/ul/li[4]/a/span")).click();


		driver.findElement(By.linkText("Site Administration")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Users")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("User Fields")).click();
		Thread.sleep(1000);

		//Click on "Add New Field" link.
		driver.findElement(By.xpath(".//*[@id='item2']/div/div[2]/a")).click();

		sb = "Gender";
		driver.findElement(By.id("label")).sendKeys(sb);
		new Select(driver.findElement(By.id("type"))).selectByValue("radio");
		driver.findElement(By.id("submit")).click();

		Thread.sleep(2000);

		WebElement customField = driver.findElement(By.xpath(".//*[@id='item2']/div/table/tbody"));
		java.util.List<WebElement> tr1 = customField.findElements(By.tagName("tr"));
		System.out.println("No.of Rows are:" + tr1.size());

		for (int i = 9; i <= tr1.size(); i++) {
			String s = driver.findElement(By.xpath(".//*[@id='item2']/div/table/tbody/tr[" + i + "]/td[1]")).getText();
			//System.out.println(s);
			if (s.contains(sb))
			{
				//Click on 'Add Option" Link to add option 1
				driver.findElement(By.xpath(".//*[@id='item2']/div/table/tbody/tr[" + i + "]/td[2]/a[3]")).click();
				selenium.waitForPageToLoad("3000");
				//	rownum = i;
				//	System.out.println("Rownum value is:  "+ rownum);
				break;
			}
		}

		driver.findElement(By.id("label")).sendKeys("Male");
		driver.findElement(By.id("submit")).click();
		Thread.sleep(6000);
		selenium.waitForPageToLoad("3000");

		for (int i = 9; i <= tr1.size(); i++) {
			String s = driver.findElement(By.xpath(".//*[@id='item2']/div/table/tbody/tr[" + i + "]/td[1]")).getText();
			//System.out.println(s);
			if (s.contains(sb))
			{
				//Click on 'Add Option" Link to add option 2
				driver.findElement(By.xpath(".//*[@id='item2']/div/table/tbody/tr[" + i + "]/td[2]/a[3]")).click();
				selenium.waitForPageToLoad("3000");
				break;
			}
		}

		driver.findElement(By.id("label")).sendKeys("Female");
		driver.findElement(By.id("submit")).click();
		Thread.sleep(6000);
		selenium.waitForPageToLoad("3000");


		driver.findElement(By.linkText("Account")).click();
		driver.findElement(By.linkText("Edit Profile")).click();
		selenium.waitForPageToLoad("3000");
		boolean b = selenium.isTextPresent("sb");
		if (b) {
			System.out.println(sb +" Custom Field found in 'Edit Profile' Page");
		}
		else
		{
			System.out.println(sb +" Custom Field NOT FOUND in Edit Profile Page");
		}


		driver.findElement(By.linkText("Site Administration")).click();
		driver.findElement(By.linkText("Users")).click();
		driver.findElement(By.linkText("Add User")).click();
		selenium.waitForPageToLoad("3000");

		boolean b2 = selenium.isTextPresent("sb");
		if (b2) {
			System.out.println(sb +" Custom Field found in Admin > 'Add User' Page");
		}
		else
		{
			System.out.println(sb +" Custom Field NOT FOUND in Admin > 'Add User' Page");
		}






		//Deleting the Added Custom User Fields.
		driver.findElement(By.linkText("Site Administration")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Users")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("User Fields")).click();
		Thread.sleep(1000);

		WebElement customField2 = driver.findElement(By.xpath(".//*[@id='item2']/div/table/tbody"));
		java.util.List<WebElement> tr2 = customField2.findElements(By.tagName("tr"));
		System.out.println("No.of Rows are:" + tr2.size());

		for (int i = 9; i <= tr1.size(); i++) {
			String s = driver.findElement(By.xpath(".//*[@id='item2']/div/table/tbody/tr[" + i + "]/td[1]")).getText();
			//System.out.println(s);
			if (s.contains(sb))
			{
				//Click on 'DELETE'
				driver.findElement(By.xpath(".//*[@id='item2']/div/table/tbody/tr[" + i +"]/td[2]/a[2]")).click();
				Alert alert = driver.switchTo().alert();
				alert.accept();
				System.out.println("User Fields Deleted.....");
				break;
			}
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

