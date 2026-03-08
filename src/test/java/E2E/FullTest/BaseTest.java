package E2E.FullTest;

import E2E.TestComponents.BaseTestPropNG;
import E2E_Shopping.POM.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class BaseTest extends BaseTestPropNG {
    String productName = "ZARA COAT 3";

    @Test(dataProvider="getData",groups="Purchase")
    public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
        //loginPage from @BeforeMethod
        loginPage.loginApplication(input.get("email"), input.get("password"));

        // PRODUCT PAGE
        ProductCatalog productCatalog = new ProductCatalog(driver);
        productCatalog.addProductToCart(input.get("product"));
        productCatalog.clickCartHeader();

        //CART PAGE
        CartPage cartPage = new CartPage(driver);
        cartPage.VerifyProductDisplay(input.get("product"));


        // confirmation page (CHECKOUT PAGE)
        CheckOutPage checkOutPage = cartPage.goToCheckout();
        checkOutPage.selectCountry("portugal");
        ConfirmationPage confirmationPage = checkOutPage.submitOrder();

        String message = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void OrderHistoryTest() {
        loginPage.loginApplication("dankamacho@hotmail.com", "1Danielcamacho!");
        OrdersPage ordersPage = new OrdersPage(driver);
        ordersPage.goToOrdersPage();
        Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        // src/main/java/E2E_Shopping/resources/reports
        List<HashMap<String, String>> jsonDataToMap = getJsonDataToMap(System.getProperty("user.dir")+"/src/test/resources/TestData/PurchaseOrder.json"); // Lista com 2 elementos:
                                                                                                                                                        // index 0 → HashMap do primeiro cenário <chave,valor>
                                                                                                                                                        // index 1 → HashMap do segundo cenário <chave,valor>
        return new Object[][] {{jsonDataToMap.get(0)},{jsonDataToMap.get(1)}}; // 2 cenarios
    }

    // Object[][] = [test scenario] [0= HashMap] -> Object["cenario"][0].get(chave) = valor
    // ex:
    // DINAMIC DATAPROVIDER :
    //    Object[][] result = new Object[data.size()][1];
    //    for (int i = 0; i < data.size(); i++) {
    //        result[i][0] = data.get(i);
    //    }
    //    return result;
    //}
}