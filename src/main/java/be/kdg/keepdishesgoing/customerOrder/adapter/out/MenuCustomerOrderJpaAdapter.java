package be.kdg.keepdishesgoing.customerOrder.adapter.out;

import be.kdg.keepdishesgoing.customerOrder.adapter.MenuProjectionEventListener;
import be.kdg.keepdishesgoing.customerOrder.adapter.mapper.MenuProjectionMapper;
import be.kdg.keepdishesgoing.customerOrder.domain.Menu;
import be.kdg.keepdishesgoing.customerOrder.domain.MenuId;
import be.kdg.keepdishesgoing.customerOrder.port.out.LoadMenuPort;
import be.kdg.keepdishesgoing.customerOrder.port.out.SaveMenuPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MenuCustomerOrderJpaAdapter implements SaveMenuPort, LoadMenuPort {

    private final MenuProjectionMapper menuMapper;
    private final MenuCustomerOrderJpaRepository menuCustomerOrderJpaRepository;

    public MenuCustomerOrderJpaAdapter(MenuProjectionMapper menuMapper, MenuCustomerOrderJpaRepository menuCustomerOrderJpaRepository) {
        this.menuMapper = menuMapper;
        this.menuCustomerOrderJpaRepository = menuCustomerOrderJpaRepository;
    }

    @Override
    public Menu loadById(MenuId menuId) {
        return menuCustomerOrderJpaRepository.findById(menuId.menuId())
                .map(menuMapper::toDomain)
                .orElse(null);
    }

    @Override
    public List<Menu> loadAll() {
        return menuCustomerOrderJpaRepository.findAll().stream()
                .map(menuMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public Menu save(Menu menu) {
        MenuCustomerOrderJpaEntity entity = menuMapper.toEntity(menu);
        MenuCustomerOrderJpaEntity saved = menuCustomerOrderJpaRepository.save(entity);
        return menuMapper.toDomain(saved);
    }
}
