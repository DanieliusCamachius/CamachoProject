package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features= {
                "src/test/resources/features/CucumberFruits/fruitTable.feature"
        },
        tags="@tag",
        glue={"stepDefinitionsCucumber","E2E.CucumberHooks"},
        monochrome = true, //reporting
        plugin = {"html:reports/CucumberReporter/cucumber.html"}
)
public class TestRunnerFruitTable extends AbstractTestNGCucumberTests {



}
