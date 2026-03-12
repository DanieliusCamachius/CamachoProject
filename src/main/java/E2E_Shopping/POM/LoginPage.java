package E2E_Shopping.POM;

import E2E_Shopping.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * LoginPage represents the login page of the e-commerce application.
 * This Page Object Model encapsulates all elements and actions related to user authentication,
 * including logging in with email and password, and handling login errors.
 *
 */
public class LoginPage extends AbstractComponent {
    WebDriver driver;

    public LoginPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    //PageFactory
    @FindBy(id = "userEmail")  //WebElement userEmail = driver.findElement(By.id("userEmail"));
    WebElement userEmail;

    @FindBy(id = "userPassword") // driver.findElement(By.id("userPassword")).sendKeys("1Danielcamacho!");
    WebElement passwordElement;

    @FindBy(id = "login") // //driver.findElement(By.id("login")).click();
    WebElement submit;
    @FindBy(xpath = "//div[@aria-label='Incorrect email or password.']")
    WebElement passwordErrorMessage;

    /**
     * Logs into the application with the provided email and password.
     * Fills in the email and password fields and clicks the submit button.
     * 
     * @param email The user's email address
     * @param password The user's password
     */
    public void loginApplication(String email, String password) {
        userEmail.sendKeys(email);
        passwordElement.sendKeys(password);
        submit.click();
    }

    //driver.findElement(By.id("userEmail")).sendKeys("dankamacho@hotmail.com");
    //driver.findElement(By.id("userPassword")).sendKeys("1Danielcamacho!");
    //driver.findElement(By.id("login")).click();

    /**
     * 
     * Checks for the presence of an error message indicating incorrect email or password.
    * */
    public String checkForErrorMessage(){
        waitForWebElementToAppear(passwordErrorMessage);
        return passwordErrorMessage.getText();
    }

    /**
     * Navigates to the login page URL.
     * Opens the e-commerce application login page in the current browser window.
     */
    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client/");
    }

}
