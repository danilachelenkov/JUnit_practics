package ru.netology.taxes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

public class IncomeTaxTypeTest {


    private IncomeTaxType incomeTaxType;

    @BeforeEach
    public void beforeEachTests() {
        System.out.println("Инициализация эксземпляров перед выполненем каждого теста.");
        incomeTaxType = new IncomeTaxType();
    }

    @AfterEach
    public void afterEachTests() {
        System.out.println("Устанавливаем пустую ссылку для эксземпляров после выполнения каждого теста.\r\n");
        incomeTaxType = null;
    }

    @ParameterizedTest
    @MethodSource("test_IncomeTaxTypeParams")
    public void test_getIncomeTaxTypeValue_isEqualsThenTrue(BigDecimal ammount, double expected) {
        System.out.println("Запуск проверки результата расчета IncomeTaxType c параметрами");
        //act
        double calculatedIncomeTax = incomeTaxType.calculateTaxFor(ammount).doubleValue();
        //assert
        Assertions.assertEquals(expected, calculatedIncomeTax);
    }

    public static Stream<Arguments> test_IncomeTaxTypeParams() {
        return Stream.of(
                Arguments.of(new BigDecimal(4030.56), 523.98),
                Arguments.of(new BigDecimal(5010.66), 651.39),
                Arguments.of(new BigDecimal(7100.51), 923.07)
        );
    }

}
