package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_02_Webbrowser_Api_Commands_Exercises {
	WebDriver driver;
	By myAccountLink = By.xpath("(//a[@title=\"My Account\"])[2]");
	By createAnAccountButton = By.xpath("//div[@class=\"buttons-set\"]//a");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get("http://live.guru99.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(myAccountLink).click();
		
	}

	@Test
	public void TC_01_Verify_Url() throws Exception {

		
		// get Login Page URL
		String loginPage = driver.getCurrentUrl();
		System.out.println(loginPage);
		Assert.assertEquals(loginPage, "http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(createAnAccountButton).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// get Register Page URL
		String registerPage = driver.getCurrentUrl();
		System.out.println(registerPage);
		Assert.assertEquals(registerPage, "http://live.demoguru99.com/index.php/customer/account/create/");

	}

	@Test
	public void TC_02_Verifiy_Title() {

		// get TITLE Login Page
		String titleLoginPage = driver.getTitle();
		System.out.println("LOGIN PAGE TITLE: " + titleLoginPage);
		Assert.assertEquals(titleLoginPage, "Customer Login");
		driver.findElement(createAnAccountButton).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// get TITLE Register Page
		String titleRegisterPage = driver.getTitle();
		System.out.println("REGISTER PAGE TITLE: " + titleRegisterPage);
		Assert.assertEquals(titleRegisterPage, "Create New Customer Account");

	}

	@Test
	public void TC_03_Navigate_Function() {

		driver.findElement(createAnAccountButton).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// get Register Page URL
		String registerPage = driver.getCurrentUrl();
		System.out.println(registerPage);
		Assert.assertEquals(registerPage, "http://live.demoguru99.com/index.php/customer/account/create/");
		// BACK TO LOGIN PAGE AND GET URL
		driver.navigate().back();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String loginPage = driver.getCurrentUrl();
		System.out.println(loginPage);
		Assert.assertEquals(loginPage, "http://live.demoguru99.com/index.php/customer/account/login/");
		// FORWARD TO REGISTER PAGE AGAIN AND VERIFY TITLE
		driver.navigate().forward();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String titleRegisterPage = driver.getTitle();
		System.out.println("REGISTER PAGE TITLE: " + titleRegisterPage);
		Assert.assertEquals(titleRegisterPage, "Create New Customer Account");
	}

	@Test
	public void TC_04_Get_Page_Resource() {

		
		// Get All Resource of LOGIN PAGE
		String loginPageResource = driver.getPageSource();
		Assert.assertTrue(loginPageResource.contains("Login or Create an Account"));
		driver.findElement(createAnAccountButton).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Get All Resource of REGISTER PAGE
		String registerPageResource = driver.getPageSource();
		Assert.assertTrue(registerPageResource.contains("Create an Account"));

	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
