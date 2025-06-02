package ifellow.steps;

import ifellow.pages.HomeworkPage;
import ifellow.pages.TaskPage;
import ifellow.utils.Props;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;

public class CreatedNewBugStep {
    static Props props = ConfigFactory.create(Props.class);
    TaskPage taskPage = new TaskPage();
    HomeworkPage homeworkPage = new HomeworkPage();

    String createBugIssueType = props.createBugIssueType();
    String createBugTopic = props.createBugTopic();
    String createBugPriority = props.createBugPriority();
    String createBugLabels = props.createBugLabels();
    String createBugDescription = props.createBugDescription();
    String createBugTaskValue = props.createBugTaskValue();
    String createBugSprintValue = props.createBugSprintValue();

    @Step("Создаем Баг")
    public void createAndResolveBug() {
        homeworkPage.clickCreateBug();
        taskPage.createAndResolveBug(createBugIssueType,
                createBugTopic,
                createBugPriority,
                createBugLabels,
                createBugDescription,
                createBugTaskValue,
                createBugSprintValue);
    }
}
