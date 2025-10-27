package be.kdg.keepdishesgoing.customerOrder.adapter.in.response;

import java.time.LocalTime;

public record ScheduleHourDto(
        String dayOfWeek,
        LocalTime openingTime,
        LocalTime closingTime
) {
}
