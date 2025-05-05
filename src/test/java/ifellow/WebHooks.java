package ifellow;

import io.github.bonigarcia.wdm.WebDriverManager;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static com.codeborne.selenide.Selenide.open;
import ifellow.pages.utils.Props;

public class WebHooks {

    @BeforeAll
    public static void beforeAll() {
    }

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        String baseUrl = Props.getProperty("baseUrl");
        open(baseUrl);
        WebDriver driver = WebDriverRunner.getWebDriver();
        driver.manage().window().maximize();
    }
}
