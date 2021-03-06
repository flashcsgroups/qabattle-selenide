package com.test.qabattle;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.test.qabattle.AllureHelpers.takeScreenshot;

public class BaseTestClass {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("beforeClass");
        if (isUnix()) {
            Configuration.browser = MyRemoteWebDriverClass.class.getName();
            Configuration.browserSize = "1920x1080";
            //Configuration.startMaximized = true;
            System.out.println("RemoteWebDriver");
        } else {
            Configuration.browser = MyChromeBrowserClass.class.getName();
            if (isUnix())
                Configuration.browserSize = "1920x1080";
            else
                Configuration.startMaximized = true;

            System.out.println("LocalWebDriver");
        }

        Configuration.reportsFolder = "target/reports";
        Configuration.screenshots = false;

        Configuration.baseUrl = System.getProperty("base.url");

        SelenideLogger.addListener("Allure Selenide", new AllureSelenide());
    }


    public static boolean isUnix() {
        String os = System.getProperty("os.name").toLowerCase();
        //System.out.println("isUnix: " + os);
        return (os.contains("nix") || os.contains("nux"));// || isWindows(); // linux or unix
    }

}
