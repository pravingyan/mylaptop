package UserMgmt;

import java.io.File;
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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import GlobalVariables.GlobalDecl;

import com.thoughtworks.selenium.Selenium;


public class EmailID_Validations {
	public static Selenium selenium;
	private WebDriver driver;
	private String baseUrl;
	String msg1, msg2, msg3, msg4;
	GlobalDecl GV =  new GlobalDecl();
	String[][] a1;

	@BeforeTest
	public void setUp() throws Exception {
		System.out.println("************************** START OF THE EXECUTION ********************************* ");
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.setEnableNativeEvents(true);

		driver = new FirefoxDriver(firefoxProfile);
		driver.manage().timeouts().implicitlyWait(02, TimeUnit.SECONDS);
		selenium = new WebDriverBackedSelenium(driver, GV.baseUrl);
	}

	@Test
	public void FirstName() throws InterruptedException, BiffException, IOException, RowsExceededException, WriteException {
		FileInputStream fi=new FileInputStream(GV.Input_ExcelFile+"SignupValidations.xls" );
		Workbook w=Workbook.getWorkbook(fi);
		Sheet s=w.getSheet("Email ID");
		System.out.println("Cloumns: "+s.getColumns());
		System.out.println("Rows: "+s.getRows());
		String[][] a = new String [s.getColumns()][s.getRows()];
		for (int i = 1; i < s.getRows(); i++) {
			for (int j = 0; j < s.getColumns(); j++) {
				a[j][i] = s.getCell(j,i).getContents();
			}
		}

		FileOutputStream fo=new FileOutputStream(GV.Output_ExcelFile+"EmaiIDResults.xls");
		WritableWorkbook wwb=Workbook.createWorkbook(fo);
		WritableSheet ws=wwb.createSheet("EmailIDResults", 0);

		for (int i = 0; i < s.getRows(); i++) {

			for (int j = 0; j < s.getColumns(); j++) {
				Label lab=new Label(j,i,s.getCell(j, i).getContents());
				ws.addCell(lab);
			}
		}



		for (int i = 1; i < s.getRows(); i++) {

			driver.get("http://50.116.0.186:81/signup");
			selenium.waitForPageToLoad("30000"); 

			System.out.println("_____________________________________");

			if (a[1][i].contains("Different")) {
				driver.findElement(By.id("email")).sendKeys("Pravin7m@yahoo.com");
				driver.findElement(By.id("confemail")).sendKeys("Suresh20sep@gmail.com");
			}
			else
			{
				driver.findElement(By.id("email")).sendKeys(a[1][i]);
				driver.findElement(By.id("confemail")).sendKeys(a[1][i]);
			}
			driver.findElement(By.xpath(".//*[@id='submit']")).click();
			
			System.out.println("Entered Value: "+a[1][i]);
			String email = ".//*[@id='errors-email']/li" ;
			String ConfEmail = ".//*[@id='errors-confemail']/li";


			try {
				if ((driver.findElement(By.xpath(email)) != null)
							|| (driver.findElement(By.xpath(ConfEmail))!= null))
				{

					try {
						msg1 = driver.findElement(By.xpath(email)).getText();
					} catch (Exception e) {
						System.out.println("Msg1 not there....");
					} 
					
					
					try {
						msg2 =driver.findElement(By.xpath(ConfEmail)).getText();
					} catch (Exception e) {
						System.out.println("Msg2 not there....");
					}
					
					System.out.println("Failed for: "+a[1][i] + "  " + "Error Message:--->"+ msg1 +",  "+ msg2 );

					Label l3 = new Label(3,i, "Fail");
					ws.addCell(l3);

					Label l4 = new Label(4,i, msg1 +", "+ msg2);
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
