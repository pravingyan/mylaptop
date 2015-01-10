/*"User Signup and Account creation"
 * 
 * 
 */

package UserMgmt;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import GlobalVariables.GlobalDecl;

import com.thoughtworks.selenium.Selenium;

public class Uesr_Signup2 {

	public static WebDriver driver;
	public static Selenium selenium;
	GlobalDecl GV = new GlobalDecl();


	@BeforeTest
	public void before() throws IOException, BiffException, RowsExceededException, WriteException {

		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.setEnableNativeEvents(true);

		driver = new FirefoxDriver(firefoxProfile);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		selenium = new WebDriverBackedSelenium(driver, GV.baseUrl);
	}


	@Test
	public void userSignup() throws InterruptedException, BiffException, IOException 
	{

		driver.get(GV.baseUrl +  "/signup");
		selenium.waitForPageToLoad("30000"); 


		FileInputStream fi = new FileInputStream(GV.Input_ExcelFile + "CreatingNew_UserDEMO.xls");
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
						selenium.highlight(s1); // To highlight a field in Yellow Color in web page
						if (s.getCell(0, i).getContents().equalsIgnoreCase("id"))
						{
							driver.findElement(By.id(s.getCell(1, i).getContents())).sendKeys(s.getCell(5, i).getContents());
							System.out.println( s.getCell(2, i).getContents() + " ----> '" + s.getCell(5, i).getContents() + "' is entered.");
						}
						else
						{
							driver.findElement(By.xpath(s.getCell(1, i).getContents())).sendKeys(s.getCell(5, i).getContents());
							System.out.println( s.getCell(2, i).getContents() + " ----> '" + s.getCell(5, i).getContents() + "' is entered.");
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
						Thread.sleep(6000);
						String s1 = s.getCell(1, i).getContents();
						selenium.highlight(s1);
						// If Dropdown INDEX is used....
						if (s.getCell(4, i).getContents().equalsIgnoreCase("Index"))
						{
							String str = s.getCell(5, i).getContents();
							int Index = Integer.parseInt(str);

							if (s.getCell(0, i).getContents().equalsIgnoreCase("id")) 
							{
								new Select(driver.findElement(By.id(s.getCell(1, i).getContents()))).selectByIndex(Index);
								System.out.println( s.getCell(2, i).getContents() + " ----> " + s.getCell(5, i).getContents() + " is selected.");
							}
							else
							{
								new Select(driver.findElement(By.xpath(s.getCell(1, i).getContents()))).selectByIndex(Index);
								System.out.println( s.getCell(2, i).getContents() + " ----> " + s.getCell(5, i).getContents() + " is selected.");
							}
						}

						//If Dropdown VALUE is used..........
						else if (s.getCell(4, i).getContents().equalsIgnoreCase("Value")) 
						{
							if (s.getCell(0, i).getContents().equalsIgnoreCase("id")) 
							{
								new Select(driver.findElement(By.id(s.getCell(1, i).getContents()))).selectByValue(s.getCell(5, i).getContents());
								System.out.println( s.getCell(2, i).getContents() + " ----> " + s.getCell(5, i).getContents() + " is selected.");
							}
							else
							{
								new Select(driver.findElement(By.xpath(s.getCell(1, i).getContents()))).selectByValue(s.getCell(5, i).getContents());
								System.out.println( s.getCell(2, i).getContents() + " ----> " + s.getCell(5, i).getContents() + " is selected.");
							}
						}

						else 
						{
							//If Dropdown VISIBLE TEXT is used.........
							if (s.getCell(0, i).getContents().equalsIgnoreCase("id")) 
							{
								new Select(driver.findElement(By.id(s.getCell(1, i).getContents()))).selectByVisibleText(s.getCell(5, i).getContents());
								System.out.println( s.getCell(2, i).getContents() + " ----> " + s.getCell(5, i).getContents() + " is selected.");
							}
							else
							{
								new Select(driver.findElement(By.xpath(s.getCell(1, i).getContents()))).selectByVisibleText(s.getCell(5, i).getContents());
								System.out.println( s.getCell(2, i).getContents() + " ----> " + s.getCell(5, i).getContents() + " is selected.");
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
							System.out.println("Checkbox enabled for: " + s.getCell(2, i).getContents()+" is: "+ s.getCell(5, i).getContents());
						}
						else
						{
							driver.findElement(By.xpath(s.getCell(1, i).getContents())).click();
							System.out.println("Checkbox enabled for: " + s.getCell(2, i).getContents()+" is: "+ s.getCell(5, i).getContents());
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
						else
						{
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

				else if(s.getCell(3, i).getContents().contains("iframe"))
				{
					//driver.switchTo().frame(iframe#21_ifr);
					driver.switchTo().frame("21_ifr");

					if (selenium.isElementPresent(s.getCell(1, i).getContents()))
					{
						String s1 = s.getCell(1, i).getContents();
						selenium.highlight(s1);

						if (s.getCell(0, i).getContents().equalsIgnoreCase("id"))
						{
							driver.findElement(By.id(s.getCell(1, i).getContents())).click();
							System.out.println("Button clicked on: "+ s.getCell(2, i).getContents());
							driver.switchTo().defaultContent();
						}
						else{
							driver.findElement(By.xpath(s.getCell(1, i).getContents())).click();
							System.out.println("Button clicked on: "+ s.getCell(2, i).getContents());
							driver.switchTo().defaultContent();
						}
					}
					else 
					{
						System.out.println(s.getCell(2, i).getContents()+" is not found.");
					}
					driver.switchTo().defaultContent();
				}
				//*************************************************************************************************************************************************

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


				//*************************************************************************************************************************************************

			}
		}

		selenium.waitForPageToLoad("3000");
		Thread.sleep(3000);

		try 
		{
			String sucess = driver.findElement(By.xpath("//*[@id='wrapper']/main/div/div/div/p")).getText();
			System.out.println("Account Creation Success Message------->" + sucess);
		} 
		catch (Exception e)
		{
			String error = driver.findElement(By.xpath(".//*/ul")).getText();
			System.out.println("Errro Messages---> " + error);
			System.out.println("Account has not been Created, Pls check the Validations");
		}
	}

	@AfterTest
	public void afterTest() throws InterruptedException {
		System.out
		.println("************************** END OF THE EXECUTION ********************************* ");
		System.out.println("");
		Thread.sleep(12000);
		//selenium.stop();
		//driver.quit();
	}
}

