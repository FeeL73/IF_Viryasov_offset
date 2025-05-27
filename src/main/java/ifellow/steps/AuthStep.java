package ifellow.steps;

import io.qameta.allure.Step;
import ifellow.pages.AuthPage;

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
    public void inputPassword(String password) {
        authPage.enterPassword(password);
    }

    @Step("Нажимаем кнопку 'Войти'")
    public void clickLoginButton() {
        authPage.clickLoginButton();
    }

    @Step("Авторизация с логином: {username} и паролем ******")
    public void login(String username, String password) {
        openLoginForm();
        inputLogin(username);
        inputPassword(password);
        clickLoginButton();
    }
}
