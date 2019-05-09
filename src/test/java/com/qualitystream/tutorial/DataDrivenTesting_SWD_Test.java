package com.qualitystream.tutorial;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class DataDrivenTesting_SWD_Test {
	
	private WebDriver driver;
	private WriteExcelFile writeFile;
	private ReadExcelFile readFile;
	private By searchBoxLocator = By.id("search_query_top");
	private By searchButtonLocator =  By.name("submit_search");
	private By resultTextLocator = By.cssSelector("span.heading-counter");
	
	@BeforeEach
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		writeFile =  new WriteExcelFile();
		readFile = new ReadExcelFile();
		
		driver.get("http://automationpractice.com");
	}
	
	@AfterEach
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void test() throws IOException {
		String filepath = "C:\\Users\\lalvarez\\eclipse-workspace\\TestAutomationTutorialJUnit\\Test.xlsx";
		
		String searchText = readFile.getCellValue(filepath, "Sheet1", 0, 0);
		
		driver.findElement(searchBoxLocator).sendKeys(searchText);
		driver.findElement(searchButtonLocator).click();
		String resultText = driver.findElement(resultTextLocator).getText();
		
		System.out.println("Page result text: " + resultText);
		
		readFile.readExcel(filepath, "Sheet1");
		
		writeFile.writeCellValue(filepath, "Sheet1", 0, 1, resultText);
		
		readFile.readExcel(filepath, "Sheet1");
	}

}
