package practice3;

public class VehicleManager
{
    private final Vehicle[] vehicles;
    private int size;

    public VehicleManager(int capacity)
    {
        if (capacity <= 0)
        {
            throw new IllegalArgumentException("Capacity must be greater than 0.");
        }
        vehicles = new Vehicle[capacity];
    }

    public boolean addVehicle(Vehicle vehicle)
    {
        if (vehicle == null || size == vehicles.length || findByVehicleNumber(vehicle.getVehicleNumber()) != null)
        {
            return false;
        }
        vehicles[size++] = vehicle;
        return true;
    }

    public Vehicle findByVehicleNumber(String vehicleNumber)
    {
        for (int index = 0; index < size; index++)
        {
            if (vehicles[index].getVehicleNumber().equalsIgnoreCase(vehicleNumber))
            {
                return vehicles[index];
            }
        }
        return null;
    }

    public Vehicle[] findByOwnerIdentityCard(String identityCardNumber)
    {
        Vehicle[] matches = new Vehicle[size];
        int matchCount = 0;
        for (int index = 0; index < size; index++)
        {
            if (vehicles[index].getOwner().getIdentityCardNumber().equals(identityCardNumber))
            {
                matches[matchCount++] = vehicles[index];
            }
        }
        Vehicle[] result = new Vehicle[matchCount];
        System.arraycopy(matches, 0, result, 0, matchCount);
        return result;
    }

    public int removeByManufacturer(String manufacturer)
    {
        int removed = 0;
        for (int index = 0; index < size;)
        {
            if (vehicles[index].getManufacturer().equalsIgnoreCase(manufacturer))
            {
                for (int next = index; next < size - 1; next++)
                {
                    vehicles[next] = vehicles[next + 1];
                }
                vehicles[--size] = null;
                removed++;
            }
            else
            {
                index++;
            }
        }
        return removed;
    }

    public String getManufacturerWithMostVehicles()
    {
        String mostPopularManufacturer = null;
        int highestCount = 0;
        for (int index = 0; index < size; index++)
        {
            String manufacturer = vehicles[index].getManufacturer();
            int count = 0;
            for (int otherIndex = 0; otherIndex < size; otherIndex++)
            {
                if (vehicles[otherIndex].getManufacturer().equals(manufacturer))
                {
                    count++;
                }
            }
            if (count > highestCount)
            {
                highestCount = count;
                mostPopularManufacturer = manufacturer;
            }
        }
        return mostPopularManufacturer;
    }

    public void sortByVehicleNumberDescending()
    {
        for (int index = 0; index < size - 1; index++)
        {
            for (int next = index + 1; next < size; next++)
            {
                if (vehicles[index].getVehicleNumber().compareToIgnoreCase(vehicles[next].getVehicleNumber()) < 0)
                {
                    Vehicle temporaryVehicle = vehicles[index];
                    vehicles[index] = vehicles[next];
                    vehicles[next] = temporaryVehicle;
                }
            }
        }
    }

    public int getCountByType(Class<? extends Vehicle> vehicleType)
    {
        int count = 0;
        for (int index = 0; index < size; index++)
        {
            if (vehicleType.isInstance(vehicles[index]))
            {
                count++;
            }
        }
        return count;
    }

    public Vehicle[] getVehicles()
    {
        Vehicle[] result = new Vehicle[size];
        System.arraycopy(vehicles, 0, result, 0, size);
        return result;
    }

    public void printStatistics()
    {
        System.out.printf("Cars: %d, Motorbikes: %d, Trucks: %d%n",
                getCountByType(Car.class), getCountByType(Motorbike.class), getCountByType(Truck.class));
    }
}
