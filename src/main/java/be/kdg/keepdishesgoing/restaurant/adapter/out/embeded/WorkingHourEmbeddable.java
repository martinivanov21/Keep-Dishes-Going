package be.kdg.keepdishesgoing.restaurant.adapter.out.embeded;

import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Embeddable
public class WorkingHourEmbeddable {

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week", nullable = false)
    private DayOfWeek dayOfWeek;

    @Column(name = "open_time", nullable = false)
    private LocalTime openingTime;

    @Column(name = "close_time", nullable = false)
    private LocalTime closingTime;

    public WorkingHourEmbeddable() {
    }

    public WorkingHourEmbeddable(DayOfWeek dayOfWeek, LocalTime openingTime, LocalTime closingTime) {
        this.dayOfWeek = dayOfWeek;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
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
