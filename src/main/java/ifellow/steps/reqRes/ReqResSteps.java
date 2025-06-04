package ifellow.steps.reqRes;

import io.qameta.allure.Param;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import ifellow.api.ReqRes;
import java.util.Map;

import static io.qameta.allure.model.Parameter.Mode.MASKED;

import static org.hamcrest.core.IsEqual.equalTo;

public class ReqResSteps {
    private final ReqRes reqRes = new ReqRes();

    @Step("Создать пользователя с данными: {userData}")
    public Response createUser(Map<String, String> userData,
                               @Param(mode=MASKED) String apiKey,
                               String contentType) {
        return reqRes.postUser(userData, apiKey, contentType)
                .extract()
                .response();
    }

    @Step("Проверить код ответа: {expectedStatus}")
    public void verifyStatusCode(Response response, int expectedStatus) {
        response.then().statusCode(expectedStatus);
    }

    @Step("Проверить что имя пользователя: {expectedName}")
    public void verifyUserName(Response response, String expectedName) {
        response.then().body("name", equalTo(expectedName));
    }
    @Step("Проверить что должность пользователя: {expectedJob}")
    public void verifyUserJob(Response response, String expectedJob) {
        response.then().body("job", equalTo(expectedJob));
    }
}
