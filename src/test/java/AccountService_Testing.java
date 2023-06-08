import application.Application;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import services.Bill;
import services.TaxService;
import taxes.IncomeTaxType;
import taxes.ProgressiveTaxType;
import taxes.VATaxType;

import java.math.BigDecimal;
import java.util.stream.Stream;

public class AccountService_Testing {

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

    @ParameterizedTest
    @MethodSource("test_IncomeTaxType_params")//arrange
    public void test_IncomeTaxType(BigDecimal ammount, double expected) {
        System.out.println("Запуск проверки результата расчета IncomeTaxType c параметрами");
        //act
        double calculatedIncomeTax = incomeTaxType.calculateTaxFor(ammount).doubleValue();
        //assert
        Assertions.assertEquals(expected, calculatedIncomeTax);
    }

    public static Stream<Arguments> test_IncomeTaxType_params() {
        return Stream.of(
                Arguments.of(new BigDecimal(4030.56), 523.98),
                Arguments.of(new BigDecimal(5010.66), 651.39),
                Arguments.of(new BigDecimal(7100.51), 923.07)
        );
    }


    @ParameterizedTest
    @MethodSource("test_ProgressiveTaxType_params")//arrange
    public void test_ProgressiveTaxType(BigDecimal ammount, double expected) {
        System.out.println("Запуск проверки результата расчета ProgressiveTaxType c параметрами");
        //act
        double calculatedIncomeTax = progressiveTaxType.calculateTaxFor(ammount).doubleValue();
        //assert
        Assertions.assertEquals(expected, calculatedIncomeTax);
    }

    public static Stream<Arguments> test_ProgressiveTaxType_params() {
        return Stream.of(
                Arguments.of(new BigDecimal(14030.26), 1403.03),
                Arguments.of(new BigDecimal(300_000.66), 45000.10)
        );
    }

    @Test
    public void test_VaTaxType() {
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

    @Test
    public void test_PrintReceipt(){
        System.out.println("Тест проверки текста квитанции");

        String expected = "Уплачена комиссия Прогрессивный налог в размере 3000,24";
        String receiptText = application.PrintReciept(new Bill(new BigDecimal(30002.34), new ProgressiveTaxType(), new TaxService()));

        Assertions.assertEquals(expected,receiptText);
    }

}
