package services;

import java.math.BigDecimal;

public class TaxService {
    public String payOut(BigDecimal taxAmount) {
        //TODO проводка в АБС банка

        return String.format("Уплачена комиссия {0} в размере %.2f", taxAmount);
    }
}
