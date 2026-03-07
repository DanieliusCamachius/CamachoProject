package stepDefinitionsCucumber;

import E2E.TestComponents.BaseTestProp;
import E2E_Shopping.POM.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;

public class StepDefinitionsShopSite extends BaseTestProp {
    public ConfirmationPage confirmationPage;

    @Given("I landed on Ecommerce Page")
    public void goToLoginPage() throws IOException {
        launchApplication();
        launchShoppingObjects();

        loginPage.goTo();
    }

    @Given("I am logged in with {string} and password {string}")
    public void loginWithUsernameAndPassword(String username, String password) {
        loginPage.loginApplication(username, password);
    }

    @When("I add the product {string} to Cart")
    public void addProductToCart(String product) {
        productCatalog.addProductToCart(product);
    }

    @When("I Checkout {string} and submit the order")
    public void checkoutCart(String product) {
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
        driver.close();
    }

    @Then("{string} message is displayed on Login Page")
    public void messageIsDisplayedOnLoginPage(String expectedMessage) {
        Assert.assertEquals(expectedMessage, loginPage.checkForErrorMessage());
        driver.close();
    }
}
