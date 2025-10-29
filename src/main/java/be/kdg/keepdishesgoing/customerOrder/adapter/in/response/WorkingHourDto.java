package be.kdg.keepdishesgoing.customerOrder.adapter.in.response;

import java.time.LocalTime;

public record WorkingHourDto(
        String dayOfWeek,
        String openingTime,
        String closingTime
) {
}
