package ifellow.pages;

import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.visible;

public class DashboardPage {
    private final SelenideElement projectLink = $x("//a[@id='browse_link']").as("кнопка Проекты");
    private final SelenideElement projectLinkTest = $x("//a[@id='admin_main_proj_link_lnk']").as("проект TEST");

    public DashboardPage goToProject() {
        projectLink.shouldBe(visible, Duration.ofSeconds(10)).click();
        projectLinkTest.shouldBe(visible, Duration.ofSeconds(10)).click();
        return this;
    }
}
