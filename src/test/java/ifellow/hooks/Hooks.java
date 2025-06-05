package ifellow.hooks;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.io.File;
import java.net.URL;
import java.time.Duration;
import ifellow.utils.Props;

public class Hooks {
    static Props props = ConfigFactory.create(Props.class);
    protected AndroidDriver driver;
    private static final String APPIUM_URL = props.appiumUrl();

    @BeforeEach
    protected void setUp() throws Exception{
        File apkFile = new File("src/test/resources/app/org.wikipedia_50460_apps.evozi.com.apk");
        UiAutomator2Options capabilities = new UiAutomator2Options();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","emulator-5554");
        capabilities.setCapability("platformVersion","8.1");
        capabilities.setCapability("automationName","UiAutomator2");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app", apkFile.getAbsolutePath());
        capabilities.setCapability("orientation","PORTRAIT");

        driver = new AndroidDriver(new URL(APPIUM_URL), capabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterEach
    protected void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }
}
