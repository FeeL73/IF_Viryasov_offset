package ifellow.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class AuthPage {
    private final SelenideElement loginButton = $x("//a[@class='aui-nav-link login-link']").as("Кнопка для открытия формы авторизации");
    private final SelenideElement loginField = $x("//input[@id='login-form-username']").as("Поле ввода логина");
    private final SelenideElement passwordField = $x("//input[@id='login-form-password']").as("Поле ввода пароля");
    private final SelenideElement submitButton = $x("//input[@id='login-form-submit']").as("Кнопка входа");

    public void openLoginForm() {
        loginButton.shouldBe(visible).click();
    }

    public void enterUsername(String username) {
        loginField.shouldBe(visible).sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.shouldBe(visible).sendKeys(password);
    }

    public void clickLoginButton() {
        submitButton.shouldBe(visible).click();
    }

}
