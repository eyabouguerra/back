package backAgil.example.back.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "compartiments")
public class Compartiment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Compartiment_ID")
    private Long id;

    @Column(name = "Capacite_Max", nullable = false)
    private double capaciteMax;

    @Column(name = "Reference", nullable = false, unique = true)
    private String reference;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "citerne_id", nullable = false)
    private Citerne citerne;

    @ManyToMany
    @JoinTable(
            name = "compartiment_type_produit",
            joinColumns = @JoinColumn(name = "compartiment_id"),
            inverseJoinColumns = @JoinColumn(name = "type_produit_id")
    )
    private Set<TypeProduit> typeProduits = new HashSet<>();

    public Compartiment() {}

    public Compartiment(double capaciteMax, String reference, Citerne citerne, Set<TypeProduit> typeProduits) {
        this.capaciteMax = capaciteMax;
        this.reference = reference;
        this.citerne = citerne;
        this.typeProduits = typeProduits;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getCapaciteMax() {
        return capaciteMax;
    }

    public void setCapaciteMax(double capaciteMax) {
        this.capaciteMax = capaciteMax;
    }

    public Citerne getCiterne() {
        return citerne;
    }

    public void setCiterne(Citerne citerne) {
        this.citerne = citerne;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Set<TypeProduit> getTypeProduits() {
        return typeProduits;
    }

    public void setTypeProduits(Set<TypeProduit> typeProduits) {
        this.typeProduits = typeProduits;
    }

    @Override
    public String toString() {
        return "Compartiment{" +
                "id=" + id +
                ", capaciteMax=" + capaciteMax +
                ", reference='" + reference + '\'' +
                ", citerne=" + citerne +
                ", typeProduits=" + typeProduits +
                '}';
    }
}
