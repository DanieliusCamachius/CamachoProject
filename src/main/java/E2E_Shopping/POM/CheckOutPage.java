package E2E_Shopping.POM;

import E2E_Shopping.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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


    public void selectCountry(String countryName){
        Actions a = new Actions(driver);
        a.sendKeys(countryDropDown,countryName).build().perform();

        waitForWebElementToAppear(By.cssSelector("button.ta-item")); //
        selectedCountry.click();
    }

    public ConfirmationPage submitOrder(){
        submit.click();
        return new ConfirmationPage(driver);
    }

}