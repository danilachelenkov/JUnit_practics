package ru.netology.taxes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.application.Application;
import ru.netology.services.Bill;
import ru.netology.services.TaxService;

import java.math.BigDecimal;
import java.util.stream.Stream;

public class VATaxTypeTest {
    private VATaxType vaTaxType;

    @BeforeEach
    public void beforeEachTests() {
        System.out.println("Инициализация эксземпляров перед выполненем каждого теста.");
        vaTaxType = new VATaxType();
    }

    @AfterEach
    public void afterEachTests() {
        System.out.println("Устанавливаем пустую ссылку для эксземпляров после выполнения каждого теста.\r\n");
        vaTaxType = null;
    }


    @Test
    public void test_getVaTaxTypeValue_isEqualsThenTrue() {
        System.out.println("Запуск поверки результата расчета VATaxType");
        //arrange
        BigDecimal ammount = new BigDecimal(1000.50);
        double expected = 180.09;
        //act
        double vaTax = vaTaxType.calculateTaxFor(ammount).doubleValue();
        //assert
        Assertions.assertEquals(expected,vaTax);
    }

    @Test
    public void test_VaTaxTypeNoneException() {
        System.out.println("Проверка что не возвращает исключений");
        //act
        Executable executable = () -> vaTaxType.calculateTaxFor(new BigDecimal(222.45));

        Assertions.assertDoesNotThrow(executable);
    }



}
