package practice3;

public class Demo
{
    public static void main(String[] args)
    {
        VehicleOwner an = new VehicleOwner("001234567890", "Nguyen Van An", "an@example.com");
        VehicleOwner binh = new VehicleOwner("001234567891", "Tran Thi Binh", "binh@example.com");
        VehicleOwner chi = new VehicleOwner("001234567892", "Le Van Chi", "chi@example.com");

        VehicleManager manager = new VehicleManager(10);
        manager.addVehicle(new Car("A1001", "Toyota", 2024, "White", an, 5, "Hybrid"));
        manager.addVehicle(new Motorbike("B1002", "Honda", 2022, "Black", binh, 150));
        manager.addVehicle(new Truck("C1003", "Suzuki", 2021, "Blue", chi, 2.5));
        manager.addVehicle(new Car("D1004", "Toyota", 2023, "Red", an, 7, "Petrol"));

        System.out.println("1. All vehicles:");
        printVehicles(manager.getVehicles());

        System.out.println("\n2. Search by vehicle number A1001:");
        System.out.println(manager.findByVehicleNumber("A1001"));

        System.out.println("\n3. Vehicles owned by ID 001234567890:");
        printVehicles(manager.findByOwnerIdentityCard("001234567890"));

        System.out.println("\n5. Manufacturer with most vehicles: " + manager.getManufacturerWithMostVehicles());

        manager.sortByVehicleNumberDescending();
        System.out.println("\n6. Vehicles sorted by number descending:");
        printVehicles(manager.getVehicles());

        System.out.println("\n7. Statistics by vehicle type:");
        manager.printStatistics();

        System.out.println("\n4. Removed " + manager.removeByManufacturer("Suzuki") + " Suzuki vehicle(s).");
    }

    private static void printVehicles(Vehicle[] vehicles)
    {
        for (Vehicle vehicle : vehicles)
        {
            System.out.println(vehicle);
        }
    }
}
