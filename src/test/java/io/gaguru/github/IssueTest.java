package io.gaguru.github;


import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.*;

@Owner("Igor Pavlov")
@Feature("Job with Issue")
public class IssueTest {
    private static final String REPOSITORY = "igor-QA/allure_gaguru";
    private static final int ISSUE = 12;
    private static final String BASE_URL = "https://github.com";

    @Test
    @DisplayName("User should be able to find the  Issue by number")
    public void shouldFindIssueByNumber(){
        link("GitHub", String.format("%s/%s", BASE_URL, REPOSITORY));
        parameter("Repository", REPOSITORY);
        parameter("Issues", ISSUE);


        step("Open boss page", () -> {
            open("https://github.com");
        });
        step("looking for Repository" + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        } );
        step("go to link Repository" + REPOSITORY, ()-> {
            $(By.linkText("igor-QA/allure_gaguru")).click();
        });
        step("go to Issues", () -> {
            $(withText("Issues")).click();
        });
        step("test Issues number" + ISSUE, () -> {
            $(withText("#" + ISSUE)).should(exist);
        });

    }
}
