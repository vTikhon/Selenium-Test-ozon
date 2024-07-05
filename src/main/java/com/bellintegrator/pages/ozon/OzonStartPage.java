package com.bellintegrator.pages.ozon;

import com.bellintegrator.properties.Properties;
import com.bellintegrator.service.WebElementsSearcher;
import com.bellintegrator.service.XPathConstructor;
import com.bellintegrator.service.BannerCloser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Класс OzonStartPage предоставляет методы для взаимодействия с элементами стартовой страницы Ozon.
 * Используется для выполнения различных действий, таких как навигация по меню, работа с баннерами и кнопками.
 *
 * @version 1.0
 * @since 2024-06-21
 * @author Vergentev Tikhon
 */
public class OzonStartPage {

    private final WebDriver webDriver;
    private final WebDriverWait wait;

    /**
     * Кнопка для открытия каталога.
     */
    @FindBy(how = How.XPATH, using = "//div[@data-widget='catalogMenu']//button")
    private WebElement catalogButton;

    /**
     * Кнопка для обновления страницы.
     */
    @FindBy(how = How.XPATH, using = "//button[contains(text(), 'Обновить')]")
    private WebElement refreshButton;

    /**
     * Конструктор класса. Инициализирует элементы страницы и устанавливает ожидание для WebDriver.
     *
     * @param webDriver экземпляр WebDriver для взаимодействия с браузером.
     */
    public OzonStartPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, Properties.testsProperties.webDriverWait());
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Возвращает кнопку открытия каталога, предварительно закрывая предупреждения о cookies и местоположении.
     *
     * @return элемент кнопки открытия каталога.
     */
    public WebElement getCatalogButton() {
        return wait.until(ExpectedConditions.visibilityOf(catalogButton));
    }

    /**
     * Возвращает элемент меню с заданным именем, ожидая его видимости.
     *
     * @return элемент меню с заданным именем.
     */
    public WebElement getMenu(String menuName) {
        String xpathForMenu = XPathConstructor
                .constructXPath("//", "span", "", "text()", "=", menuName, "") ;
        return WebElementsSearcher.getVisibleWebElements(wait, xpathForMenu).get(0);
    }

    /**
     * Возвращает кнопку для перехода к разделу "Ноутбуки", ожидая ее видимости.
     *
     * @return элемент кнопки для перехода к разделу "Ноутбуки".
     */
    public WebElement getInnerMenu(String innerMenu) {
        String xpathForInnerMenu = XPathConstructor
                .constructXPath("//", "a", "", "text()","=", innerMenu, "") ;
        return WebElementsSearcher.getVisibleWebElements(wait, xpathForInnerMenu).get(0);
    }

    /**
     * Возвращает кнопку для обновления страницы, ожидая ее видимости.
     *
     * @return элемент кнопки для обновления страницы.
     */
    public WebElement getRefreshButton() {
        return wait.until(ExpectedConditions.visibilityOf(refreshButton));
    }

    /**
     * Закрываем предупреждение о cookies.
     *
     */
    public void closeCookie(String coockieNameButton) {
        String coockieXPath1 = XPathConstructor
                .constructXPath("//", "div", "contains", "@data-widget", ",", "ookie", "");
        String coockieXPath2 = XPathConstructor
                .constructXPath("//", "button", "contains", "text()", ",", "", "");
        String coockieXPath3 = XPathConstructor
                .constructXPath("", "div", "", "text()", "=", coockieNameButton, "");
        String conditionForXPath2 = XPathConstructor
                .constructConditionForXPath("descendant", coockieXPath3);
        String cockieFullXpath = coockieXPath1 + coockieXPath2 + conditionForXPath2;
        BannerCloser.closeCookieWarning(wait, cockieFullXpath);
    }

    /**
     * Закрываем предложение о смене локации.
     *
     */
    public void skipLocationAccept() {
        String xpath1 = XPathConstructor
                .constructXPath("//", "div", "", "@class", "=","vue-portal-target", "");
        String xpath2 = XPathConstructor
                .constructXPath("//", "button", "", "@type", "=","button", "");
        String fullXPath = xpath1 + xpath2;
        BannerCloser.closeLocationWarning(wait, fullXPath);
    }
}
