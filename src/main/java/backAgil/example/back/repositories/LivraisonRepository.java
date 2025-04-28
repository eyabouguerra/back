package backAgil.example.back.repositories;

import backAgil.example.back.models.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LivraisonRepository extends JpaRepository<Livraison , Long> {
    boolean existsByCodeLivraison(String codeLivraison);


    @Query("SELECT l FROM Livraison l LEFT JOIN FETCH l.commandes WHERE l.id = :id")
    Livraison findLivraisonWithCommandes(@Param("id") Long id);

}
