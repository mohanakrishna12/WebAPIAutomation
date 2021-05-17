package com.equifax.pages;

import static org.testng.Assert.assertFalse;
import java.util.List;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.equifax.constant.CartPageConstansts;
import com.equifax.util.BaseSeleniumConfiguration;

public class CartPage extends BaseSeleniumConfiguration {
	
	@FindBy(id = CartPageConstansts.CART_ITEMS)
	WebElement cartItems;

	@FindBy(xpath = CartPageConstansts.ADD_TO_CART)
	WebElement addToCart;

	@FindBy(xpath = CartPageConstansts.OPEN_ACTIVE_CART_ITEMS)
	private List<WebElement> open_active_cart_Items;

	@FindBy(xpath = CartPageConstansts.ADD_TO_CART_PRICE)
	private WebElement addToCartPrice;

	@FindBy(xpath = CartPageConstansts.PROCEED_TO_CHECKOUT)
	private List<WebElement> proceedToCart;

	@FindBy(xpath = CartPageConstansts.PROCEED_TO_CHECKOUT_AMOUNT)
	WebElement proceedToCheckoutAmt;

	@FindBy(xpath = CartPageConstansts.DELIVER_THIS_ADDRESS)
	private List<WebElement> deliver_To_thisAddress;

	@FindBy(xpath = CartPageConstansts.CLEAR_ACTIVE_CART_ITEMS)
	private WebElement clear_cart;

	public CartPage(WebDriver webDriver) {
		super(webDriver);
		this.webDriver = webDriver;

	}

	public CartPage clearCartValues() {
		logger.log(Level.INFO, "clearing the cart values if any" );
		cartItems.click();
		wait(2);
		logger.log(Level.INFO, "count =" + open_active_cart_Items.size());

 		for (WebElement webElement : open_active_cart_Items) {
			try {
				webElement.click();
				wait(5);
				clickByXpathUsingJS(webDriver, clear_cart);
				break;
			} catch (Exception e) {
				logger.log(Level.INFO, e.getMessage() );
 			}
		}
		return new CartPage(webDriver);
	}

	public String addToCartPrice() {
		String price = null;

 			try {
				price = addToCartPrice.getText();
			} catch (Exception e) {
				logger.log(Level.WARNING, e.getMessage() );
				wait(2);
			}
		
		logger.log(Level.INFO, "addToCartPrice=" + price );

 		assertFalse(price.isEmpty(), "addToCartPrice value is empty");

		clickByXpathUsingJS(webDriver, addToCartPrice);
		clickByXpathUsingJS(webDriver, addToCart);
		return price;
	}

	public String proceedToCartPrice() {
		String procedToCheckOut = proceedToCheckoutAmt.getText();
		logger.log(Level.INFO, "procedToCheckOut=" + procedToCheckOut);

 		return procedToCheckOut;
	}

	public void deliverToThisAddress() {
		deliver_To_thisAddress.get(0).click();
	}
}