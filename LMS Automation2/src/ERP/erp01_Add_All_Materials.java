/*
Name        : Praveen Kumar D
Date        : 6-oct-2014
Project Name: ProSoft ERP.

Project managenet >> Projects 
Script: Program to Add All Materials'

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

public class erp01_Add_All_Materials
{
	public  WebDriver driver;
	public  Selenium selenium;
	public String baseUrl = "http://prosoft-dev.gyanfinder.com/index.php/user/login";

	public FileInputStream Fileinput;
	public Workbook wbook;
	Sheet UniMat;
	String mat[][];
	public String SepcFile = "C:\\Users\\Client\\Desktop\\Sample Material Specs.txt";


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


		Fileinput=new FileInputStream("E:\\PraveenQA\\Selenium Softwares\\TestDataExcel Files\\ERP Sample Test Data for DEMO automated.xls");
		//Fileinput=new FileInputStream("C:\\Users\\pravin7may\\Desktop\\17sep2014\\ERP Sample Test Data for DEMO automated.xls");
		wbook=Workbook.getWorkbook(Fileinput);

		
		UniMat = wbook.getSheet("Unique MAT");
		System.out.println("Cloumns: "+UniMat.getColumns());
		System.out.println("Rows: "+UniMat.getRows());
		mat = new String [UniMat.getColumns()][UniMat.getRows()];
		for (int i = 0; i < UniMat.getRows(); i++) {
			for (int j = 0; j < UniMat.getColumns(); j++) 
			{
				mat[j][i] = UniMat.getCell(j,i).getContents();
			}
		}
	}


	@Test
	public void uniqMat() throws InterruptedException 
	{


	driver.get(baseUrl);

		driver.findElement(By.id("UserLogin_username")).sendKeys("admin");
		driver.findElement(By.id("UserLogin_password")).sendKeys("admin");

		//Click on Submit button.
		driver.findElement(By.xpath(".//*[@id='login-form']/div[2]/div[3]/div[2]/button")).click();

		selenium.waitForPageToLoad("30000");



		for (int a = 1; a < UniMat.getRows(); a++) 
		{

			try {
				driver.get("http://prosoft-dev.gyanfinder.com/materials/default/create");
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.id("Materials_material_code")).clear();
				driver.findElement(By.id("Materials_material_code")).sendKeys(mat[1][a]);
				driver.findElement(By.id("Materials_material_name")).clear();
				driver.findElement(By.id("Materials_material_name")).sendKeys(mat[2][a]);
				driver.findElement(By.xpath(".//*[@id='Materials_material_units']")).clear();
				driver.findElement(By.xpath(".//*[@id='Materials_material_units']")).sendKeys(mat[3][a]);
				
				
				WebElement file = driver.findElement(By.id("Materials_specifications"));
				file.sendKeys(SepcFile);
				Thread.sleep(1000);
				//click on Submit button.
				driver.findElement(By.name("yt0")).click();
			
			} 
			catch (Exception e) 
			{
				System.out.println("Catch Block executed... for "+ a)	;
			}
		}
	}


	@AfterTest
	public void afterTest()
	{
		driver.close();
		driver.quit();
	}

}

