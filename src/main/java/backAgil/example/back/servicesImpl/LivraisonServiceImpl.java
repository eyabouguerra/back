package backAgil.example.back.servicesImpl;

import backAgil.example.back.models.Camion;
import backAgil.example.back.models.Citerne;
import backAgil.example.back.models.Commande;
import backAgil.example.back.models.Livraison;
import backAgil.example.back.repositories.CamionRepository;
import backAgil.example.back.repositories.CiterneRepository;
import backAgil.example.back.repositories.CommandeRepository;
import backAgil.example.back.repositories.LivraisonRepository;
import backAgil.example.back.services.CamionService;
import backAgil.example.back.services.LivraisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LivraisonServiceImpl implements LivraisonService {

    @Autowired
    private LivraisonRepository livraisonRepository;
    @Autowired
    private CamionRepository camionRepository;
    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private CamionService cService;

    @Autowired
    private CiterneRepository citerneRepository;

    public List<Livraison> getAllLivraisons() {
        return livraisonRepository.findAll();
    }

    public Optional<Livraison> getLivraisonById(Long id) {
        return livraisonRepository.findById(id);
    }

    @Override
    public Livraison addLivraison(Livraison livraison) {
        // Vérification si le code de livraison existe déjà
        if (livraisonRepository.existsByCodeLivraison(livraison.getCodeLivraison())) {
            throw new IllegalArgumentException("Code de livraison déjà utilisé.");
        }

        // Vérification de l'existence du camion
        if (livraison.getCamion() == null) {
            throw new IllegalArgumentException("Le camion est requis pour la livraison.");
        }

        Camion camion = camionRepository.findById(livraison.getCamion().getId())
                .orElseThrow(() -> new IllegalArgumentException("Camion introuvable"));

        // Vérification de l'existence de la citerne
        Citerne citerne = citerneRepository.findById(livraison.getCiterne().getId())
                .orElseThrow(() -> new IllegalArgumentException("Citerne introuvable"));

        // Vérification des commandes
        List<Commande> commandes = livraison.getCommandes().stream()
                .map(c -> commandeRepository.findById(c.getId())
                        .orElseThrow(() -> new IllegalArgumentException("Commande introuvable: ID=" + c.getId())))
                .toList();

        livraison.setCamion(camion);
        livraison.setCiterne(citerne);
        livraison.setCommandes(commandes);

        return livraisonRepository.save(livraison);
    }



    public Livraison updateLivraison(Long id, Livraison updatedLivraison) {
        return livraisonRepository.findById(id).map(livraison -> {
            livraison.setDateLivraison(updatedLivraison.getDateLivraison());
            livraison.setStatut(updatedLivraison.getStatut());
            livraison.setCamion(updatedLivraison.getCamion());
            livraison.setCommandes(updatedLivraison.getCommandes());
            return livraisonRepository.save(livraison);
        }).orElseThrow(() -> new RuntimeException("Livraison non trouvée avec l'ID : " + id));
    }

    public void deleteLivraison(Long id) {
        livraisonRepository.deleteById(id);
    }
    public String getImmatriculationByMarque(String marque) {
        List<Camion> camions = cService.getCamionsByMarque(marque);
        if (!camions.isEmpty()) {
            return camions.get(0).getImmatriculation(); // Retourne l'immatriculation du premier camion trouvé
        }
        return "Camion non trouvé pour cette marque";
    }

    public Livraison findLivraisonWithCommandes(Long id) {
        return livraisonRepository.findLivraisonWithCommandes(id);
    }



}
