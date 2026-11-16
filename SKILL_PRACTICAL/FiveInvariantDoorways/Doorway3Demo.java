package FiveInvariantDoorways;

import java.util.ArrayList;
import java.util.List;

class BadWallet {
    private long balance;
    private final List<Long> credits = new ArrayList<>();

    public void credit(long amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
        balance += amount;
        credits.add(amount);
    }

    public List<Long> getCredits() {
        return credits;
    }

    public boolean isConsistent() {
        return balance == credits.stream().mapToLong(Long::longValue).sum();
    }
}

class SafeWallet {
    private long balance;
    private final List<Long> credits = new ArrayList<>();

    public void credit(long amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
        balance += amount;
        credits.add(amount);
    }

    public List<Long> getCredits() {
        return List.copyOf(credits);
    }

    public boolean isConsistent() {
        return balance == credits.stream().mapToLong(Long::longValue).sum();
    }
}

class ParkingLot {
    private final List<String> spotIds;

    public ParkingLot(List<String> spotIds) {
        this.spotIds = new ArrayList<>(spotIds);
    }

    public int size() {
        return spotIds.size();
    }
}

public class Doorway3Demo {
    public static void main(String[] args) {

        System.out.println("--- Broken: exposed list ---");
        BadWallet bad = new BadWallet();
        bad.credit(500);
        bad.getCredits().clear();

        System.out.println("consistent=" + bad.isConsistent());

        System.out.println("--- Fixed: List.copyOf ---");
        SafeWallet safe = new SafeWallet();
        safe.credit(500);

        try {
            safe.getCredits().clear();
        } catch (UnsupportedOperationException e) {
            System.out.println("Blocked: list is read-only");
        }

        System.out.println("consistent=" + safe.isConsistent()
                + " credits=" + safe.getCredits());

        System.out.println("--- Copy on input ---");
        List<String> original = new ArrayList<>();
        original.add("101");
        original.add("102");

        ParkingLot lot = new ParkingLot(original);
        original.clear();

        System.out.println("lot size=" + lot.size() + " (still 2)");
    }
}
