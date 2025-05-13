package backAgil.example.back.services;

import backAgil.example.back.models.Camion;
import backAgil.example.back.models.Citerne;

import java.util.List;
import java.util.Optional;

public interface CamionService {
    Camion addCamion(Camion camion);
    List<Camion> getAllCamions();
    Optional<Camion> getCamionById(Long id);
    Camion updateCamion(Long id, Camion camionDetails);
    void deleteCamion(Long id);
    List<Camion> getCamionsByMarque(String marque);
    /*Citerne getCiterneByImmatriculation(String immatriculation);*/





}
