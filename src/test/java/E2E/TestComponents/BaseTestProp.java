package E2E.TestComponents;

import E2E_Shopping.POM.CartPage;
import E2E_Shopping.POM.CheckOutPage;
import E2E_Shopping.POM.LoginPage;
import E2E_Shopping.POM.ProductCatalog;
import FruitTableDev.FruitPOM.FruitTable;
import io.cucumber.core.internal.com.fasterxml.jackson.core.type.TypeReference;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTestProp {

    public WebDriver driver; // global variable
    public LoginPage loginPage;
    public ProductCatalog productCatalog;
    public CartPage cartPage;
    public CheckOutPage checkOutPage;
    public FruitTable fruitTable;

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

    @BeforeMethod
    public void launchApplication() throws IOException {
        driver = initializeDriver();
        loginPage = new LoginPage(driver);
        loginPage.goTo();
    }

    public void launchFruitApplication() throws IOException {
        driver = initializeDriver();
        fruitTable= new FruitTable(driver);
        fruitTable.goTo();
    }

    public void launchShoppingObjects() {
        productCatalog = new ProductCatalog(driver);
        cartPage = new CartPage(driver);
        checkOutPage = new CheckOutPage(driver);
    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png"));
        return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        //read json to string
        String jsonContent = FileUtils.readFileToString(new File(filePath),
                StandardCharsets.UTF_8);

        //String to HashMap => Jackson Datbind
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;
        //{map, map}
    }
}