package com.equifax.pages;

import static org.testng.Assert.assertTrue;

import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.equifax.constant.LoginPageConstansts;
import com.equifax.util.BaseSeleniumConfiguration;

public class LoginPage extends BaseSeleniumConfiguration {

	public List<WebElement> webElements = null;

	/**
	 * This is the default Constructor
	 */
	public LoginPage() {

	}

	@FindBy(id = LoginPageConstansts.LOGIN_LINK)

	WebElement loginLink;

	@FindBy(id = LoginPageConstansts.USERNAME)

	WebElement usernameElement;

	@FindBy(xpath = LoginPageConstansts.CONTINUE)

	WebElement continueLogin;
	@FindBy(xpath = LoginPageConstansts.PASSWORD)

	WebElement passwordElement;

	@FindBy(xpath = LoginPageConstansts.CLICK_TO_REFRESH_PAGE)

	WebElement approve_page;

	@FindBy(xpath = LoginPageConstansts.SUCCESFUL_LOGIN)

	WebElement succesfulLogin;

	public LoginPage(WebDriver webDriver) {
		super(webDriver);
		this.webDriver = webDriver;
	}

	public void approveLoginPage() {

		try {
			while (approve_page.isDisplayed()) {
				approve_page.click();
				wait(2);
				if (!approve_page.isDisplayed()) {
					break;
				}
			}
		} catch (Exception e) {

		}

	}

	public void validateSuccessFulLogin(String userName, String password)  {
		loginLink.click();
		usernameElement.clear();
		usernameElement.sendKeys(userName);
		continueLogin.click();
		wait(2);
		passwordElement.clear();
		passwordElement.sendKeys(password, Keys.RETURN);
		approveLoginPage();
		assertTrue(succesfulLogin.isDisplayed(), "Succesfully login to the web application");
	}
}
