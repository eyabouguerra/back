package backAgil.example.back.services;

import backAgil.example.back.models.Produit;
import java.util.List;

public interface ProduitService {


    List<Produit> getAllProduits();
    Produit getProduitById(Long id);
    void deleteProduitById(Long id);
    Produit addProduit(Produit p);
    Produit editProduit(Produit p);

    List<Produit> getProduitsByType(Long typeId);
    List<Produit>  getProductDetails(boolean isSingleProductCheckout,Long id);
}
