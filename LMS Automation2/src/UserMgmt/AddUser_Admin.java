
package UserMgmt;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import GlobalVariables.GlobalDecl;
import com.thoughtworks.selenium.Selenium;

public class AddUser_Admin
{
	public static WebDriver driver;
	public static Selenium selenium;
	GlobalDecl GV = new GlobalDecl();

	@BeforeTest
	public void addUser_Admin() throws IOException, BiffException, RowsExceededException, WriteException {

		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.setEnableNativeEvents(true);


		//Load Browser with FireBug plugin.
		//Pravin Laptop
		// File file1 = new File("C:\\Documents and Settings\\praveen\\Application Data\\Mozilla\\Firefox\\Profiles\\vd4oz1qy.default\\extensions\\firebug@software.joehewitt.com.xpi");
		//Pravin Office desktop
		File file1 = new File("C:\\Users\\praveen.GYAN\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\isdp7896.default\\extensions\\firebug@software.joehewitt.com.xpi");
		firefoxProfile.addExtension(file1);
		firefoxProfile.setPreference("extensions.firebug.currentVersion", "1.12.4"); // Avoid startup screen



		//Load 'FirePath' plugin to identify 'xPath of an element'.
		//Pravin Laptop
		//File file2 = new File("C:\\Documents and Settings\\praveen\\Application Data\\Mozilla\\Firefox\\Profiles\\vd4oz1qy.default\\extensions\\FireXPath@pierre.tholence.com.xpi");
		//Pravin Office Desktop
		File file2 = new File("C:\\Users\\praveen.GYAN\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\isdp7896.default\\extensions\\FireXPath@pierre.tholence.com.xpi");
		firefoxProfile.addExtension(file2);
		firefoxProfile.setPreference("extensions.FireXPath.currentVersion", "0.9.7");



		//Load Selenium IDE plugin to Record the scenarios...
		//Pravin Laptop
		//File file3 = new File("C:\\Documents and Settings\\praveen\\Application Data\\Mozilla\\Firefox\\Profiles\\vd4oz1qy.default\\extensions\\{a6fd85ed-e919-4a43-a5af-8da18bda539f}.xpi");
		//Pravin Office Desktop
		File file3 = new File("C:\\Users\\praveen.GYAN\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\isdp7896.default\\extensions\\{a6fd85ed-e919-4a43-a5af-8da18bda539f}.xpi");
		firefoxProfile.addExtension(file3);
		firefoxProfile.setPreference("extensions.{a6fd85ed-e919-4a43-a5af-8da18bda539f}.currentVersion", "2.4.0");



		//Load Java Formatters for Slenium IDE plugin.
		//Pravin Laptop
		//File file4 = new File("C:\\Documents and Settings\\praveen\\Application Data\\Mozilla\\Firefox\\Profiles\\vd4oz1qy.default\\extensions\\javaformatters@seleniumhq.org.xpi");
		//Pravin Office Desktop
		File file4 = new File("C:\\Users\\praveen.GYAN\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\isdp7896.default\\extensions\\javaformatters@seleniumhq.org.xpi");
		firefoxProfile.addExtension(file4);
		firefoxProfile.setPreference("extensions.javaformatters@seleniumhq.org.currentVersion", "2.4.0");



		driver = new FirefoxDriver(firefoxProfile);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		selenium = new WebDriverBackedSelenium(driver, GV.baseUrl);
	}

	@Test
	public void addUser() throws InterruptedException, RowsExceededException, WriteException, BiffException, IOException {

		System.out.println("************************** START OF THE EXECUTION ********************************* ");

		driver.get(GV.baseUrl);
		// considering that there is only one tab opened in that point.
		String mainBrowserWindow = driver.getWindowHandle();
		System.out.println("mainBrowserWindow Name is: "+ mainBrowserWindow);

		Actions act = new Actions(driver);
		WebElement onElement = driver.findElement(By.xpath(".//*[@id='wrapper']/header/div/nav/ul/li[5]/a"));
		act.contextClick(onElement).perform();
		act.sendKeys("T").perform();  // If you want the link to open in new tab then use T instead of w
		// Close the Home Tab.
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"w");// close the Home Tab


		//UserID and Password.
		driver.findElement(By.id("email")).sendKeys(GV.Username);
		driver.findElement(By.id("password")).sendKeys(GV.Password);
		//Click on Login Button.
		driver.findElement(By.xpath(".//*[@id='submit']")).click();
		//Click on Dashboard
		driver.findElement(By.xpath(".//*[@id='wrapper']/header/div/nav/ul/li[4]/a/span")).click();
		driver.findElement(By.linkText("Site Administration")).click();
		driver.findElement(By.linkText("Users")).click();
		driver.findElement(By.linkText("Add User")).click();

		FileInputStream fi = new FileInputStream(GV.Input_ExcelFile + "CreatingNew_User.xls");
		Workbook w = Workbook.getWorkbook(fi);
		Sheet s = w.getSheet("UserSignup");
		System.out.println("No.of Rows in the input Excel sheet are: " + s.getRows());

		for (int i = 1; i < s.getRows(); i++) 
		{
			if (!s.getCell(1, i).getContents().equals(""))
			{
				//*************************************************************************************************************************************************
				if (s.getCell(3, i).getContents().contains("Textbox")) 
				{
					selenium.waitForPageToLoad("30000");
					if (selenium.isElementPresent(s.getCell(1, i).getContents())) 
					{
						String s1 = s.getCell(1, i).getContents();
						selenium.highlight(s1);
						if (s.getCell(0, i).getContents().equalsIgnoreCase("id"))
						{
							driver.findElement(By.id(s.getCell(1, i).getContents())).sendKeys(s.getCell(6, i).getContents());
							System.out.println( s.getCell(2, i).getContents() + " ----> '" + s.getCell(6, i).getContents() + "' is entered.");
						}
						else
						{
							driver.findElement(By.xpath(s.getCell(1, i).getContents())).sendKeys(s.getCell(6, i).getContents());
							System.out.println( s.getCell(2, i).getContents() + " ----> '" + s.getCell(6, i).getContents() + "' is entered.");
						}	
					}
					else
					{
						System.out.println(s.getCell(2, i).getContents()+" is not found.");
					}
				}


				//*************************************************************************************************************************************************
				else if (s.getCell(3, i).getContents().contains("Dropdown"))
				{
					if (selenium.isElementPresent(s.getCell(1, i).getContents()))
					{
						selenium.waitForPageToLoad("3000");
						String s1 = s.getCell(1, i).getContents();
						selenium.highlight(s1);
						// If Dropdown INDEX is used....
						if (s.getCell(4, i).getContents().equalsIgnoreCase("Index"))
						{
							String str = s.getCell(6, i).getContents();
							int Index = Integer.parseInt(str);

							if (s.getCell(0, i).getContents().equalsIgnoreCase("id")) 
							{
								new Select(driver.findElement(By.id(s.getCell(1, i).getContents()))).selectByIndex(Index);
								System.out.println( s.getCell(2, i).getContents() + " ----> " + s.getCell(6, i).getContents() + " is selected.");
							}
							else
							{
								new Select(driver.findElement(By.xpath(s.getCell(1, i).getContents()))).selectByIndex(Index);
								System.out.println( s.getCell(2, i).getContents() + " ----> " + s.getCell(6, i).getContents() + " is selected.");
							}
						}

						//If Dropdown VALUE is used..........
						else if (s.getCell(4, i).getContents().equalsIgnoreCase("Value")) 
						{
							if (s.getCell(0, i).getContents().equalsIgnoreCase("id")) 
							{
								new Select(driver.findElement(By.id(s.getCell(1, i).getContents()))).selectByValue(s.getCell(6, i).getContents());
								System.out.println( s.getCell(2, i).getContents() + " ----> " + s.getCell(6, i).getContents() + " is selected.");
							}
							else
							{
								new Select(driver.findElement(By.xpath(s.getCell(1, i).getContents()))).selectByValue(s.getCell(6, i).getContents());
								System.out.println( s.getCell(2, i).getContents() + " ----> " + s.getCell(6, i).getContents() + " is selected.");
							}
						}

						else 
						{
							//If Dropdown VISIBLE TEXT is used.........
							if (s.getCell(0, i).getContents().equalsIgnoreCase("id")) 
							{
								new Select(driver.findElement(By.id(s.getCell(1, i).getContents()))).selectByVisibleText(s.getCell(6, i).getContents());
								System.out.println( s.getCell(2, i).getContents() + " ----> " + s.getCell(6, i).getContents() + " is selected.");
							}
							else
							{
								new Select(driver.findElement(By.xpath(s.getCell(1, i).getContents()))).selectByVisibleText(s.getCell(6, i).getContents());
								System.out.println( s.getCell(2, i).getContents() + " ----> " + s.getCell(6, i).getContents() + " is selected.");
							}
						}
					}

					else 
					{
						System.out.println(s.getCell(2, i).getContents()+" is not found.");
					}
				}
				//*************************************************************************************************************************************************


				else if (s.getCell(3, i).getContents().contains("Checkbox"))
				{
					if (selenium.isElementPresent(s.getCell(1, i).getContents())) 
					{
						String s1 = s.getCell(1, i).getContents();
						selenium.highlight(s1);

						if (s.getCell(0, i).getContents().equalsIgnoreCase("id"))
						{
							driver.findElement(By.id(s.getCell(1, i).getContents())).click();
							System.out.println("Checkbox enabled for: " + s.getCell(2, i).getContents()+" is: "+ s.getCell(6, i).getContents());
						}
						else
						{
							driver.findElement(By.xpath(s.getCell(1, i).getContents())).click();
							System.out.println("Checkbox enabled for: " + s.getCell(2, i).getContents()+" is: "+ s.getCell(6, i).getContents());
						}

					} 
					else 
					{
						System.out.println(s.getCell(2, i).getContents()+" is not found.");
					}
				}
				//*************************************************************************************************************************************************

				else if (s.getCell(3, i).getContents().contains("RadioButton"))
				{
					if (selenium.isElementPresent(s.getCell(1, i).getContents()))
					{
						String s1 = s.getCell(1, i).getContents();
						selenium.highlight(s1);

						if (s.getCell(0, i).getContents().equalsIgnoreCase("id"))
						{
							driver.findElement(By.id(s.getCell(1, i).getContents())).click();
							System.out.println("Radio Button clicked for "+s.getCell(1, i).getContents()+" is: "+ s.getCell(2, i).getContents());
						}
						else{
							driver.findElement(By.xpath(s.getCell(1, i).getContents())).click();
							System.out.println("Radio Button clicked for "+s.getCell(1, i).getContents()+" is: "+ s.getCell(2, i).getContents());
						}

					}
					else 
					{
						System.out.println(s.getCell(2, i).getContents()+" is not found.");
					}

				}

				//*************************************************************************************************************************************************

				else if (s.getCell(3, i).getContents().contains("Button"))
				{
					if (selenium.isElementPresent(s.getCell(1, i).getContents()))
					{
						String s1 = s.getCell(1, i).getContents();
						selenium.highlight(s1);

						if (s.getCell(0, i).getContents().equalsIgnoreCase("id"))
						{
							driver.findElement(By.id(s.getCell(1, i).getContents())).click();
							System.out.println("Button clicked on: "+ s.getCell(2, i).getContents());
						}
						else{
							driver.findElement(By.xpath(s.getCell(1, i).getContents())).click();
							System.out.println("Button clicked on: "+ s.getCell(2, i).getContents());
						}
					}
					else 
					{
						System.out.println(s.getCell(2, i).getContents()+" is not found.");
					}
				}
				//*************************************************************************************************************************************************

			}
		}
		Thread.sleep(3000);



		//Delete newly Added User.

		//Enter Email Id to Delete.
		driver.findElement(By.id("filter_email")).sendKeys(s.getCell(6, 7).getContents());

		selenium.focus("filter_email");

		//Click ENTER button.
		driver.findElement(By.id("filter_email")).sendKeys(Keys.RETURN);

		//Tick the Checkbox to delete
		driver.findElement(By.xpath(".//*[@id='grid']/table/tbody/tr[4]/td[1]/input")).click();

		//Click on DELETE button.
		driver.findElement(By.xpath(".//*[@id='column1']/div/div/form/input")).click();

		//Accept the Alert and click on OK button in Alert.
		Alert alert = driver.switchTo().alert();
		alert.accept();

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
