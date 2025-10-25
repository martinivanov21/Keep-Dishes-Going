package be.kdg.keepdishesgoing.restaurant.domain;

import java.util.UUID;

public record ScheduleHourId(UUID uuid) {

    public static ScheduleHourId create() {
        return new ScheduleHourId(UUID.randomUUID());
    }
    public static ScheduleHourId of(String uuid) {
        return new ScheduleHourId(UUID.fromString(uuid));
    }
}
