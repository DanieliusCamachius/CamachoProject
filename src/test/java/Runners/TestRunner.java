package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features= {
                "src/test/resources/features/CucumberShop/submitOrderCucumber.feature"
                //"src/test/resources/features/CucumberShop/submitWrongOrder.feature"
        },
        glue="stepDefinitionsCucumber",
        monochrome = true, //reporting
        plugin = {"html:target/cucumber.html"}
)
public class TestRunner extends AbstractTestNGCucumberTests {



}
