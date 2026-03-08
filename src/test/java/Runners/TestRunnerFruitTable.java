package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features= {
                "src/test/resources/features/CucumberFruits/submitOrderCucumber.feature"
        },
        tags="@tag",
        glue="stepDefinitionsCucumber",
        monochrome = true, //reporting
        plugin = {"html:reports/CucumberReporter/cucumber.html"}
)
public class TestRunnerFruitTable extends AbstractTestNGCucumberTests {



}
