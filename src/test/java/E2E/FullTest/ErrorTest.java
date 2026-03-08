package E2E.FullTest;

import E2E.TestComponents.BaseTestPropNG;
import E2E.TestComponents.Retry;
import E2E_Shopping.POM.CartPage;
import E2E_Shopping.POM.ProductCatalog;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ErrorTest extends BaseTestPropNG {
    @Test(groups = {"ErrorHandling"})
    public void errorValidation() {

        loginPage.loginApplication("dankamacho@hotmail.com", "32132123213213!");
        Assert.assertEquals("Incorrect email or password.", loginPage.checkForErrorMessage());

        // xpath = //div[@aria-label='Incorrect email or password.']
        // div[aria-label='Incorrect email or password.']
        // div[aria-label='Incorrect email or password.']
    }

    @Test(groups = {"Error100"},retryAnalyzer = Retry.class)
    public void testToFail() {
        loginPage.loginApplication("dankamacho@hotmail.com", "32132123213213!");
        Assert.assertEquals("Incorrect eeeeeeeeeeeeee or password.", loginPage.checkForErrorMessage());
    }

    @Test
    public void submitWrongOrder() throws InterruptedException, IOException {

        String productName = "ZARA COAT 3";

        //loginPage from @BeforeMethod
        loginPage.loginApplication("dankamacho@hotmail.com", "1Danielcamacho!");

        // PRODUCT PAGE
        ProductCatalog productCatalog = new ProductCatalog(driver);
        productCatalog.addProductToCart(productName);
        productCatalog.clickCartHeader();

        //CART PAGE
        CartPage cartPage = new CartPage(driver);
        Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 5");
        Assert.assertFalse(match);
    }
}