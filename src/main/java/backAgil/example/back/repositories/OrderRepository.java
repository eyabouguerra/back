package backAgil.example.back.repositories;

import backAgil.example.back.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.produit")
    List<Order> findAll();

    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.produit WHERE o.orderId = :orderId")
    Optional<Order> findById(@Param("orderId") Long orderId);
}