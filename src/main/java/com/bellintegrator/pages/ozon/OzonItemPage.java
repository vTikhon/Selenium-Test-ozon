package com.bellintegrator.pages.ozon;

import com.bellintegrator.properties.Properties;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Класс OzonItemPage предоставляет методы для взаимодействия с элементами страницы товара на сайте Ozon.
 * Используется для получения информации о товаре, такой как цена и название.
 *
 * @version 1.0
 * @since 2024-06-21
 * @author Vergentev Tikhon
 */
public class OzonItemPage {

    private final WebDriver webDriver;
    private final WebDriverWait wait;

    /**
     * Элемент, представляющий цену с картой Ozon.
     */
    @FindBy(how = How.XPATH, using = "//div[@data-widget='webPrice']//button[@type='button']" +
            "//span[contains(text(), '₽')]")
    private WebElement priceWithOzonCard;

    /**
     * Элемент, представляющий обычную цену.
     */
    @FindBy(how = How.XPATH, using = "//div[@data-widget='webPrice']" +
            "//span[contains(text(), '₽')][following-sibling::span]")
    private WebElement price;

    /**
     * Элемент, представляющий название товара.
     */
    @FindBy(how = How.XPATH, using = "//h1[contains(@class, 'Headline')]")
    private WebElement nameItem;

    /**
     * Конструктор класса. Инициализирует элементы страницы и устанавливает ожидание для WebDriver.
     *
     * @param webDriver экземпляр WebDriver для взаимодействия с браузером.
     */
    public OzonItemPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, Properties.testsProperties.webDriverWait());
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Проверяет наличие элемента цены с картой Ozon.
     *
     * @return true, если элемент цены с картой Ozon присутствует, иначе false.
     */
    public boolean isPriceWithOzonCardExist() {
        try {
            wait.until(ExpectedConditions.visibilityOf(priceWithOzonCard));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Возвращает элемент, представляющий цену с картой Ozon, ожидая его видимости.
     *
     * @return элемент, представляющий цену с картой Ozon.
     */
    public WebElement getPriceWithOzonCard() {
        return wait.until(ExpectedConditions.visibilityOf(priceWithOzonCard));
    }

    /**
     * Возвращает элемент, представляющий название товара, ожидая его видимости.
     *
     * @return элемент, представляющий название товара.
     */
    public WebElement getNameItem() {
        return wait.until(ExpectedConditions.visibilityOf(nameItem));
    }

    /**
     * Возвращает элемент, представляющий обычную цену, ожидая его видимости.
     *
     * @return элемент, представляющий обычную цену.
     */
    public WebElement getPrice() {
        return wait.until(ExpectedConditions.visibilityOf(price));
    }
}
