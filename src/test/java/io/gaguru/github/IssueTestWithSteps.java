package io.gaguru.github;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.gaguru.github.steps.WebSteps;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.gaguru.github.config.Config.config;

@Feature("Job with Issue")
@Story("Использование Steps")
@Owner("Pavlov Igor")
public class IssueTestWithSteps {

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }
    private static final String REPOSITORY = config().getRepository();
    private static final String USERNAME = config().getUserName();
    private static final String PASSWORD = config().getPassword();
    private static final String ISSUE_TITLE = "Новая Задача Issue";
    private static final String BUG_LABEL = "bug";

    private final WebSteps webSteps = new WebSteps();

    @BeforeEach
    public void initLogger() {
        SelenideLogger.addListener("allure", new AllureSelenide()
                .savePageSource(true)
                .screenshots(true));
    }
    @Test
    @DisplayName("User should be able to create the new Issue")
    public void createNewIssueWithSteps() {
        webSteps.openLoginForm();
        webSteps.loginAs(USERNAME, PASSWORD);
        webSteps.searchForRepository(REPOSITORY);
        webSteps.openRepositoryByLink(REPOSITORY);
        webSteps.openIssuesPage();
        webSteps.clickNewIssueButton();
        webSteps.selectAssignee();
        webSteps.addLabelsToIssue(BUG_LABEL);
        webSteps.setIssueTitle(ISSUE_TITLE);
        webSteps.submitNewIssue();
        webSteps.checkIssueCreation(ISSUE_TITLE);
    }
}
