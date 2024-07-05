package com.bellintegrator.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.*;

/**
 * Вспомогательный класс для работы с веб-элементами в тестах с использованием Selenium WebDriver.
 *
 * @version 1.0
 * @since 2024-07-02
 * @author Vergentev Tikhon
 */
public class WebElementsSearcher {

    /**
     * Возвращает список видимых веб-элементов, найденных по заданному XPath выражению.
     *
     * @param wait WebDriverWait для ожидания видимости элементов
     * @param xpathExpression XPath выражение для поиска элементов
     * @return список видимых веб-элементов, или null, если элементы не найдены
     */
    public static List<WebElement> getVisibleWebElements(WebDriverWait wait, String xpathExpression) {
        List<WebElement> webElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpathExpression)));
        if (webElements != null && !webElements.isEmpty()) {
            return webElements;
        } else {
            Assertions.fail("Элемент не найден на странице");
            return Collections.emptyList();
        }
    }

}
