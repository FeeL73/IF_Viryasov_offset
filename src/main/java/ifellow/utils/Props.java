
package ifellow.utils;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config.properties"})
public interface Props extends Config {
    @Key("baseUrl")
    String baseUrl();
    @Key("username")
    String username();
    @Key("password")
    String password();
    @Key("allure.screenshots")
    @DefaultValue("false")
    boolean allureScreenshots();
    @Key("allure.savePageSource")
    @DefaultValue("false")
    boolean allureSavePageSource();
    @Key("expectedUrlSubstring")
    String expectedUrlSubstring();
    @Key("taskName")
    String taskName();
    @Key("homeworkStep.taskName")
    String homeTaskName();
    @Key("homeworkStep.expectedStatus")
    String expectedStatus();
    @Key("homeworkStep.expectedVersion")
    String expectedVersion();
    @Key("createBug.issueType")
    String createBugIssueType();
    @Key("createBug.topic")
    String createBugTopic();
    @Key("createBug.priority")
    String createBugPriority();
    @Key("createBug.labels")
    String createBugLabels();
    @Key("createBug.description")
    String createBugDescription();
    @Key("createBug.taskValue")
    String createBugTaskValue();
    @Key("createBug.sprintValue")
    String createBugSprintValue();
}