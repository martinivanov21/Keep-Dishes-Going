package be.kdg.keepdishesgoing.customerOrder.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerOrderJpaRepository extends JpaRepository<CustomerOrderJpaEntity, UUID> {

    @Query("select o from CustomerOrderJpaEntity o where lower(o.customerEmail) = lower(:email) ")
    List<CustomerOrderJpaEntity> findByCustomerEmail(@Param("email") String emi);
}
