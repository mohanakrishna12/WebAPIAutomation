package com.equifax.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseSeleniumConfiguration extends BaseClass{

	public static String BROWSER_UNDER_EXECUTION = null;

	public static String USER_NAME = null;

	public static String PASSWORD = null;
	public static String BASE_URL = null;

	public static String INPUT_STRING = null;
	
	
	public BaseSeleniumConfiguration() {

		BROWSER_UNDER_EXECUTION = prop.getProperty("browser");
		USER_NAME = prop.getProperty("username");
		PASSWORD = prop.getProperty("password");
		System.out.println(prop.getProperty("browser"));
		BASE_URL = prop.getProperty("url"); 
		INPUT_STRING= prop.getProperty("search_input_string");
	}
	public BaseSeleniumConfiguration(WebDriver webDriver) {
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
	}

	enum BrowserType {
		firefox, interneExplore, chrome;
	}

	

	public WebDriver webDriver = null;
	public WebElement webElement = null;

	public void clickByXpathUsingJS(WebDriver webDriver, WebElement webElement) {
		for (int i = 0; i < 2; i++) {
			try {
				((JavascriptExecutor) webDriver).executeScript("arguments[0].click()", webElement);
				break;

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {

		PageFactory.initElements(webDriver, this);

		if (BROWSER_UNDER_EXECUTION.equals(BrowserType.chrome.toString())) {
			WebDriverManager.chromedriver().setup();

			webDriver = new ChromeDriver();
		} else if (BROWSER_UNDER_EXECUTION.equals(BrowserType.firefox.toString())) {
			WebDriverManager.firefoxdriver().setup();
			webDriver = new FirefoxDriver();
		}

		webDriver.manage().window().maximize();
		webDriver.get(BASE_URL);
		webDriver.switchTo().defaultContent();
	}
	
	@AfterMethod()
	public void tearDown() {
		webDriver.quit();
	}
}