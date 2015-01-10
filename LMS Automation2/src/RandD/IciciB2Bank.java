package RandD;

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
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



import com.thoughtworks.selenium.Selenium;

public class IciciB2Bank {
	public static WebDriver driver;
	public static Selenium selenium;

	int a, b, c;
	public String baseUrl= "http://b2.icicibank.com/";

	@BeforeTest
	public void before() throws IOException, BiffException, RowsExceededException, WriteException {

		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.setEnableNativeEvents(true);


		driver = new FirefoxDriver(firefoxProfile);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}


	@Test 
	public void RoleChange_byAdmin() throws InterruptedException, RowsExceededException, WriteException, BiffException, IOException
	{
		System.out.println("************************** START OF THE EXECUTION ********************************* ");

		driver.get(baseUrl);
		driver.findElement(By.xpath(".//*[@id='Image1']")).click();
		new Select(driver.findElement(By.id("SALUTATION"))).selectByValue("SAL001");//Select dropdown value
		driver.findElement(By.id("FIRST_NAME")).sendKeys("shraavan123");
		driver.findElement(By.id("MIDDLE_NAME")).sendKeys("kumar");
		driver.findElement(By.id("LAST_NAME")).sendKeys("a");
		driver.findElement(By.id("NAME_OF_DEBIT_CARD")).sendKeys("State Bank of India");
		driver.findElement(By.xpath(".//*[@id='PersonalDetails']/table[2]/tbody/tr[2]/td[2]/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[3]/td/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/table/tbody/tr/td[2]/a/img")).click();
		
		driver.switchTo().frame("gToday_Ubound:normal_Ubound:agenda.js");
		new Select(driver.findElement(By.id("MonSelect"))).selectByValue("4");
		new Select(driver.findElement(By.id("YearSelect"))).selectByValue("1977");
		driver.findElement(By.xpath(".//*[@id='innerDiv']/table/tbody/tr[6]/td[7]/div/a")).click();
		driver.switchTo().defaultContent();
		
		
		driver.findElement(By.id("MOTHER_MID_NAME")).sendKeys("laxmi");
		driver.findElement(By.id("FATHER_NAME")).sendKeys("Venkaiah");
		driver.findElement(By.id("radioMale")).click();
		new Select(driver.findElement(By.id("MARITAL_STATUS"))).selectByValue("MRE002");
		new Select(driver.findElement(By.id("GROSS_ANNUAL_INDIVIDUAL_INCOME"))).selectByValue("216");
		new Select(driver.findElement(By.id("SOURCE_OF_FUND"))).selectByValue("SOF001");
		new Select(driver.findElement(By.id("GROSS_ANNUAL_FAMILY_INCOME"))).selectByValue("200000");
		new Select(driver.findElement(By.id("EDUCATION"))).selectByValue("ED004");
		new Select(driver.findElement(By.id("RES_RESIDENTIAL_TYPE"))).selectByValue("RSD001");
		new Select(driver.findElement(By.id("RES_RESIDENTIAL_STATUS"))).selectByValue("RSH002");
		new Select(driver.findElement(By.id("RES_NO_YRS_CURR_RESIDENCE"))).selectByValue("001");
		new Select(driver.findElement(By.id("PAN_APPLICATION_NO"))).selectByValue("FM6001");
		driver.findElement(By.id("NOMINEE_FIRST_NAME")).sendKeys("venkateshwarlu");
		driver.findElement(By.id("NOMINEE_LAST_NAME")).sendKeys("Rekha");
		driver.switchTo().frame("gToday_Ubound:normal_Ubound:agenda.js");
		new Select(driver.findElement(By.id("MonSelect"))).selectByValue("5");
		new Select(driver.findElement(By.id("YearSelect"))).selectByValue("1974");
		driver.switchTo().defaultContent();
		driver.findElement(By.id("NOMINEE_GENDER")).click();
		new Select(driver.findElement(By.id("NOMINEE_RELATIONSHIP"))).selectByValue("RES004");
		driver.findElement(By.id("btnSave")).click(); 


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

