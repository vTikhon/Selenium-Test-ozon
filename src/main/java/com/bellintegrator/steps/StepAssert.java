package com.bellintegrator.steps;

import com.bellintegrator.pages.ozon.*;
import com.bellintegrator.service.*;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import java.util.*;

/**
 * Класс StepAssert содержит методы для выполнения проверок на страницах интернет-магазина Ozon.
 *
 * @version 1.0
 * @since 2024-06-21
 * @author Vergentev Tikhon
 */
public class StepAssert {

    private WebDriver webDriver;

    /**
     * Конструктор класса StepAssert.
     *
     * @param webDriver экземпляр WebDriver для взаимодействия с браузером.
     */
    public StepAssert(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    /**
     * Проверяет, что текущий URL содержит заданный фрагмент.
     *
     * @param urlFragment фрагмент URL, который должен содержаться в текущем URL.
     */
    @Step("Проверка, что текущий URL содержит {urlFragment}")
    public void assertCurrentUrlContains(String urlFragment) {
        boolean isContains = webDriver.getCurrentUrl().contains(urlFragment);
        Assertions.assertTrue(isContains, "Страница неверная.");
    }

    /**
     * Проверяет, что количество найденных товаров больше заданного значения.
     *
     * @param ozonCatalogPage страница каталога Ozon.
     * @param quantityItems минимальное количество товаров.
     */
    @Step("Проверка количества найденных товаров, должно быть больше {quantityItems}")
    public void assertItemsQuantity(OzonCatalogPage ozonCatalogPage, String quantityItems) {
        List<WebElement> items = ozonCatalogPage.getFoundItems();
        boolean isMore = items.size() > Integer.parseInt(quantityItems);
        Assertions.assertTrue(isMore, "Результатов меньше " + quantityItems);
    }

    /**
     * Проверяет соответствие найденных товаров заданным условиям.
     * Диапазон цен и бренды.
     *
     * @param ozonCatalogPage страница каталога Ozon.
     * @param minPriceInput минимальная цена.
     * @param maxPriceInput максимальная цена.
     * @param brandOne первый бренд.
     * @param brandTwo второй бренд.
     */
    @Step("Проверка найденных товаров на соответствие условиям:" +
            " диапазон цен от {minPriceInput} до {maxPriceInput}, бренды {brandOne} и {brandTwo}")
    public void verifyItems(OzonCatalogPage ozonCatalogPage, String minPriceInput, String maxPriceInput,
                            String brandOne, String brandTwo) {
        List<WebElement> items = ozonCatalogPage.getFoundItems();
        for (WebElement item : items) {
            String originWindow = webDriver.getWindowHandle();
            String newTab = openNewItemWindow(item);
            webDriver.switchTo().window(newTab);

            OzonItemPage ozonItemPage = new OzonItemPage(webDriver);
            WebElement checkedPrice = ozonItemPage.isPriceWithOzonCardExist() ? ozonItemPage.getPriceWithOzonCard()
                    : ozonItemPage.getPrice(); // в случае отсутсвия цены по карте
            String itemPrice = checkedPrice.getText().replaceAll("[\\u00A0₽]", "")
                    .replaceAll("[\\s\\u00A0\\u2009]", "");
            int itemPriceInt = Integer.parseInt(itemPrice);
            int minPriceInputInt = Integer.parseInt(minPriceInput);
            int maxPriceInputInt = Integer.parseInt(maxPriceInput);

            Assertions.assertTrue(itemPriceInt >= minPriceInputInt, "Цена меньше " + minPriceInputInt);
            Assertions.assertTrue(itemPriceInt <= maxPriceInputInt, "Цена больше " + maxPriceInputInt);

            String nameItem = ozonItemPage.getNameItem().getText();
            boolean isNameContainsProducer = nameItem.contains(brandOne) || nameItem.contains(brandTwo);
            Assertions.assertTrue(isNameContainsProducer,
                    "Название не содержит производителя " + brandOne + " or " + brandTwo);

            webDriver.close();
            webDriver.switchTo().window(originWindow);
        }
    }

    /**
     * Проверяет, что продукт найден по имени.
     *
     * @param ozonCatalogPage страница каталога Ozon.
     * @param firstNameItem имя искомого товара.
     */
    @Step("Проверка, что продукт найден: {firstNameItem}")
    public void assertItemFound(OzonCatalogPage ozonCatalogPage, String firstNameItem) {
        List<String> nameItems = ozonCatalogPage.getFoundNameItems(ozonCatalogPage.getFoundItems());
        boolean isNameExists = nameItems.stream().anyMatch(nameItem -> nameItem.equalsIgnoreCase(firstNameItem));
        Assertions.assertTrue(isNameExists, "Продукт не найден " + firstNameItem);
        ScreenShooter.getScreen(webDriver);
    }

    /**
     * Открывает новую вкладку с товаром и возвращает дескриптор новой вкладки.
     *
     * @param item элемент, содержащий ссылку на товар.
     * @return дескриптор новой вкладки.
     */
    private String openNewItemWindow(WebElement item) {
        Set<String> existingWindows = webDriver.getWindowHandles();
        String itemLink = item.getAttribute("href");
        ((JavascriptExecutor) webDriver).executeScript("window.open(arguments[0]);", itemLink);
        Set<String> newWindows = webDriver.getWindowHandles();
        newWindows.removeAll(existingWindows);
        return newWindows.iterator().next();
    }
}
