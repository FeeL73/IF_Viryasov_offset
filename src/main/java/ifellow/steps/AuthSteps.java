package ifellow.steps;

import com.codeborne.selenide.WebDriverRunner;
import ifellow.pages.AuthPage;
import ifellow.pages.utils.Props;
import io.cucumber.java.ru.*;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthSteps {
    @Дано("пользователь авторизовался в EduJira")
    @Когда("пользователь авторизовался с корректными данными")
    public void userLogin() {
        String username = Props.getProperty("username");
        String password = Props.getProperty("password");
        AuthPage authPage = new AuthPage();
        authPage.login(username, password);
    }
    @Тогда("отображается начальная страница")
    public void checkPageLogin() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        assertTrue(driver.getCurrentUrl().contains("secure/Dashboard.jspa"),
                "URL после авторизации должен содержать 'secure/Dashboard.jspa'");
    }
}