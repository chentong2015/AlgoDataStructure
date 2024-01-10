package interview.doctolib.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// You will write a function that takes a date as input and
// returns an object/hash containing the doctor's available slots for the next seven days,
// starting on the input date.
public class AppointmentBooking {

    // TODO. 两个层面的Filter过滤, 获取最后满足条件的结果
    // 1. Java Level: For loop | Streams Filter
    // 2. Request Level: add 'where' condition in SQL request
    public List<LocalDateTime> computeSlotsForNextSevenDays(LocalDate startDate) {
        // Get all available slot by filtering condition of events
        List<Event> events = getEventsData();
        return events.stream()
                .filter(event -> event.getKind() == Kind.OPENING)
                .map(Event::getLocalDateTime) // Method Reference, map映射到对象的其中一个属性
                .filter(localDateTime -> isValidSlot(localDateTime.toLocalDate(), startDate)) // 利用属性做进一步的过滤
                .toList();

        // List<LocalDateTime> availableSlots = new ArrayList<>();
        // for (Event event: getEventsData()) {
        //     LocalDate date = event.getLocalDateTime().toLocalDate();
        //     if (event.getKind() == Kind.OPENING && isValidSlot(date, startDate)) {
        //         availableSlots.add(event.getLocalDateTime());
        //     }
        // }
        // return availableSlots;
    }

    // 只能使用Date日期判断而非DateTime时刻(DateTime存在计算偏差)
    // The valid period/slot should be from today(after yesterday) to 7 days later
    private boolean isValidSlot(LocalDate eventDate, LocalDate startDate) {
         return eventDate.isAfter(startDate.minusDays(1))
                 && eventDate.isBefore(startDate.plusDays(8));
    }

    // Simulate database table data with events info, all events for one doctor
    private List<Event> getEventsData() {
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
