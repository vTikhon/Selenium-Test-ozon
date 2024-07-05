package com.bellintegrator.steps;

import com.bellintegrator.service.ScreenShooter;
import io.qameta.allure.Step;
import com.bellintegrator.pages.ozon.OzonCatalogPage;
import com.bellintegrator.pages.ozon.OzonStartPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

/**
 * Класс Steps содержит шаги для взаимодействия с веб-страницами Ozon.
 *
 * @version 1.0
 * @since 2024-06-21
 * @author Vergentev Tikhon
 */
public class Steps {

    private WebDriver webDriver;

    /**
     * Конструктор класса Steps.
     *
     * @param webDriver экземпляр WebDriver для взаимодействия с браузером.
     */
    public Steps(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    /**
     * Ищет и проверяет наличие товара на странице каталога Ozon.
     *
     * @param webDriver экземпляр WebDriver для взаимодействия с браузером.
     * @param startURL  ссылка веб-страницы.
     */
    @Step("Открытие новой страницы: {startURL}")
    public void openPage(WebDriver webDriver, String startURL) {
        webDriver.get(startURL);
        ScreenShooter.getScreen(webDriver);
    }

    /**
     * Нажимает кнопку обновления на стартовой странице Ozon.
     *
     * @param ozonStartPage экземпляр страницы OzonStartPage.
     */
    @Step("Нажимаем кнопку обновить")
    public void clickRefreshButton(OzonStartPage ozonStartPage) {
        ozonStartPage.getRefreshButton().click();
        ScreenShooter.getScreen(webDriver);
    }

    /**
     * Закрываем предупреждение о cookies.
     *
     */
    @Step("Закрываем предупреждение о cookie")
    public void closeCookie(OzonStartPage ozonStartPage, String coockieNameButton) {
        ozonStartPage.closeCookie(coockieNameButton);
        ScreenShooter.getScreen(webDriver);
    }

    /**
     * Закрываем предложение о смене локации.
     *
     */
    @Step("Закрываем предложение о смене локации")
    public void skipLocationAccept(OzonStartPage ozonStartPage) {
        ozonStartPage.skipLocationAccept();
        ScreenShooter.getScreen(webDriver);
    }

    /**
     * Нажимает кнопку каталога на стартовой странице Ozon.
     *
     * @param ozonStartPage экземпляр страницы OzonStartPage.
     */
    @Step("Нажимаем кнопку каталог")
    public void clickCatalogButton(OzonStartPage ozonStartPage) {
        ozonStartPage.getCatalogButton().click();
        ScreenShooter.getScreen(webDriver);
    }

    /**
     * Наводит курсор на меню с заданным именем на стартовой странице Ozon.
     *
     * @param ozonStartPage экземпляр страницы OzonStartPage.
     */
    @Step("Навигация к меню {menu}")
    public void navigateToMenu(OzonStartPage ozonStartPage, String menuName) {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(ozonStartPage.getMenu(menuName)).perform();
        ScreenShooter.getScreen(webDriver);
    }

    /**
     * Переходит к внутреннему меню по заданному URL.
     *
     * @param ozonStartPage экземпляр страницы OzonStartPage.
     */
    @Step("Навигация к подменю {innerMenu}")
    public void navigateToInnerMenu(OzonStartPage ozonStartPage, String innerMenu) {
        ozonStartPage.getInnerMenu(innerMenu).click();
        ScreenShooter.getScreen(webDriver);
    }

    /**
     * Переключается на новое окно и закрывает исходное окно.
     */
    @Step("Переключаемся на новое окно")
    public void switchToNewWindow() {
        String originalWindow = webDriver.getWindowHandle();
        Set<String> allWindows = webDriver.getWindowHandles();
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                webDriver.close();
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }
        ScreenShooter.getScreen(webDriver);
    }

    /**
     * Устанавливает диапазон цен на странице каталога Ozon.
     *
     * @param ozonCatalogPage экземпляр страницы OzonCatalogPage.
     * @param minPriceInput   минимальная цена.
     * @param maxPriceInput   максимальная цена.
     * @throws InterruptedException если поток будет прерван во время ожидания.
     */
    @Step("Установка диапазона цен: от {minPriceInput} до {maxPriceInput}")
    public void setPriceRange(OzonCatalogPage ozonCatalogPage, String minPriceInput, String maxPriceInput) {
        ozonCatalogPage.setMinPrice(minPriceInput);
        ozonCatalogPage.setMaxPrice(maxPriceInput);
        ScreenShooter.getScreen(webDriver);
    }

    /**
     * Выбирает заданные бренды на странице каталога Ozon.
     *
     * @param ozonCatalogPage экземпляр страницы OzonCatalogPage.
     * @param brandOne        первый бренд.
     * @param brandTwo        второй бренд.
     * @throws InterruptedException если поток будет прерван во время ожидания.
     */
    @Step("Выбор брендов: {brandOne} и {brandTwo}")
    public void selectBrands(OzonCatalogPage ozonCatalogPage, String brandOne, String brandTwo) {
        ozonCatalogPage.getShowAllBrandNamesButton().click();
        ozonCatalogPage.selectBrand(brandOne);
        ozonCatalogPage.selectBrand(brandTwo);
        ScreenShooter.getScreen(webDriver);
    }

    /**
     * Прокручивает страницу вниз.
     */
    @Step("Скроллинг страницы вниз")
    public void scrollPageDown() {
        Actions actions = new Actions(webDriver);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        ScreenShooter.getScreen(webDriver);
    }

    /**
     * Ищет и проверяет наличие товара на странице каталога Ozon.
     *
     * @param ozonCatalogPage экземпляр страницы OzonCatalogPage.
     * @param firstNameItem   имя искомого товара.
     */
    @Step("Поиск и проверка товара: {firstNameItem}")
    public void searchItem(OzonCatalogPage ozonCatalogPage, String firstNameItem) {
        ozonCatalogPage.enterSearchQuery(firstNameItem);
        ozonCatalogPage.getSearchButton().click();
        ScreenShooter.getScreen(webDriver);
    }

}
