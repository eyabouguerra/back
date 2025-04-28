package backAgil.example.back.repositories;

import backAgil.example.back.models.OrderDetail;
import org.hibernate.query.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
