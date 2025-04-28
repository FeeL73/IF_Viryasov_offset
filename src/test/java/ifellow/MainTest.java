package ifellow;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
public class MainTest {
    private int currentTaskCountOld;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("beforeAll");
    }

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        open("https://edujira.ifellow.ru/");
        WebDriver driver = WebDriverRunner.getWebDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void authSiteTest() {
        SelenideElement loginButton = $x("//a[@class='aui-nav-link login-link']").shouldBe(visible, Duration.ofSeconds(10));
        loginButton.click();
        SelenideElement loginField = $x("//input[@id='login-form-username']");
        Assertions.assertTrue(loginField.isDisplayed(), "Поле для ввода логина не отображается");
        SelenideElement passwordField = $x("//input[@id='login-form-password']");
        Assertions.assertTrue(passwordField.isDisplayed(), "Поле для ввода пароля не отображается");

        loginField.sendKeys("AT7");
        passwordField.sendKeys("Qwerty123");

        SelenideElement submitButton = $x("//input[@id='login-form-submit']");
        Assertions.assertTrue(submitButton.isEnabled(), "Кнопка отправки формы не активна");
        submitButton.click();

        WebDriver driver = WebDriverRunner.getWebDriver();
        Assertions.assertTrue(driver.getCurrentUrl().contains("secure/Dashboard.jspa"), "Авторизация не удалась");
    }

    @Test
    public void goProjectTest() {
        SelenideElement loginButton = $x("//a[@class='aui-nav-link login-link']").shouldBe(visible, Duration.ofSeconds(10));
        loginButton.click();
        SelenideElement loginField = $x("//input[@id='login-form-username']");
        Assertions.assertTrue(loginField.isDisplayed(), "Поле для ввода логина не отображается");
        SelenideElement passwordField = $x("//input[@id='login-form-password']");
        Assertions.assertTrue(passwordField.isDisplayed(), "Поле для ввода пароля не отображается");

        loginField.sendKeys("AT7");
        passwordField.sendKeys("Qwerty123");

        SelenideElement submitButton = $x("//input[@id='login-form-submit']");
        Assertions.assertTrue(submitButton.isEnabled(), "Кнопка отправки формы не активна");
        submitButton.click();

        WebDriver driver = WebDriverRunner.getWebDriver();
        Assertions.assertTrue(driver.getCurrentUrl().contains("secure/Dashboard.jspa"), "Авторизация не удалась");


        SelenideElement projectLink = $x("//a[@id='browse_link']");
        Assertions.assertTrue(projectLink.isDisplayed(), "Ссылка на проект не отображается");
        Assertions.assertTrue(projectLink.isEnabled(), "Ссылка на проект не активна");
        projectLink.click();

        SelenideElement projectLinkTest = $x("//a[@id='admin_main_proj_link_lnk']");
        Assertions.assertTrue(projectLinkTest.isEnabled(), "Ссылка на проект ТЕСТ не активна");
        projectLinkTest.click();
    }


    @Test
    public void checkNumberTaskTest() {
        SelenideElement loginButton = $x("//a[@class='aui-nav-link login-link']").shouldBe(visible, Duration.ofSeconds(10));
        loginButton.click();
        Assertions.assertTrue(loginButton.isDisplayed(), "Кнопка входа не отображается");

        SelenideElement loginField = $x("//input[@id='login-form-username']");
        Assertions.assertTrue(loginField.isDisplayed(), "Поле для ввода логина не отображается");

        SelenideElement passwordField = $x("//input[@id='login-form-password']");
        Assertions.assertTrue(passwordField.isDisplayed(), "Поле для ввода пароля не отображается");

        loginField.sendKeys("AT7");
        passwordField.sendKeys("Qwerty123");

        SelenideElement submitButton = $x("//input[@id='login-form-submit']");
        Assertions.assertTrue(submitButton.isEnabled(), "Кнопка отправки формы не активна");
        submitButton.click();

        WebDriver driver = WebDriverRunner.getWebDriver();
        Assertions.assertTrue(driver.getCurrentUrl().contains("secure/Dashboard.jspa"), "Авторизация не удалась");

        System.out.println("Открываем проект");
        SelenideElement projectLink = $x("//a[@id='browse_link']");
        projectLink.click();
        Assertions.assertTrue(projectLink.isDisplayed(), "Ссылка на проект не отображается");

        SelenideElement projectLinkTest = $x("//a[@id='admin_main_proj_link_lnk']");
        System.out.println("Нажимаем на проект ТЕСТ");
        projectLinkTest.click();

        SelenideElement filterProject = $x("//button[@id='subnav-trigger']");
        System.out.println("Нажимаем на Фильтр");
        filterProject.click();
        Assertions.assertTrue(filterProject.isEnabled(), "Фильтр не активен");

        SelenideElement filterProjectAllTask = $x("//a[text()='Все задачи']");
        filterProjectAllTask.shouldBe(visible, Duration.ofSeconds(10));
        filterProjectAllTask.shouldBe(Condition.enabled, Duration.ofSeconds(10));
        filterProjectAllTask.click();

        String currentTaskCountTextOld = $x("//div[@class='showing']//span").getText();
        currentTaskCountOld = Integer.parseInt(currentTaskCountTextOld.split(" из ")[1]);

        int currentTaskCount = waitForTaskCountChange(currentTaskCountOld);

        SelenideElement buttonCreate = $x("//a[@id='create_link']");
        buttonCreate.click();
        SelenideElement inputNameThems = $x("//input[@class='text long-field']");
        inputNameThems.sendKeys("fonk Остров сокровищ");
        SelenideElement createTaskNew = $x("//input[@id='create-issue-submit']");
        createTaskNew.click();

        System.out.println("Открываем проект");
        SelenideElement projectLink2 = $x("//a[@id='browse_link']");
        projectLink2.click();
        Assertions.assertTrue(projectLink.isDisplayed(), "Ссылка на проект не отображается");

        SelenideElement projectLinkTest2 = $x("//a[@id='admin_main_proj_link_lnk']");
        System.out.println("Нажимаем на проект ТЕСТ");
        projectLinkTest2.click();

        SelenideElement filterProject2 = $x("//button[@id='subnav-trigger']");
        System.out.println("Нажимаем на Фильтр");
        filterProject2.click();
        Assertions.assertTrue(filterProject.isEnabled(), "Фильтр не активен");

        SelenideElement filterProjectAllTask2 = $x("//a[text()='Все задачи']");
        filterProjectAllTask2.shouldBe(visible, Duration.ofSeconds(10));
        filterProjectAllTask2.shouldBe(Condition.enabled, Duration.ofSeconds(10));
        filterProjectAllTask2.click();

        SelenideElement currentTaskCountTextOld22 = $x("//div[@class='showing']//span");
        currentTaskCountTextOld22.shouldBe(visible, Duration.ofSeconds(10));
        currentTaskCountTextOld22.shouldBe(Condition.enabled, Duration.ofSeconds(10));

        String currentTaskCountTextOld2 = $x("//div[@class='showing']//span").getText();
        int currentTaskCountOld2 = Integer.parseInt(currentTaskCountTextOld2.split(" из ")[1]);
        int currentTaskCount22 = waitForTaskCountChange(currentTaskCountOld2);

        System.out.println("currentTaskCount22: " + currentTaskCount22);
        System.out.println("currentTaskCount: " + currentTaskCount);
        Assertions.assertTrue(currentTaskCount22 > currentTaskCount, "Количество задач не изменилось!");
    }

    private int waitForTaskCountChange(int initialTaskCount) {
        int currentTaskCountFunc = 0;
        String currentTaskCountText = "";

        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 5000) {
            try {
                currentTaskCountText = $x("//div[@class='showing']//span").getText();
                currentTaskCountFunc = Integer.parseInt(currentTaskCountText.split(" из ")[1]);
                System.out.println("waitForTaskCountChange() currentTaskCount: " + currentTaskCountFunc);
                System.out.println("waitForTaskCountChange() currentTaskCountOld: " + initialTaskCount);
                if (currentTaskCountFunc != initialTaskCount) {
                    System.out.println("Количество задач изменилось с " + initialTaskCount + " на " + currentTaskCountFunc);
                    return currentTaskCountFunc;
                }
            } catch (Exception e) {
                System.out.println("Не получили значение, повторим...");
            }
            sleep(500);
        }
        return currentTaskCountFunc;
    }

    @Test
    public void checoutTaskTest() {
        SelenideElement loginButton = $x("//a[@class='aui-nav-link login-link']").shouldBe(visible, Duration.ofSeconds(10));
        loginButton.click();
        Assertions.assertTrue(loginButton.isDisplayed(), "Кнопка входа не отображается");

        SelenideElement loginField = $x("//input[@id='login-form-username']");
        Assertions.assertTrue(loginField.isDisplayed(), "Поле для ввода логина не отображается");

        SelenideElement passwordField = $x("//input[@id='login-form-password']");
        Assertions.assertTrue(passwordField.isDisplayed(), "Поле для ввода пароля не отображается");

        loginField.sendKeys("AT7");
        passwordField.sendKeys("Qwerty123");

        SelenideElement submitButton = $x("//input[@id='login-form-submit']");
        Assertions.assertTrue(submitButton.isEnabled(), "Кнопка отправки формы не активна");
        submitButton.click();

        WebDriver driver = WebDriverRunner.getWebDriver();
        Assertions.assertTrue(driver.getCurrentUrl().contains("secure/Dashboard.jspa"), "Авторизация не удалась");

        System.out.println("Открываем проект");
        SelenideElement projectLink = $x("//a[@id='browse_link']");
        projectLink.click();
        Assertions.assertTrue(projectLink.isDisplayed(), "Ссылка на проект не отображается");

        SelenideElement projectLinkTest = $x("//a[@id='admin_main_proj_link_lnk']");
        System.out.println("Нажимаем на проект ТЕСТ");
        projectLinkTest.click();

        SelenideElement filterProject = $x("//button[@id='subnav-trigger']");
        System.out.println("Нажимаем на Фильтр");
        filterProject.click();
        Assertions.assertTrue(filterProject.isEnabled(), "Фильтр не активен");

        SelenideElement filterProjectAllTask = $x("//a[text()='Все задачи']");
        filterProjectAllTask.shouldBe(visible, Duration.ofSeconds(10));
        filterProjectAllTask.shouldBe(Condition.enabled, Duration.ofSeconds(10));
        filterProjectAllTask.click();

        String currentTaskCountTextOld = $x("//div[@class='showing']//span").getText();
        currentTaskCountOld = Integer.parseInt(currentTaskCountTextOld.split(" из ")[1]);

        int currentTaskCount = waitForTaskCountChange(currentTaskCountOld);

        SelenideElement buttonCreate = $x("//a[@id='create_link']");
        buttonCreate.click();
        SelenideElement inputNameThems = $x("//input[@class='text long-field']");
        inputNameThems.sendKeys("fonk Остров сокровищ");
        SelenideElement createTaskNew = $x("//input[@id='create-issue-submit']");
        createTaskNew.click();

        System.out.println("Открываем проект");
        SelenideElement projectLink2 = $x("//a[@id='browse_link']");
        projectLink2.click();
        Assertions.assertTrue(projectLink.isDisplayed(), "Ссылка на проект не отображается");

        SelenideElement projectLinkTest2 = $x("//a[@id='admin_main_proj_link_lnk']");
        System.out.println("Нажимаем на проект ТЕСТ");
        projectLinkTest2.click();

        SelenideElement filterProject2 = $x("//button[@id='subnav-trigger']");
        System.out.println("Нажимаем на Фильтр");
        filterProject2.click();
        Assertions.assertTrue(filterProject.isEnabled(), "Фильтр не активен");

        SelenideElement filterProjectAllTask2 = $x("//a[text()='Все задачи']");
        filterProjectAllTask2.shouldBe(visible, Duration.ofSeconds(10));
        filterProjectAllTask2.shouldBe(Condition.enabled, Duration.ofSeconds(10));
        filterProjectAllTask2.click();

        SelenideElement currentTaskCountTextOld22 = $x("//div[@class='showing']//span");
        currentTaskCountTextOld22.shouldBe(visible, Duration.ofSeconds(10));
        currentTaskCountTextOld22.shouldBe(Condition.enabled, Duration.ofSeconds(10));

        String currentTaskCountTextOld2 = $x("//div[@class='showing']//span").getText();
        int currentTaskCountOld2 = Integer.parseInt(currentTaskCountTextOld2.split(" из ")[1]);

        int currentTaskCount22 = waitForTaskCountChange(currentTaskCountOld2);

        System.out.println("currentTaskCount22: " + currentTaskCount22);
        System.out.println("currentTaskCount: " + currentTaskCount);
        Assertions.assertTrue(currentTaskCount22 > currentTaskCount, "Количество задач не изменилось!");

        SelenideElement inputSearch = $x("//input[@id='quickSearchInput']");
        inputSearch
                .setValue("TestSeleniumATHomework")
                .pressEnter();

        SelenideElement valueStatus = $x("//span[@id='status-val']");
        String valueStatusText = valueStatus.getText();
        Assertions.assertEquals("СДЕЛАТЬ", valueStatusText, "Статус задачи не соответствует ожидаемому");

        SelenideElement valueVersionEdit = $x("//span[@id='fixfor-val']");
        String valueVersionEditText = valueVersionEdit.getText();
        Assertions.assertEquals("Version 2.0", valueVersionEditText, "Статус задачи не соответствует ожидаемому");
    }


    @Test
    public void createNewBugTest() {
        SelenideElement loginButton = $x("//a[@class='aui-nav-link login-link']").shouldBe(visible, Duration.ofSeconds(10));
        loginButton.click();
        Assertions.assertTrue(loginButton.isDisplayed(), "Кнопка входа не отображается");

        SelenideElement loginField = $x("//input[@id='login-form-username']");
        Assertions.assertTrue(loginField.isDisplayed(), "Поле для ввода логина не отображается");

        SelenideElement passwordField = $x("//input[@id='login-form-password']");
        Assertions.assertTrue(passwordField.isDisplayed(), "Поле для ввода пароля не отображается");

        loginField.sendKeys("AT7");
        passwordField.sendKeys("Qwerty123");

        SelenideElement submitButton = $x("//input[@id='login-form-submit']");
        Assertions.assertTrue(submitButton.isEnabled(), "Кнопка отправки формы не активна");
        submitButton.click();

        WebDriver driver = WebDriverRunner.getWebDriver();
        Assertions.assertTrue(driver.getCurrentUrl().contains("secure/Dashboard.jspa"), "Авторизация не удалась");

        System.out.println("Открываем проект");
        SelenideElement projectLink = $x("//a[@id='browse_link']");
        projectLink.click();
        Assertions.assertTrue(projectLink.isDisplayed(), "Ссылка на проект не отображается");

        SelenideElement projectLinkTest = $x("//a[@id='admin_main_proj_link_lnk']");
        System.out.println("Нажимаем на проект ТЕСТ");
        projectLinkTest.click();

        SelenideElement filterProject = $x("//button[@id='subnav-trigger']");
        System.out.println("Нажимаем на Фильтр");
        filterProject.click();
        Assertions.assertTrue(filterProject.isEnabled(), "Фильтр не активен");

        SelenideElement filterProjectAllTask = $x("//a[text()='Все задачи']");
        filterProjectAllTask.shouldBe(visible, Duration.ofSeconds(10));
        filterProjectAllTask.shouldBe(Condition.enabled, Duration.ofSeconds(10));
        filterProjectAllTask.click();

        String currentTaskCountTextOld = $x("//div[@class='showing']//span").getText();
        currentTaskCountOld = Integer.parseInt(currentTaskCountTextOld.split(" из ")[1]);

        int currentTaskCount = waitForTaskCountChange(currentTaskCountOld);

        SelenideElement buttonCreate = $x("//a[@id='create_link']");
        buttonCreate.click();
        SelenideElement inputNameThems = $x("//input[@class='text long-field']");
        inputNameThems.sendKeys("fonk Остров сокровищ");
        SelenideElement createTaskNew = $x("//input[@id='create-issue-submit']");
        createTaskNew.click();

        System.out.println("Открываем проект");
        SelenideElement projectLink2 = $x("//a[@id='browse_link']");
        projectLink2.click();
        Assertions.assertTrue(projectLink.isDisplayed(), "Ссылка на проект не отображается");

        SelenideElement projectLinkTest2 = $x("//a[@id='admin_main_proj_link_lnk']");
        System.out.println("Нажимаем на проект ТЕСТ");
        projectLinkTest2.click();

        SelenideElement filterProject2 = $x("//button[@id='subnav-trigger']");
        System.out.println("Нажимаем на Фильтр");
        filterProject2.click();
        Assertions.assertTrue(filterProject.isEnabled(), "Фильтр не активен");

        SelenideElement filterProjectAllTask2 = $x("//a[text()='Все задачи']");
        filterProjectAllTask2.shouldBe(visible, Duration.ofSeconds(10));
        filterProjectAllTask2.shouldBe(Condition.enabled, Duration.ofSeconds(10));
        filterProjectAllTask2.click();

        SelenideElement currentTaskCountTextOld22 = $x("//div[@class='showing']//span");
        currentTaskCountTextOld22.shouldBe(visible, Duration.ofSeconds(10));
        currentTaskCountTextOld22.shouldBe(Condition.enabled, Duration.ofSeconds(10));

        String currentTaskCountTextOld2 = $x("//div[@class='showing']//span").getText();
        int currentTaskCountOld2 = Integer.parseInt(currentTaskCountTextOld2.split(" из ")[1]);
        int currentTaskCount22 = waitForTaskCountChange(currentTaskCountOld2);

        System.out.println("currentTaskCount22: " + currentTaskCount22);
        System.out.println("currentTaskCount: " + currentTaskCount);
        Assertions.assertTrue(currentTaskCount22 > currentTaskCount, "Количество задач не изменилось!");

        SelenideElement inputSearch = $x("//input[@id='quickSearchInput']");
        inputSearch
                .setValue("TestSeleniumATHomework")
                .pressEnter();

        SelenideElement valueStatus = $x("//span[@id='status-val']");
        String valueStatusText = valueStatus.getText();
        Assertions.assertEquals("СДЕЛАТЬ", valueStatusText, "Статус задачи не соответствует ожидаемому");

        SelenideElement valueVersionEdit = $x("//span[@id='fixfor-val']");
        String valueVersionEditText = valueVersionEdit.getText();
        Assertions.assertEquals("Version 2.0", valueVersionEditText, "Статус задачи не соответствует ожидаемому");

        SelenideElement buttonCreateBug = $x("//a[@id='create_link']");
        buttonCreateBug.click();

        SelenideElement inputTypeTast = $x("//input[@id='issuetype-field']");
        inputTypeTast.sendKeys("Ошибка");

        SelenideElement inputNameThemsBug = $x("//input[@class='text long-field']");
        inputNameThemsBug.sendKeys("New_BUG_100500");

        //SelenideElement clickStatusEditToEndSecondStep = $x("//div[@class='aui-dropdown2-item-group']//span[text()='Выполнено']/parent::a");
        //clickStatusEditToEndSecondStep.click();

        SelenideElement clickImportant = $x("//input[@id='priority-field']");
        clickImportant.sendKeys("Highest");

        SelenideElement clickTextareaDiscr = $x("//textarea[@id='labels-textarea']");
        clickTextareaDiscr.sendKeys("Метка");

        SelenideElement buttonDescription = $x("//label[@for='description']/following-sibling::div//button[contains(@class, 'aui-button') and text()='Визуальный']");

        if (!buttonDescription.has(Condition.focused)) {
            buttonDescription.click();
            System.out.println("Кнопка не была в фокусе, теперь она нажата.");
        } else {
            System.out.println("Кнопка уже в фокусе.");
        }

        SelenideElement iframe = $x("//iframe[@id='mce_7_ifr']");
        switchTo().frame(iframe);
        SelenideElement bodyElement = $x("//body[@id='tinymce']");
        bodyElement.setValue("Примерный текст для тестовича");
        switchTo().defaultContent();

        SelenideElement button = $x("//label[@for='environment']/following-sibling::div//button[contains(@class, 'aui-button') and text()='Визуальный']");

        if (!button.has(Condition.focused)) {
            button.click();
            System.out.println("Кнопка не была в фокусе, теперь она нажата.");
        } else {
            System.out.println("Кнопка уже в фокусе.");
        }

        SelenideElement buttonCreateTask = $x("//input[@id='create-issue-submit']");
        buttonCreateTask.click();

        SelenideElement buttonNewTask = $x("//a[@class='issue-created-key issue-link']");
        buttonNewTask.click();

        SelenideElement clickStatusEditToEnd = $x("//a[@id='opsbar-transitions_more']");
        clickStatusEditToEnd.click();
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Тест завершен");
    }
}
