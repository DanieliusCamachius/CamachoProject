package E2E.CucumberHooks;

import E2E.TestComponents.BaseTestPropCucumber;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Hooks extends BaseTestPropCucumber {

    public static WebDriver driver; // static WebDriver to be shared across step definitions (belongs to class not to an object)
    private BaseTestPropCucumber base;

    @Before
    public void beforeScenario() throws Exception {
        base = new BaseTestPropCucumber();
        driver = base.initializeDriver();
    }

    @After
    public void afterScenario() {
        if (driver != null) {
            driver.quit();
        }
    }
}