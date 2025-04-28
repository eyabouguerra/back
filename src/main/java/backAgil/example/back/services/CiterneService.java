package backAgil.example.back.services;

import backAgil.example.back.models.Citerne;

import java.util.List;
import java.util.Optional;

public interface CiterneService {
    List<Citerne> getAllCiternes();
    Optional<Citerne> getCiterneById(Long id);
    Citerne addCiterne(Citerne citerne);
    Citerne updateCiterne(Long id, Citerne newCiterne);
    void deleteCiterne(Long id);
}