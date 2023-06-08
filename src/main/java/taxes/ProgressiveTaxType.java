package taxes;

import java.math.BigDecimal;
import java.math.RoundingMode;

//Прогрессивное налогообложение
public class ProgressiveTaxType extends TaxType {
    public ProgressiveTaxType() {
        taxName = "Прогрессивный налог";
    }

    @Override
    public BigDecimal calculateTaxFor(BigDecimal amount) {
        return amount.compareTo(BigDecimal.valueOf(Taxes.PROGRESSIVE_TAX_LIMT)) == -1 ? amount.multiply(BigDecimal.valueOf(Taxes.PROGRESSIVE_TAX_LEVEL_1)).setScale(2, RoundingMode.CEILING) : amount.multiply(BigDecimal.valueOf(Taxes.PROGRESSIVE_TAX_LEVEL_2)).setScale(2, RoundingMode.CEILING);
    }
}
