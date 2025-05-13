package backAgil.example.back.models;


import backAgil.example.back.models.CommandeProduit;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "commandes")
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Commande_ID")
    private Long id;


    private String codeCommande;

    @JsonProperty("quantite")
    private Float quantite;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateCommande;

    private Float price;

    private Float totalPrice;


    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<CommandeProduit> commandeProduits;



    public Commande() {
    }

    public Commande(String codeCommande, Float quantite, Float price, Date dateCommande, Float totalPrice, List<CommandeProduit> commandeProduits) {
        this.codeCommande = codeCommande;
        this.quantite = quantite;
        this.price = price;
        this.dateCommande = dateCommande;
        this.totalPrice = totalPrice;
        this.commandeProduits = commandeProduits;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeCommande() {
        return codeCommande;
    }

    public void setCodeCommande(String codeCommande) {
        this.codeCommande = codeCommande;
    }

    public Float getQuantite() {
        return quantite;
    }
    @JsonProperty("quantite")
    public void setQuantite(Float quantite) {
        this.quantite = quantite;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CommandeProduit> getCommandeProduits() {
        return commandeProduits;
    }

    public void setCommandeProduits(List<CommandeProduit> commandeProduits) {
        this.commandeProduits = commandeProduits;
    }


    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", codeCommande='" + codeCommande + '\'' +
                ", quantite=" + quantite +
                ", dateCommande=" + dateCommande +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", commandeProduits=" + commandeProduits +

                '}';
    }
}