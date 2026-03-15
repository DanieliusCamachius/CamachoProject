package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features= {
                "src/test/resources/features/CucumberFruits/fruitTable.feature"
        },
        tags="",  // Empty tags - allows Jenkins to control filtering with -Dcucumber.filter.tags
        glue={"stepDefinitionsCucumber","E2E.CucumberHooks"},
        monochrome = true, //reporting
        plugin = {"html:reports/CucumberReporter/cucumber.html"}
)
public class TestRunnerFruitTable extends AbstractTestNGCucumberTests {



}
