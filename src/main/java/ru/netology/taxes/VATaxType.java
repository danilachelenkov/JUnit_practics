package ru.netology.taxes;

import java.math.BigDecimal;
import java.math.RoundingMode;

//НДС
public class VATaxType extends TaxType {
    public VATaxType() {
        taxName = "НДС";
    }

    @Override
    public BigDecimal calculateTaxFor(BigDecimal amount) {
        return amount.multiply(BigDecimal.valueOf(Taxes.VA_TAX)).setScale(2, RoundingMode.CEILING);
    }
}
