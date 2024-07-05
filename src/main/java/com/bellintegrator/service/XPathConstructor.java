package com.bellintegrator.service;

/**
 * Класс XPathConstructor предоставляет методы для динамического создания XPath выражений.
 *
 * @version 1.0
 * @since 2024-07-02
 * @author Vergentev Tikhon
 */
public class XPathConstructor {

    /**
     * Создает XPath выражение на основе переданных параметров.
     *
     * @param enterCommon  начальная часть XPath выражения
     * @param tagName      имя тега
     * @param condition    условие для поиска
     * @param attribute    атрибут для сравнения
     * @param sign         знак сравнения
     * @param text         текст для сравнения
     * @param tailCommon   завершающая часть XPath выражения
     * @return сформированное XPath выражение
     */
    public static String constructXPath(String enterCommon,
                                        String tagName,
                                        String condition,
                                        String attribute,
                                        String sign,
                                        String text,
                                        String tailCommon) {
        return String.format("%s%s[%s(%s%s'%s')]%s", enterCommon, tagName, condition, attribute, sign, text, tailCommon);
    }

    /**
     * Создает условие для XPath выражения на основе переданных параметров.
     *
     * @param sibling тип связи с элементом (например, preceding-sibling, following-sibling)
     * @param xPath   XPath выражение для элемента, с которым необходимо установить связь
     * @return сформированное условие для XPath выражения
     */
    public static String constructConditionForXPath(String sibling,
                                                    String xPath) {
        return String.format("[%s::%s]", sibling, xPath);
    }
}
