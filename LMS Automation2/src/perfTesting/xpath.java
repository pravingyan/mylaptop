package perfTesting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.thoughtworks.selenium.Selenium;

import java.util.StringTokenizer;

public class xpath {

	public static WebDriver driver;

	public static Selenium selenium;

	public String baseUrl;
	public int aa, bb, cc;
	public boolean phone;

	@BeforeTest

	public void beforeTest() throws InterruptedException, IOException

	{

		System.out.println("************************** START OF THE EXECUTION ********************************* ");

		baseUrl = "https://www.facebook.com/";

		FirefoxProfile firefoxProfile = new FirefoxProfile();

		firefoxProfile.setEnableNativeEvents(true);
		driver = new FirefoxDriver(firefoxProfile);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		selenium = new WebDriverBackedSelenium(driver, baseUrl);

	}

	@Test (priority = 1)

	public void login() throws InterruptedException

	{

		driver.get(baseUrl);

		Thread.sleep(2000);

		selenium.waitForPageToLoad("30000");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("9392083283");
		
		
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Enter a password:");
		JPasswordField pass = new JPasswordField(10);
		panel.add(label);
		panel.add(pass);
		String[] options = new String[]{"OK", "Cancel"};
		int option = JOptionPane.showOptionDialog(null, panel, "PASSWORD Dialouge Box",
		                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
		                         null, options, options[1]);
		
		    char[] password = pass.getPassword();
		    String p = new String(password);
		   // System.out.println("Your password is: " + p);
		    
			//Send user entered Password value into Password Field. 
		    driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(p);
		
		

		driver.findElement(By.xpath("//input[@type='submit']")).click();

		selenium.waitForPageToLoad("30000");

	}
/*
	@Test (priority = 2)

	public void getAllFriendsList() throws InterruptedException, IOException, RowsExceededException, WriteException

	{

		driver.get("https://www.facebook.com/DOPPALAPUDI.PRAVIN/friends?ft_ref=flsa");

		while(true)

		{

			selenium.waitForPageToLoad("30000");

			Long y=(Long)((JavascriptExecutor) driver).executeScript("return window.pageYOffset");

			long y1 = y.longValue();

			Thread.sleep(800);

			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)");

			y=(Long)((JavascriptExecutor) driver).executeScript("return window.pageYOffset");

			long y2 = y.longValue();

			if((y1-y2)== 0)

			{

				//System.out.println(y1);

				//System.out.println(y2);

				System.out.println("WebPage Scrolled Down Completed....");

				break;

			}

		}

		FileOutputStream fo = new FileOutputStream("C:\\Users\\pravin7may\\Desktop\\ZIP File Test output\\FaceBookFriends.xls");

		WritableWorkbook wwb = Workbook.createWorkbook(fo);

		WritableSheet ws = wwb.createSheet("Contacts", 0);



		List<WebElement> allNames = driver.findElements(By.xpath("//div[@class='fsl fwb fcb']/a"));

		System.out.println("Total No.of Contacts Pravin had is: ------> " + allNames.size());

		for(int i=0;i<allNames.size();i++)

		{

			System.out.println("Attribute is: "+ allNames.get(i).getAttribute("href"));

			System.out.println(allNames.get(i).getText());

			System.out.println("");

			Label cellData1 = new Label(0,i, allNames.get(i).getText());

			ws.addCell(cellData1);

			Label cellData2 = new Label(1,i, allNames.get(i).getAttribute("href"));

			ws.addCell(cellData2);

		}

		wwb.write();

		wwb.close();

		System.out.println(" Wrote to Excel Sheet Sucessfully");

	}

	*/@
	
	Test (priority = 3)

	public void getDetailsInfo() throws BiffException, IOException, InterruptedException, RowsExceededException, WriteException

	{

		FileInputStream fi =new FileInputStream("C:\\Users\\pravin7may\\Desktop\\ZIP File Test output\\FaceBookFriends.xls");
		Workbook w=Workbook.getWorkbook(fi);
		Sheet s=w.getSheet(0);
		System.out.println("Cloumns: "+s.getColumns());
		System.out.println("Rows: "+s.getRows());
		String[][] a = new String [s.getColumns()][s.getRows()];

		for (int i = 0; i < s.getRows(); i++) 
		{
			for (int j = 0; j < s.getColumns(); j++) 
			{
				a[j][i] = s.getCell(j,i).getContents();
				//System.out.println("a["+j+"]["+i+"]--->"+a[j][i]);
			}
		}

		FileOutputStream ContInfo = new FileOutputStream("C:\\Users\\pravin7may\\Desktop\\ZIP File Test output\\FINAL_FaceBookContact.xls");
		WritableWorkbook wwbContact = Workbook.createWorkbook(ContInfo);
		WritableSheet sheet = wwbContact.createSheet("FriendsDetailedInfo", 0);

		for (int i =0 ; i < s.getRows(); i++)
		{
			//System.out.println("______________________________________________________________________________________________________________________________________________________________________________________________________________________");
			aa = 0;
			System.out.println("");
			System.out.println("");
			System.out.println("Contact Name is: "+ a[0][i]);
			driver.get(a[1][i]);
			Thread.sleep(1000);
			selenium.waitForPageToLoad("30000");
			driver.findElement(By.linkText("About")).click();
			Thread.sleep(1000);
			selenium.waitForPageToLoad("30000");
			Label cell1 = new Label(0,i, a[0][i]);
			sheet.addCell(cell1);

			selenium.waitForPageToLoad("30000");
			try 
			{
				driver.manage().timeouts().implicitlyWait(05, TimeUnit.SECONDS);
				phone = driver.findElement(By.xpath(".//*[@id='pagelet_contact']/div")).isDisplayed();
				System.out.println(phone);
				bb = 1;
			} 
			catch (Exception e)
			{
				System.out.println("Contact Info not given by "+ a[0][i]);
				bb = 0;
			}

			if (bb > 0)
			{
				aa = 1;
			}

			if (aa == 1)
			{
				String basicinfo = driver.findElement(By.xpath(".//*[@id='pagelet_basic']/div")).getText();
				System.out.println(basicinfo);
				
				System.out.println("");
				
				String contactinfo = driver.findElement(By.xpath(".//*[@id='pagelet_contact']/div/div")).getText();
				System.out.println(contactinfo);
			}
			System.out.println("______________________________________________________________________________________________________________________________________________________________________________________________________________________");
			System.out.println("");

		}

		wwbContact.write();
		wwbContact.close();
		System.out.println(" All Contacts Info wrote to Excel Sheet Sucessfully....End of the Execution.");

	}

	@AfterTest

	public void afterTest() throws InterruptedException

	{

		System.out.println("************************** END OF THE EXECUTION ********************************* ");

		Thread.sleep(12000);

		selenium.stop();

		driver.quit();

	}

}