package ifellow.pages.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Props {

    private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";

    private static Properties properties = new Properties();

    static {
        try (FileInputStream input = new FileInputStream(CONFIG_FILE_PATH)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
