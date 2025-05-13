package ifellow.steps;
import ifellow.pages.ProjectPage;
import ifellow.pages.TaskPage;
import ifellow.pages.utils.TestContext;
import io.cucumber.java.ru.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.jupiter.api.Assertions.*;

public class CheckTaskSteps {
    private static final Logger log = LoggerFactory.getLogger(CheckTaskSteps.class);
    private ProjectPage projectPage = new ProjectPage();
    private TaskPage taskPage = new TaskPage();
    private final TestContext context = TestContext.getInstance();

    @Когда("пользователь применяет фильтр {string}")
    public void applyFilter(String filterName) {
        projectPage = new ProjectPage();
        assertDoesNotThrow(() -> projectPage.applyFilterAndViewTasks(),
                "Не удалось применить фильтр и отобразить задачи");
    }
    @И("пользователь запоминает текущее количество задач")
    public void storeTaskCount() {
        int initialTaskCountMain = projectPage.getCurrentTaskCount();
        int initialTaskCount = projectPage.waitForTaskCountChange(initialTaskCountMain);
        context.setInitialTaskCount(initialTaskCount);
        log.debug("initialTaskCount {}", initialTaskCount);
    }
    @И("создает новую задачу с названием {string}")
    public void createNewTask(String taskName) {
        taskPage = new TaskPage();
        taskPage.createNewTask(taskName);
        projectPage.applyFilterAndViewTasks();
    }
    @Тогда("количество задач должно увеличиться на 1")
    public void verifyTaskCount() {
        int initialTaskCount = context.getInitialTaskCount();
        int finalTaskCount = projectPage.waitForTaskCountChange(initialTaskCount);
        log.debug("finalTaskCount  {}", finalTaskCount);
        log.info("Количество задач изменилось с {} на {}", initialTaskCount, finalTaskCount);
        assertTrue(finalTaskCount > initialTaskCount, "Количество задач не изменилось!");
    }
}