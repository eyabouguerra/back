package backAgil.example.back.repositories;

import backAgil.example.back.models.Citerne;
import backAgil.example.back.models.Compartiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompartimentRepository extends JpaRepository<Compartiment, Long> {
    List<Compartiment> findByCiterneId(Long citerneId);
    @Query("SELECT COALESCE(SUM(c.capaciteMax), 0.0) FROM Compartiment c WHERE c.citerne.id = :citerneId")
    double sumCapaciteByCiterneId(@Param("citerneId") Long citerneId);
    @Query("SELECT COUNT(c) FROM Compartiment c WHERE c.citerne.id = :citerneId")
    long countByCiterneId(@Param("citerneId") Long citerneId);

}