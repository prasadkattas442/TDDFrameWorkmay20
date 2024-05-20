package com.qa.tests;

import java.lang.reflect.Method;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginTests extends BaseTest {
	
	LoginPage loginPage;
	ProductsPage productsPage;
    @BeforeMethod	
	  public void beforeMethod(Method m)
    { 
    	loginPage=new LoginPage();
	  System.out.println("\n" + "***** starting test:" +m.getName() + "*****"
	  +"\n"); 
	  }
	 
    @Test(priority = 1)
	public void invalidUserName() 
	{    	
	  loginPage.enterUserName("invalidusername");
	  loginPage.enterPassword("secret_sauce");
	  loginPage.pressLoginBtn();	
	String actualErrTxt=loginPage.getErrTxt();	
	  String expectedErrTxt="Username and password do not match any user in this service.";
	  System.out.println("actual error text -"+actualErrTxt+
	  "\n"+"expected error txt-"+expectedErrTxt); Assert.assertEquals(actualErrTxt,
	  expectedErrTxt);
	 
	}
	
	@Test(priority = 2)
	public void invalidPassword() {
		loginPage.enterUserName("standard_user");
		loginPage.enterPassword("invalidPassword");
		loginPage.pressLoginBtn();
		String actualErrTxt = loginPage.getErrTxt();
		String expectedErrTxt = "Username and password do not match any user in this service.";
		System.out.println("actual error text -" + actualErrTxt + "\n" + "expected error txt-" + expectedErrTxt);
		Assert.assertEquals(actualErrTxt, expectedErrTxt);
	}
	
	@AndroidFindBy(xpath  = "//android.widget.TextView[@text=\"PRODUCTS\"]")
	public static WebElement productTitleTxt;	

	@Test(priority = 3)
	public void successfuLogin() throws Exception {
		loginPage.enterUserName("standard_user");
		loginPage.enterPassword("secret_sauce");
		productsPage=loginPage.pressLoginBtn();
		Thread.sleep(2000);
		//String actualProductTitle =productTitleTxt.getText();
		String actualProductTitle = productsPage.getTitle();
		String expectedProductTitle = "PRODUCTS";
		
		  System.out.println("product title -" + actualProductTitle + "\n" +
		  "expected error txt-" + expectedProductTitle);
		  Assert.assertEquals(actualProductTitle, expectedProductTitle);
		 
	}
	 
 
}
