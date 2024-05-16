package com.roman.app.rest.Controller;

import com.roman.app.rest.Models.*;
import com.roman.app.rest.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasRole;

@RestController
public class ApiControllers {

    @Autowired
    private chefRepo chefR;
    @Autowired
    private customerRepo customerR;
    @Autowired
    private dishesRepo dishesR;
    @Autowired
    private ordersRepo ordersR;
    @Autowired
    private chef_passRepo chef_passR;

    @GetMapping(value = "/")
    public String getPage() {
        return "Welcome";
    }

    @GetMapping(value = "/chefs")
    public List<chef> getChefs() {
        return chefR.findAll();
    }

    @GetMapping(value = "/dishes")
    public List<dishes> getDishes() {
        return dishesR.findAll();
    }

    @PostMapping(value = "/dish")
    public String saveDish(@RequestBody dishes dish, @AuthenticationPrincipal User user) {
        Optional<chef> chefOpt = chefR.findByEmail(user.getUsername());
        if (chefOpt.isEmpty()) {
            return "Chef not found";
        }
        dishesR.save(dish);
        return "Dish created";
    }

    @PostMapping(value = "/chefregister")
    public String saveChef(@AuthenticationPrincipal chef chef, @RequestParam String password) {
        Optional<chef_pass> chef_passOptional = chef_passR.findByPassword(password);
        if (chef_passOptional.isEmpty()) {
            return "Chef passcode for registration is required";
        } else {
            chefR.save(chef);
            return "chef registered";
        }
    }

    @GetMapping(value = "/orders")
    public List<orders> getOrders() {
        return ordersR.findAll();
    }

//    @DeleteMapping("/order/delete/{orderId}")
//    public String DeleteOrder(@PathVariable Long orderId, @AuthenticationPrincipal User user) {
//        Optional<chef> chefOpt = chefR.findByEmail(user.getUsername());
//        Optional<customer> customerOpt = customerR.findByEmail(user.getUsername());
//        Optional<orders> orderOpt = ordersR.findById(orderId);
//        if (chefOpt.isPresent()) {
//            if (orderOpt.isPresent()) {
//                ordersR.deleteById(orderId);
//                return "Order completed";
//            } else {
//                return "Order not found";
//            }
//        } else if (customerOpt.isPresent()) {
//            if (orderOpt.isPresent()) {
//                customer customer = customerOpt.get();
//                long userId = customer.getUserId();
//                orders order = orderOpt.get();
//                long orderUserId = order.getUserId();
//
//                if (userId != orderUserId) {
//                    return "It's order of different user.";
//                }
//
//                ordersR.deleteById(orderId);
//                return "Order canceled";
//            } else {
//                return "Order not found";
//            }
//        } else {
//            return "Unauthorized";
//        }
//    }

    @GetMapping(value = "/customers")
    public List<customer> getCustomers() {
        return customerR.findAll();
    }

    @PostMapping(value = "/register")
    public String saveUser(@RequestBody customer Customer) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        String password = Customer.getPassword();
        String encodedPassword = encoder.encode(password);
        Customer.setPassword(encodedPassword);

        customerR.save(Customer);
        return "user registered";
    }

//    @PostMapping(value = "/order")
//    public String saveOrder(@RequestBody orders order, @AuthenticationPrincipal User user) {
//        Optional<dishes> dishOpt = dishesR.findById(order.getDishId());
//        Optional<customer> customerOpt = customerR.findByEmail(user.getUsername());
//        if (customerOpt.isEmpty()) {
//            return "Unauthorized";
//        } else if (dishOpt.isEmpty()) {
//            return "Dish with chosen id doesn't exist";
//        }
//
//        customer customer = customerOpt.get();
//        long userId = customer.getUserId();
//
//        order.setUserId(userId);
//        ordersR.save(order);
//        return "Order created successfully";
//    }

    // new try

}
