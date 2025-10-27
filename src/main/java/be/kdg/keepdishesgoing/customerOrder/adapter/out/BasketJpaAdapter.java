package be.kdg.keepdishesgoing.customerOrder.adapter.out;

import be.kdg.keepdishesgoing.customerOrder.adapter.mapper.BasketMapper;
import be.kdg.keepdishesgoing.customerOrder.domain.Basket;
import be.kdg.keepdishesgoing.customerOrder.domain.BasketId;
import be.kdg.keepdishesgoing.customerOrder.port.out.DeleteBasketPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.LoadBasketPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.SaveBasketPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BasketJpaAdapter implements LoadBasketPort, SaveBasketPort, DeleteBasketPort {

    private final BasketJpaRepository basketRepository;
    private final BasketMapper basketMapper;

    public BasketJpaAdapter(BasketJpaRepository basketRepository, BasketMapper basketMapper) {
        this.basketRepository = basketRepository;
        this.basketMapper = basketMapper;
    }

    @Override
    @Transactional
    public void delete(BasketId basketId) {
        basketRepository.deleteById(basketId.uuid());
    }

    @Override
    public Optional<Basket> loadById(BasketId basketId) {
        return basketRepository.findById(basketId.uuid())
                .map(basketMapper::toDomain);
    }

    @Override
    @Transactional
    public Basket save(Basket basket) {
        BasketJpaEntity entity = basketMapper.toEntity(basket);
        BasketJpaEntity saved = basketRepository.save(entity);
        return basketMapper.toDomain(saved);
    }
}
