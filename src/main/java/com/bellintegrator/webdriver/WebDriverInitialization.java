package com.bellintegrator.webdriver;

import com.bellintegrator.properties.Properties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

/**
 * Базовый класс для инициализации WebDriver перед каждым тестом и его завершения после каждого теста.
 * Используется для настройки и управления WebDriver в тестах.
 *
 * @version 1.0
 * @since 2024-06-16
 * @author Vergentev Tikhon
 */
public class WebDriverInitialization {

    /**
     * Экземпляр WebDriver, используемый в тестах.
     */
    public static WebDriver webDriver;

    /**
     * Метод, выполняющийся перед каждым тестом.
     * Инициализирует WebDriver, настраивает его и открывает браузер Chrome.
     */
    @BeforeEach
    public void setUpDriver() {
        if (System.getenv("CHROME_DRIVER") != null) {
            System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        } else {
            System.setProperty("webdriver.chrome.driver", Properties.testsProperties.chromeDriverPath());
        }
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Properties.testsProperties.implicitlyWait(), TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(Properties.testsProperties.pageLoadTimeout(), TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(Properties.testsProperties.scriptTimeout(), TimeUnit.SECONDS);
    }

    /**
     * Метод, выполняющийся после каждого теста.
     * Завершает работу WebDriver, закрывая браузер.
     */
    @AfterEach
    public void quit() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }

}
