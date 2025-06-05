package ifellow.tests;

import ifellow.hooks.Hooks;
import ifellow.pages.BeginVoiceLang;
import ifellow.pages.ExplorePage;
import ifellow.pages.SavedPage;
import ifellow.pages.SearchPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FirstTest extends Hooks {

    @Test
    public void checkMainPageIsOpened() {
        BeginVoiceLang beginVoiceLang = new BeginVoiceLang(driver);
        ExplorePage explorePage = new ExplorePage(driver);
        beginVoiceLang.skipOnboarding();
        explorePage.searchFor("Java");

        String firstResultText = explorePage.getSearchResultText();
        Assertions.assertEquals("Java", firstResultText);
    }

    @Test
    public void MyTestCechkEmulator() {
        BeginVoiceLang beginVoiceLang = new BeginVoiceLang(driver);
        ExplorePage explorePage = new ExplorePage(driver);
        SearchPage searchPage = new SearchPage(driver);
        SavedPage savedPage = new SavedPage(driver);

        beginVoiceLang.skipOnboarding();
        explorePage.openPageSearch();
        searchPage.searchFor("appium test");
        searchPage.saveNodeAppium();
        searchPage.goToSaveNode();
        String resultText = savedPage.searchAddNode();
        Assertions.assertEquals("Appium", resultText);
    }
}
