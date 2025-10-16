package be.kdg.keepdishesgoing.restaurant.adapter.in.response;

import java.time.LocalTime;
import java.util.UUID;

public record ScheduleHourDto(
        UUID scheduleHourId,
        String DayOfWeek,
        LocalTime openingTime,
        LocalTime closingTime
) {
}
