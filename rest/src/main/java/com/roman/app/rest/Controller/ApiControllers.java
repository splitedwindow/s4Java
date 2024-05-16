package com.roman.app.rest.Controller;

import com.roman.app.rest.Models.dishes;
import com.roman.app.rest.Models.orders;
import com.roman.app.rest.Models.user;
import com.roman.app.rest.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ApiControllers {

    @Autowired
    private userRepo userR;
    @Autowired
    private dishesRepo dishesR;
    @Autowired
    private ordersRepo ordersR;

    @GetMapping(value = "/")
    public String getPage() {
        return "Welcome";
    }

    @GetMapping(value = "/customer")
    public String getCustomerPage() {
        return "Welcome, customer";
    }

    @GetMapping(value = "/users")
    public List<user> getUsers() {
        return userR.findAll();
    }

    @PostMapping(value = "/register")
    public String saveUser(@RequestBody user User, @RequestParam String role) {
        if(role.equals("CUSTOMER") || role.equals("CHEF"))
        {
            if(User.getEmail().isBlank())
            {
                return "email hasn't been entered";
            } else if (User.getPassword().isBlank()) {
                return "password hasn't been entered";
            }

            User.setRole(role);
            PasswordEncoder encoder = new BCryptPasswordEncoder();

            String password = User.getPassword();
            String encodedPassword = encoder.encode(password);
            User.setPassword(encodedPassword);

            userR.save(User);
            return "user saved";
        } else {
            return "role not set";
        }
    }

    @PostMapping(value = "/customer/order")
    public String saveOrder(@RequestBody orders order, @RequestParam String email) {
        Optional<dishes> dishOpt = dishesR.findById(order.getDishId());
        Optional<user> userOpt = userR.findByEmail(email);

        if (userOpt.isEmpty()) {
            return "Unauthorized";
        } else if (dishOpt.isEmpty()) {
            return "Dish with chosen id doesn't exist";
        }

        user newUser = userOpt.get();
        long userId = newUser.getUserId();

        order.setUserId(userId);
        ordersR.save(order);
        return "Order created successfully";
    }

    @GetMapping(value = "/dishes")
    public List<dishes> getDishes() {
        return dishesR.findAll();
    }

    @PostMapping(value = "/chef/dish")
    public String saveDish(@RequestBody dishes dish, @AuthenticationPrincipal user User) {
        Optional<user> userOpt = userR.findByEmail(User.getEmail());

        if(userOpt.isEmpty())
        {
            return "chef not found";
        }

        user currentUser = userOpt.get();
        String currentRole = currentUser.getRole();
        if(!currentRole.equals("CHEF"))
        {
            return "only chef can create dishes";
        }
        dishesR.save(dish);
        return "Dish created";
    }


    @DeleteMapping("/order/delete/{orderId}")
    public String DeleteOrder(@PathVariable Long orderId, @RequestBody user User) {
        Optional<user> userOpt = userR.findByEmail(User.getEmail());
        Optional<orders> orderOpt = ordersR.findById(orderId);
        if (userOpt.isPresent()) {
            if (orderOpt.isPresent()) {
                user currentUser = userOpt.get();
                String userRole = currentUser.getRole();

                if(userRole.equals("CHEF"))
                {
                    ordersR.deleteById(orderId);
                    return "Order completed";
                } else if (userRole.equals("CUSTOMER")){
                    ordersR.deleteById(orderId);
                    return "Order canceled";
                } else {
                    return "role not found";
                }
            } else {
                return "Order not found";
            }
        } else {
            return "user not found";
        }
    }
}
