package be.kdg.keepdishesgoing.customerOrder.adapter.in;

import be.kdg.keepdishesgoing.customerOrder.adapter.in.response.*;
import be.kdg.keepdishesgoing.customerOrder.adapter.mapper.BasketMapper;
import be.kdg.keepdishesgoing.customerOrder.adapter.mapper.CustomerOrderMapper;
import be.kdg.keepdishesgoing.customerOrder.domain.Basket;
import be.kdg.keepdishesgoing.customerOrder.domain.CustomerOrder;
import be.kdg.keepdishesgoing.customerOrder.port.in.*;
import be.kdg.keepdishesgoing.customerOrder.port.out.LoadDishPort;
import jakarta.annotation.security.PermitAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/customer-order/basket")
@CrossOrigin(origins = "http://localhost:5173" )
@PermitAll
public class BasketController {

    private static final Logger logger = LoggerFactory.getLogger(BasketController.class);

    private final AddItemToBasketUseCase addToBasketUseCase;
    private final UpdateBasketItemUseCase updateBasketItemUseCase;
    private final RemoveFromBasketUseCase removeFromBasketUseCase;
    private final ClearBasketUseCase clearBasketUseCase;
    private final GetBasketUseCase getBasketUseCase;
    private final CheckoutBasketUseCase checkoutUseCase;
    private final BasketMapper basketMapper;
    private final CustomerOrderMapper orderMapper;
    private final LoadDishPort loadDishPort;

    public BasketController(AddItemToBasketUseCase addToBasketUseCase, UpdateBasketItemUseCase updateBasketItemUseCase, RemoveFromBasketUseCase removeFromBasketUseCase, ClearBasketUseCase clearBasketUseCase, GetBasketUseCase getBasketUseCase, CheckoutBasketUseCase checkoutUseCase, BasketMapper basketMapper, CustomerOrderMapper orderMapper, LoadDishPort loadDishPort) {
        this.addToBasketUseCase = addToBasketUseCase;
        this.updateBasketItemUseCase = updateBasketItemUseCase;
        this.removeFromBasketUseCase = removeFromBasketUseCase;
        this.clearBasketUseCase = clearBasketUseCase;
        this.getBasketUseCase = getBasketUseCase;
        this.checkoutUseCase = checkoutUseCase;
        this.basketMapper = basketMapper;
        this.orderMapper = orderMapper;
        this.loadDishPort = loadDishPort;
    }

    @GetMapping("/{basketId}")
    public ResponseEntity<BasketDto> getBasket(@PathVariable UUID basketId) {

        Basket basket = getBasketUseCase.getBasket(new GetBasketCommand(basketId));
        BasketDto dto = basketMapper.toDto(basket, loadDishPort);

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/{basketId}/items")
    public ResponseEntity<BasketDto> addToBasket(
            @PathVariable UUID basketId, @RequestBody AddToBasketRequest request) {

        try {
            Basket basket = addToBasketUseCase.addToBasket(new AddItemToBasketCommand(basketId, request));
            BasketDto dto = basketMapper.toDto(basket, loadDishPort);
            return ResponseEntity.ok(dto);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping("/{basketId}/items")
    public ResponseEntity<BasketDto> updateBasketItem(
            @PathVariable UUID basketId,
            @RequestBody UpdateBasketItemRequest request) {


        Basket basket = updateBasketItemUseCase.updateItem(new UpdateBasketItemCommand(basketId, request));
        BasketDto dto = basketMapper.toDto(basket, loadDishPort);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{basketId}/items/{dishId}")
    public ResponseEntity<BasketDto> removeFromBasket(
            @PathVariable UUID basketId,
            @PathVariable UUID dishId) {

        logger.info("Removing dish {} from basket {}", dishId, basketId);

        Basket basket = removeFromBasketUseCase.removeFromBasket(new RemoveFromBasketCommand(basketId, dishId));
        BasketDto dto = basketMapper.toDto(basket, loadDishPort);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{basketId}")
    public ResponseEntity<Void> clearBasket(@PathVariable UUID basketId) {
        logger.info("Clearing basket: {}", basketId);

        clearBasketUseCase.clearBasket(new ClearBasketCommand(basketId));
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{basketId}/checkout")
    public ResponseEntity<?> checkout(
            @PathVariable UUID basketId,
            @RequestBody CheckoutRequest request) {

        logger.info("Checkout basket {}: {}", basketId, request);

        try {
            CustomerOrder order = checkoutUseCase.checkout(new CheckoutBasketCommand(basketId, request));
            CustomerOrderDto dto = orderMapper.toDto(order);
            return ResponseEntity.ok(dto);
        } catch (IllegalStateException e) {
            logger.error("Checkout failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
