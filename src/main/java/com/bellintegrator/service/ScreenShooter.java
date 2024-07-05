package com.bellintegrator.service;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Класс ScreenShooter содержит методы для создания скриншотов веб-страниц и их элементов.
 *
 * @version 1.0
 * @since 2024-06-21
 * @author Vergentev Tikhon
 */
public class ScreenShooter {

    /**
     * Делает скриншот текущего состояния браузера и прикрепляет его к отчету Allure.
     *
     * @param driver экземпляр WebDriver для взаимодействия с браузером.
     * @return массив байтов, содержащий изображение скриншота.
     */
    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] getScreen(WebDriver driver){
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("src/main/resources/screenshots/screen.png"));
            return Files.readAllBytes(Paths.get("src/main/resources/screenshots", "screen.png"));
        } catch (Exception e){
            e.printStackTrace();
            Assertions.fail("Не удалось сохранить файл на диск");
        }
        return new byte[0];
    }

    /**
     * Делает скриншот указанного элемента веб-страницы и прикрепляет его к отчету Allure.
     *
     * @param driver экземпляр WebDriver для взаимодействия с браузером.
     * @param element веб-элемент, который нужно выделить перед созданием скриншота.
     * @return массив байтов, содержащий изображение скриншота.
     */
    @Attachment(value = "Element screenshot", type = "image/png")
    public static byte[] getScreen(WebDriver driver, WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
        return getScreen(driver);
    }

}
