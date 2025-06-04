package ifellow.utils;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config.properties"})
public interface Props extends Config {
    @Key("morty.url")
    String mortyUrl();
    @Key("morty.character_name")
    String mortyCharacter_name();
    @Key("reqres.base_url")
    String reqresBase_url();
    @Key("reqres.apiKey")
    String reqresApiKey();
    @Key("reqres.contentType")
    String reqresContentType();
    @Key("reqres.name")
    String reqresName();
    @Key("reqres.job")
    String reqresJob();
}

