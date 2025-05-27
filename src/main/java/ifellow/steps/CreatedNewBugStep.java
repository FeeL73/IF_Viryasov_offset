package ifellow.steps;

import ifellow.pages.HomeworkPage;
import ifellow.pages.TaskPage;
import io.qameta.allure.Step;

public class CreatedNewBugStep {
    TaskPage taskPage = new TaskPage();
    HomeworkPage homeworkPage = new HomeworkPage();

    @Step("Создать задачу и разрешить баг с параметрами: {issueType}, {topic}, {priority}, {labels}, {description}, {taskValue}, {sprintValue}")
    public void createAndResolveBug(String issueType, String topic, String priority, String labels,
                                    String description, String taskValue, String sprintValue) {
        homeworkPage.clickCreateBug();
        taskPage.createAndResolveBug(issueType, topic, priority, labels, description, taskValue, sprintValue);
    }


}
