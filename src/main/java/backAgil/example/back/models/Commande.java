package backAgil.example.back.models;

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

    @ManyToOne(fetch = FetchType.EAGER) // Changé de LAZY à EAGER pour charger le client
    @JoinColumn(name = "client_id")
    // Suppression de @JsonBackReference pour permettre la sérialisation du client
    private Client client;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<CommandeProduit> commandeProduits;

    // Constructors
    public Commande() {
    }

    public Commande(String codeCommande, Float quantite, Float price, Date dateCommande, Float totalPrice, Client client, List<CommandeProduit> commandeProduits) {
        this.codeCommande = codeCommande;
        this.quantite = quantite;
        this.price = price;
        this.dateCommande = dateCommande;
        this.totalPrice = totalPrice;
        this.client = client;
        this.commandeProduits = commandeProduits;
    }

    // Getters and Setters
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
                ", client=" + (client != null ? client.getFullName() : "null") +
                ", commandeProduits=" + commandeProduits +
                '}';
    }
}