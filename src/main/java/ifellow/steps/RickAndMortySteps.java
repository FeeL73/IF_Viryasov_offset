package ifellow.steps;

import ifellow.api.RickAndMortyApi;
import lombok.var;
import org.apache.http.HttpStatus;
import ifellow.dto.Character;

public class RickAndMortySteps {

    private final RickAndMortyApi rickAndMortyApi = new RickAndMortyApi();

    public Character getCharacterName(String name) {
        return rickAndMortyApi.getCharacterName(name)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .jsonPath()
                .getObject("results[0]", Character.class);
    }

    public Character getLastCharacterEpisode(String id) {
        String url = getLastCharacterUrlEpisode(id);
        String characterId = extractIdFromUrl(url);
        return getCharacterById(characterId);
    }

    private String getLastCharacterUrlEpisode(String episodeId) {

        return rickAndMortyApi.getEpisodeId(episodeId)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .jsonPath()
                .getString("characters[-1]");
    }

    private Character getCharacterById(String id) {
        return rickAndMortyApi.getCharacterById(id)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(Character.class);
    }

    public static String extractIdFromUrl(String url) {
        var strings = url.split("/");
        return strings[strings.length-1];
    }
}