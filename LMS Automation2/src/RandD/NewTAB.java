package RandD;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import GlobalVariables.GlobalDecl;

import com.thoughtworks.selenium.Selenium;

public class NewTAB {
	public static WebDriver driver;
	public static Selenium selenium;
	GlobalDecl GV = new GlobalDecl();
  @Test
  public void testTabs() throws InterruptedException {
	  
	  
	  
	  
	  System.out.println("************************** START OF THE EXECUTION ********************************* ");
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.setEnableNativeEvents(true);

		driver = new FirefoxDriver(firefoxProfile);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		selenium = new WebDriverBackedSelenium(driver, GV.baseUrl);
		
		driver.get(GV.baseUrl);
		//Click on Login link.
	
		
	    // considering that there is only one tab opened in that point.
	    String oldTab = driver.getWindowHandle();
	    System.out.println("Old Tab  Name is: "+ oldTab);
	    
		driver.findElement(By.xpath(".//*[@id='wrapper']/header/div/nav/ul/li[5]/a")).click();
	    ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
	    newTab.remove(oldTab);
	    newTab.size();
	

	    // change focus to new tab
	    driver.switchTo().window(newTab.get(0));
	    Thread.sleep(8000);
	  
	  //UserID and Password.
	  		driver.findElement(By.id("email")).sendKeys(GV.Username);
	  		driver.findElement(By.id("password")).sendKeys(GV.Password);
	  		//Click on Login Button.
	  		driver.findElement(By.xpath(".//*[@id='submit']")).click();
	  		//Click on Dashboard
	  	//	driver.findElement(By.xpath(".//*[@id='wrapper']/header/div/nav/ul/li[5]/a/span")).click();
	  		driver.findElement(By.linkText("Dashboard")).click();

	  		driver.findElement(By.linkText("Site Administration")).click();
	  		Thread.sleep(1000);
	  		driver.findElement(By.linkText("Users")).click();
	  		Thread.sleep(1000);
	  		driver.findElement(By.linkText("Bulk Registration")).click();
	  		Thread.sleep(1000);

	  		//driver.findElement(By.xpath(".//*[@idm='image']")).click();

	  		WebElement file = driver.findElement(By.xpath(".//*[@id='image']")); 
	  		file.sendKeys(GV.Input_ExcelFile+"bulkuploaduser07.xls" );

	  		new Select(driver.findElement(By.id("timezone"))).selectByValue("Asia/Dhaka");

	  		new Select(driver.findElement(By.id("role_id"))).selectByValue("9");

	  		driver.findElement(By.xpath(".//*[@id='submit']")).click();
	  		
	    
	    
	    
	    
	    
	    

	    // Do what you want here, you are in the new tab

	  //  driver.close();
	    // change focus back to old tab
	    driver.switchTo().window(oldTab);
	    Thread.sleep(8000);
	

	    // Do what you want here, you are in the old tab
	}

	
}
