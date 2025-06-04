package ifellow;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import java.time.Duration;
import static com.codeborne.selenide.Selenide.sleep;
import io.appium.java_client.AppiumBy;

public class FirstTest extends BaseTest{

    @Test
    public void checkMainPageIsOpened(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.id("org.wikipedia:id/fragment_onboarding_skip_button")).click();
        driver.findElement(By.id("org.wikipedia:id/search_container")).click();
        driver.findElement(By.id("org.wikipedia:id/search_src_text")).sendKeys("Java");
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(1)")).click();
        sleep(3000);
    }
}
