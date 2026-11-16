
import java.util.UUID;

record UserId(long value) {
}

record OrderId(UUID value) {
    public OrderId {
        if (value == null) {
            throw new IllegalArgumentException("Order ID cannot be null");
        }
    }

    public static OrderId random() {
        return new OrderId(UUID.randomUUID());
    }
}

public class TypedIdsDemo {

    public static void main(String[] args) {

        UserId user = new UserId(101);
        OrderId order = OrderId.random();

        System.out.println("User ID: " + user.value());
        System.out.println("Order ID: " + order.value());

        System.out.println("\nTyped IDs created successfully!");
    }
}
