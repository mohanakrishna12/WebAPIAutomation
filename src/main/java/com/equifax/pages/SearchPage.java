package com.equifax.pages;
import java.util.List;
import java.util.logging.Level;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.equifax.constant.SearchPageConstansts;
import com.equifax.util.BaseSeleniumConfiguration;
public class SearchPage extends BaseSeleniumConfiguration {
	@FindBy(xpath = SearchPageConstansts.SEARCH_KEY)
	WebElement searchKeyelement;
	@FindBy(xpath = SearchPageConstansts.SEARCH_RESULT)
	private List<WebElement> searchResult;
	public SearchPage(WebDriver webDriver) {
		super(webDriver);
		this.webDriver=webDriver;
	} 
	public void searchGivenText(String inputText) {
		searchKeyelement.click();
		searchKeyelement.sendKeys(inputText, Keys.RETURN);
		wait(2);
	}
	public String getFirstSearchValue() {
		try {
 		 return searchResult.get(0).getText() ;
		}catch (Exception e) {
			logger.log(Level.WARNING, "getFirstSearchValue==>"+e.getMessage());
		}
		return null;
	}
	public void clickFirstSearchValue() {
 		this.clickByXpathUsingJS(webDriver, searchResult.get(0)); 
	}
}