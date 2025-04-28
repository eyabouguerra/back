package backAgil.example.back.controllers;

import backAgil.example.back.models.Citerne;
import backAgil.example.back.models.Compartiment;
import backAgil.example.back.services.CiterneService;
import backAgil.example.back.services.CompartimentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/citernes")
@CrossOrigin("*")
public class CiterneController {

    @Autowired
    private CiterneService citerneService;


    @GetMapping
    public List<Citerne> getAllCiternes() {
        return citerneService.getAllCiternes();
    }



    @GetMapping("/{id}")
    public ResponseEntity<Citerne> getCiterneById(@PathVariable Long id) {
        Optional<Citerne> citerne = citerneService.getCiterneById(id);
        return citerne.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public Citerne addCiterne(@RequestBody Citerne citerne) {
        return citerneService.addCiterne(citerne);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCiterne(@PathVariable Long id, @RequestBody Citerne citerne) {
        try {
            Citerne updatedCiterne = citerneService.updateCiterne(id, citerne);
            return ResponseEntity.ok(updatedCiterne);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCiterne(@PathVariable Long id) {
        citerneService.deleteCiterne(id);
        return ResponseEntity.noContent().build();
    }
}