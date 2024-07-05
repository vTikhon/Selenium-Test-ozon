package com.bellintegrator.properties;

import org.aeonbits.owner.ConfigFactory;

/**
 * Класс для работы со свойствами тестирования.
 * Использует библиотеку OWNER для создания конфигурации свойств.
 *
 * @version 1.0
 * @since 2024-07-02
 * @author Vergentev Tikhon
 */
public class Properties {

    /**
     * Экземпляр интерфейса TestsProperties, содержащий свойства для тестов.
     * Создается с использованием фабрики конфигураций OWNER.
     */
    public static TestsProperties testsProperties = ConfigFactory.create(TestsProperties.class);
}
