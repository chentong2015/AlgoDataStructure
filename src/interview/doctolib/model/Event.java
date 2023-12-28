package interview.doctolib.model;

import java.util.List;

public class Event {

    // book appointment: move slots from opening to appointment
    private List<Slot> openingSlots;
    private List<Slot> appointmentSlots;

    public Event(List<Slot> openingSlots, List<Slot> appointmentSlots) {
        this.openingSlots = openingSlots;
        this.appointmentSlots = appointmentSlots;
    }

    public List<Slot> getOpeningSlots() {
        return openingSlots;
    }

    public List<Slot> getAppointmentSlots() {
        return appointmentSlots;
    }
}
