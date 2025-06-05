package ifellow.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class BeginVoiceLang {
    private final By skipButton = By.id("org.wikipedia:id/fragment_onboarding_skip_button");

    private AndroidDriver driver;
    public BeginVoiceLang(AndroidDriver driver) {
        this.driver = driver;
    }
    public void skipOnboarding () {
        driver.findElement(skipButton).click();
    }
}

