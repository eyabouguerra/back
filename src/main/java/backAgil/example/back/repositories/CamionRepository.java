package backAgil.example.back.repositories;

import backAgil.example.back.models.Camion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CamionRepository extends JpaRepository<Camion, Long> {
    List<Camion> findByMarque(String marque);
    Optional<Camion> findByImmatriculation(String immatriculation);


}
