package practice3;

public class Motorbike extends Vehicle
{
    private final double capacity;

    public Motorbike(String vehicleNumber, String manufacturer, int manufacturingYear, String color,
                     VehicleOwner owner, double capacity)
    {
        super(vehicleNumber, manufacturer, manufacturingYear, color, owner);
        if (capacity < 0)
        {
            throw new IllegalArgumentException("Capacity must be at least 0.");
        }
        this.capacity = capacity;
    }

    @Override
    public String getType()
    {
        return "Motorbike";
    }

    @Override
    public String toString()
    {
        return "%s, capacity=%.1f cc".formatted(super.toString(), capacity);
    }
}
