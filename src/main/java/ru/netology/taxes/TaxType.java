package ru.netology.taxes;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxType {
    protected String taxName = "Неизвестная";

    public BigDecimal calculateTaxFor(BigDecimal amount) {
        return BigDecimal.valueOf(0.0).setScale(2, RoundingMode.CEILING);
    }

    public String getTaxName() {
        return taxName;
    }
}
