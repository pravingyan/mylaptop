
package UserMgmt;

import java.io.File;
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

public class ForgotPassWord
{
	public static WebDriver driver;
	public static Selenium selenium;
	//public String baseUrl;
	GlobalDecl GV = new GlobalDecl();

	@BeforeTest
	public void beforeTest() throws IOException {
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
		selenium.waitForPageToLoad("3000");
		
		//Click on 'Login' link.
		driver.findElement(By.xpath(".//*[@id='wrapper']/header/div/nav/ul/li[6]/a")).click();
	
		//Click on 'Forgot Password link
		driver.findElement(By.linkText("Forgot Password")).click();
		
		Thread.sleep(2000);
		
		//invalid or unregistered email is given.
		driver.findElement(By.id("email")).sendKeys("pra~~!/'n7may@gmail.com");
		driver.findElement(By.id("submit")).click();
		String s = driver.findElement(By.xpath("//*[@id='wrapper']/main/div/div/div/div[2]/div")).getText();
		System.out.println("Invalid email id as Input ---> " + s);

		//Registered emailID is given....
		driver.findElement(By.id("email")).sendKeys("pravinag7@gmail.com");
		driver.findElement(By.id("submit")).click();
		String s1 = driver.findElement(By.xpath("//*[@id='wrapper']/main/div/div/div/div[2]/div")).getText();
		System.out.println("Valid email is given and ---> " + s1);

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
