package ru.netology.taxes;

import java.math.BigDecimal;
import java.math.RoundingMode;

//Подоходный налог
public class IncomeTaxType extends TaxType {
    public IncomeTaxType() {
        taxName = "Подоходный налог";
    }

    @Override
    public BigDecimal calculateTaxFor(BigDecimal amount) {
        return amount.multiply(BigDecimal.valueOf(Taxes.INCOME_TAX)).setScale(2, RoundingMode.CEILING);
    }

    @Override
    public String toString() {
        return taxName;
    }
}
