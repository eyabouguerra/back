package backAgil.example.back.repositories;

import backAgil.example.back.models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
    @Query("SELECT p FROM Produit p JOIN FETCH p.typeProduit")
    List<Produit> findAllWithTypeProduit();

    List<Produit> findByTypeProduit_Id(Long typeId);
    boolean existsByCodeProduit(String codeProduit);

}
