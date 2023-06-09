package ru.netology.taxes;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

public class ProgressiveTaxTypeTest {
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
    @MethodSource("test_ProgressiveTaxTypeParams")//arrange
    public void test_ProgressiveTaxTypeValue_isEqualsThenTrue(BigDecimal ammount, double expected) {
        System.out.println("Запуск проверки результата расчета ProgressiveTaxType c параметрами");
        //act
        double calculatedIncomeTax = progressiveTaxType.calculateTaxFor(ammount).doubleValue();
        //assert
        Assertions.assertEquals(expected, calculatedIncomeTax);
    }

    public static Stream<Arguments> test_ProgressiveTaxTypeParams() {
        return Stream.of(
                Arguments.of(new BigDecimal(14030.26), 1403.03),
                Arguments.of(new BigDecimal(300_000.66), 45000.10)
        );
    }


}
