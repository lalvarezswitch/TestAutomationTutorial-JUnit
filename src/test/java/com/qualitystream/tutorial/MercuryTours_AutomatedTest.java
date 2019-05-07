package com.qualitystream.tutorial;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MercuryTours_AutomatedTest {
	
	private WebDriver driver;
	By registerLinkLocator = By.linkText("REGISTER");
	By registerPageLocator = By.xpath("//img[@src='/images/masts/mast_register.gif']");
	
	By usernameLocator = By.id("email");
	By passwordLocator = By.name("password");
	By confirmPassLocator = By.cssSelector("input[name='confirmPassword']");
	
	By registerBtnLocator = By.name("register");
	
	By userLocator = By.name("userName");
	By passLocator = By.name("password");
	By signInBtnLocator = By.name("login");
	
	By homePageLocator = By.xpath("//img[@src='/images/masts/mast_flightfinder.gif']");
	
	@BeforeEach
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://newtours.demoaut.com/mercurywelcome.php");		
	}
	
	@AfterEach
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	@Test
	public void registerUser() throws Exception {
		driver.findElement(registerLinkLocator).click();
		Thread.sleep(2000);
		if (driver.findElement(registerPageLocator).isDisplayed()) {
			driver.findElement(usernameLocator).sendKeys("qualityadmin");
			driver.findElement(passwordLocator).sendKeys("pass1");
			driver.findElement(confirmPassLocator).sendKeys("pass1");
			
			driver.findElement(registerBtnLocator).click();
		} else {
			System.out.println("Register page was not found");
		}
		
		List<WebElement> fonts =  driver.findElements(By.tagName("font"));
		
		assertEquals("Note: Your user name is qualityadmin.", fonts.get(5).getText());
	}
	
	@Test
	public void signIn() throws InterruptedException {
		if (driver.findElement(userLocator).isDisplayed()) {
			driver.findElement(userLocator).sendKeys("qualityadmin");
			driver.findElement(passLocator).sendKeys("pass1");
			driver.findElement(signInBtnLocator).click();
			Thread.sleep(2000);
			assertTrue(driver.findElement(homePageLocator).isDisplayed());
		} else {
			System.out.println("Username textbox was not present");
		}
	}
}
