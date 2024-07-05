package com.bellintegrator.data;

import com.bellintegrator.properties.Properties;
import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

/**
 * Класс DataProvider предоставляет данные для параметризованных тестов.
 * Содержит методы, которые возвращают потоки аргументов для тестов.
 * Каждый метод в этом классе используется для предоставления данных для конкретного теста.
 *
 * @version 1.0
 * @since 2024-06-18
 * @author Vergentev Tikhon
 */
public class DataProvider {


    /**
     * Предоставляет данные для третьего теста.
     *
     * @return Stream из аргументов, содержащих URL и текст для поиска на странице.
     */
    public static Stream<Arguments> dataForTestFour() {
        return Stream.of(
                Arguments.of(
                        Properties.testsProperties.ozonUrl(),
                        "ОК",
                        "Электроника",
                        "Ноутбуки",
                        "https://www.ozon.ru/category/noutbuki-15692/",
                        "Lenovo",
                        "HP",
                        "10000",
                        "30000",
                        "12"
                )
        );
    }
}
