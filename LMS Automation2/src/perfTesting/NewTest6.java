package perfTesting;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Selenium;

public class NewTest6 extends TestBase{
	//public static Selenium selenium;
	@Test
	public void f() throws InterruptedException {
		//driver.get(GV.baseUrl);
		getDriver().manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		getDriver().get("http://50.116.0.185:81");
		//Click on Login.
		getDriver().findElement(By.xpath(".//*[@id='wrapper']/header/div/nav/ul/li[6]/a/span")).click();
		getDriver().findElement(By.id("email")).sendKeys("sky_shravan@yahoo.co.in");
		getDriver().findElement(By.id("password")).sendKeys("Prison123456");

		getDriver().findElement(By.id("submit")).click();
		Thread.sleep(2000);

		System.out.println("User 6 Logged in");
		for (int z = 0; z < 10; z++) {
			getDriver().findElement(By.linkText("My Courses")).click();
			Thread.sleep(2000);
			getDriver().findElement(By.linkText("physics")).click();


			Thread.sleep(5000);


			getDriver().findElement(By.linkText("chapter1")).click();

			getDriver().findElement(By.linkText("Assignment for Automation")).click();

			getDriver().findElement(By.xpath(".//*[@id='column1']/div/div/table/tbody/tr[2]/td[3]/a")).click();


			WebElement file = getDriver().findElement(By.xpath(".//*[@id='image']")); 
			file.sendKeys("C:\\Users\\pravin7may\\Desktop\\Lighthouse");

			getDriver().findElement(By.xpath(".//*[@id='submit']")).click();


			Thread.sleep(1000);

			getDriver().findElement(By.linkText("My Courses")).click();
			Thread.sleep(2000);
			getDriver().findElement(By.linkText("physics")).click();


			Thread.sleep(5000);


			getDriver().findElement(By.linkText("chapter1")).click();


			getDriver().findElement(By.linkText("Lesson1")).click();
			getDriver().findElement(By.linkText("Test for Assessment")).click();

			//Click on 'Take Test'
			getDriver().findElement(By.xpath(".//*[@id='view-content']/table/tbody/tr/td/form/table/tbody/tr[3]/td/input")).click();

			getDriver().findElement(By.id("answer1")).sendKeys("Selenium is Functional Testing Tool.");

			//Click on Finish Test.
			getDriver().findElement(By.xpath(".//*[@id='view-content']/table[2]/tbody/tr[2]/td/input")).click();
		}
		System.out.println("User 6 Closed");
	}


}




