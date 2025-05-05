package ifellow.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.visible;

public class AuthPage {
    private SelenideElement loginButton = $x("//a[@class='aui-nav-link login-link']").as("Кнопка для открытия формы авторизации");
    private SelenideElement loginField = $x("//input[@id='login-form-username']").as("Поле ввода логина");
    private SelenideElement passwordField = $x("//input[@id='login-form-password']").as("Поле ввода пароля");
    private SelenideElement submitButton = $x("//input[@id='login-form-submit']").as("Кнопка входа");

    public AuthPage login(String username, String password) {
        loginButton.shouldBe(visible).click();
        loginField.shouldBe(visible).sendKeys(username);
        passwordField.shouldBe(visible).sendKeys(password);
        submitButton.shouldBe(visible).click();
        return this;
    }
}
