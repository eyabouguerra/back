package backAgil.example.back.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "camions")
public class Camion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Camion_ID")
    private Long id;
    private String marque;
    private String modele;
    private String immatriculation;
    private int kilometrage;
    private String statut;


    @OneToMany(mappedBy = "camion")
    @JsonBackReference // Evite la sérialisation de la liste de livraisons pour empêcher la récursion
    private List<Livraison> livraisons;


    @OneToOne
    @JoinColumn(name = "citerne_id", referencedColumnName = "Citerne_ID")
    private Citerne citerne;


    public Camion() {

    }

    public Camion(Long id, String marque, String modele, String immatriculation, int kilometrage, List<Livraison> livraisons, String statut, Citerne citerne) {
        this.id = id;
        this.marque = marque;
        this.modele = modele;
        this.immatriculation = immatriculation;
        this.kilometrage = kilometrage;
        this.livraisons = livraisons;
        this.statut = statut;
        this.citerne = citerne;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public int getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(int kilometrage) {
        this.kilometrage = kilometrage;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public List<Livraison> getLivraisons() {
        return livraisons;
    }

    public void setLivraisons(List<Livraison> livraisons) {
        this.livraisons = livraisons;
    }

    public Citerne getCiterne() {
        return citerne;
    }

    public void setCiterne(Citerne citerne) {
        this.citerne = citerne;
    }

    @Override
    public String toString() {
        return "Camion{" +
                "id=" + id +
                ", marque='" + marque + '\'' +
                ", modele='" + modele + '\'' +
                ", immatriculation='" + immatriculation + '\'' +
                ", kilometrage=" + kilometrage +
                ", statut='" + statut + '\'' +
                ", livraisons=" + livraisons +
                ", citerne=" + citerne +
                '}';
    }
}