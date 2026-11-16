package FiveInvariantDoorways;

import java.util.ArrayList;
import java.util.List;

interface Describable {
    String describe();
}

class Registry {
    void register(Describable d) {
        System.out.println("Registry sees: " + d.describe());
    }
}

class LeakyBooking implements Describable {
    private final int bookingId;
    private String status;

    LeakyBooking(int bookingId, Registry registry) {
        registry.register(this);
        this.bookingId = bookingId;
        this.status = "CREATED";
    }

    @Override
    public String describe() {
        return "id=" + bookingId + ", status=" + status;
    }
}

final class SafeBooking implements Describable {
    private final int bookingId;
    private String status;

    private SafeBooking(int bookingId) {
        this.bookingId = bookingId;
        this.status = "CREATED";
    }

    static SafeBooking create(int bookingId, Registry registry) {
        SafeBooking b = new SafeBooking(bookingId);
        registry.register(b);
        return b;
    }

    @Override
    public String describe() {
        return "id=" + bookingId + ", status=" + status;
    }
}

class BaseBooking {
    BaseBooking() {
        init();
    }

    protected void init() { }
}

class PremiumBooking extends BaseBooking {
    private final List<String> perks = new ArrayList<>();

    @Override
    protected void init() {
        perks.add("VALET");
    }
}

public class Doorway5Demo {
    public static void main(String[] args) {
        Registry registry = new Registry();

        System.out.println("--- Broken: leaking this ---");
        new LeakyBooking(501, registry);

        System.out.println("--- Fixed: static factory ---");
        SafeBooking.create(502, registry);

        System.out.println("--- Broken: overridable method in constructor ---");
        try {
            new PremiumBooking();
        } catch (NullPointerException e) {
            System.out.println(
                "NullPointerException: subclass field was not initialised yet"
            );
        }
    }
}
