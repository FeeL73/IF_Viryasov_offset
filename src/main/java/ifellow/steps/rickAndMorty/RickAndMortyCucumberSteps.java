package ifellow.steps.rickAndMorty;

import ifellow.utils.Props;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.aeonbits.owner.ConfigFactory;
import ifellow.dto.Character;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RickAndMortyCucumberSteps {
    final RickAndMortySteps rickAndMortySteps = new RickAndMortySteps();
    static Props props = ConfigFactory.create(Props.class);
    Character morty = new Character();
    Character character = new Character();

    @Когда("Получаем данные по персонажу")
    public void  getDataMorty() {
        this.morty = rickAndMortySteps.getCharacterByName(props.mortyCharacter_name());
    }

    @Тогда("Получаем последнего персонажа из последнего эпизода")
    public void  getLastCharacter() {
        String episodeUrl = this.morty.getEpisodeList().get(this.morty.getEpisodeList().size() - 1);
        this.character = rickAndMortySteps.getLastCharacterEpisode(RickAndMortySteps.extractIdFromUrl(episodeUrl));
    }

    @И("Сравниваем вид персонажей")
    public void  comparisonView() {
        assertEquals(this.morty.getSpecies(), this.character.getSpecies(),
                "Раса Морти и последнего персонажа должен совпадать.");
    }

    @И("Сравниваем локацию персонажей")
    public void  comparisonLocation() {
        assertNotEquals(this.morty.getLocation().getName(), this.character.getLocation().getName(),
                "Названия локаций Морти и последнего персонажа не должны совпадать.");

    }
}