import java.util.Scanner;
class Item {
    private String name;
    private double price;
    private int stock;

    Item(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
    boolean available(int q) { return q > 0 && q <= stock; }
    void buy(int q) { stock -= q; }
    double getPrice() { return price; }
    String getName() { return name; }

    public String toString() {
        return name + " Rs." + price + " Stock:" + stock;
    }
}
public class VendingMachine {
    static double payment(Scanner sc, double total) {
        while (true) {
            try {
                System.out.print("Enter payment: Rs.");
                double p = sc.nextDouble();
                if (p >= total) return p;
                System.out.println("Insufficient! Need Rs." + (total - p));
            } catch (Exception e) {
                System.out.println("Enter a valid amount!");
                sc.nextLine();
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Item[] items = {
            new Item("Chips", 20, 5),
            new Item("Chocolate", 30, 5),
            new Item("Biscuit", 25, 5),
            new Item("Juice", 40, 5),
            new Item("Water", 15, 5)
        };
        double total = 0;
        while (true) {
            System.out.println("\n===== VENDING MACHINE =====");
            for (int i = 0; i < items.length; i++)
                System.out.println((i + 1) + ". " + items[i]);

            System.out.println("0. Finish");
            System.out.print("Choose item: ");

            try {
                int choice = sc.nextInt();
                if (choice == 0) break;

                if (choice < 1 || choice > items.length) {
                    System.out.println("Invalid choice!");
                    continue;
                }
                Item item = items[choice - 1];

                System.out.print("Enter quantity: ");
                int q = sc.nextInt();

                if (!item.available(q)) {
                    System.out.println("Invalid quantity or insufficient stock!");
                    continue;
                }
                item.buy(q);
                total += item.getPrice() * q;

                System.out.println(q + " " + item.getName() + "(s) added.");
                System.out.println("Current total: Rs." + total);

            } catch (Exception e) {
                System.out.println("Invalid input!");
                sc.nextLine();
            }
        }
        if (total > 0) {
            System.out.println("\nTotal: Rs." + total);
            double pay = payment(sc, total);
            System.out.println("Change: Rs." + (pay - total));
            System.out.println("Thank you! Please collect your items.");
        } else {
            System.out.println("No items selected.");
        }
        System.out.println("Vending machine closed.");
    }
}