package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.BaseTest;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductsPage extends BaseTest {
	
	public ProductsPage() 
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath  = "//android.widget.TextView[@text=\"PRODUCTS\"]")
	public static WebElement productTitleTxt;

	
	public String getTitle() {
		return getAttribute(productTitleTxt, "text");
	}
	
}
