package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static helpers.Attach.*;
import static io.qameta.allure.Allure.step;

public class TestBase {
    @BeforeAll
    static void config() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.browserCapabilities = new DesiredCapabilities();
        Configuration.browserSize = "1980x1024";
        Configuration.headless = false;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub/";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    public void tearDown(){
        screenShotAs("After test screenshot");
        pageSource();
        browserConsoleLogs();
        attachVideo();
        step ("Закрываем браузер", Selenide::closeWebDriver);
    }
}
