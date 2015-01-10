
package UserMgmt;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import GlobalVariables.GlobalDecl;
import com.thoughtworks.selenium.Selenium;

public class Logout
{
	public static WebDriver driver;
	public static Selenium selenium;
	GlobalDecl GV = new GlobalDecl();
	@BeforeTest
	public void beforeTest() throws IOException {
		//baseUrl = "http://lms.gyanfinder.com/moodle/login/index.php";
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
		Thread.sleep(2000);

		boolean b = driver.findElement(By.xpath(".//*[@id='wrapper']/header/div/nav/ul/li[6]/a/span")).isEnabled();
		if (b) 
		{
			System.out.println("Logged in Successful");
			driver.findElement(By.xpath(".//*[@id='wrapper']/header/div/nav/ul/li[6]/a/span")).click();
			System.out.println("Clicked on Logout Button.");
		}
		else
		{
			System.out.println("Error---> Not Logged in....");
		}


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
