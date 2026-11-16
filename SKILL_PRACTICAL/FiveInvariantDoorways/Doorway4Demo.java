package FiveInvariantDoorways;

import java.util.HashSet;
import java.util.Set;

class DefaultSpot {
    private final int spotId;

    DefaultSpot(int spotId) {
        this.spotId = spotId;
    }
}

class HalfSpot {
    private final int spotId;

    HalfSpot(int spotId) {
        this.spotId = spotId;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof HalfSpot other && other.spotId == spotId;
    }
}

class MutableEqSpot {
    private final int spotId;
    private boolean available = true;

    MutableEqSpot(int spotId) {
        this.spotId = spotId;
    }

    void park() {
        available = false;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof MutableEqSpot other
                && other.available == available;
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(available);
    }
}

class GoodSpot {
    private final int spotId;

    GoodSpot(int spotId) {
        this.spotId = spotId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o instanceof GoodSpot other && spotId == other.spotId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(spotId);
    }
}

public class Doorway4Demo {
    public static void main(String[] args) {

        Set<DefaultSpot> a = new HashSet<>();
        a.add(new DefaultSpot(101));
        a.add(new DefaultSpot(101));

        System.out.println("Default equality : size=" + a.size()
                + " (same spot booked twice)");

        Set<HalfSpot> b = new HashSet<>();
        b.add(new HalfSpot(101));

        System.out.println("equals w/o hashCode: contains="
                + b.contains(new HalfSpot(101))
                + " (almost always false)");

        Set<MutableEqSpot> c = new HashSet<>();
        MutableEqSpot s = new MutableEqSpot(101);
        c.add(s);
        s.park();

        System.out.println("Mutable equality : contains=" + c.contains(s)
                + " (object lost)");

        Set<GoodSpot> d = new HashSet<>();
        d.add(new GoodSpot(101));
        d.add(new GoodSpot(101));

        System.out.println("Identity equality : size=" + d.size()
                + " contains=" + d.contains(new GoodSpot(101)));
    }
}
