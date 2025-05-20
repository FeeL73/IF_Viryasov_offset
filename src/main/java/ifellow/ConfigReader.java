package ifellow;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties props = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getResourceAsStream("/config.properties")) {
            props.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка конфига", e);
        }
    }

    public static String getProp(String propertyString) {
        return props.getProperty(propertyString);
    }
}