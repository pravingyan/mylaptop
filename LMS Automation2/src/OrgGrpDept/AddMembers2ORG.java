//  Adding Memebers into an Organisation thru "Bulk Upload".

package OrgGrpDept;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import GlobalVariables.GlobalDecl;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

public class AddMembers2ORG {
	public static WebDriver driver;
	public static Selenium selenium;
	GlobalDecl GV = new GlobalDecl();
	int a, b, c;

	@BeforeTest
	public void addUser_Admin() throws IOException, BiffException, RowsExceededException, WriteException {
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.setEnableNativeEvents(true);

		driver = new FirefoxDriver(firefoxProfile);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		selenium = new WebDriverBackedSelenium(driver, GV.baseUrl);
	}

	@Test
	public void f() throws InterruptedException, RowsExceededException, WriteException, BiffException, IOException {
		System.out.println("************************** START OF THE EXECUTION ********************************* ");
		driver.get(GV.baseUrl);
		//Click on Login link.
		driver.findElement(By.xpath(".//*[@id='wrapper']/header/div/nav/ul/li[6]/a")).click();
		//UserID and Password.
		driver.findElement(By.id("email")).sendKeys(GV.Username);
		driver.findElement(By.id("password")).sendKeys(GV.Password);
		//Click on Login Button.
		driver.findElement(By.xpath(".//*[@id='submit']")).click();
		//Click on Dashboard
		driver.findElement(By.xpath(".//*[@id='wrapper']/header/div/nav/ul/li[5]/a/span")).click();

		driver.findElement(By.linkText("Site Administration")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Organizations")).click();
		Thread.sleep(1000);  driver.findElement(By.linkText("Manage Organizations")).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath(".//*[@id='filter_title']")).sendKeys("AutoTestORG");
		//Press ENTER key (Keyboard ENTER Key)
		Keyboard kb = ((RemoteWebDriver) driver).getKeyboard();
		kb.pressKey(Keys.RETURN);


		//Click on Add Members Icon.
		driver.findElement(By.xpath(".//*[@id='grid']/table/tbody/tr[4]/td[6]/a[4]")).click();

		driver.findElement(By.linkText("Bulk Registration")).click();
		
		//Upload File for Bulk Reg of Members.
		WebElement file = driver.findElement(By.xpath(".//*[@id='image']")); 
		file.sendKeys(GV.Input_ExcelFile+"bulkuploaduser07.xls" );

		new Select(driver.findElement(By.id("timezone"))).selectByValue("Asia/Dhaka");

		driver.findElement(By.xpath(".//*[@id='submit']")).click();

	}

	@AfterTest
	public void afterTest() throws InterruptedException {
		System.out
		.println("************************** END OF THE EXECUTION ********************************* ");
		System.out.println("");
		Thread.sleep(2000);
		//selenium.stop();
		//driver.quit();
	}
}

