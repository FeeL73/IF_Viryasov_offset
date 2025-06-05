package ifellow.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SavedPage {
    private AndroidDriver driver;

    private final By searchCard = By.id("org.wikipedia:id/search_card");
    private final By searchInput = By.id("org.wikipedia:id/search_src_text");
    private final By savedNode = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(5)");
    private final By getNameNode = AppiumBy.androidUIAutomator("new UiSelector().text(\"Appium\")");


    private final By backButton = AppiumBy.androidUIAutomator("new UiSelector().description(\"Navigate up\")");
    private final By savedPage = By.id("org.wikipedia:id/nav_tab_reading_lists");


    public SavedPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public String searchAddNode() {
        driver.findElement(savedNode).click();
        WebElement element = driver.findElement(getNameNode);
        return element.getText();
    }

}
