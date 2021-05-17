/*
 * Request you to have 2 sets of exercises(Java8 and above) completed and share the GitHub link for review.

1. Selenium WebDriver - Page Object Model:

1. Visit amazon.com Page
2. Search for Book 'qa testing for beginners'
3. Click on 1st item in the listed results.
 
4. Before Click on add to cart Add to Cart asset price from Step3. 
5. Click on Add to Cart.

6. Before Click on Proceed to Checkout asset price from Step3.
7. Click on proceed to checkout

5. On the checkout page assert price from Step3.
 */

package com.equifax.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.logging.Level;

import org.testng.annotations.Test;

import com.equifax.pages.CartPage;
import com.equifax.pages.LoginPage;
import com.equifax.pages.SearchPage;
import com.equifax.util.BaseSeleniumConfiguration;

public class WebAutomationTest extends BaseSeleniumConfiguration {

	@Test
	public void testAddCartFunctionality() {
		LoginPage loginpage = new LoginPage(webDriver);
		loginpage.validateSuccessFulLogin(USER_NAME, PASSWORD);
		CartPage cart = new CartPage(webDriver);
		SearchPage search = new SearchPage(webDriver);
		cart.clearCartValues();
		search.searchGivenText(INPUT_STRING);
		String firstSearchValue =search.getFirstSearchValue().replaceAll("[\t\n\r]", ".");		
		search.clickFirstSearchValue();
		String addToCartPrice = cart.addToCartPrice();
		assertEquals(firstSearchValue, addToCartPrice, "cart item price is not matched");
		logger.log(Level.INFO, "addToCartPrice=" + addToCartPrice);
		String total_cart_amount = cart.proceedToCartPrice();
		assertEquals(addToCartPrice, total_cart_amount,
				"Add to cart value and proceed to checkout cart values are not same");
	}
}
