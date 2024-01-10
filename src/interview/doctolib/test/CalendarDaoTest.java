package interview.doctolib.test;

import interview.doctolib.dao.CalendarDao;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public class CalendarDaoTest {

    public static void main(String[] args) throws Exception {
        CalendarDao calendarDao = new CalendarDao();
        List<Timestamp> slots = calendarDao.getAvailableSlots(LocalDate.now());
        // Show all available slots timestamp
    }
}
