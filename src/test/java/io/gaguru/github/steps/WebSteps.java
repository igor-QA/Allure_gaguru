package io.gaguru.github.steps;

import  com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

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
        open(LOGIN_PAGE);
    }
    @Step("Login to GitHub account")
    public void loginAs(final String user, final String password) {
        $("#login_field").setValue(user);
        $("#password").setValue(password);
        $(byName("commit")).click();
    }
    @Step("Search for repository")
    public void searchForRepository( final String repository) {
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
        $("#assignees-select-menu").click();
    }
    @Step("Add Labels To Issue")
    public void addLabelsToIssue(String BUG_LABEL) {
        $("#labels-select-menu").click();
        $(withText(BUG_LABEL)).click();
    }
    @Step("Input Issue Title")
    public void setIssueTitle(String ISSUE_TITLE) {
        $("input[name='issue[title]']").sendKeys(ISSUE_TITLE);

    }
    @Step("Submit NewIssue")
    public void submitNewIssue() {
        $("#new_issue").submit();
    }

    @Step("Check Issue Creation")
    public void checkIssueCreation(String ISSUE_TITLE) {
        $("span.js-issue-title").shouldHave(text(ISSUE_TITLE));
    }
}
