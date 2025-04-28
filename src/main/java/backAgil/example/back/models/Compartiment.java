package backAgil.example.back.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "compartiments")
public class Compartiment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Compartiment_ID")
    private Long id;

    @Column(name = "Capacite_Max", nullable = false)
    private double capaciteMax;

    @Column(name = "Reference", nullable = false, unique = true)  // Ajout de la colonne Reference
    private String reference;


    @Enumerated(EnumType.STRING)
    @Column(name = "Statut", nullable = false)
    private Statut statut; // Statut du compartiment (plein, en cours, vide)


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "citerne_id", nullable = false)
    private Citerne citerne;


    @Enumerated(EnumType.STRING)
    private TypeProduit typeProduit;

    public Compartiment() {}
    public enum TypeProduit {
        GAZ, CARBURANT, LUBRIFIANT
    }

    public enum Statut {
        PLEIN,
        EN_COURS,
        VIDE;
    }

    public Compartiment(double capaciteMax, String reference, Statut statut, Citerne citerne, TypeProduit typeProduit) {
        this.capaciteMax = capaciteMax;
        this.reference = reference;
        this.statut = statut;
        this.citerne = citerne;
        this.typeProduit = typeProduit;
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



    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
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

    public TypeProduit getTypeProduit() {
        return typeProduit;
    }

    public void setTypeProduit(TypeProduit typeProduit) {
        this.typeProduit = typeProduit;
    }

    @Override
    public String toString() {
        return "Compartiment{" +
                "id=" + id +
                ", capaciteMax=" + capaciteMax +
                ", reference='" + reference + '\'' +
                ", statut=" + statut +
                ", citerne=" + citerne +
                ", typeProduit=" + typeProduit +
                '}';
    }
}