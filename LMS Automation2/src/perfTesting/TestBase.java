package perfTesting;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.thoughtworks.selenium.Selenium;
 
public class TestBase {
 
    protected ThreadLocal<RemoteWebDriver> threadDriver = null;
	public static Selenium selenium;
    @BeforeMethod
    public void setUp() throws MalformedURLException {
         threadDriver = new ThreadLocal<RemoteWebDriver>();
        DesiredCapabilities dc = new DesiredCapabilities();
        FirefoxProfile fp = new FirefoxProfile();
        dc.setCapability(FirefoxDriver.PROFILE, fp);
        dc.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
        threadDriver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc));
        
    }
 
    public WebDriver getDriver() {
    //	getDriver().manage().window().maximize();
    	//getDriver().manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	//	selenium = new WebDriverBackedSelenium(getDriver(), "http://50.116.0.185:81");
        return threadDriver.get();
    }
 
    @AfterMethod
    public void closeBrowser() {
        getDriver().quit();
 
    }
}