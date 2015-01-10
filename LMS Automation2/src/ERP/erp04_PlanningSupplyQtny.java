/*
Name        : Praveen Kumar D
Date        : 6-oct-2014
Project Name: ProSoft ERP.

Project managenet >> Projects 
Script: Program to Add Schedules and 'Schedule Locations'

 */
package ERP;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

public class erp04_PlanningSupplyQtny
{
	public  WebDriver driver;
	public  Selenium selenium;
	public String baseUrl = "http://prosoft-dev.gyanfinder.com/index.php/user/login";

	public FileInputStream Fileinput;
	public Workbook wbook;
	Sheet Shedules;
	String mat[][];


	// **************** Enter the following inputs ********************************
	int WO_number = 1;
	String inputSheetName = "Planning-SupplyQty";
	// ****************************************************************************




	@BeforeTest
	public void beforetest() throws BiffException, IOException
	{
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.setEnableNativeEvents(true);
		driver = new FirefoxDriver(firefoxProfile);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		selenium = new WebDriverBackedSelenium(driver, baseUrl);

		// Position of Window on Screen.
		driver.manage().window().setPosition(new Point(-10, 0));
		//Dimensions--------------------------------> Width, Height.
		driver.manage().window().setSize(new Dimension(1380,560));


		Fileinput=new FileInputStream("E:\\PraveenQA\\Selenium Softwares\\TestDataExcel Files\\inputdataERP.xls");
		//Fileinput=new FileInputStream("C:\\Users\\pravin7may\\Desktop\\17sep2014\\ERP Sample Test Data for DEMO automated.xls");
		wbook=Workbook.getWorkbook(Fileinput);


		Shedules = wbook.getSheet(inputSheetName);
		System.out.println("Cloumns: "+Shedules.getColumns());
		System.out.println("Rows: "+Shedules.getRows());
		mat = new String [Shedules.getColumns()][Shedules.getRows()];
		for (int i = 0; i < Shedules.getRows(); i++) 
		{
			for (int j = 0; j <Shedules.getColumns(); j++) 
			{
				mat[j][i] = Shedules.getCell(j,i).getContents();
			}
		}
	}


	@Test
	public void LOAwork() throws InterruptedException 
	{


		driver.get(baseUrl);

		driver.findElement(By.id("UserLogin_username")).sendKeys("admin");
		driver.findElement(By.id("UserLogin_password")).sendKeys("admin");

		//Click on Submit button.
		driver.findElement(By.xpath(".//*[@id='login-form']/div[2]/div[3]/div[2]/button")).click();

		selenium.waitForPageToLoad("30000");


		driver.get("http://prosoft-dev.gyanfinder.com/planning/duration/index/workorder_id/"+ WO_number);
		WebElement dur = driver.findElement(By.xpath(".//*[@id='planning-duration-grid']/table/tbody"));
		java.util.List<WebElement> durtr = dur.findElements(By.xpath(".//*/a[1]/span"));
		System.out.println("Duration of Planning is: " + durtr.size());
	

		driver.get("http://prosoft-dev.gyanfinder.com/planning/supplyQuantity/index/workorder_id/"+ WO_number);
		WebElement litr = driver.findElement(By.xpath(".//*[@id='content']/div[3]/form/table/tbody"));
		java.util.List<WebElement> tr = litr.findElements(By.tagName("tr"));
		java.util.List<WebElement> td = litr.findElements(By.xpath("//tr[3]/td/input"));
		System.out.println("Rows Size is: " + tr.size() ); 
		System.out.println("Table Data Size is: " + (td.size()) ); 

		for (int a = 1; a <= (tr.size()-2); a++) 
		{
			int d = durtr.size();
			int z = d;
			for (int b = 1 ; b < (Shedules.getColumns()-1) ; b++)
			{
				if (z < 1)
				{
					z = d;
					continue;
				}
				else
				{
					driver.findElement(By.xpath(".//*[@id='content']/div[3]/form/table/tbody/tr["+ (a+2) + "]/td[" + (b+1)+ "]/input")).clear();
					driver.findElement(By.xpath(".//*[@id='content']/div[3]/form/table/tbody/tr["+ (a+2) + "]/td[" + (b+1)+ "]/input")).sendKeys(mat[b][a+1]);
					z--;
				}
			}
		}

		//Click on SUBMIT Button.
		driver.findElement(By.name("yt0")).click();

	}


	@AfterTest
	public void afterTest()
	{
		driver.close();
		driver.quit();
	}

}

