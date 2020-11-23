package io.gaguru.github;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.logevents.SelenideLogger.*;

public class ListenerTest {
}
@BeforeEach
public void initLogger() {
    SelenideLogger.addListener("allure", new AllureSelenide())
            .savePageSource(true)
            .screenshots(true);
}
