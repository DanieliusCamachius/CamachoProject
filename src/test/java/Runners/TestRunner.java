package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features= {
                //"src/test/resources/features/CucumberShop/fruitTable.feature"
                "src/test/resources/features/CucumberShop/errorValidationCucumber.feature"
                //"src/test/resources/features/CucumberShop/submitOrderCucumber.feature"
                //"src/test/resources/features/CucumberShop/registrationCucumber.feature"
                //"src/test/resources/features/CucumberShop/"
        },
        tags="@tag",
        glue={"stepDefinitionsCucumber","E2E.CucumberHooks"},
        monochrome = true, //reporting
        plugin = {"html:reports/CucumberReporter/cucumber.html"}
)
public class TestRunner extends AbstractTestNGCucumberTests {



}
