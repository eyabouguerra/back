package backAgil.example.back.repositories;

import backAgil.example.back.models.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface LivraisonRepository extends JpaRepository<Livraison , Long> {
    boolean existsByCodeLivraison(String codeLivraison);
    List<Livraison> findByDateLivraison(Date dateLivraison);
}
