package backAgil.example.back.models;

public class OrderProductQuantity {
    private Long id;
    private int quantity;

    public OrderProductQuantity() {
    }

    public OrderProductQuantity(Long id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}