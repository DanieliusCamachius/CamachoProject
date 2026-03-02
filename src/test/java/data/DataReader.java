package data;

import E2E.TestComponents.BaseTestProp;
import io.cucumber.core.internal.com.fasterxml.jackson.core.type.TypeReference;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class DataReader {
    // deprecated, now in BaseTestProp
    BaseTestProp baseTestProp;

    // public List<HashMap<String, String>> getJsonDataToMap() throws IOException
    // {
    //     //read json to string
    //     String jsonContent = 	FileUtils.readFileToString(new File(System.getProperty("user.dir")+"/src/test/java/data/PurchaseOrder.json"),
    //             StandardCharsets.UTF_8);
    //     //String to HashMap => Jackson Datbind
    //     ObjectMapper mapper = new ObjectMapper();
    //     List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
    //     });
    //     return data;
    //     //{map, map}
    // }
}
