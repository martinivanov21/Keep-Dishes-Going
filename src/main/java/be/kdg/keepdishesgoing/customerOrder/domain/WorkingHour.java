package be.kdg.keepdishesgoing.customerOrder.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class WorkingHour {
    private final DayOfWeek dayOfWeek;
    private final LocalTime openingTime;
    private final LocalTime closingTime;

    public WorkingHour(DayOfWeek dayOfWeek, LocalTime openingTime, LocalTime closingTime) {
        this.dayOfWeek = dayOfWeek;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }
}
