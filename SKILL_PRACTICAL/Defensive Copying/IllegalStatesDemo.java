
import java.time.Instant;

sealed interface Payment permits Pending, Paid, Failed {
}

record Pending(String paymentId, double amount) implements Payment {
}

record Paid(String paymentId, double amount, Instant paidAt)
        implements Payment {
}

record Failed(String paymentId, double amount, String reason)
        implements Payment {
}

public class IllegalStatesDemo {

    static String describe(Payment payment) {

        if (payment instanceof Pending) {
            return "Payment is waiting.";
        }

        if (payment instanceof Paid p) {
            return "Payment completed at " + p.paidAt();
        }

        if (payment instanceof Failed f) {
            return "Payment failed: " + f.reason();
        }

        return "Unknown payment status.";
    }

    public static void main(String[] args) {

        Payment p1 = new Pending("P101", 500.0);

        Payment p2 = new Paid(
            "P102", 1000.0, Instant.now()
        );

        Payment p3 = new Failed(
            "P103", 750.0, "Insufficient balance"
        );

        System.out.println(describe(p1));
        System.out.println(describe(p2));
        System.out.println(describe(p3));
    }
}

