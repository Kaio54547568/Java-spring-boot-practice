package practice3;

public class Truck extends Vehicle
{
    private final double tonnage;

    public Truck(String vehicleNumber, String manufacturer, int manufacturingYear, String color,
                 VehicleOwner owner, double tonnage)
    {
        super(vehicleNumber, manufacturer, manufacturingYear, color, owner);
        if (tonnage < 0)
        {
            throw new IllegalArgumentException("Tonnage must be at least 0.");
        }
        this.tonnage = tonnage;
    }

    @Override
    public String getType()
    {
        return "Truck";
    }

    @Override
    public String toString()
    {
        return "%s, tonnage=%.1f tons".formatted(super.toString(), tonnage);
    }
}
