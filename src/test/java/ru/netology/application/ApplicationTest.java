package ru.netology.application;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.services.Bill;
import ru.netology.services.TaxService;
import ru.netology.taxes.ProgressiveTaxType;

import java.math.BigDecimal;

public class ApplicationTest {

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
    public void test_EqualsContentOfPrintReceipt() {
        System.out.println("Тест проверки текста квитанции");

        String expected = "Уплачена комиссия Прогрессивный налог в размере 3000,24";
        String receiptText = application.PrintReciept(new Bill(new BigDecimal(30002.34), new ProgressiveTaxType(), new TaxService()));

        Assertions.assertEquals(expected, receiptText);
    }

}
