package practice2;

import java.time.LocalDate;
import java.util.Scanner;

public class Demo
{
    public static void main(String[] args)
    {
        InventoryManager manager = new InventoryManager(10);

        manager.addItem(new Food("F01", "Milk", 12, 30_000, LocalDate.of(2026, 7, 1), LocalDate.of(2026, 7, 20), "Vinamilk"));
        manager.addItem(new Electronic("E01", "Fan", 2, 500_000, 12, 0.06));
        manager.addItem(new Cookery("C01", "Bowl", 60, 45_000, "Minh Long", LocalDate.of(2026, 7, 1)));

        manager.printReport(LocalDate.of(2026, 7, 28));
    }
}
