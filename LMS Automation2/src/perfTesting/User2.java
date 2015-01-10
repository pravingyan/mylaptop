package perfTesting;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import GlobalVariables.GlobalDecl;
import com.thoughtworks.selenium.Selenium;

public class User2
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		selenium = new WebDriverBackedSelenium(driver, GV.baseUrl);
	}

	@Test
	public void User2() throws InterruptedException, RowsExceededException, WriteException, BiffException, IOException {


		driver.get(GV.baseUrl);

		selenium.waitForPageToLoad("3000");
		//Click on Login.
		driver.findElement(By.xpath(".//*[@id='wrapper']/header/div/nav/ul/li[6]/a/span")).click();
		driver.findElement(By.id("email")).sendKeys("shraavan.abbanaboina@gmail.com");
		driver.findElement(By.id("password")).sendKeys("Prison123456");

		driver.findElement(By.id("submit")).click();
		Thread.sleep(2000);

		//Click on 'Course Administartion'.
		driver.findElement(By.xpath(".//*[@id='acc3']/li[4]/a")).click();

		// Click on Create a Course
		driver.findElement(By.xpath(".//*[@id='acc3']/li[4]/ul/li[2]/a")).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath(".//*[@id='title']")).sendKeys("Automation Course");
		driver.findElement(By.xpath(".//*[@id='shortname']")).sendKeys("SeleAuto");

		new Select(driver.findElement(By.id("category"))).selectByValue("12");
		driver.findElement(By.xpath(".//*[@id='active-Yes']")).click();

		driver.findElement(By.xpath(".//*[@id='submit']")).click();

		selenium.waitForPageToLoad("3000");
		Thread.sleep(1000);
		//Click on 'Course Administartion'.
		driver.findElement(By.xpath(".//*[@id='acc3']/li[4]/a")).click();

		//Click on 'Courses'
		driver.findElement(By.xpath(".//*[@id='acc3']/li[4]/ul/li[1]/a")).click();

		Thread.sleep(2000);
		WebElement UserList = driver.findElement(By.xpath(".//*[@id='view-content']/div/div/table/tbody"));
		java.util.List<WebElement> tr1 = UserList.findElements(By.tagName("tr"));
		/*System.out.println("No.of Users in Page " + i + " are: " +(tr1.size()-1));
		System.out.println("");
		 */
		for (int i = 2; i < tr1.size(); i++) {

			String s =driver.findElement(By.xpath(".//*[@id='view-content']/div/div/table/tbody/tr[" +i +"]/td[2]")).getText();
			if (s.contains("Automation Course")) 
			{
				//Delete the Course.
				//driver.findElement(By.xpath(".//*[@id='view-content']/div/div/table/tbody/tr[" +i+ "]/td[4]/a[2]")).click();
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
