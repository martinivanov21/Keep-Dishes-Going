package be.kdg.keepdishesgoing.restaurant.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class ScheduleHour {
    private UUID scheduleHourId;
    private LocalDateTime time;

    public UUID getScheduleHourId() {
        return scheduleHourId;
    }

    public void setScheduleHourId(UUID scheduleHourId) {
        this.scheduleHourId = scheduleHourId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
