package ifellow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import lombok.var;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ifellow.steps.ReqResSteps;
import ifellow.utils.FileUtil;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReqResTest {

    @Test
    @DisplayName("Create user and check response")
    void reqResApiTest() throws JsonProcessingException {
        Map<String, String> userData = new ObjectMapper().readValue(
                FileUtil.readFileFromResources("user.json"), HashMap.class);

        userData.put("name", "Tomato");
        userData.put("job", "Eat maket");

        var reqResSteps = new ReqResSteps();
        String apiKey = ConfigReader.getProp("reqres.apiKey");
        String contentType = ConfigReader.getProp("reqres.contentType");
        Response response = reqResSteps.createUser(userData, apiKey, contentType);

        Map<String, String> responseData = response.jsonPath().get();

        var  expectedStatusCode = Integer.parseInt(ConfigReader.getProp("reqres.expected_status_code"));
        var  expectedName = ConfigReader.getProp("reqres.name");
        var  expectedJob = ConfigReader.getProp("reqres.job");

        assertEquals(expectedStatusCode, response.getStatusCode(), "Status code should be " + expectedStatusCode);
        assertEquals(expectedName, responseData.get("name"), "Name should be " + expectedName);
        assertEquals(expectedJob, responseData.get("job"), "Job should be " + expectedJob);
    }
}