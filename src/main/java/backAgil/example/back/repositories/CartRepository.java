package backAgil.example.back.repositories;

import backAgil.example.back.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    //public List<Cart>findByUser(User user);
}
