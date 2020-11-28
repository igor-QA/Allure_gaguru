package io.gaguru.github;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static io.gaguru.github.config.Config.config;
import static io.qameta.allure.Allure.*;
import static org.openqa.selenium.By.linkText;

@Feature("Job with Issue")
@Story("Использование Lambda steps")
@Owner("Igor Pavlov")
public class IssueLambdaTest {
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

    @Test
    @DisplayName("User should be able to create the new Issue")
    public void createNewIssue() {
        link("GitHub", String.format("%s/%s", LOGIN_PAGE, REPOSITORY));
        parameter("Repository", REPOSITORY);
        parameter("Boss Page", LOGIN_PAGE);

        step("Open Boss Page", () -> {
            open(LOGIN_PAGE);
        });
        step("Login to GitHub", () -> {
            $("#login_field").setValue(USERNAME);
            $("#password").setValue(PASSWORD);
            $(byValue("Sign in")).click();
        });
        step("looking for Repository " + REPOSITORY, () -> {
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Go to link Repository " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Go to Issues page", () -> {
            $("span[data-content='Issues']").click();
        });
        step(String.format("Create new issue with label and assignee %s and %s", ISSUE_TITLE, BUG_LABEL), () -> {
            $("a.btn-primary").click();
            $("#assignees-select-menu").click();
            $("span.js-username").click();
            $("#assignees-select-menu").click();
            $("#labels-select-menu").click();
            $(withText(BUG_LABEL)).click();
            $("input[name='issue[title]']").sendKeys(ISSUE_TITLE);
            $("#new_issue").submit();
        });
        step("Issue check created with a title" + ISSUE_TITLE, () -> {
            $("span.js-issue-title").shouldHave(text(ISSUE_TITLE));
        });
    }
}
