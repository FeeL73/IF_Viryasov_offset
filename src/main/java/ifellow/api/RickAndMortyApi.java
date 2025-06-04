package ifellow.api;

import io.restassured.response.ValidatableResponse;
import ifellow.utils.Props;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;

public class RickAndMortyApi extends BaseApi {
    static Props props = ConfigFactory.create(Props.class);
    private static final String CHARACTER =  "/api/character";
    private static final String EPISODE =  "/api/episode";
    public RickAndMortyApi() {
        super(props.mortyUrl());
    }
    public ValidatableResponse getCharacterName(String name) {
        return given()
                .when()
                .queryParam("name", name)
                .get(CHARACTER)
                .then();
    }
    public ValidatableResponse getCharacterById(String id) {
        return given()
                .when()
                .get(String.format("%s/%s", CHARACTER, id))
                .then();
    }
    public ValidatableResponse getEpisodeId(String id) {
        return given()
                .when()
                .get(String.format("%s/%s", EPISODE, id))
                .then();
    }
}