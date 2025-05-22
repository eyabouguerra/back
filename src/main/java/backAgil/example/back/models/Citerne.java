package backAgil.example.back.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Entity
@Table(name = "citernes")
public class Citerne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Citerne_ID")
    private Long id;
    private String reference;

    private double capacite;
    @NotNull
    private Integer nombreCompartiments;

    @JsonManagedReference
    @OneToMany(mappedBy = "citerne", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Compartiment> compartiments;

   /* @OneToMany(mappedBy = "camion")
    @JsonBackReference // Evite la sérialisation de la liste de livraisons pour empêcher la récursion
    private List<Livraison> livraisons;*/


    @OneToOne(mappedBy = "citerne")
    private Camion camion;



    public Citerne() {}

    public Citerne(String reference, Integer nombreCompartiments, double capacite, List<Compartiment> compartiments, Camion camion) {
        this.reference = reference;
        this.nombreCompartiments = nombreCompartiments;
        this.capacite = capacite;
        this.compartiments = compartiments;
        this.camion = camion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public double getCapacite() {
        return capacite;
    }

    public void setCapacite(double capacite) {
        this.capacite = capacite;
    }

    public List<Compartiment> getCompartiments() {
        return compartiments;
    }

    public void setCompartiments(List<Compartiment> compartiments) {
        this.compartiments = compartiments;
    }


    @JsonIgnore
    public Camion getCamion() {
        return camion;
    }

    public void setCamion(Camion camion) {
        this.camion = camion;
    }

    public Integer getNombreCompartiments() {
        return nombreCompartiments;
    }

    public void setNombreCompartiments(Integer nombreCompartiments) {
        this.nombreCompartiments = nombreCompartiments;
    }

    @Override
    public String toString() {
        return "Citerne{" +
                "id=" + id +
                ", reference='" + reference + '\'' +
                ", capacite=" + capacite +
                ", nombreCompartiments=" + nombreCompartiments +
                ", compartiments=" + compartiments +
                ", camion=" + camion +
                '}';
    }
}