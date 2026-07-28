package practice2;

import java.time.LocalDate;

public class Food extends Item
{
    private static final double VAT_RATE = 0.05;

    private final LocalDate manufactureDate;
    private final LocalDate expirationDate;
    private final String supplier;

    public Food(String productCode, String name, int quantity, double unitPrice, LocalDate manufactureDate, LocalDate expirationDate, String supplier)
    {
        super(productCode, name, quantity, unitPrice);
        if (manufactureDate == null || expirationDate == null || supplier == null || supplier.isBlank())
        {
            throw new IllegalArgumentException("Food dates and supplier must be provided.");
        }
        if (expirationDate.isBefore(manufactureDate))
        {
            throw new IllegalArgumentException("Expiration date must be on or after manufacture date.");
        }
        this.manufactureDate = manufactureDate;
        this.expirationDate = expirationDate;
        this.supplier = supplier;
    }

    @Override
    public double getVatRate()
    {
        return VAT_RATE;
    }

    @Override
    public ConsumptionStatus evaluateConsumption(LocalDate today)
    {
        return getQuantity() > 0 && expirationDate.isBefore(today) ? ConsumptionStatus.HARD_TO_SELL : ConsumptionStatus.NOT_EVALUATED;
    }

    @Override
    public String getType()
    {
        return "Food";
    }
}
