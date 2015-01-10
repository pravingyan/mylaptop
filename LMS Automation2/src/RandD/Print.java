package RandD;

import java.io.FileInputStream;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

public class Print
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

	@BeforeTest
	public void beforetest() throws BiffException, IOException
	{
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.setEnableNativeEvents(true);
		driver = new FirefoxDriver(firefoxProfile);

		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		selenium = new WebDriverBackedSelenium(driver, baseUrl);

		// Position of Window on Screen.
		driver.manage().window().setPosition(new Point(-10, 0));
		//Dimensions--------------------------------> Width, Height.
		driver.manage().window().setSize(new Dimension(1380,620));


		Fileinput=new FileInputStream("E:\\TEST DATA\\TestDataExcelFiles\\Input_TestDataExcelFiles\\"+ ExcelName + ".xls");
		wbook=Workbook.getWorkbook(Fileinput);


		Shedules=wbook.getSheet(sheetName);
		//System.out.println("Cloumns: "+Shedules.getColumns());
		//System.out.println("Rows: "+Shedules.getRows());
		mat = new String [Shedules.getColumns()][Shedules.getRows()];
		for (int i = start-1; i <= end-1; i++) {
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
		//Click on Submit button.
		driver.findElement(By.xpath(".//*[@id='login-form']/div[2]/div[3]/div[2]/button")).click();
		driver.findElement(By.linkText("Project Management")).click();

		// DB Connection
		Class.forName(driverJDBC).newInstance();
		Connection conn = DriverManager.getConnection(url+dbName,userName,password);
		Statement st = conn.createStatement();

		ResultSet res = st.executeQuery("SELECT * from Table Name where...");
		while (res.next()) 
		{
			Mat_id =res.getString("material_id");
			System.out.println(Mat_id + "\t" + runTimeOutput);


		}
		conn.close();
		System.out.println("Target Mat_id value: " + FinalMatID);
	} 




	//Reading Data from a Web Table

	WebElement UserList = driver.findElement(By.xpath(".//*[@id='grid']/table/tbody"));
	java.util.List<WebElement> tr = UserList.findElements(By.tagName("tr"));
	for (int j = 4; j <= tr.size(); j++)
	{
		String s1 = driver.findElement(By.xpath(".//*[@id='grid']/table/tbody/tr["+j+"]/td[1]/a")).getText();
		if(s1.contains(CourseName))
		{
			// steps or actions...
		}
	}





	new Select(driver.findElement(By.id("idf"))).selectByVisibleText(p); //Index , Value, Visible Text.



	else if (s.getCell(3, i).getContents().contains("Captcha")) 
	{
		selenium.waitForPageToLoad("30000");

		String s1 = s.getCell(1, i).getContents();
		selenium.highlight(s1);

		String Captcha = JOptionPane.showInputDialog("Pls enter your 'Captcha Code'");
		driver.findElement(By.id(s.getCell(1, i).getContents())).sendKeys(Captcha);
		selenium.focus(s.getCell(1, i).getContents());
		driver.findElement(By.id(s.getCell(1, i).getContents())).sendKeys(Keys.TAB);
	}




	else if(s.getCell(3, i).getContents().contains("iframe"))
	{
		//driver.switchTo().frame(iframe#21_ifr);
		driver.switchTo().frame("21_ifr");
		//Do some operations.....
		driver.switchTo().defaultContent();
	}
	@AfterTest
	public void afterTest()
	{
		driver.close();
		driver.quit();
	}

}

}