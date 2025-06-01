package ifellow;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.codeborne.selenide.WebDriverRunner;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static com.codeborne.selenide.Selenide.open;
import ifellow.pages.utils.Props;

public class WebHooks {
    static Props props = ConfigFactory.create(Props.class);

    @BeforeAll
    public static void beforeAll() {
        boolean allureScreenshots = props.allureScreenshots();
        boolean allureSavePageSource = props.allureSavePageSource();
        SelenideLogger.addListener("AllureSelenide",
                MaskedAllureSelenide.create(allureScreenshots, allureSavePageSource));
    }

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        String baseUrl = props.baseUrl();
        open(baseUrl);
        WebDriver driver = WebDriverRunner.getWebDriver();
        driver.manage().window().maximize();
    }
    @AfterEach
    public void tearDown() {
        WebDriverRunner.clearBrowserCache();
        WebDriverRunner.closeWebDriver();
    }
}