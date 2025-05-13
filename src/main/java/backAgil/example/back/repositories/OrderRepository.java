package backAgil.example.back.repositories;

import backAgil.example.back.models.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Cette annotation permet de charger les produits associés en une seule requête
    @EntityGraph(attributePaths = {"orderProducts", "orderProducts.produit"})
    List<Order> findAll();

    @EntityGraph(attributePaths = {"orderProducts", "orderProducts.produit"})
    Optional<Order> findById(Long id);
}