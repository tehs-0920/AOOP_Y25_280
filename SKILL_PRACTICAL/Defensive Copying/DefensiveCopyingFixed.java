
import java.util.Date;

public class DefensiveCopyingFixed {

    public static final class Period {
        private final Date start;
        private final Date end;

        public Period(Date start, Date end) {

            // Defensive copies while receiving objects
            this.start = new Date(start.getTime());
            this.end = new Date(end.getTime());

            if (this.start.after(this.end)) {
                throw new IllegalArgumentException(
                    "Start date must be before or equal to end date"
                );
            }
        }

        // Defensive copies while returning objects
        public Date start() {
            return new Date(start.getTime());
        }

        public Date end() {
            return new Date(end.getTime());
        }
    }

    public static void main(String[] args) {

        Date s = new Date();
        Date e = new Date();

        Period p = new Period(s, e);

        System.out.println("Before modification:");
        System.out.println("Start: " + p.start());
        System.out.println("End: " + p.end());

        e.setYear(78);

        System.out.println("\nAfter modifying original Date object:");
        System.out.println("Start: " + p.start());
        System.out.println("End: " + p.end());

        if (p.start().after(p.end())) {
            System.out.println("ERROR: Period is INVALID!");
        } else {
            System.out.println("SUCCESS: Period is still VALID!");
        }
    }
}
