package E2E_Shopping.POM;

import java.util.List;
import E2E_Shopping.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

	public Boolean VerifyProductDisplay(String productName) {
        return cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
	}

	public CheckOutPage goToCheckout() {
		checkoutEle.click();
		return new CheckOutPage(driver);
	}

}
