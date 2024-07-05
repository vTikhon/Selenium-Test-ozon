package com.bellintegrator.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BannerCloser {

    /**
     * Закрывает предупреждение о файлах cookie, найденное по заданному XPath выражению.
     *
     * @param wait WebDriverWait для ожидания видимости элемента
     * @param xpathExpression XPath выражение для поиска предупреждения о файлах cookie
     */
    public static void closeCookieWarning(WebDriverWait wait, String xpathExpression) {
        WebElement cookieWarning = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));
        if (cookieWarning != null && cookieWarning.isDisplayed() && cookieWarning.isEnabled()) {
            cookieWarning.click();
        }
    }

    /**
     * Закрывает предложение о смене локации, найденное по заданному XPath выражению.
     *
     * @param wait WebDriverWait для ожидания видимости элемента
     * @param xpathExpression XPath выражение для поиска предупреждения о проверки локации
     */
    public static void closeLocationWarning(WebDriverWait wait, String xpathExpression) {
        WebElement warningButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathExpression)));
        if (warningButton != null && warningButton.isDisplayed() && warningButton.isEnabled()) {
            warningButton.click();
        }
    }
}
