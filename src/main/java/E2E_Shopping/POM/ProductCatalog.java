package E2E_Shopping.POM;

import E2E_Shopping.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

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

    public List<WebElement> getProductList(){
        waitForWebElementToAppear(productBy);
        return products;
    }

    public WebElement getProductByName (String productName){
        // so retornar o 1st que encontrar;
        return getProductList().stream().filter(product->
                product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
    }

    public void addProductToCart(String productName){
        WebElement prod = getProductByName(productName); // returns single section of a webDriver
        prod.findElement(addToCart).click();
        waitForWebElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);
    }

}