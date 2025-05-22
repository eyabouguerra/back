package backAgil.example.back.services;

import backAgil.example.back.models.OrderInput;
import backAgil.example.back.models.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDetailService {
    Order placeOrder(OrderInput orderInput);

    List<Order> getAllOrdersWithProducts();

    Optional<Order> getOrderWithProducts(Long orderId);
}