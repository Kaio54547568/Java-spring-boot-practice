package practice2;

import java.time.LocalDate;

public abstract class Item
{
    private final String productCode;
    private final String name;
    private int quantity;
    private double unitPrice;

    protected Item(String productCode, String name, int quantity, double unitPrice)
    {
        if (productCode == null || productCode.isBlank())
        {
            throw new IllegalArgumentException("Product code must not be blank.");
        }
        if (name == null || name.isBlank())
        {
            throw new IllegalArgumentException("Name must not be blank.");
        }
        if (quantity < 0)
        {
            throw new IllegalArgumentException("Quantity must be at least 0.");
        }
        if (unitPrice < 0) {
            throw new IllegalArgumentException("Unit price must be at least 0.");
        }

        this.productCode = productCode;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getProductCode()
    {
        return productCode;
    }

    public String getName()
    {
        return name;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        if (quantity < 0)
        {
            throw new IllegalArgumentException("Quantity must be at least 0.");
        }
        this.quantity = quantity;
    }

    public double getUnitPrice()
    {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice)
    {
        if (unitPrice < 0)
        {
            throw new IllegalArgumentException("Unit price must be at least 0.");
        }
        this.unitPrice = unitPrice;
    }

    public double getVatAmount()
    {
        return quantity * unitPrice * getVatRate();
    }

    public abstract double getVatRate();

    public abstract ConsumptionStatus evaluateConsumption(LocalDate today);

    public abstract String getType();

    @Override
    public String toString()
    {
        return "%s{code='%s', name='%s', quantity=%d, unitPrice=%.2f, VAT=%.2f}".formatted(getType(), productCode, name, quantity, unitPrice, getVatAmount());
    }
}
