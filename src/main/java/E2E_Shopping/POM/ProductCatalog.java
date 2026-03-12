package E2E_Shopping.POM;

import E2E_Shopping.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

/**
 * ProductCatalog represents the product catalog/main shopping page of the e-commerce application.
 * This Page Object Model encapsulates all elements and actions related to browsing products,
 * including retrieving product lists and adding products to the shopping cart.
 *
 */
public class ProductCatalog extends AbstractComponent {

    WebDriver driver;

    public ProductCatalog(WebDriver driver){
        super(driver); // driver enviada para pai
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    //PageFactory
    @FindBy(css = ".mb-3")  // List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
    List<WebElement> products;
    @FindBy(css = ".ng-animating")  // List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
    WebElement spinner;

    By productBy = By.cssSelector(".mb-3"); // single element
    By addToCart = By.cssSelector("button.btn.w-10"); // By.cssSelector("button.btn.w-10.add-to-cart");
    By toastMessage = By.cssSelector("#toast-container");

    /**
     * Retrieves the list of all available products on the catalog page.
     * Waits for products to be visible before returning the list.
     * 
     * @return A List of WebElement objects representing all products on the page
     */
    public List<WebElement> getProductList(){
        waitForWebElementToAppear(productBy);
        return products;
    }

    /**
     * Finds and returns a specific product by its name from the product list.
     * Uses stream filtering to find the first product matching the given name.
     * 
     * @param productName The exact name of the product to find
     * @return A WebElement representing the product container, or null if not found
     */
    public WebElement getProductByName (String productName){
        // so retornar o 1st que encontrar;
        return getProductList().stream().filter(product->
                product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
    }

    /**
     * Adds a specific product to the shopping cart.
     * Finds the product by name, clicks the "Add to Cart" button, and waits for the toast confirmation message.
     * Also waits for the loading spinner to disappear before returning.
     * 
     * @param productName The exact name of the product to add to the cart
     */
    public void addProductToCart(String productName){
        WebElement prod = getProductByName(productName); // returns single section of a webDriver
        prod.findElement(addToCart).click();
        waitForWebElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);
    }

}