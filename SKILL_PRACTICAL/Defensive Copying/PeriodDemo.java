
import java.util.Date;

public class PeriodDemo {

    public static final class Period {
        private final Date start;
        private final Date end;

        public Period(Date s, Date e) {
            if (s.after(e)) {
                throw new IllegalArgumentException(
                    "Start date must be before or equal to end date"
                );
            }

            // Problem: storing original references
            this.start = s;
            this.end = e;
        }

        public Date start() {
            return start;
        }

        public Date end() {
            return end;
        }
    }

    public static void main(String[] args) {

        Date s = new Date();
        Date e = new Date();

        Period p = new Period(s, e);

        System.out.println("Before modification:");
        System.out.println("Start: " + p.start());
        System.out.println("End: " + p.end());

        // Modify original end date
        e.setYear(78);

        System.out.println("\nAfter modifying original Date object:");
        System.out.println("Start: " + p.start());
        System.out.println("End: " + p.end());

        if (p.start().after(p.end())) {
            System.out.println("\nERROR: Period is now INVALID!");
        } else {
            System.out.println("\nPeriod is still valid.");
        }
    }
}
