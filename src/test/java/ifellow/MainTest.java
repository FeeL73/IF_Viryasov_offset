package ifellow;

import ifellow.steps.*;
import io.qameta.allure.*;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;
import ifellow.pages.utils.Props;


@Epic("Управление проектами")
@Feature("Навигация")
public class MainTest extends WebHooks {
    static Props props = ConfigFactory.create(Props.class);

    ProjectTestStep projectTestStep = new ProjectTestStep();
    AuthStep authStep = new AuthStep();
    TaskCountStep taskCountStep = new TaskCountStep();
    HomeworkStep homeworkStep = new HomeworkStep();
    CreatedNewBugStep createdNewBugStep = new CreatedNewBugStep();

    @Test
    @DisplayName("1. Авторизация в https://edujira.ifellow.ru/")
    public void authSiteTest() {
        String username = props.username();
        String password = props.password();
        authStep.login(username, password);
        authStep.verifyUrlAfterLogin("secure/Dashboard.jspa");
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
