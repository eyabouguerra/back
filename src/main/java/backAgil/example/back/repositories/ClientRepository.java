package backAgil.example.back.repositories;

import backAgil.example.back.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}