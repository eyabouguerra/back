package backAgil.example.back.repositories;

import backAgil.example.back.models.Citerne;
import backAgil.example.back.models.Compartiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CiterneRepository extends JpaRepository<Citerne, Long> {
    Optional<Citerne> findByReference(String reference);

}