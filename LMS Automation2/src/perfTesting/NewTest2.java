package perfTesting;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Selenium;

public class NewTest2 extends TestBase{
	public static Selenium selenium;
	@Test
	public void f() throws InterruptedException {
		//driver.get(GV.baseUrl);
		getDriver().manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		getDriver().get("http://50.116.0.185:81");
		getDriver().manage().window().maximize();

		//Click on Login.
		getDriver().findElement(By.xpath(".//*[@id='wrapper']/header/div/nav/ul/li[6]/a/span")).click();
		getDriver().findElement(By.id("email")).sendKeys("shraavan.abbanaboina@gmail.com");
		getDriver().findElement(By.id("password")).sendKeys("Prison123456");

		getDriver().findElement(By.id("submit")).click();
		Thread.sleep(2000);

		System.out.println("User 2 Logged in");

		for (int a = 0; a < 10; a++) {
			//Click on 'Course Administartion'.
			getDriver().findElement(By.xpath(".//*[@id='acc3']/li[4]/a")).click();

			// Click on Create a Course
			getDriver().findElement(By.xpath(".//*[@id='acc3']/li[4]/ul/li[2]/a")).click();
			Thread.sleep(1000);

			getDriver().findElement(By.xpath(".//*[@id='title']")).sendKeys("AutomationTWO");
			getDriver().findElement(By.xpath(".//*[@id='shortname']")).sendKeys("SeleAuto");

			new Select(getDriver().findElement(By.id("category"))).selectByValue("12");
			getDriver().findElement(By.xpath(".//*[@id='active-Yes']")).click();

			getDriver().findElement(By.xpath(".//*[@id='submit']")).click();

			//System.out.println("AutomationTWO Created......");
			//selenium.waitForPageToLoad("3000");
			Thread.sleep(3000);
			//Click on 'Course Administartion'.
			getDriver().findElement(By.xpath(".//*[@id='acc3']/li[4]/a")).click();

			//Click on 'Courses'
			getDriver().findElement(By.xpath(".//*[@id='acc3']/li[4]/ul/li[1]/a")).click();

			Thread.sleep(2000);
			WebElement UserList = getDriver().findElement(By.xpath(".//*[@id='view-content']/div/div/table/tbody"));
			java.util.List<WebElement> tr1 = UserList.findElements(By.tagName("tr"));
			/*System.out.println("No.of Users in Page " + i + " are: " +(tr1.size()-1));
			System.out.println("");
			 */
			for (int i = 2; i < tr1.size(); i++) {

				String s =getDriver().findElement(By.xpath(".//*[@id='view-content']/div/div/table/tbody/tr[" +i +"]/td[2]")).getText();
				if (s.contains("AutomationTWO")) 
				{
					//Delete the Course.
					getDriver().findElement(By.xpath(".//*[@id='view-content']/div/div/table/tbody/tr[" +i+ "]/td[4]/a[2]")).click();
					//System.out.println("AutomationTWO Deleted......");
					break;
				}
			}

		}
		System.out.println("User 2 Closed");

	}

}

