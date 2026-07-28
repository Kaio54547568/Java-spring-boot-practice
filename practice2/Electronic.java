package practice2;

import java.time.LocalDate;

public class Electronic extends Item
{
    private static final double VAT_RATE = 0.10;

    private final int warrantyMonths;
    private final double capacityKw;

    public Electronic(String productCode, String name, int quantity, double unitPrice, int warrantyMonths, double capacityKw)
    {
        super(productCode, name, quantity, unitPrice);
        if (warrantyMonths < 0 || capacityKw < 0)
        {
            throw new IllegalArgumentException("Warranty period and capacity must be at least 0.");
        }
        this.warrantyMonths = warrantyMonths;
        this.capacityKw = capacityKw;
    }

    @Override
    public double getVatRate()
    {
        return VAT_RATE;
    }

    @Override
    public ConsumptionStatus evaluateConsumption(LocalDate today)
    {
        return getQuantity() < 3 ? ConsumptionStatus.SELL_WELL : ConsumptionStatus.NOT_EVALUATED;
    }

    @Override
    public String getType()
    {
        return "Electronic";
    }
}
