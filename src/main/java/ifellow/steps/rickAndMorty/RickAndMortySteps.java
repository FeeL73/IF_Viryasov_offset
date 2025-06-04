package ifellow.steps.rickAndMorty;

import ifellow.api.RickAndMortyApi;

import io.qameta.allure.Step;

import lombok.var;
import org.apache.http.HttpStatus;
import ifellow.dto.Character;

public class RickAndMortySteps{
    private final RickAndMortyApi rickAndMortyApi = new RickAndMortyApi();
    @Step("Получаем персонажа {name}")
    public Character getCharacterName(String name) {
        return rickAndMortyApi.getCharacterName(name)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .jsonPath()
                .getObject("results[0]", Character.class);
    }

    @Step("Получаем последнего персонажа в эпизоде с ID: {id}")
    public Character getLastCharacterEpisode(String id) {
        String url = getLastCharacterUrlEpisode(id);
        String characterId = extractIdFromUrl(url);
        return getCharacterById(characterId);
    }

    @Step("Получаем URL последнего персонажа по ID эпизода {episodeId}")
    private String getLastCharacterUrlEpisode(String episodeId) {
        return rickAndMortyApi.getEpisodeId(episodeId)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .jsonPath()
                .getString("characters[-1]");
    }

    @Step("Получаем параметры Персонажа с ID {id}")
    private Character getCharacterById(String id) {
        return rickAndMortyApi.getCharacterById(id)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(Character.class);
    }

    @Step("Получаем значение ID из URL {url}")
    public static String extractIdFromUrl(String url) {
        var strings = url.split("/");
        return strings[strings.length-1];
    }
}