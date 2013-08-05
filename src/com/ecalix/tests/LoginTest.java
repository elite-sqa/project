package com.ecalix.tests;


/*import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;*/
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;


import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.ecalix.common.Common;
import com.ecalix.pageobjects.PageObject;

import extra.ReadProperties;

@Test(groups={"g"})
public class LoginTest{
	
	public Actions builder; 
	public WebElement e;
	//JavascriptExecutor js;
	public WebDriver driver;
	public String sURL ="http://demo.opensourcecms.com/wordpress/wp-login.php"; 
	WebDriverWait wait;
	Common c;
	
	
	@Parameters({"Browser"})
	@BeforeMethod
	public void Open(String browser){
		
		c = new Common();
		
		c.OpenBrowser(browser);
		//Common.NavigateURL(sURL);
		
		driver = c.getDriver();

	}
	
	@Parameters({"Browser"})
	@Test(groups={"g1"})//Need Open Browser to run group test
	public void Login(String broswer) throws AWTException, InterruptedException{
		
		wait = new WebDriverWait(driver, 5);
		
		ReadProperties.Read("C:/Users/Quang Do/Selenium/ProjectWF/config.properties");
		c.NavigateURL("https://www.facebook.com/");
		//ReadProperties();
		
		//PageObject page = PageFactory.initElements(driver, PageObject.class);
		PageObject page = new PageObject();
		
		PageFactory.initElements(driver, page);
		
		//handle not found error
		try{
			wait.until(ExpectedConditions.visibilityOf(page.getUsername()));
			page.EnterUsername("uzumaki_hyuga@yahoo.com");
		}
		catch(NoSuchElementException e)
		{
			driver.manage().deleteAllCookies();
			c.NavigateURL("https://www.facebook.com/");
			page.EnterUsername("uzumaki_hyuga@yahoo.com");
		}
		
		page.EnterPass("yen1307");
		
		page.ClickPersist();
		
		page.ClickLoginButton();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='fbxWelcomeBoxName']")));
		
		String name = driver.findElement(By.xpath("//a[@class='fbxWelcomeBoxName']")).getText();
		
		Assert.assertTrue(name != null);
		
		System.out.println("Name is " + name);
		
	     Set<Cookie> cookies = driver.manage().getCookies();
	       
	        //To find the number of cookies used by this site
	        System.out.println("Number of cookies in this site "+cookies.size());
	       
	        for(Cookie cookie:cookies)
	        {
	            System.out.println(cookie.getName()+" "+cookie.getValue());
	           
	            //This will delete cookie By Name
	            driver.manage().deleteCookieNamed(cookie.getName());
	           
	            //This will delete the cookie
	            driver.manage().deleteCookie(cookie);
	        }
	       
	        //This will delete all cookies.
	        driver.manage().deleteAllCookies();
	       
	    c.NavigateURL("https://www.google.com/");
		
		c.NavigateURL("https://www.facebook.com/");
		
		c.CloseBrowser();
		/*
		driver.findElement(By.id("user_login")).sendKeys("admin");
		driver.findElement(By.id("user_pass")).sendKeys("demo123");
		driver.findElement(By.id("wp-submit")).click();
		Common.waitFor(10);
		Common.NavigateURL("https://www.google.com/");
		driver.manage().deleteAllCookies();
		Common.NavigateURL(sURL);
		Thread.sleep(5000);*/
		
		/*
		builder = new Actions(driver);
		e = driver.findElement(By.cssSelector("li#insuranceTab a.navLevel1"));
		System.out.println(e.getText());
		Select selectElement = new Select(driver.findElement(By.cssSelector("select#destination"))); 
		System.out.println(selectElement.toString());*/
	}
	
	@Parameters({"Browser"})
	@Test(groups={"g2"})
	public void Logout(String broswer){
		
		wait = new WebDriverWait(driver, 5);
		
		driver.get(sURL);
		driver.findElement(By.id("user_login")).sendKeys("admin");
		driver.findElement(By.id("user_pass")).sendKeys("demo123");
		driver.findElement(By.id("wp-submit")).click();
		builder = new Actions(driver);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//a[@title='My Account']"))));
		
		//Firefox//e = driver.findElement(By.cssSelector("html.wp-toolbar body.wp-admin div#wpwrap div#wpcontent div#wpadminbar div.quicklinks ul#wp-admin-bar-top-secondary.ab-top-secondary li#wp-admin-bar-my-account.menupop a.ab-item"));
		e = driver.findElement(By.xpath("//a[@title='My Account']"));
		builder.moveToElement(e).build().perform();
		
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		//wait.until(ExpectedConditions.elementToBeClickable(By.id<locator>));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Log Out")));
		
		//Firefox//driver.findElement(By.cssSelector(a)).click();
		driver.findElement(By.linkText("Log Out")).click();
		
		driver.get(sURL);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_login")));
		driver.findElement(By.id("user_login")).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_pass")));
		driver.findElement(By.id("user_pass")).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wp-submit")));
		
		driver.findElement(By.id("user_login")).sendKeys("admin");
		driver.findElement(By.id("user_pass")).sendKeys("demo123");
		
		driver.findElement(By.id("wp-submit")).click();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//Assert.assertTrue(driver.getPageSource().contains(": The password field is empty."));
	}
	
	
	@AfterMethod
	public void Close(){
		c.CloseBrowser();
	}
}
