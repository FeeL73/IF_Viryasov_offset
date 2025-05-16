
package ifellow.hooks;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import static com.codeborne.selenide.Selenide.open;
import ifellow.pages.utils.Props;

public class CucumHooks {

    @Before
    public void setUp() {
        Configuration.timeout = 10000;
        String baseUrl = Props.getProperty("baseUrl");
        open(baseUrl);
        WebDriver driver = WebDriverRunner.getWebDriver();
        driver.manage().window().maximize();
    }
    @After
    public void tearDown() {
        WebDriverRunner.closeWebDriver();
    }
}
