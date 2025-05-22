package backAgil.example.back.controllers;

import backAgil.example.back.models.Camion;
import backAgil.example.back.models.Citerne;
import backAgil.example.back.models.Livraison;
import backAgil.example.back.repositories.CamionRepository;
import backAgil.example.back.repositories.CommandeRepository;
import backAgil.example.back.repositories.LivraisonRepository;
import backAgil.example.back.services.CamionService;
import backAgil.example.back.services.LivraisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/livraisons")
public class LivraisonController {
    @Autowired
    private LivraisonService livraisonService;
    @Autowired
    private CamionService camionService;
    @Autowired
    private CamionRepository camionRepository;
    @Autowired
    private LivraisonRepository livraisonRepository;
    @GetMapping
    public ResponseEntity<List<Livraison>> getAllLivraisons() {
        List<Livraison> livraisons = livraisonService.getAllLivraisons();
        return ResponseEntity.ok(livraisons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livraison> getLivraisonById(@PathVariable Long id) {
        Optional<Livraison> livraison = livraisonService.getLivraisonById(id);
        return livraison.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Livraison> updateLivraison(@PathVariable Long id, @RequestBody Livraison updatedLivraison) {
        try {
            Livraison livraison = livraisonService.updateLivraison(id, updatedLivraison);
            return ResponseEntity.ok(livraison);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Retourne Not Found si la mise à jour échoue
        }
    }

    @PostMapping
    public ResponseEntity<Livraison> createLivraison(@RequestBody Livraison livraison) {
        Livraison newLivraison = livraisonService.addLivraison(livraison);
        return ResponseEntity.status(201).body(newLivraison);
    }
    // CHECK if codeCommande exists.filter(camion -> !camionsUtilises.contains(camion))
    @GetMapping("/check-code")
    public ResponseEntity<Map<String, Boolean>> checkCodeCommande(@RequestParam String codeLivraison) {
        boolean exists = livraisonRepository.existsByCodeLivraison(codeLivraison);
        return ResponseEntity.ok(Map.of("exists", exists));
    }
    @GetMapping("/camions/disponibles")
    public List<Camion> getCamionsDisponibles(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return livraisonService.getCamionsDisponiblesPourDate(date);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivraison(@PathVariable Long id) {
        livraisonService.deleteLivraison(id);
        return ResponseEntity.noContent().build(); // Retourne un code 204 si la suppression est réussie
    }
    @GetMapping("/citerne/disponibles")
    public List<Citerne> getCiterneDisponibles(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return livraisonService.getCiterneDisponiblesPourDate(date);
    }








}
