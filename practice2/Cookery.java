package practice2;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Cookery extends Item
{
    private static final double VAT_RATE = 0.10;

    private final String manufacturer;
    private final LocalDate arrivalDate;

    public Cookery(String productCode, String name, int quantity, double unitPrice, String manufacturer, LocalDate arrivalDate)
    {
        super(productCode, name, quantity, unitPrice);
        if (manufacturer == null || manufacturer.isBlank() || arrivalDate == null)
        {
            throw new IllegalArgumentException("Manufacturer and arrival date must be provided.");
        }
        this.manufacturer = manufacturer;
        this.arrivalDate = arrivalDate;
    }

    @Override
    public double getVatRate()
    {
        return VAT_RATE;
    }

    @Override
    public ConsumptionStatus evaluateConsumption(LocalDate today)
    {
        long storageDays = ChronoUnit.DAYS.between(arrivalDate, today);
        return getQuantity() > 50 && storageDays > 10 ? ConsumptionStatus.SLOW_SALE : ConsumptionStatus.NOT_EVALUATED;
    }

    @Override
    public String getType()
    {
        return "Cookery";
    }
}
