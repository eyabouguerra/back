package backAgil.example.back.repositories;


import backAgil.example.back.models.TypeProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeProduitRepository extends JpaRepository<TypeProduit, Long> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}
