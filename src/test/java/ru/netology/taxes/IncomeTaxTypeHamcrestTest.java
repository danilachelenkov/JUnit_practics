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

public class IncomeTaxTypeHamcrestTest {

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

    @Test
    public void test_IncomeTaxType_Hamcrest() {
        double bDInput = incomeTaxType.calculateTaxFor(new BigDecimal(5010.56)).doubleValue();
        assertThat(bDInput, closeTo(651.37, 0.01));
    }


    @ParameterizedTest
    @CsvSource({
            "5010.56,651.37,0.01",
            "7845.10,1019.87,0.01"
    })
    public void test_IncomeTaxTypeHamcrestParams(double input, double expected, double delta) {
        System.out.println("Запуск Hamcrest проверки результата расчета IncomeTaxType c параметрами");
        double bDInput = incomeTaxType.calculateTaxFor(new BigDecimal(input)).doubleValue();
        assertThat(bDInput, closeTo(expected, delta));
    }

    @Test
    public void givenClass_whenString_getName() {
        assertThat(incomeTaxType, hasToString("Подоходный налог"));
    }

    @Test
    public void givenSubclass_whenChecked_thenTrue() {
        assertThat(incomeTaxType,instanceOf(TaxType.class));
    }


}
