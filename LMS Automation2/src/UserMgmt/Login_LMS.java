
package UserMgmt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import GlobalVariables.GlobalDecl;
import com.thoughtworks.selenium.Selenium;

public class Login_LMS
{
	public static WebDriver driver;
	public static Selenium selenium;
	GlobalDecl GV = new GlobalDecl();

	@BeforeTest
	public void beforeTest() throws IOException {
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.setEnableNativeEvents(true);

		driver = new FirefoxDriver(firefoxProfile);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		selenium = new WebDriverBackedSelenium(driver, GV.baseUrl);
	}

	@Test
	public void login() throws InterruptedException, RowsExceededException, WriteException, BiffException, IOException 
	{
			FileInputStream fi=new FileInputStream(GV.Input_ExcelFile+"LoginData.xls" );
			Workbook w=Workbook.getWorkbook(fi);
			Sheet s=w.getSheet("Login Data");
			System.out.println("Cloumns: "+s.getColumns());
			System.out.println("Rows: "+s.getRows());
			String[][] a = new String [s.getColumns()][s.getRows()];
			for (int i = 1; i < s.getRows(); i++) {
				for (int j = 0; j < s.getColumns(); j++) {
					a[j][i] = s.getCell(j,i).getContents();
	
				}
			}

		FileOutputStream fo=new FileOutputStream(GV.Output_ExcelFile+"LoginResults.xls");

		WritableWorkbook wwb=Workbook.createWorkbook(fo);
		WritableSheet ws=wwb.createSheet("LoginResults", 0);
		for (int i = 0; i < s.getRows(); i++) {
			for (int j = 0; j < s.getColumns(); j++) {	
				Label l=new Label(j,i,s.getCell(j, i).getContents());
				ws.addCell(l);
			}
		}



		System.out.println("************************** START OF THE EXECUTION ********************************* ");
		for (int i = 1; i < s.getRows() ; i++) {

			System.out.println("_______________________________________________________________");

			driver.get(GV.baseUrl +"login");
					
			driver.findElement(By.id("email")).sendKeys(a[1][i]);
			driver.findElement(By.id("password")).sendKeys(a[2][i]);

			driver.findElement(By.id("submit")).click();
			Thread.sleep(2000);

			String url = driver.getCurrentUrl();

			if (url.contains("login"))
			{
				String err = driver.findElement(By.xpath(".//*[@id='wrapper']/main/div/div/div")).getText();
				String content = err;
				content = content.replace("Login", "");
				content = content.replace("Forgot Password", "");
				content = content.replace("*Email ID", "");
				content = content.replace("*Password", "");
				content = content.replace("\n", ", ");
				content = content.replace(",", " ");
				System.out.println("Error Message---> "+content);

				System.out.println
				("You have entered '"+ a[1][i] +"' \\ '"+ a[2][i]+ "' ---> Result: Login Failed");
				Label l1=new Label(4,i, "Fail");
				ws.addCell(l1);	

				Label l3=new Label(5,i, content);
				ws.addCell(l3);
				driver.get(GV.baseUrl +"login");
			}

			else
			{
				//Click on Dashboard
				driver.findElement(By.xpath(".//*[@id='wrapper']/header/div/nav/ul/li[4]/a/span")).click();

				System.out.println("You have entered '"+ a[1][i] +"' \\ '"+ a[2][i]+ "' ---> Result:Login Sucessful");
				Label l2=new Label(4,i, "Pass");
				ws.addCell(l2);
				//Click on Logout button.
				driver.findElement(By.xpath("html/body/div[1]/header/div/nav/ul/li[4]/a/span")).click();
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
