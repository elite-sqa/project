package extra;

import java.util.concurrent.TimeUnit;






import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class F {
	public WebDriver driver;
	public String sURL ="http://demo.opensourcecms.com/wordpress/wp-login.php"; 
	
	@BeforeMethod
	public void OpenBrowser(){
		/*System.setProperty("webdriver.chrome.driver", "C:\\Users\\Quang Do\\Downloads\\chromedriver.exe");
		driver = new ChromeDriver();*/
		
		System.setProperty("webdriver.ie.driver", "C:\\Users\\Quang Do\\Downloads\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		
		/*FirefoxProfile profile = new FirefoxProfile();   
		profile.setEnableNativeEvents(true);     
		driver = new FirefoxDriver(profile);*/
	}
	/*
	@Test
	public void Login(){
		
		driver.get(sURL);
		driver.findElement(By.id("user_login")).sendKeys("admin");
		driver.findElement(By.id("user_pass")).sendKeys("demo123");
		driver.findElement(By.id("wp-submit")).click();
		Assert.assertEquals("Dashboard ‹ WordPress Demo Install — WordPress", driver.getTitle());
	}
	
	@Test
	public void LoginErr(){
		
		driver.get(sURL);
		driver.findElement(By.id("user_login")).sendKeys("admin");
		driver.findElement(By.id("wp-submit")).click();
		Assert.assertTrue("Error Message", driver.getPageSource().contains(": The password field is empty."));
		
	}
	
	@Test
	public void PassErr(){
		driver.get(sURL);
		driver.findElement(By.id("user_login")).clear();
		driver.findElement(By.id("user_pass")).sendKeys("demo123");
		driver.findElement(By.id("wp-submit")).click();
		Assert.assertTrue("Error Message", driver.getPageSource().contains(": The username field is empty."));
	}
	
	@Test
	public void Comment() throws InterruptedException{
		driver.get(sURL);
		driver.findElement(By.id("user_login")).sendKeys("admin");
		driver.findElement(By.id("user_pass")).sendKeys("demo123");
		driver.findElement(By.id("wp-submit")).click();
		driver.findElement(By.xpath("//a[@class='wp-has-submenu wp-not-current-submenu menu-top menu-icon-links']")).click();
		driver.findElement(By.xpath("//a[@href='edit-tags.php?taxonomy=link_category']")).click();
		Thread.sleep(5000);
		//a[@href='edit-comments.php']
	}*/
		
	
	public Actions builder; 
	public WebElement e;
	public String a = "html.wp-toolbar body.wp-admin div#wpwrap div#wpcontent div#wpadminbar div.quicklinks ul#wp-admin-bar-top-secondary.ab-top-secondary li#wp-admin-bar-my-account.menupop div.ab-sub-wrapper ul#wp-admin-bar-user-actions.ab-submenu li#wp-admin-bar-logout a.ab-item";

	
	@Test
	public void Logout(){
		driver.get(sURL);
		driver.findElement(By.id("user_login")).sendKeys("admin");
		driver.findElement(By.id("user_pass")).sendKeys("demo123");
		driver.findElement(By.id("wp-submit")).click();
		builder = new Actions(driver);
		
		WebDriverWait wait = new WebDriverWait(driver, 1);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//a[@title='My Account']"))));
		
		//Firefox//e = driver.findElement(By.cssSelector("html.wp-toolbar body.wp-admin div#wpwrap div#wpcontent div#wpadminbar div.quicklinks ul#wp-admin-bar-top-secondary.ab-top-secondary li#wp-admin-bar-my-account.menupop a.ab-item"));
		e = driver.findElement(By.xpath("//a[@title='My Account']"));
		builder.moveToElement(e).build().perform();
		
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		//wait.until(ExpectedConditions.elementToBeClickable(By.id<locator>));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Log Out")));
		
		//Firefox//driver.findElement(By.cssSelector(a)).click();
		driver.findElement(By.linkText("Log Out")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_login")));
		driver.findElement(By.id("user_login")).clear();
		driver.get(sURL);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_login")));
		driver.findElement(By.id("user_login")).sendKeys("admin");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_pass")));
		driver.findElement(By.id("user_pass")).sendKeys("demo123");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wp-submit")));
		driver.findElement(By.id("wp-submit")).click();
		try{
			Thread.sleep(3000);}
			catch(Exception e){}
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Assert.assertTrue(driver.getPageSource().contains(": The password field is empty."));

	}
	
	@AfterMethod
	public void tearDown(){
		
		driver.quit();
	}
}
