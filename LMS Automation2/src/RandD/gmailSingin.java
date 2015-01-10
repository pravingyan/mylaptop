package RandD;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Selenium;

public class gmailSingin {





 public static WebDriver driver;
 public static Selenium selenium;
 public String baseUrl= "http://www.gmail.com/login";

 @BeforeTest
 public void before() throws IOException, BiffException, RowsExceededException, WriteException 
 {

  FirefoxProfile firefoxProfile = new FirefoxProfile();
  firefoxProfile.setEnableNativeEvents(true);

  driver= new FirefoxDriver(firefoxProfile);
  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  selenium = new WebDriverBackedSelenium(driver, baseUrl);
 }
 @Test
 public void addNewRole() throws InterruptedException  {
  System.out.println("************************** START OF THE EXECUTION ********************************* ");
  driver.get(baseUrl);
  driver.findElement(By.id("gmail-create-account")).click();
  driver.findElement(By.id("FirstName")).sendKeys("shraavan1234");
  driver.findElement(By.id("LastName")).sendKeys("kumar");
  driver.findElement(By.id("GmailAddress")).sendKeys("shraavan1234");
  driver.findElement(By.id("Passwd")).sendKeys("9833328837timsy");
  driver.findElement(By.id("PasswdAgain")).sendKeys("9833328837timsy");
  driver.findElement(By.xpath(".//*[@id='BirthMonth']/div[1]/div[2]")).click();
 // selenium.click("css=#HiddenBirthMonth");
  driver.findElement(By.xpath(".//*[@id=':2']/div")).click();
 
 }
}