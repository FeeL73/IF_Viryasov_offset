package ifellow;

import ifellow.pages.*;
import org.junit.jupiter.api.*;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import ifellow.pages.utils.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest extends WebHooks {
    private static final Logger log = LoggerFactory.getLogger(ProjectPage.class);
    private AuthPage authPage;
    private DashboardPage dashboardPage;
    private ProjectPage projectPage;
    private TaskPage taskPage;

    HomeworkPage homeworkPage = new HomeworkPage();

    @Test
    @DisplayName("1. Авторизация в https://edujira.ifellow.ru/")
    public void authSiteTest() {
        String username = Props.getProperty("username");
        String password = Props.getProperty("password");
        authPage = new AuthPage();
        authPage.login(username, password);
        WebDriver driver = WebDriverRunner.getWebDriver();
        assertTrue(driver.getCurrentUrl().contains("secure/Dashboard.jspa"),
                "URL после авторизации должен содержать 'secure/Dashboard.jspa'");
        log.info("Успешная авторизация пользователя: {}", username);
    }

    @Test
    @DisplayName("2. Перейти в проект Test")
    public void goProjectTest() {
        authSiteTest();
        dashboardPage = new DashboardPage();
        assertDoesNotThrow(() -> dashboardPage.goToProject(),
                "Не удалось перейти в проект Test");
        log.info("Успешный переход в проект Test");
    }

    @Test
    @DisplayName("3. Проверить общее количество заведенных задач в проекте")
    public void chechCountTaskTest() {
        goProjectTest();
        projectPage = new ProjectPage();
        assertDoesNotThrow(() -> projectPage.applyFilterAndViewTasks(),
                "Не удалось применить фильтр и отобразить задачи");
        int initialTaskCountMain = projectPage.getCurrentTaskCount();
        int initialTaskCount = projectPage.waitForTaskCountChange(initialTaskCountMain);
        log.debug("initialTaskCount {}", initialTaskCount);
        taskPage = new TaskPage();
        taskPage.createNewTask("fonk Остров сокровищ");
        projectPage.applyFilterAndViewTasks();
        int finalTaskCount = projectPage.waitForTaskCountChange(initialTaskCount);
        log.debug("finalTaskCount  {}", finalTaskCount);
        log.info("Количество задач изменилось с {} на {}", initialTaskCount, finalTaskCount);
        assertTrue(finalTaskCount > initialTaskCount, "Количество задач не изменилось!");
    }

    @Test
    @DisplayName("4. Перейти в задачу TestSeleniumATHomework и проверить")
    public void homeworkCheckTest(){
        chechCountTaskTest();
        projectPage.searchForTask("TestSeleniumATHomework");
        String taskStatus = homeworkPage.getTaskStatus();
        String taskVersion = homeworkPage.getTaskVersion();
        Assertions.assertEquals("СДЕЛАТЬ", taskStatus, "Статус задачи не соответствует ожидаемому");
        Assertions.assertEquals("Version 2.0", taskVersion, "Версия задачи не соответствует ожидаемому");
    }

    @Test
    @DisplayName("5. Создать новый баг с описанием")
    public void createBugTest(){
        homeworkCheckTest();
        homeworkPage.createBug();
        taskPage.createAndResolveBug("123","123","Highest","hh","gfg","TEST-121544","Доска Спринт 1");
    }

    @AfterEach
    public void tearDown() {
        log.debug("Тест завершен");
    }
}
