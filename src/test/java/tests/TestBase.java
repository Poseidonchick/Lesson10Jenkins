package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.ConfigLoader;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static helpers.Attach.*;
import static io.qameta.allure.Allure.step;
import static java.lang.String.format;

public class TestBase {


    @BeforeAll
    static void config() {
        ConfigLoader credentials = ConfigFactory.create(ConfigLoader.class);
        String login = credentials.login();
        String password = credentials.password();
        Configuration.remote = format("https://%s:%s@%s", login, password, System.getProperty("url"));
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.browserCapabilities = new DesiredCapabilities();
        Configuration.browserSize = "1980x1024";
        Configuration.headless = false;
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
