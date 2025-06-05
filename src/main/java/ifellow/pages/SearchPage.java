package ifellow.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchPage {
    private AndroidDriver driver;

    private final By searchCard = By.id("org.wikipedia:id/search_card");
    private final By searchInput = By.id("org.wikipedia:id/search_src_text");
    private final By searchOneList = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(1)");
    private final By saveNode = By.id("org.wikipedia:id/page_save");
    private final By backButton = AppiumBy.androidUIAutomator("new UiSelector().description(\"Navigate up\")");
    private final By savedPage = By.id("org.wikipedia:id/nav_tab_reading_lists");

    public SearchPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void searchFor(String searchText) {
        driver.findElement(searchCard).click();
        driver.findElement(searchInput).sendKeys(searchText);
    }

    public void saveNodeAppium(){
        driver.findElement(searchOneList).click();
        driver.findElement(saveNode).click();
    }
    public void goToSaveNode(){
        driver.findElement(backButton).click();
        driver.findElement(backButton).click();
        driver.findElement(savedPage).click();
    }

}
