package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_03_Webelement_Api_Commands_Exercises {

	WebDriver driver;
	By emailTextBoxBy = By.id("mail");
	By ageOptionUnder18ButtonBy = By.id("under_18");
	By educationTextBoxBy = By.id("edu");
	By job1DropdownBy = By.id("job1");
	By interestDevelopmentBy = By.id("development");
	By slider01By = By.id("slider-1");
	By passwordTextBoxBy = By.id("password");
	By ageRadioButtonDisabledBy = By.id("radio-disabled");
	By biographyTextBoxBy = By.id("bio");
	By interestCheckBoxDisabled = By.id("check-disbaled");
	By slider02By = By.id("slider-2");
	By job3DropdownBy = By.id("job3");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
	}

	@Test
	public void TC_01_Verified_Element_Displayed() {
		
		if(isElementDisplyed(emailTextBoxBy)) {
			sendKeyToElement(emailTextBoxBy,"Automation Testing");
			SleepInSecond(2);
		}
		
		if(isElementDisplyed(ageOptionUnder18ButtonBy)) {
			clickElement(ageOptionUnder18ButtonBy);
			SleepInSecond(2);
		}
		
		if (isElementDisplyed(educationTextBoxBy)) {
			sendKeyToElement(educationTextBoxBy,"Automation Testing");
			SleepInSecond(2);
		}
		
	}
	@Test
	public void TC_02_Verified_Element_Enable_Disable() {
		// ENABLED
		Assert.assertTrue(isElementEnabled(emailTextBoxBy));
		Assert.assertTrue(isElementEnabled(educationTextBoxBy));
		Assert.assertTrue(isElementEnabled(ageOptionUnder18ButtonBy));
		Assert.assertTrue(isElementEnabled(slider01By));
		Assert.assertTrue(isElementEnabled(interestDevelopmentBy));
		Assert.assertTrue(isElementEnabled(job1DropdownBy));
		
		// DISABLED
		Assert.assertFalse(isElementEnabled(passwordTextBoxBy));
		Assert.assertFalse(isElementEnabled(ageRadioButtonDisabledBy));
		Assert.assertFalse(isElementEnabled(biographyTextBoxBy));
		Assert.assertFalse(isElementEnabled(interestCheckBoxDisabled));
		Assert.assertFalse(isElementEnabled(slider02By));
		Assert.assertFalse(isElementEnabled(job3DropdownBy));
	}
	@Test
	public void TC_03_Verified_Element_Is_Selected() {
		
		clickElement(ageOptionUnder18ButtonBy);
		clickElement(interestDevelopmentBy);
		Assert.assertTrue(isElementSelected(ageOptionUnder18ButtonBy));
		Assert.assertTrue(isElementSelected(interestDevelopmentBy));
		clickElement(interestDevelopmentBy);
		Assert.assertFalse(isElementSelected(interestDevelopmentBy));
	}
	
	public WebElement find(By by) {
		return driver.findElement(by);
	}
	public void clickElement(By by) {
		WebElement element = find(by);
		element.click();
	}
	public void sendKeyToElement(By by, String value) {
		WebElement element = find(by);
		element.sendKeys(value);
	}
	public boolean isElementDisplyed(By by) {
		WebElement element = find(by);
		if (element.isDisplayed()) {
			System.out.println("Element with by [" + by +"] is DISPLAYED");
			return true;
		} else {
			System.out.println("Element with by [" +by +"] is NOT DISPLAYED");
			return false;
		}
	}
	
	public boolean isElementEnabled(By by) {
		WebElement element = find(by);
		if(element.isEnabled()) {
			System.out.println("Element with by [" + by +"] is ENABLED");
			return true;
		} else {
			System.out.println("Element with by [" +by +"] is DISABLED");
			return false;
		}
	}
	public boolean isElementSelected(By by) {
		WebElement element = find(by);
		if(element.isSelected()) {
			System.out.println("Element with by [" + by +"] is Selected");
			return true;
		} else {
			System.out.println("Element with by [" +by +"] is Not Selected");
			return false;
		}
	}
	
	public void SleepInSecond (long second ) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
