package backAgil.example.back.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String orderFullName;
    private String orderFullOrder;
    private String orderContactNumber;
    private String orderStatuts;
    private float orderAmount;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Produit> produit;

    public Order() {}

    public Order(Long orderId, String orderFullName, String orderFullOrder, String orderContactNumber,
                 String orderStatuts, float orderAmount, Client client, List produit) {
        this.orderId = orderId;
        this.orderFullName = orderFullName;
        this.orderFullOrder = orderFullOrder;
        this.orderContactNumber = orderContactNumber;
        this.orderStatuts = orderStatuts;
        this.orderAmount = orderAmount;
        this.client = client;
        this.produit = produit;
    }

    // Getters and Setters
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public String getOrderFullName() { return orderFullName; }
    public void setOrderFullName(String orderFullName) { this.orderFullName = orderFullName; }
    public String getOrderFullOrder() { return orderFullOrder; }
    public void setOrderFullOrder(String orderFullOrder) { this.orderFullOrder = orderFullOrder; }
    public String getOrderContactNumber() { return orderContactNumber; }
    public void setOrderContactNumber(String orderContactNumber) { this.orderContactNumber = orderContactNumber; }
    public String getOrderStatuts() { return orderStatuts; }
    public void setOrderStatuts(String orderStatuts) { this.orderStatuts = orderStatuts; }
    public float getOrderAmount() { return orderAmount; }
    public void setOrderAmount(float orderAmount) { this.orderAmount = orderAmount; }
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
    public List<Produit> getProduit() { return produit; }
    public void setProduit(List produit) { this.produit = produit; }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderFullName='" + orderFullName + '\'' +
                ", orderFullOrder='" + orderFullOrder + '\'' +
                ", orderContactNumber='" + orderContactNumber + '\'' +
                ", orderStatuts='" + orderStatuts + '\'' +
                ", orderAmount=" + orderAmount +
                ", client=" + client +
                ", produit=" + produit +
                '}';
    }
}