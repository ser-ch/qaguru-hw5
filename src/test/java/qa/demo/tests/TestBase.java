package qa.demo.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import qa.demo.helpers.Attach;
import qa.demo.pages.RegistrationPage;

public class TestBase {
    protected RegistrationPage registrationPage = new RegistrationPage();

    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.browserSize = "1920x1080";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub/";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
