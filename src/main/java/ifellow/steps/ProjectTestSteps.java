package ifellow.steps;

import ifellow.pages.*;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProjectTestSteps {
    private DashboardPage dashboardPage;
    @Когда("пользователь переходит в проект TEST")
    public void goProjectTest() {
        dashboardPage = new DashboardPage();
        assertDoesNotThrow(() -> dashboardPage.goToProject(),
                "Не удалось перейти в проект Test");
    }
    @Тогда("пользователь находится в проекте TEST")
    public void checkGoProjectTest() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("projects/TEST"),
                "Ожидалось, что URL будет содержать 'projects/TEST', но было: " + currentUrl);
    }
}