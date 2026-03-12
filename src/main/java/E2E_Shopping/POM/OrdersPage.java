package E2E_Shopping.POM;

import E2E_Shopping.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * OrdersPage represents the orders/order history page of the e-commerce application.
 * This Page Object Model encapsulates all elements and actions related to viewing user orders,
 * including verifying product orders.
 *
 * @author QA Team
 * @version 1.0
 */
public class OrdersPage extends AbstractComponent {
    WebDriver driver;

    @FindBy(css = ".totalRow button")
    WebElement checkoutEle;

    @FindBy(css = "tr td:nth-child(3)") // all table rows
    private List<WebElement> productNames;

    public OrdersPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    /**
     * Navigates to the Orders page by clicking the "My Orders" link.
     * Uses the clickMyOrders() method from the parent AbstractComponent class.
     */
    public void goToOrdersPage(){
        clickMyOrders();
    }

    /**
     * Verifies if a specific product is displayed in the orders list.
     * Performs a case-insensitive comparison with product names in the orders table.
     * 
     * @param productName The name of the product to verify
     * @return true if the product is found in the orders, false otherwise
     */
    public Boolean VerifyOrderDisplay(String productName) {
        return productNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
    }
}
