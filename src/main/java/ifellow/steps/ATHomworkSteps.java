package ifellow.steps;

import ifellow.pages.HomeworkPage;
import ifellow.pages.ProjectPage;
import io.cucumber.java.ru.*;
import org.junit.jupiter.api.Assertions;

public class ATHomworkSteps {
    private ProjectPage projectPage = new ProjectPage();
    private HomeworkPage homeworkPage = new HomeworkPage();
    @Когда("пользователь переходит в задачу {string}")
    public void checkNameTask(String nameTask) {
        projectPage.searchForTask(nameTask);
    }

    @Тогда("статус задачи равен {string}")
    public void checkNameStatus(String nameStatus) {
        String taskStatus = homeworkPage.getTaskStatus();
        Assertions.assertEquals(nameStatus, taskStatus, "Статус задачи не соответствует ожидаемому");
    }
    @И("версия задачи равна {string}")
    public void checkNameVersion(String nameVersion) {
        String taskVersion = homeworkPage.getTaskVersion();
        Assertions.assertEquals(nameVersion, taskVersion, "Версия задачи не соответствует ожидаемому");
    }

}
