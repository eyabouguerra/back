package backAgil.example.back.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "commande_produits")
public class CommandeProduit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "commande_id")
    @JsonBackReference
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

    private Float quantite;

    public CommandeProduit() {
    }

    public CommandeProduit(Commande commande, Produit produit, Float quantite) {
        this.commande = commande;
        this.produit = produit;
        this.quantite = quantite;
    }

    // getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Float getQuantite() {
        return quantite;
    }

    public void setQuantite(Float quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "CommandeProduit{" +
                "id=" + id +
                ", commande=" + commande +
                ", produit=" + produit +
                ", quantite=" + quantite +
                '}';
    }
}

