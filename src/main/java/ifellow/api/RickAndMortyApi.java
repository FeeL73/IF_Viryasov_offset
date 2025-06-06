package ifellow.api;

import io.restassured.response.ValidatableResponse;
import ifellow.utils.Props;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;

public class RickAndMortyApi extends BaseApi {
    static Props props = ConfigFactory.create(Props.class);
    String CHARACTER = props.character();
    String EPISODE = props.episode();

    public RickAndMortyApi() {
        super(props.mortyUrl());
    }

    private ValidatableResponse sendGetRequest(String endpoint, String paramName, String paramValue) {
        return given()
                .when()
                .queryParam(paramName, paramValue)
                .get(endpoint)
                .then();
    }

    private ValidatableResponse sendGetRequestById(String endpoint, String id) {
        return given()
                .when()
                .get(String.format("%s/%s", endpoint, id))
                .then();
    }

    public ValidatableResponse getCharacterName(String name) {
        return sendGetRequest(CHARACTER, "name", name);
    }

    public ValidatableResponse getCharacterById(String id) {
        return sendGetRequestById(CHARACTER, id);
    }

    public ValidatableResponse getEpisodeId(String id) {
        return sendGetRequestById(EPISODE, id);
    }
}
