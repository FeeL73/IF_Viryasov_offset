package ifellow.pages;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

public class ExplorePage {
    private AndroidDriver driver;

    private final By searchContainer = By.id("org.wikipedia:id/search_container");
    private final By searchInput = By.id("org.wikipedia:id/search_src_text");
    private final By searchOneList = AppiumBy.androidUIAutomator("new UiSelector().text(\"Java\")");
    private final By openPageSearch = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"org.wikipedia:id/navigation_bar_item_icon_container\").instance(2)");
            //By.id("org.wikipedia:id/navigation_bar_item_active_indicator_view");

    public ExplorePage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void searchFor(String searchText) {
        driver.findElement(searchContainer).click();
        driver.findElement(searchInput).sendKeys(searchText);
    }

    public String getSearchResultText() {
        WebElement element = driver.findElement(searchOneList);
        return element.getText();
    }

    public void openPageSearch() {
        driver.findElement(openPageSearch).click();
    }
}
