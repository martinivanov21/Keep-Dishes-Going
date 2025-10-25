package be.kdg.keepdishesgoing.restaurant.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

public class ScheduleHour {

    private ScheduleHourId scheduleHourId;

    private DayOfWeek dayOfWeek;

    private LocalTime openingTime;
    private LocalTime closingTime;

    public ScheduleHour(ScheduleHourId scheduleHourId, DayOfWeek dayOfWeek, LocalTime openingTime, LocalTime closingTime) {
        this.scheduleHourId = scheduleHourId;
        this.dayOfWeek = dayOfWeek;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public ScheduleHourId getScheduleHourId() {
        return scheduleHourId;
    }

    public void setScheduleHourId(ScheduleHourId scheduleHourId) {
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
