package be.kdg.keepdishesgoing.customerOrder.adapter.in.response;

public record CheckoutRequest(
        String customerName,
        String customerEmail,
        String deliveryStreet,
        int deliveryNumber,
        String deliveryCity
) {
}
