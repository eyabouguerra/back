package backAgil.example.back.repositories;

import backAgil.example.back.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT c FROM Client c WHERE LOWER(c.fullName) = LOWER(:fullName)")
    Optional<Client> findByFullName(@Param("fullName") String fullName);

}