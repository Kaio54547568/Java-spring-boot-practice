package practice2;

import java.time.LocalDate;
import java.util.Scanner;

public class InventoryManager
{
    private final Item[] items;
    private int size;

    public InventoryManager(int capacity)
    {
        if (capacity <= 0)
        {
            throw new IllegalArgumentException("Capacity must be greater than 0.");
        }
        items = new Item[capacity];
    }

    public boolean addItem(Item item)
    {
        if (item == null || size == items.length || containsProductCode(item.getProductCode()))
        {
            return false;
        }
        items[size++] = item;
        return true;
    }

    public boolean addItemFromConsole(Scanner scanner)
    {
        System.out.println("Choose type: 1. Food  2. Electronic  3. Cookery");
        int choice = Integer.parseInt(scanner.nextLine());

        System.out.print("Product code: ");
        String code = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        System.out.print("Unit price: ");
        double unitPrice = Double.parseDouble(scanner.nextLine());

        Item item;
        switch (choice)
        {
            case 1:
                System.out.print("Manufacture date (yyyy-MM-dd): ");
                LocalDate manufactureDate = LocalDate.parse(scanner.nextLine());
                System.out.print("Expiration date (yyyy-MM-dd): ");
                LocalDate expirationDate = LocalDate.parse(scanner.nextLine());
                System.out.print("Supplier: ");
                item = new Food(code, name, quantity, unitPrice, manufactureDate, expirationDate, scanner.nextLine());
                break;
            case 2:
                System.out.print("Warranty months: ");
                int warrantyMonths = Integer.parseInt(scanner.nextLine());
                System.out.print("Capacity (KW): ");
                double capacityKw = Double.parseDouble(scanner.nextLine());
                item = new Electronic(code, name, quantity, unitPrice, warrantyMonths, capacityKw);
                break;
            case 3:
                System.out.print("Manufacturer: ");
                String manufacturer = scanner.nextLine();
                System.out.print("Arrival date (yyyy-MM-dd): ");
                item = new Cookery(code, name, quantity, unitPrice, manufacturer, LocalDate.parse(scanner.nextLine()));
                break;
            default:
                System.out.println("Invalid type.");
                return false;
        }

        return addItem(item);
    }

    public boolean containsProductCode(String productCode)
    {
        for (int index = 0; index < size; index++)
        {
            if (items[index].getProductCode().equalsIgnoreCase(productCode))
            {
                return true;
            }
        }
        return false;
    }

    public int getTotalQuantity(Class<? extends Item> itemType)
    {
        int total = 0;
        for (int index = 0; index < size; index++)
        {
            if (itemType.isInstance(items[index]))
            {
                total += items[index].getQuantity();
            }
        }
        return total;
    }

    public double getTotalVatAmount(Class<? extends Item> itemType)
    {
        double total = 0;
        for (int index = 0; index < size; index++)
        {
            if (itemType.isInstance(items[index]))
            {
                total += items[index].getVatAmount();
            }
        }
        return total;
    }

    public void printReport(LocalDate today)
    {
        for (int index = 0; index < size; index++)
        {
            Item item = items[index];
            System.out.printf("%s | Consumption: %s%n", item, item.evaluateConsumption(today));
        }

        System.out.printf("Food: quantity=%d, VAT=%.2f%n", getTotalQuantity(Food.class), getTotalVatAmount(Food.class));
        System.out.printf("Electronic: quantity=%d, VAT=%.2f%n", getTotalQuantity(Electronic.class), getTotalVatAmount(Electronic.class));
        System.out.printf("Cookery: quantity=%d, VAT=%.2f%n", getTotalQuantity(Cookery.class), getTotalVatAmount(Cookery.class));
    }
}
