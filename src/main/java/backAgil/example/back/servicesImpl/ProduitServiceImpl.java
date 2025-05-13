package backAgil.example.back.servicesImpl;

import backAgil.example.back.models.Cart;
import backAgil.example.back.models.Produit;
import backAgil.example.back.repositories.CartRepository;
import backAgil.example.back.repositories.ProduitRepository;
import backAgil.example.back.services.ProduitService; // Importez l'interface ProduitService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProduitServiceImpl implements ProduitService { // Implémentez l'interface ProduitService
    @Autowired
    private ProduitRepository pRepo;
    /*@Autowired
    private UserRepository uRepo;
     */
    @Autowired
    private CartRepository cRepo;

    @Autowired
    private ProduitRepository produitRepository;


    @Override
    public List<Produit> getAllProduits() {
        return pRepo.findAll();
    }

    @Override
    public Produit getProduitById(Long id) {
        return pRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteProduitById(Long id) {
        pRepo.deleteById(id);
    }

    @Override
    public Produit addProduit(Produit p) {
        try {
            return pRepo.save(p);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'ajout du produit: " + e.getMessage(), e);
        }
    }


    @Override
    public Produit editProduit(Produit p) {
        if (p.getId() == null) {
            throw new IllegalArgumentException("L'ID du produit ne doit pas être null.");
        }

        Produit existingProduit = pRepo.findById(p.getId())
                .orElseThrow(() -> new RuntimeException("Produit avec ID " + p.getId() + " introuvable"));

        // Mise à jour des champs sans affecter le typeProduit
        existingProduit.setCodeProduit(p.getCodeProduit());
        existingProduit.setNomProduit(p.getNomProduit());
        existingProduit.setLibelle(p.getLibelle());
        existingProduit.setPrix(p.getPrix());
        existingProduit.setDate(p.getDate());
        existingProduit.setDescription(p.getDescription());

        // Ne modifie pas typeProduit si le champ est nul
        if (p.getTypeProduit() != null) {
            existingProduit.setTypeProduit(p.getTypeProduit());
        }

        return pRepo.save(existingProduit);
    }

    public List<Produit> getProduitsByType(Long typeId) {

        return pRepo.findByTypeProduit_Id(typeId);
    }

    /*public List<Produit> getProductDetails(boolean isSingleProductCheckout,Long id){
        if(isSingleProductCheckout) {
            List<Produit> list = new ArrayList<>();
            Produit product = pRepo.findById(id).get();
            list.add(product);
            return list;
        }else {
        String username = JWTRequestFilter.CURRENT_USER.
        User user = uRepo.findById(username).get();

        List<Cart> carts = cRepo.findByUser(user);
            return carts.stream().map(x -> x.getProduit().collect(Collectors.toList()));
            List<Produit> produits = cRepo.findAll()  // Get all Cart objects
                    .stream()
                    .map(cart -> cart.getProduct())  // Map to the Produit of each Cart
                    .collect(Collectors.toList());  // Collect the results into a list
            return produits;


        }
    }*/
    public List<Produit> getProductDetails(boolean isSingleProductCheckout, Long id) {
        if (isSingleProductCheckout) {
            // Acheter un seul produit
            Produit product = pRepo.findById(id).orElseThrow(() -> new RuntimeException("Produit non trouvé"));
            List<Produit> list = new ArrayList<>();
            list.add(product);
            return list;
        } else {
            // Récupérer tous les produits des paniers (sans se soucier de l'utilisateur)
            List<Cart> carts = cRepo.findAll(); // Récupère tous les paniers disponibles
            List<Produit> produits = carts.stream()
                    .map(cart -> cart.getProduct())  // Mapper chaque panier à son produit
                    .collect(Collectors.toList());  // Collecter les produits dans une liste
            return produits;
        }
    }


    @Override
    public Produit getProductById(Long id) {
        return produitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
    }

}
