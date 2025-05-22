package backAgil.example.back.repositories;

import backAgil.example.back.models.Commande;
import backAgil.example.back.models.CommandeProduit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface commandeProduitRepository extends JpaRepository<CommandeProduit, Long> {
    List<CommandeProduit> findByCommandeId(Long commandeId);}
