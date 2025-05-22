package backAgil.example.back.controllers;

import backAgil.example.back.models.Cart;
import backAgil.example.back.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/cart")
@CrossOrigin("*")
@RestController
public class CartController {
    @Autowired
private CartService cartService;


    //@PreAuthorize("hasRole('User')")
    @GetMapping({"/addToCart/{id}"})
    public Cart addToCart(@PathVariable(name ="id") Long id) {
        return cartService.addToCart(id);

    }
    //@PreAuthorize("hasRole('User')")
    @GetMapping({"/getCartDetails"})
    public List <Cart> getCartDetails(){
        return cartService.getCartDetails();

    }
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeFromCart(@PathVariable Long id) {
        boolean isRemoved = cartService.removeFromCart(id);
        if (isRemoved) {
            return ResponseEntity.ok("Produit supprimé avec succès du panier");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produit non trouvé dans le panier");
        }
    }


}


