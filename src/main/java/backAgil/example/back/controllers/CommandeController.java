package backAgil.example.back.controllers;
import backAgil.example.back.models.CommandeProduit;
import backAgil.example.back.models.Commande;
import backAgil.example.back.models.Produit;
import backAgil.example.back.models.TypeProduit;
import backAgil.example.back.repositories.*;
import backAgil.example.back.services.CommandeService;
import backAgil.example.back.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/commandes/v1")
@CrossOrigin("*")
public class CommandeController {

    @Autowired
    private CommandeService cService;

    @Autowired
    private ProduitService produitService;
    @Autowired
    private commandeProduitRepository  commandeProduitRepository;


    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private TypeProduitRepository typeProduitRepository;

    // GET all commandes

    @GetMapping
    public List<Commande> getAll() {
        return cService.getAllCommandes();  // Retourne directement la liste des commandes
    }

    // GET commande by ID
    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommandeById(@PathVariable("id") Long id) {
        return commandeRepository.findCommandeWithProduitsAndTypes(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CHECK if codeCommande exists
    @GetMapping("/check-code")
    public ResponseEntity<Map<String, Boolean>> checkCodeCommande(@RequestParam String codeCommande) {
        boolean exists = commandeRepository.existsByCodeCommande(codeCommande);
        return ResponseEntity.ok(Map.of("exists", exists));
    }



    // ADD commande
    @PostMapping
    public ResponseEntity<Commande> addCommande(@RequestBody Commande commande) {
        Commande createdCommande = cService.addCommande(commande);
        return new ResponseEntity<>(createdCommande, HttpStatus.CREATED);
    }


    // EDIT commande
    @PutMapping("/{id}")
    public ResponseEntity<?> editCommande(@PathVariable("id") Long id, @RequestBody Commande c) {
        try {
            c.setId(id);

            if (c.getCommandeProduits() != null) {
                for (CommandeProduit cp : c.getCommandeProduits()) {
                    Produit produit = cp.getProduit();
                    if (produit != null && produit.getId() != null) {
                        Produit existingProduit = produitService.getProduitById(produit.getId());
                        if (existingProduit != null) {
                            produit.setNomProduit(existingProduit.getNomProduit());
                            produit.setDescription(existingProduit.getDescription());
                            produit.setPrix(existingProduit.getPrix());
                            produit.setDate(existingProduit.getDate());
                        }
                    }
                    cp.setCommande(c); // Important : rattacher chaque commandeProduit à la commande
                }
            }

            Commande updatedCommande = cService.editCommande(c);
            return ResponseEntity.ok(updatedCommande);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erreur serveur : " + e.getMessage());
        }
    }


    // DELETE commande
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommande(@PathVariable("id") Long id) {
        cService.deleteCommandeById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idCommande}/type-produits")
    public List<TypeProduit> getTypeProduitsParCommande(@PathVariable Long idCommande) {
        List<CommandeProduit> commandeProduits = commandeProduitRepository.findByCommandeId(idCommande);

        return commandeProduits.stream()
                .map(CommandeProduit::getProduit)
                .filter(Objects::nonNull)
                .map(Produit::getTypeProduit)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
    }


}
