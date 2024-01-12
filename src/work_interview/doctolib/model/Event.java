package work_interview.doctolib.model;

import java.time.LocalDateTime;

public class Event {

    private int id;
    // The type of this event
    private Kind kind;
    // timestamps: datetime 就是一个时间戳
    // startAt: 起始时刻的时间戳
    // endAt: 结束时刻的时间戳
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
