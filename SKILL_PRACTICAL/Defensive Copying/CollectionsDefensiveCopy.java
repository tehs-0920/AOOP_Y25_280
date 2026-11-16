
import java.util.ArrayList;
import java.util.List;

public class CollectionsDefensiveCopy {

    private final List<String> items;

    public CollectionsDefensiveCopy(List<String> items) {
        this.items = List.copyOf(items);
    }

    public List<String> getItems() {
        return items;
    }

    public static void main(String[] args) {

        List<String> original = new ArrayList<>();

        original.add("Laptop");
        original.add("Mouse");
        original.add("Keyboard");

        CollectionsDefensiveCopy order =
            new CollectionsDefensiveCopy(original);

        System.out.println("Original items: " + original);
        System.out.println("Order items: " + order.getItems());

        original.add("Monitor");

        System.out.println("\nAfter modifying original list:");
        System.out.println("Original items: " + original);
        System.out.println("Order items: " + order.getItems());

        try {
            order.getItems().add("Printer");
        } catch (UnsupportedOperationException ex) {
            System.out.println("\nCannot modify the returned list!");
        }
    }
}
