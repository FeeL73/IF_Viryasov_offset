package ifellow.utils;

import java.io.IOException;
import java.io.InputStream;

public class FileUtil {
    public static String readFileFromResources(String fileName) {
        InputStream inputStream = FileUtil.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new RuntimeException("Файл не найден: " + fileName);
        }
        try {
            return new String(inputStream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}