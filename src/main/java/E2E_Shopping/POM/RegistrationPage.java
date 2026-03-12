package E2E_Shopping.POM;

import E2E_Shopping.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * RegistrationPage represents the user registration page of the e-commerce application.
 * This Page Object Model encapsulates all elements and actions related to user account creation,
 * including filling in personal information, selecting occupations and gender, setting passwords,
 * and confirming age verification.
 */
public class RegistrationPage extends AbstractComponent {
    WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;
    
    @FindBy(id = "userEmail")
    private WebElement emailInput;

    // Phone Number field
    @FindBy(id = "userMobile")
    private WebElement phoneNumberInput;

    // Occupation dropdown
    @FindBy(css = "select[formcontrolname='occupation']")
    private WebElement occupationDropdown;

    // Gender radio buttons
    @FindBy(xpath = "//input[@formcontrolname='gender' and @value='Male']")
    private WebElement maleRadioButton;

    @FindBy(xpath = "//input[@formcontrolname='gender' and @value='Female']")
    private WebElement femaleRadioButton;

    @FindBy(id = "userPassword")
    private WebElement passwordInput;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPasswordInput;

    @FindBy(xpath = "//input[@formcontrolname='required' and @type='checkbox']")
    private WebElement ageCheckbox;
    
    @FindBy(id = "login")
    private WebElement registerButton;
    
    @FindBy(css = "h1.headcolor")
    private WebElement header;

    @FindBy(css = "a.text-reset")
    private WebElement goToRegisterButton;

    /**
     * Completes the entire registration form with all provided information.
     * Fills in first name, last name, email, phone number, selects occupation and gender,
     * enters password and confirm password, checks the age verification checkbox, and clicks register.
     *
     * @param firstName      First name of the user
     * @param lastName       Last name of the user
     * @param email          Email address
     * @param phoneNumber    Phone number
     * @param occupation     Occupation (Doctor, Student, Engineer, Scientist)
     * @param gender         Gender (Male or Female)
     * @param password       Password
     * @param confirmPassword Confirm password
     */
    public void completeRegistration(String firstName, String lastName, String email,
                                                   String phoneNumber, String occupation, String gender,
                                                   String password, String confirmPassword) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        emailInput.sendKeys(email);
        phoneNumberInput.sendKeys(phoneNumber);
        selectOccupation(occupation);
        selectGender(gender);
        passwordInput.sendKeys(password);
        confirmPasswordInput.sendKeys(confirmPassword);
        if (!ageCheckbox.isSelected()) {
            ageCheckbox.click();
        }
        registerButton.click();
    }

    /**
     * Selects an occupation from the occupation dropdown
     *
     * @param occupation Occupation value (Doctor, Student, Engineer, Scientist)
     */
    private void selectOccupation(String occupation) {
        // Create Select object for dropdown
        Select select = new Select(occupationDropdown);
        select.selectByVisibleText(occupation);
    }

    /**
     * Selects gender from radio buttons
     *
     * @param gender Gender value (Male or Female)
     */
    private void selectGender(String gender) {
        if ("Male".equalsIgnoreCase(gender)) {
            maleRadioButton.click();
        } else if ("Female".equalsIgnoreCase(gender)) {
            femaleRadioButton.click();
        }
    }

    /**
     * Retrieves the confirmation message displayed on the page after registration.
     * Typically displays "Account Created Successfully" or an error message.
     * 
     * @return The text of the confirmation/header message
     */
    public String getConfirmationMessage() {
        return header.getText();
    }

    /**
     * Navigate to the registration page
     */
    public void goToRegistration() {
        driver.get("https://rahulshettyacademy.com/client/#/auth/login");
        goToRegisterButton.click();
    }
    
}
