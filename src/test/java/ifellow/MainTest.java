package ifellow;

import ifellow.steps.*;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import ifellow.pages.utils.Props;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Управление проектами")
@Feature("Навигация")
public class MainTest extends WebHooks {
    ProjectTestStep projectTestStep = new ProjectTestStep();
    AuthStep authStep = new AuthStep();
    TaskCountStep taskCountStep = new TaskCountStep();
    HomeworkStep homeworkStep = new HomeworkStep();
    CreatedNewBugStep createdNewBugStep = new CreatedNewBugStep();

    @Test
    @DisplayName("1. Авторизация в https://edujira.ifellow.ru/")
    public void authSiteTest() {
        String username = Props.getProperty("username");
        String password = Props.getProperty("password");
        authStep.login(username, password);
        WebDriver driver = WebDriverRunner.getWebDriver();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(webDriver -> {
                    String currentUrl = webDriver.getCurrentUrl();
                    return currentUrl != null && currentUrl.contains("secure/Dashboard.jspa");
                });
        String currentUrl = driver.getCurrentUrl();
        assert currentUrl != null;
        assertTrue(currentUrl.contains("secure/Dashboard.jspa"),
                "URL после авторизации должен содержать 'secure/Dashboard.jspa'");
    }

    @Test
    @DisplayName("2. Перейти в проект 'TEST'")
    public void goProjectTest() {
        authSiteTest();
        projectTestStep.navigateToTestProject();
    }

    @Test
    @DisplayName("3. Проверить общее количество заведенных задач в проекте")
    public void checkTasksCountTest() {
        goProjectTest();
        taskCountStep.applyAllTasksFilter();
        int initialCount = taskCountStep.getCurrentTasksCount();
        int initialTaskCount = taskCountStep.waitForTasksCountChange(initialCount);
        taskCountStep.createNewTask("fonk Остров сокровищ");
        taskCountStep.applyAllTasksFilter();
        int finalCount = taskCountStep.waitForTasksCountChange(initialTaskCount);
        taskCountStep.verifyTasksCountIncreased(initialTaskCount, finalCount);
    }

    @Test
    @DisplayName("4. Проверить задачу TestSeleniumATHomework")
    public void homeworkCheckTest() {
        checkTasksCountTest();
        homeworkStep.searchForTask("TestSeleniumATHomework")
                .verifyTaskStatus("СДЕЛАТЬ")
                .verifyTaskVersion("Version 2.0");
    }

    @Test
    @DisplayName("5. Создать новый баг с описанием")
    @Description("Тест на создание нового бага с подробным описанием")
    public void createBugTest() {
        homeworkCheckTest();
        createdNewBugStep.createAndResolveBug("Ошибка","123","Highest","hh","gfg","TEST-121544","Доска Спринт 1");
    }
}
