package com.ecalix.pageobjects;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.FindBy;

import com.ecalix.common.Common;

public class PageObject{
	//public static String qUser = "user_login";
	//public static String qPass = "user_pass";//constant & share
	//public static String qButton = "wp-submit";
	public WebDriver driver;
	
	public PageObject(){}
	
	public PageObject(WebDriver driver){
		this.driver = driver;
	}
	
	
	@FindBy(id="email")
	private WebElement username;
	
	@FindBy(id="pass")
	private WebElement passwd;
	
	@FindBy(id="persist_box")
	private WebElement checkbox;
	
	@FindBy(id="u_0_b")
	private WebElement button;
	
	
	public WebElement getUsername(){
		return username;
		
	}
	public void EnterUsername(String qValue){
		username.clear();
		username.sendKeys(qValue);
	}
	
	public void EnterPass(String qValue){
		passwd.clear();
		passwd.sendKeys(qValue);
	}
	
	public void ClickLoginButton(){
		button.click();
	}
	
	public void ClickPersist(){
		checkbox.click();
	}
	
	public void isTitle(String qValue){
		Assert.assertEquals(qValue, driver.getTitle());
		System.out.println("Title Expected: " + qValue + " | Actual Title: " + driver.getTitle());		
	}

	public void isTrue(String qValue){
	
		Assert.assertTrue(driver.getPageSource().contains(qValue));
		System.out.println("Contains: " + qValue);
	}
	

}
