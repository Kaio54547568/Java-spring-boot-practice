package practice3;

import java.time.Year;
import java.util.Set;

public abstract class Vehicle
{
    private static final Set<String> ALLOWED_MANUFACTURERS = Set.of("Honda", "Yamaha", "Toyota", "Suzuki");

    private final String vehicleNumber;
    private final String manufacturer;
    private final int manufacturingYear;
    private final String color;
    private final VehicleOwner owner;

    protected Vehicle(String vehicleNumber, String manufacturer, int manufacturingYear, String color, VehicleOwner owner)
    {
        if (vehicleNumber == null || vehicleNumber.length() != 5)
        {
            throw new IllegalArgumentException("Vehicle number must contain exactly 5 characters.");
        }
        if (!ALLOWED_MANUFACTURERS.contains(manufacturer))
        {
            throw new IllegalArgumentException("Manufacturer must be Honda, Yamaha, Toyota, or Suzuki.");
        }
        int currentYear = Year.now().getValue();
        if (manufacturingYear <= 2000 || manufacturingYear > currentYear)
        {
            throw new IllegalArgumentException("Manufacturing year must be from 2001 to " + currentYear + ".");
        }
        if (color == null || color.isBlank() || owner == null)
        {
            throw new IllegalArgumentException("Color and owner must be provided.");
        }

        this.vehicleNumber = vehicleNumber;
        this.manufacturer = manufacturer;
        this.manufacturingYear = manufacturingYear;
        this.color = color;
        this.owner = owner;
    }

    public String getVehicleNumber()
    {
        return vehicleNumber;
    }

    public String getManufacturer()
    {
        return manufacturer;
    }

    public int getManufacturingYear()
    {
        return manufacturingYear;
    }

    public String getColor()
    {
        return color;
    }

    public VehicleOwner getOwner()
    {
        return owner;
    }

    public abstract String getType();

    @Override
    public String toString()
    {
        return "%s{number='%s', manufacturer='%s', year=%d, color='%s', owner=%s}".formatted(
                getType(), vehicleNumber, manufacturer, manufacturingYear, color, owner);
    }
}
