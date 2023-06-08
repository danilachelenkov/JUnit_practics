package application;

import services.Bill;
import services.TaxService;
import taxes.IncomeTaxType;
import taxes.ProgressiveTaxType;
import taxes.TaxType;
import taxes.VATaxType;

import java.math.BigDecimal;

public class Application {
    private String receiptText;
    public void start() {

        Bill[] bills = new Bill[]{
                new Bill(new BigDecimal("15833.44"), new ProgressiveTaxType(), new TaxService()),
                new Bill(new BigDecimal("100000.01"), new ProgressiveTaxType(), new TaxService()),
                new Bill(new BigDecimal("36432.12"), new IncomeTaxType(), new TaxService()),
                new Bill(new BigDecimal("12223.65"), new VATaxType(), new TaxService())
        };

        for (Bill bill : bills) {
            PrintReciept(bill);
        }
    }

    public String PrintReciept(Bill bill) {
        if (bill != null) {
            String str = String.format("%s", bill.getTaxType().getTaxName());
            receiptText = bill.payTaxes().replace("{0}", str);

            System.out.println(receiptText);
        }
        return receiptText;
    }
}
