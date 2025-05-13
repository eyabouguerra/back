package backAgil.example.back.servicesImpl;

import backAgil.example.back.models.OrderDetail;
import backAgil.example.back.models.OrderInput;
import backAgil.example.back.models.OrderProductQuantity;
import backAgil.example.back.models.Produit;
import backAgil.example.back.repositories.CartRepository;
import backAgil.example.back.repositories.OrderDetailRepository;
import backAgil.example.back.repositories.ProduitRepository;
import backAgil.example.back.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

   private static final String ORDER_PLACED = "Placed";

    @Autowired
    private OrderDetailRepository oRepo;

    @Autowired
    private ProduitRepository pRepo;
/*

    @Autowired
    private UserRepository uRepo;
*/

    public void placeOrder(OrderInput orderInput) {
        List<OrderProductQuantity> productQuantityList = orderInput.getOrderProductQuantityList();

        for (OrderProductQuantity o : productQuantityList) {
            Produit product = pRepo.findById(o.getId()).get();
            /*
            string currentUser = JwtRequestFilter.CURRENT_USER;
            User user = uRepo.findById(currentUser).get();
            */
            Double total = Double.valueOf(product.getPrix() * o.getQuantity().floatValue());
            OrderDetail orderDetail = new OrderDetail(
                    orderInput.getFullName(),
                    orderInput.getFullAddress(),
                    orderInput.getContactNumber(),
                    product.getPrix() *o.getQuantity(),
                    ORDER_PLACED,
                    product
                    //user
            );

            oRepo.save(orderDetail);
        }
    }

}
