package backAgil.example.back.controllers;

import backAgil.example.back.models.Produit;
import backAgil.example.back.models.TypeProduit;
import backAgil.example.back.services.TypeProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/typeproduits/v1")
public class TypeProduitController {

    private final TypeProduitService typeProduitService;

    @Autowired
    public TypeProduitController(TypeProduitService typeProduitService) {
        this.typeProduitService = typeProduitService;
    }

    @GetMapping
    public List<TypeProduit> getAllTypeProduits() {
        return typeProduitService.getAllTypeProduits();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeProduit> getTypeProduitById(@PathVariable Long id) {
        Optional<TypeProduit> typeProduit = typeProduitService.getTypeProduitById(id);
        return typeProduit.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<TypeProduit> addTypeProduit(@RequestBody TypeProduit typeProduit) {
        TypeProduit createdProduit = typeProduitService.addTypeProduit(typeProduit);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeProduit> updateTypeProduit(@PathVariable Long id, @RequestBody TypeProduit typeProduitDetails) {
        TypeProduit updatedTypeProduit = typeProduitService.updateTypeProduit(id, typeProduitDetails);
        return ResponseEntity.ok(updatedTypeProduit); // Retourne le produit mis à jour
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypeProduit(@PathVariable Long id) {
        typeProduitService.deleteTypeProduit(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
