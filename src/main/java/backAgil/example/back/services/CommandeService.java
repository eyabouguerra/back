package backAgil.example.back.services;

import backAgil.example.back.models.Commande;
import backAgil.example.back.models.Produit;

import java.util.List;

public interface CommandeService {
    public List<Commande> getAllCommandes();
    public Commande getCommandeById(Long id);
    public void deleteCommandeById(Long id);
    public Commande addCommande(Commande commande);
    public Commande editCommande(Commande c);

    boolean checkCodeCommandeExists(String codeCommande);
}
