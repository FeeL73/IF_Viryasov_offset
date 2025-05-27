package ifellow.steps;

import ifellow.pages.ProjectPage;
import ifellow.pages.TaskPage;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class TaskCountStep {
    private static final Logger log = LoggerFactory.getLogger(TaskCountStep.class);
    private final ProjectPage projectPage = new ProjectPage();
    private final TaskPage taskPage = new TaskPage();

    @Step("Применить фильтр 'Все задачи'")
    public void applyAllTasksFilter() {
        projectPage.applyFilterAndViewTasks();
    }

    @Step("Получить текущее количество задач")
    public int getCurrentTasksCount() {
        int count = projectPage.getCurrentTaskCount();
        log.debug("Текущее количество задач: {}", count);
        return count;
    }

    @Step("Дождаться изменения количества задач")
    public int waitForTasksCountChange(int initialCount) {
        int newCount = projectPage.waitForTaskCountChange(initialCount);
        log.debug("Новое количество задач: {}", newCount);
        return newCount;
    }

    @Step("Создать новую задачу '{taskName}'")
    public void createNewTask(String taskName) {
        taskPage.createNewTask(taskName);
    }

    @Step("Проверить, что количество задач увеличилось")
    public void verifyTasksCountIncreased(int initialCount, int finalCount) {
        log.info("Количество задач изменилось с {} на {}", initialCount, finalCount);
        assertTrue(String.format("Количество задач не изменилось (было: %d, стало: %d)", initialCount, finalCount),
                finalCount > initialCount);
    }
}