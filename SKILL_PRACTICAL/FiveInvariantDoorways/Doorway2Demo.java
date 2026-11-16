package FiveInvariantDoorways;

import java.time.LocalDateTime;
import java.util.Date;

class BrokenBooking {
    private final Date start;
    private final Date end;

    BrokenBooking(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    Date getStart() {
        return start;
    }

    Date getEnd() {
        return end;
    }
}

class SafeBooking {
    private final Date start;
    private final Date end;

    SafeBooking(Date start, Date end) {
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());
    }

    Date getStart() {
        return new Date(start.getTime());
    }

    Date getEnd() {
        return new Date(end.getTime());
    }
}

class ImmutableBooking {
    private final LocalDateTime start;
    private final LocalDateTime end;

    ImmutableBooking(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    LocalDateTime getStart() {
        return start;
    }

    LocalDateTime getEnd() {
        return end;
    }
}

public class Doorway2Demo {
    public static void main(String[] args) {
        Date start = new Date(1000);
        Date end = new Date(5000);

        System.out.println("--- Broken: escape on the way out ---");
        BrokenBooking broken = new BrokenBooking(start, end);
        broken.getEnd().setTime(0);
        System.out.println("start=" + broken.getStart().getTime()
                + " end=" + broken.getEnd().getTime()
                + " valid=" + (broken.getEnd().after(broken.getStart())));

        System.out.println("--- Broken: escape on the way in ---");
        Date externalEnd = new Date(5000);
        BrokenBooking brokenInput = new BrokenBooking(start, externalEnd);
        externalEnd.setTime(0);
        System.out.println("end=" + brokenInput.getEnd().getTime());

        System.out.println("--- Fix 1: defensive copies ---");
        SafeBooking safe = new SafeBooking(start, new Date(5000));
        safe.getEnd().setTime(0);
        System.out.println("end=" + safe.getEnd().getTime());

        System.out.println("--- Fix 2: immutable LocalDateTime ---");
        ImmutableBooking immutable = new ImmutableBooking(
                LocalDateTime.of(2026, 11, 9, 10, 0),
                LocalDateTime.of(2026, 11, 9, 12, 0));
        System.out.println("end=" + immutable.getEnd()
                + " (no setter exists, nothing to attack)");
    }
}