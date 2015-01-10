
package userPermissions;


import java.io.IOException;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import GlobalVariables.GlobalDecl;

public class Permissions 
{

	GlobalDecl GV = new GlobalDecl();

	@BeforeTest
	public void beforetest() throws BiffException, IOException, RowsExceededException, WriteException, InterruptedException
	{
		GV.login();
		GV.driver.findElement(By.xpath("//a[contains(text(),'Site Administration')]")).click();
		GV.driver.findElement(By.xpath("//a[contains(text(),'Site Permissions')]")).click();
	}


	@Test (priority = 1)
	public void userPermissionsByRole() throws InterruptedException, RowsExceededException, WriteException, BiffException, IOException 
	{
		WebElement UserList = GV.driver.findElement(By.xpath(".//*[@id='column1']/div/div/form/table/tbody"));
		java.util.List<WebElement> tr = UserList.findElements(By.tagName("tr"));
		java.util.List<WebElement> td = UserList.findElements(By.xpath("//tr[2]/td"));
		System.out.println("No.of ROWS are:"+ tr.size());
		System.out.println("No.of COLUMNS are:"+ td.size());

		for (int i = 2; i <= tr.size(); i++) 
		{
			for (int j = 2; j <= td.size(); j++)
			{
				boolean b = GV.driver.findElement(By.xpath(".//*[@id='column1']/div/div/form/table/tbody/tr["+i+"]/td["+j+"]/input")).isSelected();
				if(b)
				{
					String Permission = GV.driver.findElement(By.xpath(".//*[@id='column1']/div/div/form/table/tbody/tr["+i+"]/td[1]")).getText();
					String Role = GV.driver.findElement(By.xpath(".//*[@id='column1']/div/div/form/table/tbody/tr[1]/th["+j+"]")).getText();
					System.out.println(Permission + "---->" + "Enabled for ---> " + Role) ;
				}
				else
				{
					//Enable the Checkbox
					GV.driver.findElement(By.xpath(".//*[@id='column1']/div/div/form/table/tbody/tr["+i+"]/td["+j+"]/input")).click();
				}
			}
		}
	}

	@AfterTest
	public void afterTest() throws InterruptedException {
		System.out
		.println("************************** END OF THE EXECUTION ********************************* ");
		System.out.println("");
		Thread.sleep(2000);
		GV.selenium.stop();
		GV.driver.quit();
	}
}
