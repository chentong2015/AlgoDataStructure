package interview.doctolib;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class AppointmentBookingTest {

    public void testComputeSlots() {
        LocalDate startDate = LocalDate.now().plusDays(3);
        System.out.println(startDate); // 2024-01-01

        AppointmentBooking appointmentBooking = new AppointmentBooking();
        List<LocalDateTime> slots = appointmentBooking.computeSlotsForNextSevenDays(startDate);

        // Create assertions for the results
        for (LocalDateTime localDateTime: slots) {
            System.out.println(localDateTime);
        }
    }
}
