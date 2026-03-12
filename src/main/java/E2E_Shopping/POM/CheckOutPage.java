package E2E_Shopping.POM;

import E2E_Shopping.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * CheckOutPage represents the checkout page of the e-commerce application.
 * This Page Object Model encapsulates all elements and actions related to the checkout process,
 * including country selection and order submission.
 */
public class CheckOutPage extends AbstractComponent {

    WebDriver driver;

    public CheckOutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[placeholder='Select Country']") // dropdown dos paises
    private WebElement countryDropDown;

    @FindBy(css = ".action__submit")
    private WebElement submit; //botao do submit

    @FindBy(css = "button.ta-item")
    private WebElement selectedCountry; // o pais que se vai escolher

    By results = By.cssSelector("button.ta-item");


    /**
     * Selects a country from the country dropdown.
     * Enters the country name in the dropdown field and clicks the matching result.
     * 
     * @param countryName The name of the country to select
     */
    public void selectCountry(String countryName){
        Actions a = new Actions(driver);
        a.sendKeys(countryDropDown,countryName).build().perform();

        waitForWebElementToAppear(results);
        selectedCountry.click();
    }

    /**
     * Submits the order by clicking the submit button.
     * 
     * @return A new ConfirmationPage instance displaying the order confirmation
     */
    public ConfirmationPage submitOrder(){
        submit.click();
        return new ConfirmationPage(driver);
    }

}