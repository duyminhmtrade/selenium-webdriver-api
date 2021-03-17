package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_01_Locator_Selenium_Exercises {
	WebDriver driver;
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
  }
  
  //TC_01_Login Email Password Empty
  @Test
  public void TC_01_Login_Empty() throws Exception {
	  driver.get("http://live.guru99.com/index.php/customer/account/login/");
	  
	  driver.findElement(By.id("email")).sendKeys("");
	  driver.findElement(By.id("pass")).sendKeys("");
	  Thread.sleep(1000);
	  driver.findElement(By.id("send2")).click();
	  Thread.sleep(1000); 
	  Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
	  Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
  }
  
  
  //TC_02_Login Email invalid
  @Test
  public void TC_02_Login_Invalid_Username() throws Exception {
	  driver.get("http://live.guru99.com/index.php/customer/account/login/");
	  
	  driver.findElement(By.id("email")).sendKeys("123124@412412.34534");
	  //driver.findElement(By.id("pass")).sendKeys("");
	  Thread.sleep(1000);
	  driver.findElement(By.id("send2")).click();
	  Thread.sleep(1000);
	  Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
  }
  
  
  //TC_03_Login Password < 6 characters
  @Test
  public void TC_03_Login_Password_Less_Than_Six_Characters() throws Exception {
	  driver.get("http://live.guru99.com/index.php/customer/account/login/");
	  
	  driver.findElement(By.id("email")).sendKeys("nguyenming1012@gmail.com");
	  driver.findElement(By.id("pass")).sendKeys("123");
	  Thread.sleep(1000);
	  driver.findElement(By.id("send2")).click();
	  Thread.sleep(1000);
	  Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
  }
  
  
  //TC_04_Login Password Invalid 
  @Test
  public void TC_04_Login_Password_Invalid() throws Exception {
	  driver.get("http://live.guru99.com/index.php/customer/account/login/");
	  
	  driver.findElement(By.id("email")).sendKeys("nguyenming1012@gmail.com");
	  driver.findElement(By.id("pass")).sendKeys("23423415125");
	  Thread.sleep(1000);
	  driver.findElement(By.id("send2")).click();
	  Thread.sleep(2000);
	  Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");
  }
  @AfterClass
  public void afterClass() {
	  driver.close();
  }

}
