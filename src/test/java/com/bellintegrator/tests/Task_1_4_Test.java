package com.bellintegrator.tests;

import com.bellintegrator.pages.ozon.OzonCatalogPage;
import com.bellintegrator.pages.ozon.OzonStartPage;
import com.bellintegrator.steps.Steps;
import com.bellintegrator.steps.StepAssert;
import com.bellintegrator.webdriver.WebDriverInitialization;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Этот класс содержит тесты для проверки интернет-магазина Ozon.
 * Используются параметризованные тесты для выполнения одного и того же теста с различными наборами данных.
 *
 * @version 1.0
 * @since 2024-06-21
 * @author Vergentev Tikhon
 */
public class Task_1_4_Test extends WebDriverInitialization {

    /**
     * Тест для поиска и проверки товаров на сайте интернет-магазина Ozon.
     *
     * @param startURL       Начальный URL для начала теста.
     * @param checkURL       Ожидаемый фрагмент URL для проверки.
     * @param brandOne       Первый бренд для выбора в фильтре.
     * @param brandTwo       Второй бренд для выбора в фильтре.
     * @param minPriceInput  Минимальная цена для установки в фильтре.
     * @param maxPriceInput  Максимальная цена для установки в фильтре.
     * @param quantityItems  Минимальное количество товаров, ожидаемое в результатах поиска.
     */
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @MethodSource("com.bellintegrator.data.DataProvider#dataForTestFour")
    @DisplayName("Проверка интернет-магазина Ozon")
    public void testSearchingLaptops(String startURL, String cockieButtonName, String menuName, String innerMenuName, String checkURL, String brandOne, String brandTwo,
                                   String minPriceInput, String maxPriceInput,
                                   String quantityItems) {

        Steps steps = new Steps(webDriver);
        StepAssert stepAssert = new StepAssert(webDriver);

        OzonStartPage ozonStartPage = new OzonStartPage(webDriver);
        steps.openPage(webDriver, startURL);
        steps.clickRefreshButton(ozonStartPage);
        steps.closeCookie(ozonStartPage, cockieButtonName);
        steps.skipLocationAccept(ozonStartPage);
        steps.clickCatalogButton(ozonStartPage);
        steps.navigateToMenu(ozonStartPage, menuName);
        steps.navigateToInnerMenu(ozonStartPage, innerMenuName);
        steps.switchToNewWindow();

        OzonCatalogPage ozonCatalogPage = new OzonCatalogPage(webDriver);
        stepAssert.assertCurrentUrlContains(checkURL);

        steps.setPriceRange(ozonCatalogPage, minPriceInput, maxPriceInput);
        steps.selectBrands(ozonCatalogPage, brandOne, brandTwo);
        steps.scrollPageDown();
        stepAssert.assertItemsQuantity(ozonCatalogPage, quantityItems);
        stepAssert.verifyItems(ozonCatalogPage, minPriceInput, maxPriceInput, brandOne, brandTwo);

        String firstNameItem = ozonCatalogPage.getFoundNameItems(ozonCatalogPage.getFoundItems()).get(0);
        steps.searchItem(ozonCatalogPage, firstNameItem);
        stepAssert.assertItemFound(ozonCatalogPage, firstNameItem);
    }
}
