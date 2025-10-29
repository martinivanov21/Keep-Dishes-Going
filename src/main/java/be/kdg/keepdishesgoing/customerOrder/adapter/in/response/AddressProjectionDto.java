package be.kdg.keepdishesgoing.customerOrder.adapter.in.response;

public record AddressProjectionDto(
        String deliveryStreet,
        int deliveryNumber,
        String deliveryCity

) {
}
