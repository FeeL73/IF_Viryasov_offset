package ifellow.pages;

import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.sleep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectPage {
    private static final Logger log = LoggerFactory.getLogger(ProjectPage.class);

    private final SelenideElement filterProject = $x("//button[@id='subnav-trigger']").as("Переключить фильтр");
    private final SelenideElement filterProjectAllTask = $x("//a[text()='Все задачи']").as("Все задачи в фильтре");
    private final SelenideElement taskCounter = $x("//div[@class='showing']//span").as("Количество задач");
    private final SelenideElement inputSearch = $x("//input[@id='quickSearchInput']").as("Поле 'Поиск' на странице");

    public void applyFilterAndViewTasks() {
        filterProject.shouldBe(visible).click();
        filterProjectAllTask
                .shouldBe(visible, Duration.ofSeconds(10))
                .shouldBe(enabled, Duration.ofSeconds(10))
                .click();
    }

    public int getCurrentTaskCount() {
        String countText = taskCounter.shouldBe(visible).getText();
        return Integer.parseInt(countText.split(" из ")[1]);

    }

    public int waitForTaskCountChange(int initialTaskCount) {
        int currentTaskCountFunc = 0;
        String currentTaskCountText;
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 10000) {
            try {
                currentTaskCountText = $x("//div[@class='showing']//span").getText();
                currentTaskCountFunc = Integer.parseInt(currentTaskCountText.split(" из ")[1]);
                log.debug("waitForTaskCountChange() currentTaskCountOld:  {}", initialTaskCount);
                if (currentTaskCountFunc != initialTaskCount) {
                    log.debug("Количество задач изменилось с \" + initialTaskCount + \" на  {}", currentTaskCountFunc);
                    return currentTaskCountFunc;
                }
            } catch (Exception e) {
                log.debug("Не получили значение, повторим...");
            }
            sleep(1000);
        }
        return currentTaskCountFunc;
    }

    public void searchForTask(String taskName) {
        inputSearch.shouldBe(visible).setValue(taskName).pressEnter();
    }
}