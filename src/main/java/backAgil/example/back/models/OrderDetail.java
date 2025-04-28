package backAgil.example.back.models;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String orderFullName;
    private String orderFullOrder;
    private String orderContactNumber;

    private String orderStatuts;
    private float orderAmount;
    @OneToOne
    private Produit produit;
    /*@OneToOne
    private User user;*/



    public OrderDetail(String orderFullName, String orderFullOrder, String orderContactNumber, float orderAmount, String orderStatuts, Produit produit) {
        this.orderFullName = orderFullName;
        this.orderFullOrder = orderFullOrder;
        this.orderContactNumber = orderContactNumber;
        this.orderAmount = orderAmount;
        this.orderStatuts = orderStatuts;
        this.produit = produit;

    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderFullName() {
        return orderFullName;
    }

    public void setOrderFullName(String orderFullName) {
        this.orderFullName = orderFullName;
    }

    public String getOrderFullOrder() {
        return orderFullOrder;
    }

    public void setOrderFullOrder(String orderFullOrder) {
        this.orderFullOrder = orderFullOrder;
    }

    public String getOrderStatuts() {
        return orderStatuts;
    }

    public void setOrderStatuts(String orderStatuts) {
        this.orderStatuts = orderStatuts;
    }

    public String getOrderContactNumber() {
        return orderContactNumber;
    }

    public void setOrderContactNumber(String orderContactNumber) {
        this.orderContactNumber = orderContactNumber;
    }

    public float getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(float orderAmount) {
        this.orderAmount = orderAmount;
    }


}

