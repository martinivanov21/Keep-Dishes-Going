package be.kdg.keepdishesgoing.restaurant.adapter.mapper;

import be.kdg.keepdishesgoing.restaurant.adapter.out.restaurant.RestaurantOrderItemJpaEntity;
import be.kdg.keepdishesgoing.restaurant.adapter.out.restaurant.RestaurantOrderJpaEntity;
import be.kdg.keepdishesgoing.restaurant.domain.RestaurantOrder;
import be.kdg.keepdishesgoing.restaurant.domain.enums.OrderStatus;
import org.springframework.stereotype.Component;

@Component
public class RestaurantOrderMappter {
    public RestaurantOrderJpaEntity toJpa(RestaurantOrder domain) {
        var e = new RestaurantOrderJpaEntity();
        e.setCustomerOrderId(domain.getCustomerOrderId());
        e.setRestaurantId(domain.getRestaurantId());
        e.setCustomerName(domain.getCustomerName());
        e.setCustomerEmail(domain.getCustomerEmail());
        e.setDeliveryStreet(domain.getDeliveryStreet());
        e.setDeliveryNumber(domain.getDeliveryNumber());
        e.setDeliveryCity(domain.getDeliveryCity());
        e.setTotalPrice(domain.getTotalPrice());
        e.setEstimatedTime(domain.getEstimatedTime());
        e.setSubmittedTime(domain.getSubmittedTime());
        e.setOrderStatus(domain.getOrderStatus());

        var items = domain.getItems().stream().map(di -> {
            var ji = new RestaurantOrderItemJpaEntity();
            ji.setDishId(di.getDishId());
            ji.setDishName(di.getDishName());
            ji.setQuantity(di.getQuantity());
            ji.setUnitPrice(di.getUnitPrice());
            ji.setLineTotal(di.getLineTotal());
            ji.setOrder(e);
            return ji;
        }).toList();
        e.getItems().clear();
        e.getItems().addAll(items);
        return e;
    }

    public RestaurantOrder toDomain(RestaurantOrderJpaEntity e) {
        var d = new RestaurantOrder(e.getCustomerOrderId(), e.getRestaurantId());
        // rebuild with the submitted factory-like behavior
        var items = e.getItems().stream()
                .map(ji -> new RestaurantOrder.Item(ji.getDishId(), ji.getDishName(),
                        ji.getQuantity(), ji.getUnitPrice(), ji.getLineTotal()))
                .toList();
        var rebuilt = RestaurantOrder.submitted(
                e.getCustomerOrderId(), e.getRestaurantId(),
                e.getCustomerName(), e.getCustomerEmail(),
                e.getDeliveryStreet(), e.getDeliveryNumber(), e.getDeliveryCity(),
                e.getTotalPrice(), e.getEstimatedTime(), e.getSubmittedTime(),
                items
        );
        if (e.getOrderStatus() == OrderStatus.ACCEPTED) rebuilt.accept(e.getEstimatedTime());
        if (e.getOrderStatus() == OrderStatus.REJECTED) rebuilt.decline();
        return rebuilt;
    }

    public void merge(RestaurantOrder domain, RestaurantOrderJpaEntity target) {
        target.setRestaurantId(domain.getRestaurantId());
        target.setCustomerName(domain.getCustomerName());
        target.setCustomerEmail(domain.getCustomerEmail());
        target.setDeliveryStreet(domain.getDeliveryStreet());
        target.setDeliveryNumber(domain.getDeliveryNumber());
        target.setDeliveryCity(domain.getDeliveryCity());
        target.setTotalPrice(domain.getTotalPrice());
        target.setEstimatedTime(domain.getEstimatedTime());
        target.setSubmittedTime(domain.getSubmittedTime());
        target.setOrderStatus(domain.getOrderStatus());

        target.getItems().clear();
        var items = domain.getItems().stream().map(di -> {
            var ji = new RestaurantOrderItemJpaEntity();
            ji.setOrder(target);
            ji.setDishId(di.getDishId());
            ji.setDishName(di.getDishName());
            ji.setQuantity(di.getQuantity());
            ji.setUnitPrice(di.getUnitPrice());
            ji.setLineTotal(di.getLineTotal());
            return ji;
        }).toList();
        target.getItems().addAll(items);
    }
}
