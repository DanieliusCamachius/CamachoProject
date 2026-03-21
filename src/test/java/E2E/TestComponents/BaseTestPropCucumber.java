package E2E.TestComponents;

import E2E_Shopping.POM.CartPage;
import E2E_Shopping.POM.CheckOutPage;
import E2E_Shopping.POM.LoginPage;
import E2E_Shopping.POM.ProductCatalog;
import FruitTableDev.FruitPOM.FruitTable;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTestPropCucumber{

    public WebDriver driver; // global variable


    public WebDriver initializeDriver() throws IOException {
        // Properties class can read global properties
        Properties prop = new Properties();

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/E2E_Shopping/resources/GlobalData.properties");
        prop.load(fis);

        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");

        if (browserName.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if (browserName.contains("headless")) {
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1920, 1080)); // 4 headless mode

        } else if (browserName.equalsIgnoreCase("edge")) {
            // driver = new EdgeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            // driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }
}