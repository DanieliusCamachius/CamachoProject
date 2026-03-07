package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features= {
                //"src/test/resources/features/CucumberShop/submitOrderCucumber.feature"
                "src/test/resources/features/CucumberShop/errorValidationCucumber.feature"
                //"src/test/resources/features/CucumberShop/"
        },
        tags="@tag",
        glue="stepDefinitionsCucumber",
        monochrome = true, //reporting
        plugin = {"html:reports/CucumberReporter/cucumber.html"}
)
public class TestRunner extends AbstractTestNGCucumberTests {



}
