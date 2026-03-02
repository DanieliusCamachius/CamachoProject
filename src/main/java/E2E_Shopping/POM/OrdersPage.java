package E2E_Shopping.POM;

import E2E_Shopping.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

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
    public void goToOrdersPage(){
        clickMyOrders();
    }

    public Boolean VerifyOrderDisplay(String productName) {
        return productNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
    }
}
