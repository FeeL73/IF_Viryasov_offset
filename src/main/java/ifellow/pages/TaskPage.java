package ifellow.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskPage {
    public static final Logger log = LoggerFactory.getLogger(TaskPage.class);
    private final SelenideElement buttonCreate = $x("//a[@id='create_link']").as("Кнопка создания Задачи");
    private final SelenideElement inputNameThems = $x("//input[@class='text long-field']").as("Поле ввода названия задачи");
    private final SelenideElement createTaskNew = $x("//input[@id='create-issue-submit']").as("Кнопка создания задачи");
    private final SelenideElement issueTypeField = $x("//input[@id='issuetype-field']").as("Тип Бага");
    private final SelenideElement topicBag = $x("//input[@class='text long-field']").as("Тема Бага");
    private final SelenideElement descriptionFrame = $x("//div[@id='description-wiki-edit']//iframe").as("Фрейм редактора описания");
    private final SelenideElement descriptionBody = $x("//body[@id='tinymce']").as("Описание Body");
    private final SelenideElement editVersion = $x("(//select[@id='fixVersions'])//option[@value='10001']").as("Исправить в версиях");
    private final SelenideElement editTochVersion = $x("(//select[@id='versions'])//option[@value='10001']").as("Затронуты версии");
    private final SelenideElement priorityField = $x("//input[@id='priority-field']").as("Приоритет Бага");
    private final SelenideElement labelsTextarea = $x("//textarea[@id='labels-textarea']").as("Описание Метки Бага");
    private final SelenideElement descriptionVisualButton = $x("//label[@for='description']/following-sibling::div//button[contains(@class, 'aui-button') and text()='Визуальный']").as("Кнопка 'Визаульный' для формы Описание");
    private final SelenideElement environmentVisualButton = $x("//label[@for='environment']/following-sibling::div//button[contains(@class, 'aui-button') and text()='Визуальный']").as("Кнопка 'Визаульный' для формы Окружение");
    private final SelenideElement environmentFrame = $x("//div[@id='environment-wiki-edit']//iframe").as("Фрейм редактора окружения");
    private final SelenideElement environmentBody = $x("//body[@id='tinymce']").as("Body редактора окружения");
    private final SelenideElement createdIssueLink = $x("//a[@class='issue-created-key issue-link']").as("Созданная ссылка на рассылку");
    private final SelenideElement moreActionsButton = $x("//a[@id='opsbar-transitions_more']").as("Кнопка дополнительных действий");
    private final SelenideElement resolveOption = $x("//div[@class='aui-dropdown2-item-group']//span[text()='Выполнено']/parent::a").as("Разрешить опцию");
    private final SelenideElement submitButton = $x("//input[@id='create-issue-submit']").as("Кнопка создания Бага");
    private final SelenideElement relatedTasks =  $x("//select[@id='issuelinks-linktype']/option[@value='clones']").as("Связанные задачи");
    private final SelenideElement task = $x("//textarea[@id='issuelinks-issues-textarea']").as("задачи");
    private final SelenideElement executor = $x("//button[@id='assign-to-me-trigger']").as("исполнитель");
    private final SelenideElement linkToEpic = $x("//input[@id='customfield_10100-field']").as("Ссылка на эпики");
    private final SelenideElement sprint = $x("//input[@id='customfield_10104-field']").as("Спринт");
    private final SelenideElement fileInput = $x("(//div[@class='field-group file-input-list long-field'])//input[@class='issue-drop-zone__file ignore-inline-attach']").as("Ввод файла");
    private final SelenideElement popupNode = $x("//div[@id='aui-flag-container']");

    public void createAndResolveBug(String issueType, String topic, String priority, String labels, String description, String taskValue, String sprintValue) {

        issueTypeField.shouldBe(visible).sendKeys(issueType);
        topicBag.shouldBe(visible).sendKeys(topic);
        clickVisualButtonIfNotFocused(descriptionVisualButton);
        setTextInFrame(descriptionFrame, descriptionBody, description);
        editVersion.shouldBe(visible).click();
        priorityField.click();
        priorityField.sendKeys(priority);
        labelsTextarea.shouldBe(visible).sendKeys(labels);
        clickVisualButtonIfNotFocused(environmentVisualButton);
        setTextInFrame(environmentFrame, environmentBody, description);
        String filePath = "C:\\Games\\test.txt";
        fileInput.sendKeys(filePath);
        editTochVersion.shouldBe(visible).click();
        relatedTasks.click();
        task.setValue(taskValue);
        task.pressEnter();
        executor.click();
        linkToEpic.shouldBe(visible);
        linkToEpic.click();
        linkToEpic.shouldBe(visible);
        linkToEpic.shouldHave(attribute("aria-activedescendant"));
        linkToEpic.sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ARROW_DOWN);
        linkToEpic.sendKeys(Keys.ENTER);
        sprint.setValue(sprintValue);
        sprint.pressEnter();
        submitButton.shouldBe(visible, Duration.ofSeconds(10)).click();
        createdIssueLink.shouldBe(visible).click();
        moreActionsButton.shouldBe(visible).click();
        resolveOption.shouldBe(visible).click();
        popupNode.shouldBe(visible, Duration.ofSeconds(10));
    }

    private void clickVisualButtonIfNotFocused(SelenideElement button) {
        if (!button.has(focused)) {
            button.click();
            log.debug("Кнопка не была в фокусе, теперь она нажата.");
        } else {
            log.debug("Кнопка уже в фокусе.");
        }
    }

    private void setTextInFrame(SelenideElement  frameLocator, SelenideElement  bodyLocator, String text) {
        frameLocator.shouldBe(Condition.visible, Duration.ofSeconds(10));
        switchTo().frame(frameLocator);
        bodyLocator.setValue(text);
        switchTo().defaultContent();
    }

    public void createNewTask(String taskName) {
        buttonCreate.shouldBe(visible).click();
        inputNameThems.shouldBe(visible).sendKeys(taskName);
        createTaskNew.shouldBe(visible).click();
    }
}
