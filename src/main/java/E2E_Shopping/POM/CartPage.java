package E2E_Shopping.POM;

import java.util.List;
import E2E_Shopping.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * CartPage represents the shopping cart page of the e-commerce application.
 * This Page Object Model encapsulates all elements and actions related to the shopping cart,
 * including verifying products in the cart and proceeding to checkout.
 */
public class CartPage extends AbstractComponent {
	WebDriver driver;

	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;					// parent => class="totalRow"
											// actual => type="button"

	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartProducts;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); // Cria proxies (objetos “fantasma”) para cada WebElement.
	}

	/**
	 * Verifies if a specific product is displayed in the shopping cart.
	 * Performs a case-insensitive comparison with product names in the cart.
	 * 
	 * @param productName The name of the product to verify
	 * @return true if the product is found in the cart, false otherwise
	 */
	public Boolean VerifyProductDisplay(String productName) {
        return cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
	}

	/**
	 * Clicks the checkout button and navigates to the CheckOut page.
	 * 
	 * @return A new CheckOutPage instance for further interactions
	 */
	public CheckOutPage goToCheckout() {
		checkoutEle.click();
		return new CheckOutPage(driver);
	}

}
