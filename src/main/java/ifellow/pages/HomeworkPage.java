package ifellow.pages;

import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class HomeworkPage {

    private final SelenideElement valueStatus = $x("//span[@id='status-val']").as("Статус задачи");
    private final SelenideElement valueVersionEdit = $x("//span[@id='fixfor-val']").as("Версия задачи");
    private final SelenideElement buttonCreateBug = $x("//a[@id='create_link']").as("Кнопка создания Бага");

    public String getTaskStatus() {
        return valueStatus.getText();
    }
    public String getTaskVersion() {
        return valueVersionEdit.getText();
    }
    public HomeworkPage createBug() {
        buttonCreateBug.shouldBe(visible, Duration.ofSeconds(10)).click();
        return this;
    }
}
