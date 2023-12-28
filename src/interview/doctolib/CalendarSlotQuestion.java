package interview.doctolib;

import interview.doctolib.model.Event;
import interview.doctolib.model.Slot;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CalendarSlotQuestion {

    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        Stream<LocalDate> localDateStream = now.datesUntil(now.plusWeeks(1));
        for (LocalDate date: localDateStream.toList()) {
            System.out.println(date.getDayOfWeek());
            System.out.println(date.getDayOfMonth());
        }

        LocalDate startDate = LocalDate.now().plusDays(1);
        List<Slot> results = computeSlotsForNextSevenDays(startDate);
        for (Slot slot: results) {
            System.out.println(slot);
        }
    }

    // 1. What's the unit tests for this function ?
    private static List<Slot> computeSlotsForNextSevenDays(LocalDate startDate) {
        // Where to get the event / calendar of the doctor ?
        Event event = getEvent();

        List<Slot> results = new ArrayList<>();
        for (Slot slot: event.getOpeningSlots()) {
            if (isValidDate(startDate, slot.getLocalDate())) {
                results.add(slot);
            }
        }
        return results;
    }

    // 2. What's the problem and background algo ?
    // Filter the slot in opening slots list of the event
    private static boolean isValidDate(LocalDate startDate, LocalDate date) {
        return date.isAfter(startDate.minusDays(1))
                && date.isBefore(startDate.plusDays(8));
    }

    private static Event getEvent() {
        List<Slot> openingSlots = new ArrayList<>();
        List<Slot> appointmentSlots = new ArrayList<>();

        openingSlots.add(new Slot(LocalDate.now(), 9));
        openingSlots.add(new Slot(LocalDate.now(), 12));
        openingSlots.add(new Slot(LocalDate.now(), 15));
        openingSlots.add(new Slot(LocalDate.now().plusDays(2), 15));
        openingSlots.add(new Slot(LocalDate.now().plusDays(3), 10));
        openingSlots.add(new Slot(LocalDate.now().plusDays(5), 14));
        openingSlots.add(new Slot(LocalDate.now().plusDays(9), 18));
        openingSlots.add(new Slot(LocalDate.now().plusDays(10), 10));
        appointmentSlots.add(new Slot(LocalDate.now(), 11));
        appointmentSlots.add(new Slot(LocalDate.now().plusDays(2), 9));
        appointmentSlots.add(new Slot(LocalDate.now().plusDays(5), 13));

        return new Event(openingSlots, appointmentSlots);
    }
}
