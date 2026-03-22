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
        try {
            base = new BaseTestPropCucumber();
            driver = base.initializeDriver();

            if (driver == null) {
                throw new RuntimeException("Driver not initialized in @Before hook");
            }
            System.out.println("Driver ready");

        } catch (Exception e) {
            System.err.println("Critical Error while creating scenario: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @After
    public void afterScenario() {
        try {
            if (driver != null) {
                driver.quit();
                System.out.println("Scenario Finished - Driver Closed");
            }
        } catch (Exception e) {
            System.err.println("Error while closing scenario: " + e.getMessage());
            // Não relançar exceção em @After para não mascarar erros anteriores do cenário
        }
    }
}