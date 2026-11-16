
import java.util.*;

record OrderUserId(long value) {
}

record PurchaseOrderId(UUID value) {

    public PurchaseOrderId {
        Objects.requireNonNull(value);
    }

    public static PurchaseOrderId random() {
        return new PurchaseOrderId(UUID.randomUUID());
    }
}

record Item(String name, int quantity) {

    public Item {
        Objects.requireNonNull(name);

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                "Quantity must be greater than zero"
            );
        }
    }
}

record Order(
    PurchaseOrderId id,
    OrderUserId owner,
    List<Item> items
) {

    public Order {
        Objects.requireNonNull(id);
        Objects.requireNonNull(owner);
        Objects.requireNonNull(items);

        if (items.isEmpty()) {
            throw new IllegalArgumentException(
                "Order must contain at least one item"
            );
        }

        // Defensive copying
        items = List.copyOf(items);
    }
}

public class CombinedOrderDemo {

    public static void main(String[] args) {

        OrderUserId user = new OrderUserId(101);

        PurchaseOrderId orderId = PurchaseOrderId.random();

        List<Item> items = new ArrayList<>();

        items.add(new Item("Laptop", 1));
        items.add(new Item("Mouse", 2));

        Order order = new Order(orderId, user, items);

        System.out.println("ORDER DETAILS");
        System.out.println("--------------------");

        System.out.println("Order ID: " + order.id().value());
        System.out.println("User ID: " + order.owner().value());
        System.out.println("Items: " + order.items());

        // Modify the original list
        items.add(new Item("Keyboard", 1));

        System.out.println("\nAfter modifying original list:");

        System.out.println("Original list: " + items);
        System.out.println("Order items: " + order.items());
    }
}
