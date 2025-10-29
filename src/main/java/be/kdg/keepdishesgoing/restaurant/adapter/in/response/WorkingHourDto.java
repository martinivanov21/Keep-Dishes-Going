package be.kdg.keepdishesgoing.restaurant.adapter.in.response;

public record WorkingHourDto(
        String dayOfWeek,  String openingTime, String closingTime
) {
}
