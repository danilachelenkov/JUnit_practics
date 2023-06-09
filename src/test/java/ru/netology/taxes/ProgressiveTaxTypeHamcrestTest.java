package ru.netology.taxes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.application.Application;
import ru.netology.services.Bill;
import ru.netology.services.TaxService;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ProgressiveTaxTypeHamcrestTest {
    private ProgressiveTaxType progressiveTaxType;

    @BeforeEach
    public void beforeEachTests() {
        System.out.println("Инициализация эксземпляров перед выполненем каждого теста.");
        progressiveTaxType = new ProgressiveTaxType();
    }

    @AfterEach
    public void afterEachTests() {
        System.out.println("Устанавливаем пустую ссылку для эксземпляров после выполнения каждого теста.\r\n");
        progressiveTaxType = null;
    }

    @ParameterizedTest
    @CsvSource({
            "14030.26, 1403.03, 0.01",
            "300000.66, 45000.1, 0.01"
    })
    public void test_ProgressiveTaxType_HamcrestParams(double input, double expected, double delta) {
        System.out.println("Запуск Hamcrest проверки результата расчета ProgressiveTaxType c параметрами");
        //act
        double bDInput = progressiveTaxType.calculateTaxFor(new BigDecimal(input)).doubleValue();
        assertThat(bDInput, closeTo(expected, delta));
    }
}
