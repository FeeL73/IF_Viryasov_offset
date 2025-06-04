package ifellow.api;

import io.restassured.response.ValidatableResponse;
import ifellow.utils.Props;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;
import java.util.Map;

public class ReqRes extends BaseApi {
    static Props props = ConfigFactory.create(Props.class);
    private static final String USERS_URN =  "/api/users";

    public ReqRes() {
        super(props.reqresBase_url());
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