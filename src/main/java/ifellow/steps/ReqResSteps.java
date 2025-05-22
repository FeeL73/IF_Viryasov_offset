package ifellow.steps;

import io.restassured.response.Response;
import ifellow.api.ReqRes;
import java.util.Map;

public class ReqResSteps {
    private final ReqRes reqRes = new ReqRes();

    public Response createUser(Map<String, String> userData, String apiKey, String contentType) {
        return reqRes.postUser(userData, apiKey, contentType)
                .extract()
                .response();
    }
}
