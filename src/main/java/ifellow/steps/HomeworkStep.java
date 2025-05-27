package ifellow.steps;

import ifellow.pages.HomeworkPage;
import ifellow.pages.ProjectPage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

public class HomeworkStep {
    private final HomeworkPage homeworkPage = new HomeworkPage();
    private final ProjectPage projectPage = new ProjectPage();

    @Step("Поиск задачи '{taskName}'")
    public HomeworkStep searchForTask(String taskName) {
        projectPage.searchForTask(taskName);
        return this;
    }

    @Step("Проверить статус задачи")
    public HomeworkStep verifyTaskStatus(String expectedStatus) {
        String actualStatus = homeworkPage.getStatusText();
        Assertions.assertEquals(expectedStatus, actualStatus,
                "Статус задачи не соответствует ожидаемому");
        return this;
    }

    @Step("Проверить версию задачи")
    public void verifyTaskVersion(String expectedVersion) {
        String actualVersion = homeworkPage.getVersionText();
        Assertions.assertEquals(expectedVersion, actualVersion,
                "Версия задачи не соответствует ожидаемому");
    }
}