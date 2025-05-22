package backAgil.example.back.models;


import jakarta.persistence.*;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartId")
    private Long id;
    @OneToOne
    private Produit product;
/*@OneToOne
private User user;
 */
    //Quand je fait user rejenerer tout******///////


    public Cart() {

    }

    public Cart(Produit product) {
        this.product = product;
    }

    public Produit getProduct() {
        return product;
    }

    public void setProduct(Produit product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", product=" + product +
                '}';
    }
}
