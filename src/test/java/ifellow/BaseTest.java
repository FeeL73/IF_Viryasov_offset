package ifellow;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.net.URL;

public class BaseTest {
    protected AndroidDriver driver;
    private static final String APPIUM_URL = "http://127.0.0.1:4723/";

    @BeforeEach
    protected void setUp() throws Exception{
        UiAutomator2Options uiAutomator2Options = new UiAutomator2Options();
        uiAutomator2Options.setCapability("platformName","Android");
        uiAutomator2Options.setCapability("deviceName","emulator-5554");
        uiAutomator2Options.setCapability("platformVersion","8.1");
        uiAutomator2Options.setCapability("automationName","UiAutomator2");
        uiAutomator2Options.setCapability("appPackage","org.wikipedia");
        uiAutomator2Options.setCapability("appActivity",".main.MainActivity");

        driver = new AndroidDriver(new URL(APPIUM_URL), uiAutomator2Options);
    }

    @AfterEach
    protected void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }
}
