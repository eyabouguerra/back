package backAgil.example.back.controllers;

import backAgil.example.back.models.Produit;

import backAgil.example.back.models.TypeProduit;
import backAgil.example.back.repositories.ProduitRepository;
import backAgil.example.back.services.ProduitService;
import backAgil.example.back.services.TypeProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RequestMapping("api/produits/v1")
@CrossOrigin("*")
@RestController
public class ProduitController {
    @Autowired
    private ProduitService pService;
    @Autowired
    private ProduitRepository produitRepository;


    //METH 1 : GET All Produit
    @GetMapping
    public List<Produit> getAllProduit(){
        return produitRepository.findAllWithTypeProduit();
    }
    //METH 2 : GET Produit By ID
    @GetMapping("/{id}")
    public Produit getProduitById(@PathVariable("id") Long id) {

        return pService.getProduitById(id);
    }


    @PostMapping()
    public ResponseEntity<Produit> addProduit(@RequestBody Produit produit) {
        try {
            System.out.println("🚀 Produit reçu: " + produit); // Log pour voir ce qui est reçu
            Produit savedProduit = pService.addProduit(produit);
            System.out.println("✅ Produit sauvegardé avec succès: " + savedProduit);
            return ResponseEntity.ok(savedProduit);
        } catch (Exception e) {
            e.printStackTrace(); // Affiche l'erreur dans la console Spring Boot
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/produits/check-code")
    public ResponseEntity<Boolean> checkCodeProduit(@RequestParam String code) {
        boolean exists = produitRepository.existsByCodeProduit(code);
        return ResponseEntity.ok(exists);
    }



    @PutMapping()
    public ResponseEntity<Produit> editProduit(@RequestBody Produit produit) {
        if (produit.getId() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        Produit updatedProduit = pService.editProduit(produit);
        return ResponseEntity.ok(updatedProduit);
    }

    //METH 5 : DELETE produit

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        if (!produitRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        produitRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }



    @GetMapping("/type/{typeId}")
    public ResponseEntity<List<Produit>> getProduitsByType(@PathVariable Long typeId) {
        List<Produit> produits = pService.getProduitsByType(typeId);
        return ResponseEntity.ok(produits);
    }

    //@PreAuthorize("hasRole('Admin')")
    @GetMapping("/getProductDetails/{isSingleProductCheckout}/{id}")
    public List<Produit> getProductDetails(
            @PathVariable(name = "isSingleProductCheckout") boolean isSingleProductCheckout,
            @PathVariable(name = "id") Long id) {
        return pService.getProductDetails(isSingleProductCheckout, id);
    }


}








