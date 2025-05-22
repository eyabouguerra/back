package backAgil.example.back.services;

import backAgil.example.back.models.Produit;
import backAgil.example.back.models.TypeProduit;

import java.util.List;
import java.util.Optional;

public interface TypeProduitService {

    List<TypeProduit> getAllTypeProduits();

    Optional<TypeProduit> getTypeProduitById(Long id);

    TypeProduit addTypeProduit(TypeProduit typeProduit);

    TypeProduit updateTypeProduit(Long id, TypeProduit typeProduit);

    void deleteTypeProduit(Long id);



}
