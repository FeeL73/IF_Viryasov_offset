package ifellow;

import lombok.var;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ifellow.dto.Character;
import ifellow.steps.RickAndMortySteps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RickAndMortyTest {
    @Test
    @DisplayName("Проверка данных по местонахождению и расе персонажа")
    void rickAndMortyApiTest() {
        var rickAndMortySteps = new RickAndMortySteps();
        Character morty = rickAndMortySteps.getCharacterName(ConfigReader.getProp("morty.character_name"));
        String episodeUrl = morty.getEpisodeList().get(morty.getEpisodeList().size() - 1);
        Character character = rickAndMortySteps.getLastCharacterEpisode(RickAndMortySteps.extractIdFromUrl(episodeUrl));
        assertEquals(morty.getSpecies(), character.getSpecies(), "Раса Морти и последнего персонажа должен совпадать.");
        assertNotEquals(morty.getLocation().getName(), character.getLocation().getName(), "Названия локаций Морти и последнего персонажа не должны совпадать.");
    }
}