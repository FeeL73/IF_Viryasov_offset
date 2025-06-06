package ifellow.steps.rickAndMorty;

import ifellow.api.RickAndMortyApi;
import ifellow.dto.Character;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;

public class RickAndMortySteps {
    private final RickAndMortyApi rickAndMortyApi = new RickAndMortyApi();

    @Step("Получаем персонажа {name}")
    public Character getCharacterByName(String name) {
        return extractResponseBody(
                rickAndMortyApi.getCharacterName(name),
                "results[0]",
                Character.class
        );
    }

    @Step("Получаем последнего персонажа в эпизоде с ID: {id}")
    public Character getLastCharacterEpisode(String id) {
        String url = getLastCharacterUrlFromEpisode(id);
        String characterId = extractIdFromUrl(url);
        return getCharacterById(characterId);
    }

    @Step("Получаем URL последнего персонажа по ID эпизода {episodeId}")
    private String getLastCharacterUrlFromEpisode(String episodeId) {
        return extractResponseBody(
                rickAndMortyApi.getEpisodeId(episodeId),
                "characters[-1]",
                String.class
        );
    }

    @Step("Получаем параметры персонажа с ID {id}")
    private Character getCharacterById(String id) {
        return extractResponseBody(
                rickAndMortyApi.getCharacterById(id),
                Character.class
        );
    }

    @Step("Извлекаем ID из URL {url}")
    public static String extractIdFromUrl(String url) {
        return url.substring(url.lastIndexOf('/') + 1);
    }

    private <T> T extractResponseBody(ValidatableResponse response, String jsonPath, Class<T> type) {
        return response
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .jsonPath()
                .getObject(jsonPath, type);
    }

    private <T> T extractResponseBody(ValidatableResponse response, Class<T> type) {
        return extractResponseBody(response, "", type);
    }
}