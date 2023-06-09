package ru.netology.application;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.services.Bill;
import ru.netology.services.TaxService;
import ru.netology.taxes.IncomeTaxType;
import ru.netology.taxes.ProgressiveTaxType;
import ru.netology.taxes.TaxType;
import ru.netology.taxes.VATaxType;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ApplicationHamcrestTest {

    private Application application;


    @BeforeEach
    public void beforeEachTests() {
        System.out.println("Инициализация эксземпляров перед выполненем каждого теста.");
        application = new Application();
    }

    @AfterEach
    public void afterEachTests() {
        System.out.println("Устанавливаем пустую ссылку для эксземпляров после выполнения каждого теста.\r\n");
        application = null;
    }

    @Test
    public void test_getPrintReceipt_equalSetReceiptValueIsTrue() {
        System.out.println("Тест проверки текста квитанции не зависимо от регистра");
        //arrange
        String expected = "Уплачена комиссия Прогрессивный налог в размере 3000,24";
        //act
        String receiptText = application.PrintReciept(new Bill(new BigDecimal(30002.34), new ProgressiveTaxType(), new TaxService()));
        //assert
        assertThat(receiptText, equalToIgnoringCase(expected));
    }

    @Test
    public void checkInclusiveWord_thenCorrect( ){
        String word = "налог";
        String receiptText = application.PrintReciept(new Bill(new BigDecimal(30002.34), new ProgressiveTaxType(), new TaxService()));
        assertThat(receiptText, containsString(word));
    }

    @Test
    public void CheckNotNull_thenCorrect() {
        String receiptText = application.PrintReciept(new Bill(new BigDecimal(30002.34), new ProgressiveTaxType(), new TaxService()));
        assertThat(receiptText, notNullValue());
    }

}
