package be.kdg.keepdishesgoing.restaurant.domain;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class ScheduleHour {

    private UUID scheduleHourId;

    private DayOfWeek dayOfWeek;

    private LocalTime openingTime;
    private LocalTime closingTime;

    public ScheduleHour(UUID scheduleHourId, DayOfWeek dayOfWeek, LocalTime openingTime, LocalTime closingTime) {
        this.scheduleHourId = scheduleHourId;
        this.dayOfWeek = dayOfWeek;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public UUID getScheduleHourId() {
        return scheduleHourId;
    }

    public void setScheduleHourId(UUID scheduleHourId) {
        this.scheduleHourId = scheduleHourId;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }
}
