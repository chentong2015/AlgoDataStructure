package interview.doctolib.model;

// Events come in two kinds:
// - opening: times when the doctor is available to take patients
// - appointment: times when the doctor is not available because she's with a patient
public enum Kind {

    OPENING,
    APPOINTMENT
}