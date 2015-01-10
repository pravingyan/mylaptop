package ERP;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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

public class ERP_AddMat2Schedules
{
	public  WebDriver driver;
	public  Selenium selenium;
	public String baseUrl = "http://prosoft-dev.gyanfinder.com/index.php/user/login";

	public FileInputStream Fileinput;
	public Workbook wbook;
	Sheet Shedules;
	String mat[][];

	String url = "jdbc:mysql://50.116.0.186:3306/";
	String dbName = "prosoft";
	String driverJDBC = "com.mysql.jdbc.Driver";
	String Mat_id;
	String FinalMatID;
	int start =  26 ;
	int end =  38 ;	
	int WO_number = 4 ;
	int type_of_WO = 1 ; // Supply or Errection or Civil
	int sch = 1 ;
	String Spec = "C:\\Users\\Admin\\Desktop\\Material Specifications.txt";


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
		driver.manage().window().setSize(new Dimension(1370,600));


		//Fileinput=new FileInputStream("E:\\TEST DATA\\TestDataExcelFiles\\Input_TestDataExcelFiles\\P02-Original.xls");
		Fileinput=new FileInputStream("D:\\PRAVIN QA\\Selenium Softwares\\TestDataExcel Files\\P02-Original used in Selenium.xls");
		wbook=Workbook.getWorkbook(Fileinput);


		Shedules=wbook.getSheet("Supply");
		//System.out.println("Cloumns: "+Shedules.getColumns());
		//System.out.println("Rows: "+Shedules.getRows());
		mat = new String [Shedules.getColumns()][Shedules.getRows()];
		for (int i = start-1; i <= end-1; i++) 
		{
			for (int j = 1; j < 7; j++) 
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

		//Click on "Project Management" Menu Link.
		driver.findElement(By.linkText("Project Management")).click();

		//Click on "Projects" Sub Menu Link.
		driver.findElement(By.linkText("Projects")).click();


		//Click on WorkOrder --> "Update/Edit" Icon.
		driver.findElement(By.xpath(".//*[@id='projects-grid']/table/tbody/tr["+ WO_number +"]/td[3]/a[2]/span")).click();


		//click on hyper link "Workorders".
		driver.findElement(By.linkText("Workorders")).click();


		//Select and click on type of WorkOrder Example...( Supply WO, Errection WO , Supply & Erection WO, Civil WO)
		driver.findElement(By.xpath(".//*[@id='workorders-grid']/table/tbody/tr["+ type_of_WO  +"]/td[7]/a[2]/span")).click();		

		//Click on "Schedules" screen link.
		driver.findElement(By.linkText("Schedules")).click();


		//Add Materails to Selected Schedules.
		driver.findElement(By.xpath(".//*[@id='wo-schedules-grid']/table/tbody/tr["+ sch +"]/td[3]/a[1]/span")).click();


/*
		//Click on "Schedules" screen link.
		driver.findElement(By.linkText("Schedules")).click();


		//Add Materails to Selected Schedules.
		driver.findElement(By.xpath(".//*[@id='wo-schedules-grid']/table/tbody/tr["+ sch +"]/td[3]/a[1]/span")).click();


		// Click on "Create" Button to add Materials to Schedules.
		driver.findElement(By.linkText("Create")).click();
*/

		for (int i = start-1; i <= end-1; i++) 
		{
			Mat_id = "zero";
			FinalMatID = "zero";
			try {
				driver.manage().timeouts().implicitlyWait(05, TimeUnit.SECONDS);


				/*				//Get Username from User while runtime.
				System.out.println("Pls enter Database Username in input popup");
				String usrname = JOptionPane.showInputDialog("Pls enter your 'Database Username'");
				String userName = usrname;*/
				String userName = "gyandba";

				/*	//Get Password from User while runtime.
				System.out.println("Pls enter Database Password in input popup");
				String pass = JOptionPane.showInputDialog("Pls enter your 'Database Password'");
				String password = pass;*/
				String password = "gyandba007";

				System.out.println("");
				//System.out.println("USERID" + "\t" + "ROLEID" + "\t" + "USERNAME");

				try {
					Class.forName(driverJDBC).newInstance();
					Connection conn = DriverManager.getConnection(url+dbName,userName,password);
					Statement st = conn.createStatement();
					System.out.println("Excel mat[][] value is: "+mat[1][i]);
					ResultSet res = st.executeQuery("SELECT `material_id`, `material_name`" +
							" FROM `materials` WHERE `material_name` like '%" + mat[1][i] + "%' " +
							"order by `material_id` ");
					//ResultSet res = st.executeQuery("SELECT `material_id`, `material_name` FROM `materials` WHERE `material_id` = 335");
					Mat_id = null;
					while (res.next()) 
					{
						System.out.println("res = " + res.getString("material_name"));
						Mat_id =res.getString("material_id");
						String materailName =res.getString("material_name");
						String runTimeOutput = materailName.replace(" ","");
						System.out.println(Mat_id + "\t" + runTimeOutput);

						String Excelinput = mat[1][i].replace(" ","");
						if ((Excelinput.length()) == (runTimeOutput.length()))
						{
							FinalMatID = Mat_id;
						}
						else
						{
							System.out.println("Mat_id:" + Mat_id + " --> Not matched...");
						}

					}
					conn.close();
					System.out.println("Target Mat_id value: " + FinalMatID);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					System.out.println("DatabaseCatch block executed");
				}


				System.out.println("Material Id is--> " + FinalMatID);
				if (FinalMatID.equals("zero")) 
				{

					System.out.println("New Material Add this/////////////: " + mat[1][i]);
					new Select(driver.findElement(By.id("WoScheduleMaterials_material_id"))).selectByValue("157");
				} 
				else 
				{
					// Click on "Create" Button to add Materials to Schedules.
					driver.findElement(By.linkText("Create")).click();
					
					new Select(driver.findElement(By.id("WoScheduleMaterials_material_id"))).selectByValue(FinalMatID);
					System.out.println("Materail found and value is---> " + FinalMatID);			
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

				driver.findElement(By.id("WoScheduleMaterials_specifications")).click();
				WebElement file = driver.findElement(By.id("WoScheduleMaterials_specifications	"));
				file.sendKeys(Spec);
				
				//Click on SUBMIT Button to add Material.
				driver.findElement(By.name("yt0")).click();
				selenium.waitForPageToLoad("20000");

				try 
				{
					driver.manage().timeouts().implicitlyWait(03, TimeUnit.SECONDS);

					String s = driver.findElement(By.xpath(".//*[@id='wo-schedule-materials-form']/div/div[2]/div[1]/ul/li")).getText();
					
					System.out.println("Error Validation:-----------------> " + s);
					if (s.length() > 1)
					{
						//Click on "Schedules" screen link.
						driver.findElement(By.linkText("Schedules")).click();


						//Add Materails to Selected Schedules.
						driver.findElement(By.xpath(".//*[@id='wo-schedules-grid']/table/tbody/tr["+ sch +"]/td[3]/a[1]/span")).click();


						// Click on "Create" Button to add Materials to Schedules.
						driver.findElement(By.linkText("Create")).click();

						selenium.waitForPageToLoad("20000");
						System.out.println(" ");
						System.out.println("Material: --> " + mat[1][i]);
						System.out.println("Error Validation:-----------------> " + s);
						System.out.println(" ");
					} 
				}
				catch (Exception e2) 
				{
					System.out.println("Material added sucessfully: --> " + mat[1][i]);
					System.out.println(" ");
				}				
			} 
			catch (Exception e) 
			{
				System.out.println("Material not found: --> " + mat[1][i]);
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

