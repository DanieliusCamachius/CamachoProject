package stepDefinitionsCucumber;

import E2E.CucumberHooks.Hooks;
import FruitTableDev.FruitPOM.FruitTable;
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

public class StepDefinitionsFruitSite {


    private FruitTable fruitTable;
    
    public StepDefinitionsFruitSite() { }

    @Given("I landed on fruit table page")
    public void goToFruitPage() throws IOException {
        fruitTable = new FruitTable(Hooks.driver);
        fruitTable.goTo();
    }

    @Given("I click download button")
    public void clickDownloadButton() {
        fruitTable.clickDownload();
    }

    @When("I upload new data file from {string}")
    public void verifyDownloadNotif(String path) {
        fruitTable.uploadXml(path);
    }

    @Then("Then Notification of successful upload is displayed")
    public void verifyUploadNotification() {
        fruitTable.checkUploadNotification();
    }

    @Then("Table data is valid")
    public void netDataValidation() {
        List<FruitDTO> fruits = getTableData(Hooks.driver);
        List<FruitDTO> expectedFruits = createFruitList();
        Assert.assertTrue(compareLists(expectedFruits, fruits));
    }
}
