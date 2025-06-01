package ifellow.steps;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Param;
import io.qameta.allure.Step;
import ifellow.pages.AuthPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static io.qameta.allure.model.Parameter.Mode.HIDDEN;
import static io.qameta.allure.model.Parameter.Mode.MASKED;


public class AuthStep {

    public final AuthPage authPage = new AuthPage();

    @Step("Открываем форму авторизации")
    public void openLoginForm() {
        authPage.openLoginForm();
    }

    @Step("Вводим логин: {username}")
    public void inputLogin(String username) {
        authPage.enterUsername(username);
    }

    @Step("Вводим пароль: ******")
    public void inputPassword(@Param(mode=MASKED) String password) {
        authPage.enterPassword(password);
    }

    @Step("Нажимаем кнопку 'Войти'")
    public void clickLoginButton() {
        authPage.clickLoginButton();
    }

    @Step("Авторизация с логином: {username} и паролем ****")
    public void login(String username, @Param(mode=HIDDEN) String password) {
        openLoginForm();
        inputLogin(username);
        inputPassword(password);
        clickLoginButton();
    }

    @Step("Проверяем, что URL после авторизации содержит: {expectedUrlSubstring}")
    public void verifyUrlAfterLogin(String expectedUrlSubstring) {
        WebDriver driver = WebDriverRunner.getWebDriver();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(webDriver -> {
                    String currentUrl = webDriver.getCurrentUrl();
                    return currentUrl != null && currentUrl.contains(expectedUrlSubstring);
                });
        String currentUrl = driver.getCurrentUrl();
        assert currentUrl != null;
        assert currentUrl.contains(expectedUrlSubstring) :
                "URL после авторизации должен содержать '" + expectedUrlSubstring + "'";
    }
}
