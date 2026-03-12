package E2E_Shopping.POM;

import E2E_Shopping.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * ConfirmationPage represents the order confirmation page of the e-commerce application.
 * This Page Object Model encapsulates elements and actions related to displaying the order confirmation message
 * after a successful purchase.
 *
 */
public class ConfirmationPage extends AbstractComponent {
    WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".hero-primary")
    WebElement confirmationMessage;

    /**
     * Retrieves the confirmation message displayed on the page.
     * 
     * @return The text of the confirmation message
     */
    public String getConfirmationMessage() {
        return confirmationMessage.getText();
    }
}
