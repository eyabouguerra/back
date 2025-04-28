package backAgil.example.back.servicesImpl;


import backAgil.example.back.models.Cart;
import backAgil.example.back.models.Produit;
import backAgil.example.back.repositories.CartRepository;

import backAgil.example.back.repositories.ProduitRepository;
import backAgil.example.back.services.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cRepo;
    @Autowired
    private ProduitRepository pRepo;
    /*
    @Autowired
    private UserRepository uRepo;
     */


    public Cart addToCart (Long id){
        Produit produit = pRepo.findById(id).get();
        /*String = username = jwtRequestFilter.CUURRENT_USER;
       User user = null;
       if(username != null){
       user = uRepo.findById(username).get();
       }
        */
        if(produit != null /* && user != null*/){
            Cart cart = new Cart(produit/*,user*/);
            return cRepo.save(cart);
        }
        return null;
    }
   /* public List<Cart> getCartDetails() {
        String username = JwtRequestFilter.CURRENT_USER;
        User user =  uRepo.findById(username).get();
        return cRepo.findByUser(user);
    }*/
    public List<Cart> getCartDetails() {
        return cRepo.findAll();
    }

    public boolean removeFromCart(Long id) {
        // Rechercher le produit dans le panier
        List<Cart> cartItems = cRepo.findAll();

        for (Cart cartItem : cartItems) {
            if (cartItem.getProduct().getId().equals(id)) {
                cRepo.delete(cartItem);  // Supprimer l'élément du panier
                return true;
            }
        }
        return false;  // Retourne false si le produit n'a pas été trouvé dans le panier
    }



}
