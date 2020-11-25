package io.gaguru.github.steps;

import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.gaguru.github.config.Config.config;
import static io.qameta.allure.Allure.parameter;
import static org.openqa.selenium.By.linkText;


public class WebSteps {
    private static final String LOGIN_PAGE = config().getLoginPage();


    @Step("Open Login form")
    public void openLoginForm() {
        parameter("Login Form", LOGIN_PAGE);
        open(LOGIN_PAGE);
    }

    @Step("Login to GitHub account")
    public void LoginAs(final String login, final String password) {
        $("#login_field").setValue(login);
        $("#password").setValue(password);
        $(byName("commit")).click();
    }

    @Step("Search for repository")
    public void searchForRepository( final String repository) {
        parameter("Repository", repository);
        $(".header-search-input").setValue(repository);
        $(".header-search-input").submit();
    }

    @Step("Open repository by link")
    public void openRepositoryByLink(String repository) {
        $(linkText(repository)).click(); 
    }
    @Step("Open Issues page")
    public void openIssuesPage() {
        $("span[data-content='Issues']").click();
    }
    @Step("Click 'New issue' button")
    public void clickNewIssueButton() {
        $("a.btn-primary").click();
    }
    @Step("Select assignee")
    public void selectAssignee() {
        $("#assignees-select-menu").click();
        $("span.js-username").click();
        $("body").click();
    }
    @Step("addLabelsToIssue")
    public void addLabelsToIssue(String BUG_LABEL) {
        $("#labels-select-menu").click();
        $(withText(BUG_LABEL)).click();
    }
    @Step("setIssueTitle")
    public void setIssueTitle(String ISSUE_TITLE) {
        $("input[name='issue[title]']").sendKeys(ISSUE_TITLE);

    }
    @Step("submitNewIssue")
    public void submitNewIssue() {
        $("#new_issue").submit();
    }

    @Step("CheckIssueCreation")
    public void CheckIssueCreation(String ISSUE_TITLE) {
        $("span.js-issue-title").shouldHave(text(ISSUE_TITLE));
    }
}
