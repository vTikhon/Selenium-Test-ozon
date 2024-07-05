package com.bellintegrator.properties;

import org.aeonbits.owner.Config;

/**
 * Интерфейс для доступа к свойствам тестирования.
 * Использует библиотеку OWNER для загрузки свойств из различных источников.
 *
 * @version 1.0
 * @since 2024-07-02
 * @author Vergentev Tikhon
 */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "file:src/main/resources/tests.properties"
})
public interface TestsProperties extends Config {

    /**
     * Путь к драйверу Chrome.
     * @return строка, содержащая путь к драйверу Chrome.
     */
    @Config.Key("webdriver.chrome.driver")
    String chromeDriverPath();

    /**
     * Время неявного ожидания для WebDriver в секундах.
     * @return целое число, представляющее время неявного ожидания.
     */
    @Config.Key("webdriver.timeouts.implicitlywait")
    int implicitlyWait();

    /**
     * Время ожидания загрузки страницы для WebDriver в секундах.
     * @return целое число, представляющее время ожидания загрузки страницы.
     */
    @Config.Key("webdriver.timeouts.pageloadtimeout")
    int pageLoadTimeout();

    /**
     * Время ожидания выполнения скрипта для WebDriver в секундах.
     * @return целое число, представляющее время ожидания выполнения скрипта.
     */
    @Config.Key("webdriver.timeouts.scripttimeout")
    int scriptTimeout();

    /**
     * Время явного ожидания для WebDriver в секундах.
     * @return целое число, представляющее время явного ожидания.
     */
    @Config.Key("webdriver.wait")
    int webDriverWait();

    /**
     * URL для тестирования на сайте Ozon.
     * @return строка, содержащая URL Ozon.
     */
    @Config.Key("ozon.url")
    String ozonUrl();
}
