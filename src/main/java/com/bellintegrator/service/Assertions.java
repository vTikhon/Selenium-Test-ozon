package com.bellintegrator.service;

import io.qameta.allure.Step;
import com.bellintegrator.webdriver.WebDriverInitialization;
import org.openqa.selenium.WebDriver;

/**
 * Класс Assertions содержит методы для выполнения проверок в тестах с использованием аннотаций Allure.
 * Эти методы добавляют шаги в отчеты Allure, что позволяет сделать тесты более информативными.
 *
 * @version 1.0
 * @since 2024-07-02
 */
public class Assertions {

    /**
     * Проверяет, что условие истинно.
     *
     * @param condition условие, которое должно быть истинным.
     * @param message сообщение, которое будет выведено в случае, если условие ложно.
     */
    @Step("Проверяем, что условие истинно: {message}")
    public static void assertTrue(boolean condition, String message) {
        org.junit.jupiter.api.Assertions.assertTrue(condition, message);
        WebDriver webDriver = WebDriverInitialization.webDriver;
        if (webDriver != null) {
            ScreenShooter.getScreen(webDriver);
        }
    }

    /**
     * Регистрирует неудачу проверки с указанным сообщением.
     *
     * @param message сообщение, которое будет выведено в отчете о неудаче.
     */
    @Step("Проверка завершилась неудачей: {message}")
    public static void fail(String message) {
        org.junit.jupiter.api.Assertions.fail(message);
        WebDriver webDriver = WebDriverInitialization.webDriver;
        if (webDriver != null) {
            ScreenShooter.getScreen(webDriver);
        }
    }
}
