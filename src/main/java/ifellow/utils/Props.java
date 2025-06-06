package ifellow.utils;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config.properties"})
public interface Props extends Config {
    @Key("morty.url")
    String mortyUrl();
    @Key("morty.character_name")
    String mortyCharacter_name();
    @Key("morty.character")
    String character();
    @Key("morty.episode")
    String episode();

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
    @Key("reqres.urn")
    String reqresUrn();
    @Key("reqres.x-apiHeader")
    String xApiHeader();
    @Key("reqres.contentHeader")
    String contentTypeHeader();

}

