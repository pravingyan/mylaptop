package perfTesting;

import java.awt.GridLayout;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.thoughtworks.selenium.Selenium;

public class CopyOfxpath {

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

		System.out.println("BaseURL: " +  baseUrl);
		
		driver.get(baseUrl);

		Thread.sleep(2000);

		selenium.waitForPageToLoad("30000");
		//	driver.findElement(By.xpath("//input[@id='email']")).sendKeys("9392083283");


		//Using a JPanel as the message for the JOptionPane
		JPanel userPanel = new JPanel();
		userPanel.setLayout(new GridLayout(0,1,2,2));

		//Labels for the textfield components        
		JLabel usernameLbl = new JLabel("Username:");
		JLabel passwordLbl = new JLabel("Password:");

		JTextField username = new JTextField();
		JPasswordField passwordFld = new JPasswordField();

		//Add the components to the JPanel        
		userPanel.add(usernameLbl);
		userPanel.add(username);
		userPanel.add(passwordLbl);
		userPanel.add(passwordFld);

		//As the JOptionPane accepts an object as the message
		//it allows us to use any component we like - in this case 
		//a JPanel containing the dialog components we want
		int input = JOptionPane.showConfirmDialog(null, userPanel, "Enter your password:"
				,JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		String usrname = username.getText();
		//Send Username field value.
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(usrname);
		//get Password to char array
		char[] password = passwordFld.getPassword();
		//Convert Char array to String.
		String str = String.valueOf(password);
		//Send Password field value.
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(str);
	}



	@AfterTest

	public void afterTest() throws InterruptedException

	{

		System.out.println("************************** END OF THE EXECUTION ********************************* ");

		Thread.sleep(12000);

		selenium.stop();

		//driver.quit();

	}

}