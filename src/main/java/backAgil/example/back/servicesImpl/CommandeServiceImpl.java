package backAgil.example.back.servicesImpl;

import backAgil.example.back.models.Commande;
import backAgil.example.back.models.CommandeProduit;
import backAgil.example.back.models.Produit;
import backAgil.example.back.repositories.CommandeRepository;
import backAgil.example.back.repositories.ProduitRepository;
import backAgil.example.back.repositories.commandeProduitRepository;
import backAgil.example.back.services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommandeServiceImpl implements CommandeService {

    @Autowired
    private CommandeRepository cRepo;
    @Autowired
    private commandeProduitRepository  commandeProduitRepository;

    @Autowired
    private ProduitRepository pRepo;

    @Override
    public List<Commande> getAllCommandes() {
        return cRepo.findAll();
    }

    @Override
    public Commande getCommandeById(Long id) {
        Commande commande = cRepo.findById(id).orElse(null);
        if (commande != null) {
            // Initialisation explicite des relations
            commande.getCommandeProduits().forEach(cp -> {
                Produit produit = cp.getProduit();
                if (produit != null) {
                    produit.getTypeProduit(); // force le chargement du type de produit
                }
            });
        }
        return commande;
    }


    @Override
    public void deleteCommandeById(Long id) {
        cRepo.deleteById(id);
    }


    @Override
    public Commande addCommande(Commande commande) {
        if (commande.getCommandeProduits() == null) {
            commande.setCommandeProduits(new ArrayList<>());
        }

        List<CommandeProduit> commandeProduits = new ArrayList<>();
        Float totalPrice = 0.0f;

        for (CommandeProduit cp : commande.getCommandeProduits()) {
            Produit produit = cp.getProduit();

            if (cp.getQuantite() == null || cp.getQuantite() <= 0) {
                cp.setQuantite(1.0f);
            }

            Float prixProduit = produit.getPrix();
            float sousTotal = cp.getQuantite() * prixProduit;
            totalPrice += sousTotal;

            cp.setCommande(commande);
            commandeProduits.add(cp);
        }

        commande.setTotalPrice(totalPrice);

        cRepo.save(commande); // cascade: Commande + ses CommandeProduits
        commandeProduitRepository.saveAll(commandeProduits); // optionnel si cascade

        return commande;
    }

    @Override
    public Commande editCommande(Commande updatedCommande) {
        Commande existingCommande = cRepo.findById(updatedCommande.getId())
                .orElseThrow(() -> new IllegalArgumentException("Commande non trouvée"));

        existingCommande.setCodeCommande(updatedCommande.getCodeCommande());
        existingCommande.setQuantite(updatedCommande.getQuantite());
        existingCommande.setDateCommande(updatedCommande.getDateCommande());
        existingCommande.setPrice(updatedCommande.getPrice());
        existingCommande.setTotalPrice(updatedCommande.getTotalPrice());

        // Supprimer les anciennes relations et ajouter les nouvelles
        existingCommande.getCommandeProduits().clear();
        if (updatedCommande.getCommandeProduits() != null) {
            for (CommandeProduit cp : updatedCommande.getCommandeProduits()) {
                cp.setCommande(existingCommande);  // rattache la commande
                existingCommande.getCommandeProduits().add(cp);
            }
        }

        return cRepo.save(existingCommande);
    }

}
