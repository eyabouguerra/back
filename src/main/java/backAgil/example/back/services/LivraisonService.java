package backAgil.example.back.services;

import backAgil.example.back.models.Citerne;
import backAgil.example.back.models.Livraison;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LivraisonService {

    Livraison addLivraison(Livraison livraison);

    List<Livraison> getAllLivraisons();

    Optional<Livraison> getLivraisonById(Long id);

    Livraison updateLivraison(Long id, Livraison updatedLivraison);

    void deleteLivraison(Long id);
    String getImmatriculationByMarque(String marque);


    @Query("SELECT l FROM Livraison l LEFT JOIN FETCH l.commandes WHERE l.id = :id")
    Livraison findLivraisonWithCommandes(@Param("id") Long id);

}