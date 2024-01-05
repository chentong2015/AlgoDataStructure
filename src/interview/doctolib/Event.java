package interview.doctolib;

import java.time.LocalDateTime;

public class Event {

    private int id;
    // The type of this event
    private Kind kind;
    // One slot time: start time + duration ?
    private LocalDateTime localDateTime;

    public Event(int id, Kind kind, LocalDateTime localDateTime) {
        this.id = id;
        this.kind = kind;
        this.localDateTime = localDateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", kind=" + kind +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
