package stepDefinitionsCucumber;

import E2E.TestComponents.BaseTestProp;
import FruitTableDev.DTO.FruitDTO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;


import java.io.IOException;
import java.util.List;

import static FruitTableDev.FruitPOM.FruitTable.compareLists;
import static FruitTableDev.FruitPOM.FruitTable.getTableData;
import static FruitTableDev.starting.createFruitList;

public class StepDefinitionsFruitSite extends BaseTestProp {


    @Given("I landed on fruit table page")
    public void goToFruitPage() throws IOException {
        launchFruitApplication();
    }

    @Given("I click download button")
    public void clickDownloadButton()  {
        fruitTable.clickDownload();
    }

    @When("I upload new data file from {string}")
    public void verifyDownloadNotif(String path)  {
        fruitTable.uploadXml(path);
    }
    //    Then Notification of successful upload is displayed
    //    And New data is valid

    @Then("Then Notification of successful upload is displayed")
    public void verifyUploadNotification(){
        fruitTable.checkUploadNotification();
    }

    @Then("Table data is valid")
    public void netDataValidation(){
        List<FruitDTO> fruits = getTableData(driver);
        List<FruitDTO> expectedFruits = createFruitList();

        Assert.assertTrue(compareLists(expectedFruits, fruits));
    }


    //public void checkCheckoutMessage(String expectedMessage) {
    //    String message = confirmationPage.getConfirmationMessage();
    //    Assert.assertTrue(message.equalsIgnoreCase(expectedMessage));
    //    driver.close();
    //}
//
    //@Then("{string} message is displayed on Login Page")
    //public void messageIsDisplayedOnLoginPage(String expectedMessage) {
    //    Assert.assertEquals(expectedMessage, loginPage.checkForErrorMessage());
    //    driver.close();
    //}
}
