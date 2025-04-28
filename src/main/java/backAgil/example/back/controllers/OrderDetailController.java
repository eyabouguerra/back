package backAgil.example.back.controllers;

import backAgil.example.back.models.OrderInput;
import backAgil.example.back.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/order")
public class OrderDetailController {
   @Autowired
    private OrderDetailService orderDetailService;


    //@PreAuthorize("hasRole('User')")
    @PostMapping({"/placeOrder"})
    public void placeOrder(@RequestBody OrderInput orderInput){
        orderDetailService.placeOrder(orderInput);

    }
}
