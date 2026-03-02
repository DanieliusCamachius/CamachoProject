package E2E_Shopping.dCamacho;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class oldCode {
    public static void main(String[] args) throws InterruptedException {
        ChromeDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client/"); // https://rahulshettyacademy.com/AutomationPractice/
        String productName = "ZARA COAT 3";


        // LOGIN
        driver.findElement(By.id("userEmail")).sendKeys("dankamacho@hotmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("1Danielcamacho!");
        driver.findElement(By.id("login")).click();

        // selecionar todos os elementos (com o view e o cart)
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement prod = products.stream().filter(product-> product.findElement(By.cssSelector("b"))
                .getText().equals("ZARA COAT 3")).findFirst().orElse(null); // so retornar o 1st que encontrar
        Assert.assertNotNull(prod);

        // ADD TO CART
        prod.findElement(By.cssSelector("button.btn.w-10")).click(); // ".card-body button:last-of-type"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click(); //atributo chamado routerlink que contem cart

        // Verify o que adicionamos é o que esta no cart

        List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cardsection h3")); // div= "cartSection" element located at = h3
                                                                        // XPATH = //div[contains(@class,'cartSection')]//h3
        // cartProducts.stream().filter(cartProduct-> cartProduct.getText().equals(productName));

        Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName)); // retorna true se o AnyMatch retornar true (que existe)
        //Assert.assertTrue(match);
        driver.findElement(By.cssSelector(".totalRow button")).click(); // parent = class="totalRow"
                                                                        // actual = type="button"

        // confirmation page
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"portugal").build().perform();
                                                                            // ta-results list-group ng-star-inserted (.<classname>)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.cssSelector("button.ta-item")).click();
        driver.findElement(By.cssSelector(".action__submit")).click();

        // thank you page
        boolean purchaseThanks = driver.findElement(By.cssSelector(".hero-primary")).getText().equalsIgnoreCase("Thankyou for the order.");
        Assert.assertTrue(purchaseThanks);

    }
}