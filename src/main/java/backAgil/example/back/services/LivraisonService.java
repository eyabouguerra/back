package backAgil.example.back.services;

import backAgil.example.back.models.Camion;
import backAgil.example.back.models.Citerne;
import backAgil.example.back.models.Livraison;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface LivraisonService {

    Livraison addLivraison(Livraison livraison);

    List<Livraison> getAllLivraisons();

    Optional<Livraison> getLivraisonById(Long id);

    Livraison updateLivraison(Long id, Livraison updatedLivraison);

    void deleteLivraison(Long id);

    public List<Citerne> getCiterneDisponiblesPourDate(Date date);
    public List<Camion> getCamionsDisponiblesPourDate(Date date);
}