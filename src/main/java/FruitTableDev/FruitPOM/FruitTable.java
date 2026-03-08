package FruitTableDev.FruitPOM;

import E2E_Shopping.AbstractComponents.AbstractComponent;
import E2E_Shopping.POM.LoginPage;
import FruitTableDev.DTO.FruitDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FruitTable extends AbstractComponent {
    WebDriver driver;

    public FruitTable(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#downloadButton")
    private WebElement downloadButton;
    @FindBy(css = "#fileinput")
    private WebElement upload;

    // @FindBy(css=".sc-jsEeTM.itluUR.rdt_TableRow")
    // private List<WebElement> tableRows;
    //@FindBy(css=".cJTPDY.rdt_TableCell")
    //private List<WebElement> tableCols;

    By uploadNotification = By.xpath("//div[contains(text(),'Updated Excel Data Successfully.')]");
    static By tableRows = By.cssSelector(".sc-jsEeTM.itluUR.rdt_TableRow");
    static By tableCols = By.cssSelector(".cJTPDY.rdt_TableCell");

    public void goTo() throws IOException {
        driver.get("https://rahulshettyacademy.com/upload-download-test/");
    }

    public void clickDownload() {
        downloadButton.click();
    }

    public void uploadXml(String path) {
        upload.sendKeys(path);
    }

    public void checkUploadNotification() {
        waitForWebElementToAppear(uploadNotification);
        Assert.assertEquals(driver.findElement(uploadNotification).getText(), "Updated Excel Data Successfully.");
        waitForElementToDisappear(uploadNotification);
    }

    public static List<FruitDTO> getTableData(WebDriver driver) {
        List<WebElement> rows = driver.findElements(tableRows);
        List<FruitDTO> fruits = new ArrayList<>();

        for (WebElement row : rows) {
            List<WebElement> cols = row.findElements(tableCols); //

            FruitDTO fruit = new FruitDTO(
                    Integer.parseInt(cols.get(0).getText().trim()),
                    cols.get(1).getText().trim(),
                    cols.get(2).getText().trim(),
                    Double.parseDouble(cols.get(3).getText().trim()),
                    cols.get(4).getText().trim()
            );
            fruits.add(fruit);
        }
        return fruits;
    }

    public static boolean compareLists(List<FruitDTO> expected, List<FruitDTO> actual) {
        if (expected.size() != actual.size()) return false;

        // sort w serialNumber
        expected.sort(Comparator.comparing(FruitDTO::getSerialNumber));
        actual.sort(Comparator.comparing(FruitDTO::getSerialNumber));

        for (int i = 0; i < expected.size(); i++) {
            FruitDTO e = expected.get(i);
            FruitDTO a = actual.get(i);

            if (e.getSerialNumber() != a.getSerialNumber()) return false;
            if (!e.getName().equalsIgnoreCase(a.getName())) return false;
            if (!e.getColor().equalsIgnoreCase(a.getColor())) return false;
            if (e.getPrice() != a.getPrice()) return false;
            if (!e.getSeason().equalsIgnoreCase(a.getSeason())) return false;
        }
        return true;
    }
}
