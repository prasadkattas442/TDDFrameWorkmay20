package com.qa;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BaseTest {
	protected static AppiumDriver driver;
	protected static Properties props;
   InputStream inStream=null;
  @Parameters({"platformName","platformVersion","deviceName"})
  @BeforeTest
  public void beforeTest(String platformName,String platformVersion,String deviceName) {
	  try 
	  {
		  props=new Properties();
		  String propFileName="config.properties";
		  inStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		  props.load(inStream);
		  DesiredCapabilities dc=new DesiredCapabilities();
			dc.setCapability("platformName",platformName);
			dc.setCapability("platformVersion",platformVersion);
			dc.setCapability("deviceName", deviceName);
			dc.setCapability("automationName", props.getProperty("androidAutomationName"));		
			dc.setCapability("appPackage",props.getProperty("androidAppPackage"));
			dc.setCapability("appActivity",props.getProperty("androidAppActivity"));
			URL appUrl=getClass().getClassLoader().getResource(props.getProperty("androidAppLocation"));
			dc.setCapability("app",appUrl);		
			URL url=new URL(props.getProperty("appiumURL"));		
			driver=new AndroidDriver(url,dc);
			String sessionId=driver.getSessionId().toString();
		  }
	  catch(Exception e) 
	  {
		  e.printStackTrace();
	  } 
  }
  public void waitForVisibility(WebElement e) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(e));
	}
    public void click(WebElement e) {
		waitForVisibility(e);
		e.click();
	}
	public void sendKeys(WebElement e, String txt) {
		waitForVisibility(e);
		e.sendKeys(txt);
	}
	public String getAttribute(WebElement e, String attribute) {
		waitForVisibility(e);
		return e.getAttribute(attribute);
	}
	@AfterTest
	public void afterTest() {
		//driver.quit();
	}

}
