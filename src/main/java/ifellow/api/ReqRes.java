package ifellow.api;

import io.restassured.response.ValidatableResponse;
import ifellow.ConfigReader;
import static io.restassured.RestAssured.given;
import java.util.Map;

public class ReqRes extends BaseApi {
    private static final String USERS_URN =  "/api/users";
    public ReqRes() {
        super(ConfigReader.getProp("reqres.base_url"));
    }
    public ValidatableResponse postUser(Map<String, String> userData, String apiKey, String contentType) {
        return given()
                .header("x-api-key", apiKey)
                .header("Content-Type", contentType)
                .when()
                .body(userData)
                .post(USERS_URN)
                .then();
    }
}