package ifellow;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.LogEventListener;
import org.jetbrains.annotations.NotNull;
import ifellow.utils.Props;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class MaskedAllureSelenide implements LogEventListener {
    private final boolean screenshots;
    private final boolean savePageSource;
    static Props props = ConfigFactory.create(Props.class);

    public MaskedAllureSelenide(boolean screenshots, boolean savePageSource) {
        this.screenshots = screenshots;
        this.savePageSource = savePageSource;
    }

    @Override
    public void beforeEvent(@NotNull LogEvent event) {
    }

    @Override
    public void afterEvent(LogEvent event) {
        String stepName = event.getElement() + " " + maskSensitiveData(event.getSubject());
        Allure.step(stepName, () -> {
            if (screenshots) {
                attachScreenshot();
            }
            if (savePageSource && event.getStatus() == LogEvent.EventStatus.FAIL) {
                attachPageSource();
            }
        });
    }

    @SuppressWarnings("UnusedReturnValue")
    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] attachScreenshot() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
        }
        return new byte[0];
    }

    @Attachment(value = "Page source", type = "text/html")
    private void attachPageSource() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.getWebDriver().getPageSource();
        }
    }

    private String maskSensitiveData(String input) {
        String password = props.password();
        return input.replaceAll("(?i)send keys\\(\\s*['\"]?" + password + "['\"]?\\s*\\)", "send keys(******)");
    }

    public static MaskedAllureSelenide create(boolean screenshots, boolean savePageSource) {
        return new MaskedAllureSelenide(screenshots, savePageSource);
    }
}