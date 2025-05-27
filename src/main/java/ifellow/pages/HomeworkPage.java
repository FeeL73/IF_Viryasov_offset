package ifellow.pages;

import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class HomeworkPage {

    private final SelenideElement statusValue = $x("//span[@id='status-val']").as("Статус задачи");
    private final SelenideElement versionValue = $x("//span[@id='fixfor-val']").as("Версия задачи");
    private final SelenideElement createBugButton = $x("//a[@id='create_link']").as("Кнопка создания Бага");

    public String getStatusText() {
        return statusValue.shouldBe(visible).getText();
    }

    public String getVersionText() {
        return versionValue.shouldBe(visible).getText();
    }

    public void clickCreateBug() {
        createBugButton.shouldBe(visible, Duration.ofSeconds(10)).click();
    }
}
