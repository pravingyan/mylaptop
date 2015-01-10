/*
Name        : Praveen Kumar D
Date        : 6-oct-2014
Project Name: ProSoft ERP.

Script: Add Materials to the given Schedules

 */
package ERP;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
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
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

public class erp03_AddMats2Schedules
{
	public  WebDriver driver;
	public  Selenium selenium;
	public String baseUrl = "http://prosoft-dev.gyanfinder.com/index.php/user/login";

	public FileInputStream Fileinput;
	public Workbook wbook;
	Sheet Shedules;
	String mat[][];
	String[] temp;

	// **************** Enter the following inputs ********************************
	int start =  3 ;  //Enter 'Starting' ROW Number
	int end   =  8 ;	 // Enter 'Ending' ROW Number
	int sch = 1; // Enter 'Schedule Number'
	String SheetName = "LOAinput";  //Enter input Sheet Name in Excel WorkBook.
	int WO_number = 1 ; // Enter WorkOrder Number.
	int page = 2; // Enter Pagination Page number where given Schedule is dispalyed.
	// ****************************************************************************



	@BeforeTest
	public void beforetest() throws BiffException, IOException
	{
		// **************** Entered Values are: ***************************************
		System.out.println("Script for: Add Materials to the given Schedules (erp03_AddMats2Schedules), ENTERED VALUES ARE: ");

		System.out.println("Start Row No -->"+ start);
		System.out.println("End Row No --> "+  end);
		System.out.println("Schedule No = " + sch);
		System.out.println("Input Sheet Name: " + SheetName);
		System.out.println("WO_Number = " + WO_number);
		System.out.println("Pagination Page No = "+ page);
		System.out.println(" ");
		// ****************************************************************************


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



		Shedules=wbook.getSheet(SheetName);
		//System.out.println("Cloumns: "+Shedules.getColumns());
		//System.out.println("Rows: "+Shedules.getRows());
		mat = new String [Shedules.getColumns()][Shedules.getRows()];
		for (int i = start-1; i <= end-1; i++) 
		{
			for (int j = 1; j <= 9; j++) 
			{
				mat[j][i] = Shedules.getCell(j,i).getContents();
				//System.out.println("mat["+j+"]["+ i+"] = " + mat[j][i]);
			}
		}
	}

	@Test
	public void addMat2Sched() throws InterruptedException 
	{


		driver.get(baseUrl);

		driver.findElement(By.id("UserLogin_username")).sendKeys("admin");
		driver.findElement(By.id("UserLogin_password")).sendKeys("admin");

		//Click on Submit button.
		driver.findElement(By.xpath(".//*[@id='login-form']/div[2]/div[3]/div[2]/button")).click();

		selenium.waitForPageToLoad("30000");
		String wo = "http://prosoft-dev.gyanfinder.com/workorders/schedules/index/workorder_id/"+ WO_number;
		driver.get(wo);
		System.out.println("Work Order No is: "+ wo);

		String p = "http://prosoft-dev.gyanfinder.com/index.php/workorders/schedules/index/ajax/wo-schedules-grid/workorder_id/"+ WO_number +"/WoSchedules_page/"+ page;
		driver.get(p);
		System.out.println("Page No is: "+ p);

		//Add Materails to Selected Schedules.
		String sc = ".//*[@id='wo-schedules-grid']/table/tbody/tr["+ sch +"]/td[3]/a[1]/span";
		System.out.println("Schedule Name ---> " + driver.findElement(By.xpath(".//*[@id='wo-schedules-grid']/table/tbody/tr[" + sch + "]/td[1]")).getText());
		driver.findElement(By.xpath(sc)).click();



		System.out.println("Project Name:----> "+driver.findElement(By.xpath("html/body/div[2]/div[2]/div/div[1]/ol/li[3]/a")).getText());
		System.out.println("URL---> "+ driver.getCurrentUrl());
		System.out.println("");

		for (int i = start-1; i <= end-1; i++) 
		{

			// Click on "Create" Button to add Materials to Schedules.
			driver.findElement(By.linkText("Create")).click();

			try 
			{

				driver.manage().timeouts().implicitlyWait(05, TimeUnit.SECONDS);
				//Material Dropdown, Get All Dropdown Values.

				try{

					List<WebElement> allMaterials=new Select(driver.findElement(By.id("WoScheduleMaterials_material_id"))).getOptions();
					for(WebElement allmat:allMaterials)
					{
						String s = null;

						// System.out.println(allmat.getText());    //It will return the text of each option
						// System.out.p	rintln(allmat.getAttribute("value"));    //it will return the value attribute of each option
						s = allmat.getText();

						/* delimiter */
						String delimiter = " - ";
						/* given string will be split by the argument delimiter provided. */
						temp = s.split(delimiter);
						/* print substrings */
						// System.out.println("Splitted String is: "+temp[0]);					

						//	System.out.println("Main String is: "+ mat[1][i]);
						//System.out.println("Sub String is:: "+ s1);
						String m = mat[1][i];
						String mat = m.trim();
						if (temp[0].equals(mat))
						{
							System.out.println("Splitted String is: "+temp[0]);
							String val = allmat.getAttribute("value");
							System.out.println("Value of Material is--: " + val);
							new Select(driver.findElement(By.id("WoScheduleMaterials_material_id"))).selectByValue(val);
							continue;
						} 
					}
				}
				catch (Exception e) 
				{

					System.out.println("Error Message:--> " + e.getMessage());

				}



				driver.findElement(By.id("WoScheduleMaterials_quantity")).clear();
				driver.findElement(By.id("WoScheduleMaterials_quantity")).sendKeys(mat[3][i]);
				driver.findElement(By.id("WoScheduleMaterials_exworks")).clear();
				driver.findElement(By.id("WoScheduleMaterials_exworks")).sendKeys(mat[4][i]);
				driver.findElement(By.id("WoScheduleMaterials_freight")).clear();
				driver.findElement(By.id("WoScheduleMaterials_freight")).sendKeys(mat[5][i]);
				driver.findElement(By.id("WoScheduleMaterials_insurance")).clear();
				driver.findElement(By.id("WoScheduleMaterials_insurance")).sendKeys(mat[6][i]);

				driver.findElement(By.id("WoScheduleMaterials_other1")).clear();
				driver.findElement(By.id("WoScheduleMaterials_other1")).sendKeys(mat[7][i]);
				driver.findElement(By.id("WoScheduleMaterials_other2")).clear();
				driver.findElement(By.id("WoScheduleMaterials_other2")).sendKeys(mat[8][i]);
				driver.findElement(By.id("WoScheduleMaterials_other3")).clear();
				driver.findElement(By.id("WoScheduleMaterials_other3")).sendKeys(mat[9][i]);

				//Click on SUBMIT Button to add Material.
				driver.findElement(By.name("yt0")).click();

				selenium.waitForPageToLoad("20000");

				try 
				{
					driver.manage().timeouts().implicitlyWait(03, TimeUnit.SECONDS);

					String err = driver.findElement(By.xpath(".//*[@id='wo-schedule-materials-form']/div/div[2]/div[1]/ul/li")).getText();

					System.out.println("Error Validation:-----------------> " + err);
					if (err.length() > 1)
					{
						driver.get("http://prosoft-dev.gyanfinder.com/workorders/schedules/index/workorder_id/"+ WO_number);

						//Add Materails to Selected Schedules.
						driver.findElement(By.xpath(".//*[@id='wo-schedules-grid']/table/tbody/tr["+ sch +"]/td[3]/a[1]/span")).click();

						// Click on "Create" Button to add Materials to Schedules.
						driver.findElement(By.linkText("Create")).click();

						selenium.waitForPageToLoad("20000");
						System.out.println(" ");
						System.out.println("Material: --> " + temp[0]+ " - "+ mat[2][i]);
						System.out.println("Error Validation:-----------------> " + err);
						System.out.println(" ");
					} 
				}
				catch (Exception e2) 
				{
					System.out.println("Material added sucessfully: --> " + mat[2][i]);
					System.out.println(" ");
				}				
			} 
			catch (Exception e) 
			{
				System.out.println("Material not found: --> " + mat[2][i]);
				System.out.println(" ");
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

