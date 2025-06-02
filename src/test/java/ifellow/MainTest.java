package ifellow;

import ifellow.steps.*;
import io.qameta.allure.*;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;
import ifellow.utils.Props;

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
    @DisplayName("1. Авторизация")
    public void authSiteTest() {
        String username = props.username();
        String password = props.password();
        String expectedUrlSubstring = props.expectedUrlSubstring();
        authStep.login(username, password);
        authStep.verifyUrlAfterLogin(expectedUrlSubstring);
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
        String taskName = props.taskName();
        goProjectTest();
        taskCountStep.applyAllTasksFilter();
        int initialCount = taskCountStep.getCurrentTasksCount();
        int initialTaskCount = taskCountStep.waitForTasksCountChange(initialCount);
        taskCountStep.createNewTask(taskName);
        taskCountStep.applyAllTasksFilter();
        int finalCount = taskCountStep.waitForTasksCountChange(initialTaskCount);
        taskCountStep.verifyTasksCountIncreased(initialTaskCount, finalCount);
    }

    @Test
    @DisplayName("4. Проверить задачу TestSeleniumATHomework")
    public void homeworkCheckTest() {
        String homeTaskName = props.homeTaskName();
        String expectedStatus = props.expectedStatus();
        String expectedVersion = props.expectedVersion();
        checkTasksCountTest();
        homeworkStep.searchForTask(homeTaskName)
                .verifyTaskStatus(expectedStatus)
                .verifyTaskVersion(expectedVersion);
    }

    @Test
    @DisplayName("5. Создать новый баг с описанием")
    @Description("Тест на создание нового бага с подробным описанием")
    public void createBugTest() {
        String createBugIssueType = props.createBugIssueType();
        String createBugTopic = props.createBugTopic();
        String createBugPriority = props.createBugPriority();
        String createBugLabels = props.createBugLabels();
        String createBugDescription = props.createBugDescription();
        String createBugTaskValue = props.createBugTaskValue();
        String createBugSprintValue = props.createBugSprintValue();
        homeworkCheckTest();
        createdNewBugStep.createAndResolveBug(createBugIssueType,
                createBugTopic,
                createBugPriority,
                createBugLabels,
                createBugDescription,
                createBugTaskValue,
                createBugSprintValue);
    }
}
