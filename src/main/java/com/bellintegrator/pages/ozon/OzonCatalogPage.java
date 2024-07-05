package com.bellintegrator.pages.ozon;

import com.bellintegrator.properties.Properties;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс OzonCatalogPage предоставляет методы для взаимодействия с элементами страницы каталога на сайте Ozon.
 * Используется для выполнения различных действий, таких как установка ценового диапазона, выбор брендов и поиск товаров.
 *
 * @version 1.0
 * @since 2024-06-21
 * @author Vergentev Tikhon
 */
public class OzonCatalogPage {

    private final WebDriver webDriver;
    private final WebDriverWait wait;

    /**
     * Поле для ввода минимальной цены.
     */
    @FindBy(how = How.XPATH, using = "//div[@data-widget='filtersDesktop']//div[@filter-key='currency_price']" +
            "//input[@type='text'][following-sibling::p[contains(text(), 'от')]]")
    private WebElement minPriceInput;

    /**
     * Поле для ввода максимальной цены.
     */
    @FindBy(how = How.XPATH, using = "//div[@data-widget='filtersDesktop']//div[@filter-key='currency_price']" +
            "//input[@type='text'][following-sibling::p[contains(text(), 'до')]]")
    private WebElement maxPriceInput;

    /**
     * Кнопка для показа всех брендов.
     */
    @FindBy(how = How.XPATH, using = "//div[@data-widget='filtersDesktop']" +
            "//div[@lexems='[object Object]'][preceding-sibling::div[span[text()='Бренд']]]" +
            "//button[div[text()='Посмотреть все']]")
    private WebElement showAllBrandNamesButton;

    /**
     * Список элементов, представляющих бренды.
     */
    @FindBy(how = How.XPATH, using = "//div[@data-widget='filtersDesktop']" +
            "//div[@lexems='[object Object]'][preceding-sibling::div[span[text()='Бренд']]]" +
            "//span[contains(text(), '')]")
    private List<WebElement> brandNames;

    /**
     * Список найденных товаров.
     */
    @FindBy(how = How.XPATH, using = "//div[@data-widget='searchResultsV2']" +
            "//a[contains(@href, '') and not(contains(@data-prerender, 'true'))]")
    private List<WebElement> foundItems;

    /**
     * Поле для ввода поискового запроса.
     */
    @FindBy(how = How.XPATH, using = "//div[@data-widget='searchBarDesktop']" +
            "//form[@action='/search']//input[@placeholder='Искать на Ozon']")
    private WebElement searchArea;

    /**
     * Кнопка для выполнения поиска.
     */
    @FindBy(how = How.XPATH, using = "//div[@data-widget='searchBarDesktop']//form[@action='/search']" +
            "//button[@type='submit']")
    private WebElement searchButton;

    /**
     * Конструктор класса. Инициализирует элементы страницы и устанавливает ожидание для WebDriver.
     *
     * @param webDriver экземпляр WebDriver для взаимодействия с браузером.
     */
    public OzonCatalogPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, Properties.testsProperties.webDriverWait());
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Возвращает список найденных товаров, ожидая их видимости.
     *
     * @return список веб-элементов, представляющих найденные товары.
     */
    public List<WebElement> getFoundItems() {
        return wait.until(ExpectedConditions.visibilityOfAllElements(foundItems));
    }

    /**
     * Возвращает элемент кнопки "Посмотреть все бренды", ожидая его видимости.
     *
     * @return элемент кнопки "Посмотреть все бренды".
     */
    public WebElement getShowAllBrandNamesButton() {
        return wait.until(ExpectedConditions.visibilityOf(showAllBrandNamesButton));
    }

    /**
     * Возвращает элемент для ввода поискового запроса, ожидая его кликабельности.
     *
     * @return элемент ввода поискового запроса.
     */
    public WebElement getSearchArea() {
        return wait.until(ExpectedConditions.elementToBeClickable(searchArea));
    }

    /**
     * Вводит заданный поисковый запрос в поле поиска.
     *
     * @param query поисковый запрос для ввода.
     */
    public void enterSearchQuery(String query) {
        getSearchArea().sendKeys(query);
    }

    /**
     * Возвращает элемент кнопки для выполнения поиска, ожидая его кликабельности.
     *
     * @return элемент кнопки для выполнения поиска.
     */
    public WebElement getSearchButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(searchButton));
    }

    /**
     * Устанавливает минимальную цену в фильтре и ожидает завершения обновления страницы.
     *
     * @param price минимальная цена для установки.
     */
    public void setMinPrice(String price) {
        WebElement minPrice = wait.until(ExpectedConditions.elementToBeClickable(minPriceInput));
        Actions actions = new Actions(webDriver);
        actions.click(minPrice)
                .keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys(price)
                .sendKeys(Keys.ENTER)
                .perform();
        waitForLoadingIndicator();
    }

    /**
     * Устанавливает максимальную цену в фильтре и ожидает завершения обновления страницы.
     *
     * @param price максимальная цена для установки.
     */
    public void setMaxPrice(String price) {
        WebElement maxPrice = wait.until(ExpectedConditions.elementToBeClickable(maxPriceInput));
        Actions actions = new Actions(webDriver);
        actions.click(maxPrice)
                .keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys(price)
                .sendKeys(Keys.ENTER)
                .perform();
        waitForLoadingIndicator();
    }

    /**
     * Выбирает бренд по имени и ожидает завершения обновления страницы.
     *
     * @param name имя бренда для выбора.
     */
    public void selectBrand(String name) {
        for (WebElement brandName : brandNames) {
            if (brandName.getText().equalsIgnoreCase(name)) {
                wait.until(ExpectedConditions.elementToBeClickable(brandName)).click();
                waitForLoadingIndicator();
                return;
            }
        }
        Assertions.fail("Brand not found: " + name);
    }

    /**
     * Ожидает, пока индикатор загрузки исчезнет, что означает завершение обновления страницы.
     */
    private void waitForLoadingIndicator() {
        By loadingIndicatorBy =
                By.xpath("//div[contains(@class, '')][following-sibling::div[@id='paginatorContent']]");
        WebElement loadingElement = wait.until(ExpectedConditions.presenceOfElementLocated(loadingIndicatorBy));
        wait.until(ExpectedConditions.stalenessOf(loadingElement));
    }

    /**
     * Возвращает список имен найденных товаров.
     *
     * @param webElements список веб-элементов, представляющих товары.
     * @return список имен найденных товаров.
     */
    public List<String> getFoundNameItems(List<WebElement> webElements) {
        List<String> foundNameItems = new ArrayList<>();
        for (WebElement webElement : webElements) {
            foundNameItems.add(webElement.findElement(By.xpath(".//span")).getText());
        }
        return foundNameItems;
    }
}
