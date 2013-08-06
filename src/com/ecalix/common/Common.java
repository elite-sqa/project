package com.ecalix.common;

//import java.util.concurrent.TimeUnit;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;



public class Common {
	//declare variable - create a name to hold something
	public WebDriver driver; 
	int a;
	//public String sURL ="http://demo.opensourcecms.com/wordpress/wp-login.php"; 
	public Common(){
		
	}
	
	public void OpenBrowser(String browser){
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Quang Do\\Downloads\\chromedriver.exe");
		//driver = new ChromeDriver();
		
		System.setProperty("webdriver.ie.driver", "C:\\Users\\Quang Do\\Downloads\\IEDriverServer.exe");
		
		/*FirefoxProfile profile = new FirefoxProfile();   
		profile.setEnableNativeEvents(true);     
		driver = new FirefoxDriver(profile);*/
		
		DesiredCapabilities Capabilities = new DesiredCapabilities();
		 
		System.out.println("Browser = " + browser);
		
		if (browser.equalsIgnoreCase("FireFox")) {
			
			ProfilesIni allProfiles = new ProfilesIni();
			FirefoxProfile profile = allProfiles.getProfile("qd0411");
			driver = new FirefoxDriver(profile);
			/*
			FirefoxProfile profile = new FirefoxProfile();   
			profile.setEnableNativeEvents(true);     
			driver = new FirefoxDriver(profile);
			
			ProfilesIni allProfiles = new ProfilesIni();
			FirefoxProfile profile = allProfiles.getProfile("qd0411");
			driver = new FirefoxDriver(profile);*/
			driver.manage().deleteAllCookies();

		} else if(browser.equalsIgnoreCase("IE")) {

			Capabilities = DesiredCapabilities.internetExplorer();
	
			Capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
	
			driver = new InternetExplorerDriver(Capabilities);
	
			driver.manage().deleteAllCookies();

		} else if(browser.equalsIgnoreCase("Chrome")) {
			
			/*try{
				Thread.sleep(9000);}
				catch(Exception e){}
			*/
			Capabilities = DesiredCapabilities.chrome();
	
			Capabilities.setCapability("chrome.switches", Arrays.asList("--start-maximized"));
	
			driver = new ChromeDriver(Capabilities);
			
		} 
		
		driver.manage().window().maximize();
		
		/*
		profile.setPreference("browser.cache.disk.enable", false);
		profile.setPreference("browser.cache.memory.enable", false);
		profile.setPreference("browser.cache.offline.enable", false);
		profile.setPreference("network.http.use-cache", false);
		*/
		/*profile.setPreference("capability.policy.policynames", "strict") ;
		profile.setPreference("capability.policy.strict.Window.alert", "noAccess") ;
		profile.setPreference("capability.policy.strict.Window.confirm", "noAccess") ;
		profile.setPreference("capability.policy.strict.Window.prompt", "noAccess") ; */

		/*File profileDir = new File("C:\\Users\\Quang Do\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles");
		FirefoxProfile profile = new FirefoxProfile(profileDir);
		//profile.setPreferences(extraPrefs);
		driver = new FirefoxDriver(profile);
			
		System.out.println("Open Browser");
		
		
		/*driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();*/
		
		
	}
	
	public void waitFor(long s){
		
		driver.manage().timeouts().implicitlyWait(s, TimeUnit.SECONDS);
		System.out.println("Wait for " + s + "s");
	}
		
	 public boolean isAlertPresent(){
		try
		{
		    driver.switchTo().alert();
		    return true;
		}   // try
		catch (NoAlertPresentException Ex)
		{
		    return false;
		}   // catch
	}   // isAlertPresent() 
		 
	Alert alert;
	public String getAlert(){
	        alert = driver.switchTo().alert();
	        String str = alert.getText();
	
	        alert.accept();
	        return str;
	} 
	 
	public WebElement findID(String s){
		
		return driver.findElement(By.id(s));
	}
	
	public void writeID(String i, String s){
		driver.findElement(By.id(i)).sendKeys(s);
		System.out.println("Write " + s + " at " + i);
	}
	
	public void writeX(String x, String s){
		driver.findElement(By.xpath(x)).sendKeys(s);
		System.out.println("Write " + s + " at " + x);
	}
	
	public void clickID(String s){
		driver.findElement(By.id(s)).click();
		System.out.println("Click " + s);
	}
	
	public void clickX(String x){
		driver.findElement(By.xpath(x)).click();
		System.out.println("Click " + x);
	}
	
	public void clickLT(String lt){
		driver.findElement(By.linkText(lt)).click();
		System.out.println("Click " + lt);
	}
	
	public void deleteCooki(){
		
		driver.manage().deleteAllCookies();
	}
	
	public void NavigateURL(String sURL){
		driver.get(sURL);
		System.out.println("Navigate to " + sURL);
	}
	
	public void CloseBrowser(){
		driver.quit();
		System.out.println("Closing Browser");
	}
	
	public WebDriver getDriver(){	
		return this.driver;
	}

}

