package interview.doctolib;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// You will write a function that takes a date as input and
// returns an object/hash containing the doctor's available slots for the next seven days,
// starting on the input date.
//
// SQL database table that contains events. Events come in two kinds:
// - opening: times when the doctor is available to take patients
// - appointment: times when the doctor is not available because she's with a patient
public class AppointmentBooking {

    // 返回的Slot是一个具体的DateTime时刻，如果是一个时间段则需要两个时刻
    // 考虑到每个slot的时间间隔固定，因此是需要起始时间即可
    public static void main(String[] args) {
        LocalDate startDate = LocalDate.now().plusDays(3);
        System.out.println(startDate); // 2024-01-01

        List<LocalDateTime> slots = computeSlotsForNextSevenDays(startDate);
        for (LocalDateTime localDateTime: slots) {
            System.out.println(localDateTime);
        }
    }

    // Get all events and filter all available slots
    private static List<LocalDateTime> computeSlotsForNextSevenDays(LocalDate startDate) {
        List<LocalDateTime> availableSlots = new ArrayList<>();
        for (Event event: getEventsData()) {
            if (event.getKind() == Kind.OPENING &&
                    isValidSlot(event.getLocalDateTime().toLocalDate(), startDate)) {
                availableSlots.add(event.getLocalDateTime());
            }
        }
        return availableSlots;
    }

    // 比较的时候只能使用Date日期判断，而非使用DateTime时刻判断
    private static boolean isValidSlot(LocalDate eventDate, LocalDate startDate) {
         return eventDate.isAfter(startDate.minusDays(1))
                 && eventDate.isBefore(startDate.plusDays(8));
    }

    // Simulate database table data with all events information
    private static List<Event> getEventsData() {
        List<Event> eventList = new ArrayList<>();
        eventList.add(new Event(1, Kind.OPENING, LocalDateTime.now().plusDays(1)));
        eventList.add(new Event(2, Kind.APPOINTMENT, LocalDateTime.now().plusDays(1).plusHours(2)));
        eventList.add(new Event(3, Kind.OPENING, LocalDateTime.now().plusDays(1).plusHours(4)));
        eventList.add(new Event(4, Kind.OPENING, LocalDateTime.now().plusDays(2)));
        eventList.add(new Event(5, Kind.OPENING, LocalDateTime.now().plusDays(3)));
        eventList.add(new Event(6, Kind.APPOINTMENT, LocalDateTime.now().plusDays(4)));
        eventList.add(new Event(7, Kind.OPENING, LocalDateTime.now().plusDays(6)));
        eventList.add(new Event(8, Kind.OPENING, LocalDateTime.now().plusDays(8)));
        eventList.add(new Event(9, Kind.OPENING, LocalDateTime.now().plusDays(10)));
        return eventList;
    }
}
