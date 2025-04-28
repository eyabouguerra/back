package backAgil.example.back.servicesImpl;

import backAgil.example.back.models.Produit;
import backAgil.example.back.models.TypeProduit;
import backAgil.example.back.repositories.ProduitRepository;
import backAgil.example.back.repositories.TypeProduitRepository;
import backAgil.example.back.services.TypeProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class TypeProduitServiceImpl implements TypeProduitService {

    private final TypeProduitRepository typeProduitRepository;

    @Autowired
    public TypeProduitServiceImpl(TypeProduitRepository typeProduitRepository) {
        this.typeProduitRepository = typeProduitRepository;
    }

    @Override
    public List<TypeProduit> getAllTypeProduits() {
        return typeProduitRepository.findAll();
    }

    @Override
    public Optional<TypeProduit> getTypeProduitById(Long id) {
        return typeProduitRepository.findById(id);
    }

    @Override
    public TypeProduit addTypeProduit(TypeProduit typeProduit) {
        return typeProduitRepository.save(typeProduit);
    }

    @Override
    public TypeProduit updateTypeProduit(Long id, TypeProduit typeProduitDetails) {
        TypeProduit typeProduit = typeProduitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));

        typeProduit.setName(typeProduitDetails.getName());
        typeProduit.setDescription(typeProduitDetails.getDescription());
        typeProduit.setDate(typeProduitDetails.getDate());

        return typeProduitRepository.save(typeProduit); // Retourne le produit mis à jour
    }


    @Override
    public void deleteTypeProduit(Long id) {
        typeProduitRepository.deleteById(id);
    }



}
