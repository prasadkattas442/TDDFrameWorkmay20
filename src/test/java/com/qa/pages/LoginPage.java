package com.qa.pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage extends BaseTest {
	
	public LoginPage() 
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	@AndroidFindBy(accessibility = "test-Username")
	private WebElement usernameTxtField;
	@AndroidFindBy(accessibility = "test-Password")
	private WebElement passwordTxtField;
	@AndroidFindBy(accessibility = "test-LOGIN")
	private WebElement loginBtn;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Username and password do not match any user in this service.\"]")
	private WebElement errTxt;

	public LoginPage enterUserName(String username) {
		sendKeys(usernameTxtField, username);
		return this;
	}
	public LoginPage enterPassword(String password) {
		sendKeys(passwordTxtField, password);
		return this;
	}
	public ProductsPage pressLoginBtn() {
		click(loginBtn);
		return new ProductsPage();
	}	
	  public String getErrTxt() { 
		  return errTxt.getAttribute("text"); 
	  }
	 
	 
}
