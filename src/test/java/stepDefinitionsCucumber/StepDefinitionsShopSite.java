package stepDefinitionsCucumber;

import E2E.CucumberHooks.Hooks;
import E2E.TestComponents.BaseTestPropCucumber;
import E2E_Shopping.POM.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;

public class StepDefinitionsShopSite{
    public ConfirmationPage confirmationPage;
    public LoginPage loginPage;
    public ProductCatalog productCatalog;
    public CartPage cartPage;
    public CheckOutPage checkOutPage;


    public StepDefinitionsShopSite() { }

    @Given("I landed on Ecommerce Page")
    public void goToLoginPage() throws IOException {

        loginPage = new LoginPage(Hooks.driver);
        loginPage.goTo(); /**CHECK IF NEEDED**/
    }

    @Given("I am logged in with {string} and password {string}")
    public void loginWithUsernameAndPassword(String username, String password) {
        loginPage.loginApplication(username, password);
    }

    @When("I add the product {string} to Cart")
    public void addProductToCart(String product) {
        productCatalog = new ProductCatalog(Hooks.driver);

        productCatalog.addProductToCart(product);
    }

    @When("I Checkout {string} and submit the order")
    public void checkoutCart(String product) {
        cartPage= new CartPage(Hooks.driver);
        checkOutPage = new CheckOutPage(Hooks.driver);

        productCatalog.clickCartHeader();
        cartPage.VerifyProductDisplay(product);
        cartPage.goToCheckout();
        checkOutPage.selectCountry("portugal");
        confirmationPage = checkOutPage.submitOrder();
    }

    @Then("{string} message is displayed on Confirmation Page")
    public void checkCheckoutMessage(String expectedMessage) {
        String message = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(message.equalsIgnoreCase(expectedMessage));
    }

    @Then("{string} message is displayed on Login Page")
    public void messageIsDisplayedOnLoginPage(String expectedMessage) {
        Assert.assertEquals(expectedMessage, loginPage.checkForErrorMessage());
    }
}
