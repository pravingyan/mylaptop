package UserMgmt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import GlobalVariables.GlobalDecl;

import com.thoughtworks.selenium.Selenium;

public class Username_Validations 
{
	public  Selenium selenium;
	private WebDriver driver;
	GlobalDecl GV = new GlobalDecl();
	private String baseUrl;
	String msg;

	String[][] a1;

	@BeforeTest
	public void before() throws IOException, BiffException, RowsExceededException, WriteException {

		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.setEnableNativeEvents(true);

		driver = new FirefoxDriver(firefoxProfile);
		driver.manage().timeouts().implicitlyWait(05, TimeUnit.SECONDS);
		selenium = new WebDriverBackedSelenium(driver, GV.baseUrl);
	}


	@Test
	public void f() throws RowsExceededException, WriteException, BiffException, IOException
	{

		FileInputStream fi=new FileInputStream(GV.Input_ExcelFile+"SignupValidations.xls" );
		Workbook w=Workbook.getWorkbook(fi);
		Sheet s=w.getSheet("UserName");
		System.out.println("Cloumns: "+s.getColumns());
		System.out.println("Rows: "+s.getRows());
		String[][] a = new String [s.getColumns()][s.getRows()];
		for (int i = 1; i < s.getRows(); i++) 
		{
			for (int j = 0; j < s.getColumns(); j++) 
			{
				a[j][i] = s.getCell(j,i).getContents();
			}
		}


		FileOutputStream fo=new FileOutputStream(GV.Output_ExcelFile+"UserNameResults.xls");
		WritableWorkbook wwb=Workbook.createWorkbook(fo);
		WritableSheet ws=wwb.createSheet("FirstName_Results", 0);

		for (int i = 0; i < s.getRows(); i++) 
		{

			for (int j = 0; j < s.getColumns(); j++) 
			{
				Label lab=new Label(j,i,s.getCell(j, i).getContents());
				ws.addCell(lab);
			}
		}


		
		
		for (int i = 1; i < s.getRows(); i++)
		{
			driver.get("http://50.116.0.186:81/signup");
			System.out.println("_____________________________________");
			driver.findElement(By.id("username")).clear();
			selenium.focus("username");
			System.out.println(a[1][i]);
			driver.findElement(By.id("username")).sendKeys(a[1][i]);
			driver.findElement(By.id("username")).sendKeys(Keys.TAB);

			System.out.println("Entered Value: "+a[1][i]);
			String errorXpath = ".//*[@id='errors-username']/li" ;
			
			
			try {
				if (driver.findElement(By.xpath(errorXpath)) != null)
				{
					msg = driver.findElement(By.xpath(errorXpath)).getText();
					System.out.println("Failed for: "+a[1][i] + "  " + "Error Message:--->"+ msg );
					Label l3 = new Label(3,i, "Fail");
					ws.addCell(l3);

					Label l4 = new Label(4,i, msg);
					ws.addCell(l4);
				}

			} catch (Exception e) {
				System.out.println("  Sucessful for: "+ s.getCell(1,i).getContents());
				Label l2 = new Label(3,i, "Pass");
				ws.addCell(l2);
			}
		}
		wwb.write();
		wwb.close();
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
