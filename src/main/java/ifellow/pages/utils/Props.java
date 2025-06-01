
package ifellow.pages.utils;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config.properties"})
public interface Props extends Config {

    @Key("baseUrl")
    String baseUrl();

    @Key("username")
    String username();

    @Key("password")
    String password();

    @Key("allure.screenshots")
    @DefaultValue("false")
    boolean allureScreenshots();

    @Key("allure.savePageSource")
    @DefaultValue("false")
    boolean allureSavePageSource();
}