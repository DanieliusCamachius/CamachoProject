package stepDefinitionsCucumber;

import E2E.CucumberHooks.Hooks;
import E2E.TestComponents.BaseTestPropCucumber;
import E2E_Shopping.POM.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;

/**
 * StepDefinitionsShopSite contains Cucumber step definitions for the e-commerce shopping site tests.
 * This class implements Gherkin step definitions for login, product catalog browsing, cart management,
 * checkout process, and user registration functionality.
 */
public class StepDefinitionsShopSite{
    public ConfirmationPage confirmationPage;
    public LoginPage loginPage;
    public ProductCatalog productCatalog;
    public CartPage cartPage;
    public CheckOutPage checkOutPage;
    public RegistrationPage registrationPage;


    public StepDefinitionsShopSite() { }

    /**
     * Navigates to the login/home page of the e-commerce application.
     * Step Definition: "I landed on Ecommerce Page"
     * 
     * @throws IOException if navigation fails
     */
    @Given("I landed on Ecommerce Page")
    public void goToLoginPage() throws IOException {

        loginPage = new LoginPage(Hooks.driver);
        loginPage.goTo(); /**CHECK IF NEEDED**/
    }

    /**
     * Logs into the application with the provided email and password.
     * Step Definition: "I am logged in with {string} and password {string}"
     * 
     * @param username The user's email address
     * @param password The user's password
     */
    @Given("I am logged in with {string} and password {string}")
    public void loginWithUsernameAndPassword(String username, String password) {
        loginPage.loginApplication(username, password);
    }

    /**
     * Adds a specific product to the shopping cart.
     * Step Definition: "I add the product {string} to Cart"
     * 
     * @param product The name of the product to add to the cart
     */
    @When("I add the product {string} to Cart")
    public void addProductToCart(String product) {
        productCatalog = new ProductCatalog(Hooks.driver);

        productCatalog.addProductToCart(product);
    }

    /**
     * Navigates to cart, verifies the product is present, and completes the checkout process.
     * Selects Portugal as the country and submits the order.
     * Step Definition: "I Checkout {string} and submit the order"
     * 
     * @param product The name of the product to verify in the cart before checkout
     */
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

    /**
     * Verifies that the expected confirmation message is displayed on the confirmation page.
     * Step Definition: "{string} message is displayed on Confirmation Page"
     * 
     * @param expectedMessage The expected confirmation message text
     */
    @Then("{string} message is displayed on Confirmation Page")
    public void checkCheckoutMessage(String expectedMessage) {
        String message = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(message.equalsIgnoreCase(expectedMessage));
    }

    /**
     * Verifies that an expected error message is displayed on the login page.
     * Step Definition: "{string} message is displayed on Login Page"
     * 
     * @param expectedMessage The expected error message text
     */
    @Then("{string} message is displayed on Login Page")
    public void messageIsDisplayedOnLoginPage(String expectedMessage) {
        Assert.assertEquals(expectedMessage, loginPage.checkForErrorMessage());
    }

    /**
     * Navigates to the registration page from the login page.
     * Step Definition: "I am on the registration page"
     */
    @Given("I am on the registration page")
    public void goToRegistrationPage() {
        registrationPage = new RegistrationPage(Hooks.driver);
        registrationPage.goToRegistration();
    }

    /**
     * Completes the user registration form with all provided information.
     * Fills first name, last name, email, phone, occupation, gender, password, and confirm password fields.
     * Step Definition: "I complete registration with first name {string}, last name {string}, email {string}, 
     * phone {string}, occupation {string}, gender {string}, password {string}, confirm password {string}"
     * 
     * @param firstName The user's first name
     * @param lastName The user's last name
     * @param email The user's email address
     * @param phone The user's phone number
     * @param occupation The user's occupation (Doctor, Student, Engineer, Scientist)
     * @param gender The user's gender (Male or Female)
     * @param password The user's password
     * @param confirmPassword The user's password confirmation
     */
    @When("I complete registration with first name {string}, last name {string}, email {string}, phone {string}, occupation {string}, gender {string}, password {string}, confirm password {string}")
    public void completeRegistration(String firstName, String lastName, String email, String phone, String occupation, String gender, String password, String confirmPassword) {
        registrationPage.completeRegistration(firstName, lastName, email, phone, occupation, gender, password, confirmPassword);
    }

    /**
     * Verifies that the registration process was successful.
     * Asserts that the confirmation message equals "Account Created Successfully".
     * Step Definition: "Registration should be successful"
     */
    @Then("Registration should be successful")
    public void registrationShouldBeSuccessful() {
        Assert.assertEquals(registrationPage.getConfirmationMessage(),"Account Created Successfully");
        //Assert.assertTrue(Hooks.driver.getCurrentUrl().contains("login"));
    }
}
