package E2E_Shopping.POM;

import E2E_Shopping.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public void loginApplication(String email, String password) {
        userEmail.sendKeys(email);
        passwordElement.sendKeys(password);
        submit.click();
    }

    //driver.findElement(By.id("userEmail")).sendKeys("dankamacho@hotmail.com");
    //driver.findElement(By.id("userPassword")).sendKeys("1Danielcamacho!");
    //driver.findElement(By.id("login")).click();

    public String checkForErrorMessage(){
        waitForWebElementToAppear(passwordErrorMessage);
        return passwordErrorMessage.getText();
    }

    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client/");
    }

}
