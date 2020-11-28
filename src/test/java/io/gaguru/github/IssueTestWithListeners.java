package io.gaguru.github;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.gaguru.github.config.Config.config;
import static org.openqa.selenium.By.linkText;
import static io.gaguru.github.named.NamedBy.css;
import static io.gaguru.github.named.NamedBy.named;

@Feature("Job with Issue")
@Story("Использование Listeners")
@Owner("Pavlov Igor")
public class IssueTestWithListeners {

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }
    private static final String REPOSITORY = config().getRepository();
    private static final String LOGIN_PAGE = config().getLoginPage();
    private static final String USERNAME = config().getUserName();
    private static final String PASSWORD = config().getPassword();
    private static final String ISSUE_TITLE = "Новая Задача Issue";
    private static final String BUG_LABEL = "bug";

    @BeforeEach
    public void initListener() {
        SelenideLogger.addListener("allure", new AllureSelenide()
                .savePageSource(true)
                .screenshots(true));
    }
    @Test
    @DisplayName("User should be able to create the new Issue")
    public void createNewIssueWithListeners() {

        open(LOGIN_PAGE);
        $(css("#login_field").as("Ввод данных Пользователя")).setValue(USERNAME);
        $(css("#password").as("Ввод пароля")).setValue(PASSWORD);
        $(named(byValue("Sign in")).as("Подтверждение и переход в репозиторий")).click();
        $(css(".header-search-input").as("Поисковая строка в заголовке")).sendKeys(REPOSITORY);
        $(css(".header-search-input").as("Поисковая строка в заголовке")).submit();
        $(named( linkText(REPOSITORY)).as("Ссылка на репозиторий")).click();
        $(css("span[data-content='Issues']").as("Переход на страницу Issue")).click();
        $(css("a.btn-primary").as("Создать новую задачу")).click();
        $(css("#assignees-select-menu").as("Назначить задачу")).click();
        $(css("span.js-username").as("Выбрать пользователя")).click();
        $(".select-menu-item").pressEscape();
        $(css("#labels-select-menu").as("Выбрать Тег")).click();
        $(named(withText(BUG_LABEL)).as("Тег Баг")).click();
        $(css("input[name='issue[title]']").as("Ввод Заголовка Задачи")).sendKeys(ISSUE_TITLE);
        $(css("#new_issue").as("Новая Задача")).submit();
        $(css("span.js-issue-title").as("Проверка создания Задачи")).shouldHave(text(ISSUE_TITLE));
    }
}