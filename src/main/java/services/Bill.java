package services;

import taxes.TaxType;

import java.math.BigDecimal;

public class Bill {

    private final BigDecimal amount;
    private final TaxType taxType;

    private final TaxService taxService;

    public Bill(BigDecimal amount, TaxType taxType, TaxService taxService) {
        this.amount = amount;
        this.taxType = taxType;
        this.taxService = taxService;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TaxType getTaxType() {
        return taxType;
    }

    public TaxService getTaxService() {
        return taxService;
    }

    public String payTaxes() {
        BigDecimal tax = taxType.calculateTaxFor(amount);
        return taxService.payOut(tax);
    }
}
