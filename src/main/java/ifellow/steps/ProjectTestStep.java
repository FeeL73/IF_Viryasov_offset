package ifellow.steps;

import ifellow.pages.DashboardPage;
import io.qameta.allure.Step;

public class ProjectTestStep {
    private final DashboardPage dashboardPage = new DashboardPage();

    @Step("Открыть список проектов")
    public ProjectTestStep openProjectsList() {
        dashboardPage.clickProjectLink();
        return this;
    }

    @Step("Выбрать проект 'TEST'")
    public void selectTestProject() {
        dashboardPage.clickTestProject();
    }

    @Step("Перейти в проект 'TEST'")
    public void navigateToTestProject() {
        openProjectsList()
                .selectTestProject();
    }
}