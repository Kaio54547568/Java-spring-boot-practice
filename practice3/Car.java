package practice3;

public class Car extends Vehicle
{
    private final int seatCount;
    private final String engineType;

    public Car(String vehicleNumber, String manufacturer, int manufacturingYear, String color,
               VehicleOwner owner, int seatCount, String engineType)
    {
        super(vehicleNumber, manufacturer, manufacturingYear, color, owner);
        if (seatCount <= 0 || engineType == null || engineType.isBlank())
        {
            throw new IllegalArgumentException("Seat count must be positive and engine type must be provided.");
        }
        this.seatCount = seatCount;
        this.engineType = engineType;
    }

    @Override
    public String getType()
    {
        return "Car";
    }

    @Override
    public String toString()
    {
        return "%s, seats=%d, engineType='%s'".formatted(super.toString(), seatCount, engineType);
    }
}
