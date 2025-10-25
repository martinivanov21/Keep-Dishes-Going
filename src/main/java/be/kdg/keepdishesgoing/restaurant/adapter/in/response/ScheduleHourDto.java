package be.kdg.keepdishesgoing.restaurant.adapter.in.response;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

public record ScheduleHourDto(
        UUID scheduleHourId,
        DayOfWeek dayOfWeek,
        LocalTime openingTime,
        LocalTime closingTime
) {
}
