package FruitTableDev;


import FruitTableDev.DTO.FruitDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static FruitTableDev.FruitPOM.FruitTable.compareLists;
import static FruitTableDev.FruitPOM.FruitTable.getTableData;

public class starting {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // temporary
        driver.get("https://rahulshettyacademy.com/upload-download-test/");

        // download
        driver.findElement(By.cssSelector("#downloadButton")).click();// OR driver.findElement(By.id("downloadButton")).click();
        //edit excel

        //upload , wait for success message and wait for disappear
        //upload files -> type=file
        WebElement upload = driver.findElement(By.cssSelector("#fileinput"));// OR driver.findElement(By.id("fileinput")).click();
        upload.sendKeys("C:/Users/Camacho/Downloads/download.xlsx"); // C:\Users\Camacho\Downloads

        // wait for "Updated Excel Data Successfully." to disappear //div[contains(text(),'Updated Excel Data Successfully.')]
        By uploadNotification = By.xpath("//div[contains(text(),'Updated Excel Data Successfully.')]");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(uploadNotification));
        String uploadNotificationText = driver.findElement(uploadNotification).getText();
        System.out.println("--------DEBUG---------- " + uploadNotificationText);
        Assert.assertEquals(uploadNotificationText, "Updated Excel Data Successfully.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(uploadNotification));

        System.out.println("Message Gone");
        // verify updated excel data showing in the web table

        List<FruitDTO> fruits = getTableData(driver);
        List<FruitDTO> expectedFruits = createFruitList();

        Assert.assertTrue(compareLists(expectedFruits, fruits));


        driver.close();

    }


    public static List<FruitDTO> createFruitList() {
        // array list to manipulate later
        return new ArrayList<>(List.of(
                new FruitDTO(1, "Mango", "Green", 100.5, "Summer"),
                new FruitDTO(2, "Apple", "Orange", 5, "Winter"),
                new FruitDTO(3, "Papaya", "Orange", 187, "Spring"),
                new FruitDTO(4, "Banana", "Yellow", 69, "All"),
                new FruitDTO(5, "Kivi", "Black", 1, "Never"),
                new FruitDTO(6, "Orange", "Orange", 199, "Summer")
        ));
    }

}
