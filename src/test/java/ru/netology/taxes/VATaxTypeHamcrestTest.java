package ru.netology.taxes;

import ru.netology.application.Application;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.services.Bill;
import ru.netology.services.TaxService;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class VATaxTypeHamcrestTest {
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
    public void test_VaTaxType_Hamcrest() {
        System.out.println("Запуск Hamcrest поверки результата расчета VATaxType");
        //arrange

        double expected = 180.09;
        double delta = 0.01;
        //act
        double ammount = vaTaxType.calculateTaxFor(new BigDecimal(1000.50)).doubleValue();
        //assert
        assertThat(ammount, closeTo(expected, delta));
    }
}
