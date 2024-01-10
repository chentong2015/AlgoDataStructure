package interview.doctolib.test;

import interview.doctolib.model.AppointmentBooking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class AppointmentBookingTest {

    // 2024-01-13
    // 2024-01-13T17:31:35.643351
    // 2024-01-16T17:31:35.643356
    // 2024-01-18T17:31:35.643359
    // 2024-01-20T17:31:35.643362
    public static void main(String[] args) {
        LocalDate startDate = LocalDate.now().plusDays(3);
        System.out.println(startDate);

        AppointmentBooking appointmentBooking = new AppointmentBooking();
        List<LocalDateTime> slots = appointmentBooking.computeSlotsForNextSevenDays(startDate);

        // Create assertions for the results
        for (LocalDateTime localDateTime: slots) {
            System.out.println(localDateTime);
        }
    }
}
