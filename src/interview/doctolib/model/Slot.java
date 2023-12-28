package interview.doctolib.model;

import java.time.LocalDate;

// To make it simple, we assume that one slot represents one hour begins at 9AM - 6PM
public class Slot {

    private LocalDate localDate;
    private int hour;

    public Slot(LocalDate localDate, int hour) {
        this.localDate = localDate;
        if (hour < 9 || hour > 18) {
            throw new RuntimeException("Hour value is not permitted");
        }
        this.hour = hour;
    }

    public int getHour() {
        return hour;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "localDate=" + localDate +
                ", hour=" + hour +
                '}';
    }
}
