package ifellow.api;
import io.restassured.RestAssured;

public abstract class BaseApi {

    public BaseApi(String baseUrl) {
        RestAssured.requestSpecification = Specification.baseRequestSpec(baseUrl);
        RestAssured.responseSpecification = Specification.baseResponseSpecSuccess();
    }
}