package ifellow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import lombok.var;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ifellow.steps.ReqResSteps;
import ifellow.utils.FileUtil;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;

public class ReqResTest {

    @Test
    @DisplayName("Создание пользователя и проверка ответа")
    void reqResApiTest() throws JsonProcessingException {
        Map<String, String> userData = new ObjectMapper().readValue(
                FileUtil.readFileFromResources("user.json"), HashMap.class);

        userData.put("name", "Tomato");
        userData.put("job", "Eat maket");

        var reqResSteps = new ReqResSteps();
        String apiKey = ConfigReader.getProp("reqres.apiKey");
        String contentType = ConfigReader.getProp("reqres.contentType");
        Response response = reqResSteps.createUser(userData, apiKey, contentType);

        var  expectedName = ConfigReader.getProp("reqres.name");
        var  expectedJob = ConfigReader.getProp("reqres.job");
        response.then().statusCode(HttpStatus.SC_CREATED);
        response.then().body("name", equalTo(expectedName));
        response.then().body("job", equalTo(expectedJob));
    }
}