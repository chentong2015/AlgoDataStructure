package interview.doctolib.model;

// Events come in two kinds:
// - opening: times when the doctor is available to take patients
// - appointment: times when the doctor is not available because she's with a patient
public enum Kind {

    // appointment的时间段一定是包含在opening开门时间段内的
    // 需要排除已经预约的时间，才能获取开放预约的时间
    OPENING,
    APPOINTMENT
}