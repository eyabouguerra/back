package backAgil.example.back.services;

import backAgil.example.back.models.Cart;
import backAgil.example.back.models.Produit;

import java.util.List;

public interface CartService {
    Cart addToCart (Long id);
    List<Cart> getCartDetails();
    boolean removeFromCart(Long id);

}
