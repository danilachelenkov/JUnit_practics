import ru.netology.application.Application;
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

public class AccountService_TestingHamcrest {

    private Application application;
    private IncomeTaxType incomeTaxType;
    private VATaxType vaTaxType;
    private ProgressiveTaxType progressiveTaxType;

    @BeforeEach
    public void beforeEachTests() {
        System.out.println("Инициализация эксземпляров перед выполненем каждого теста.");
        application = new Application();
        incomeTaxType = new IncomeTaxType();
        vaTaxType = new VATaxType();
        progressiveTaxType = new ProgressiveTaxType();
    }

    @AfterEach
    public void afterEachTests() {
        System.out.println("Устанавливаем пустую ссылку для эксземпляров после выполнения каждого теста.\r\n");
        application = null;
        incomeTaxType = null;
        vaTaxType = null;
        progressiveTaxType = null;
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
    public void test_IncomeTaxType_HamcrestParams(double input, double expected, double delta) {
        System.out.println("Запуск Hamcrest проверки результата расчета IncomeTaxType c параметрами");
        double bDInput = incomeTaxType.calculateTaxFor(new BigDecimal(input)).doubleValue();
        assertThat(bDInput, closeTo(expected, delta));
    }


    @ParameterizedTest
    @CsvSource({
            "14030.26, 1823.94, 0.01",
            "300000.66, 39000.09, 0.01"
    })
    public void test_ProgressiveTaxType_HamcrestParams(double input, double expected, double delta) {
        System.out.println("Запуск Hamcrest проверки результата расчета ProgressiveTaxType c параметрами");
        //act
        double bDInput = incomeTaxType.calculateTaxFor(new BigDecimal(input)).doubleValue();
        assertThat(bDInput, closeTo(expected, delta));
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

    @Test
    public void test_getPrintReceipt_equalSetReceiptValue() {
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

    @Test
    public void givenClass_whenString_getName() {
        assertThat(incomeTaxType, hasToString("Подоходный налог"));
    }

    @Test
    public void givenSubclass_whenChecked_thenTrue() {
        assertThat(incomeTaxType,instanceOf(TaxType.class));
    }


}
