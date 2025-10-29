package be.kdg.keepdishesgoing.customerOrder.adapter.out;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Embeddable
public class WorkingHourCustomerEmbeddable {

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week", nullable = false)
    private DayOfWeek dayOfWeek;

    @Column(name = "open_time", nullable = false)
    private LocalTime open;

    @Column(name = "close_time", nullable = false)
    private LocalTime close;

    protected WorkingHourCustomerEmbeddable() { }

    public WorkingHourCustomerEmbeddable(DayOfWeek dayOfWeek, LocalTime open, LocalTime close) {
        this.dayOfWeek = dayOfWeek;
        this.open = open;
        this.close = close;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getOpen() {
        return open;
    }

    public void setOpen(LocalTime open) {
        this.open = open;
    }

    public LocalTime getClose() {
        return close;
    }

    public void setClose(LocalTime close) {
        this.close = close;
    }
}
