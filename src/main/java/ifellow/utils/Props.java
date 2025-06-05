
package ifellow.utils;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config.properties"})
public interface Props extends Config {
    @Key("appium_url")
    String appiumUrl();
}