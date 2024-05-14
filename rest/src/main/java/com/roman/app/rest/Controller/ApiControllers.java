package com.roman.app.rest.Controller;

import com.roman.app.rest.Models.*;
import com.roman.app.rest.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ApiControllers {

    @Autowired
    private ChefRepo chefR;
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

    // get all the chefs
    @GetMapping(value="/chefs")
    public List<Chef> getChefs() {
        return chefR.findAll();
    }


    // get all the dishes
    @GetMapping(value="/dishes")
    public List<Dishes> getDishes() {
        return dishesR.findAll();
    }

//    crete new dish
    @PostMapping(value = "/dish")
    public String saveDish(@RequestBody Dishes dish, @RequestParam String email,@RequestParam String password){
        Optional<Chef> chefOpt = chefR.findByEmailAndPassword(email, password);
        if (chefOpt.isEmpty()) {
            return "Chef not found";
        }
        dishesR.save(dish);
        return "Dish created";
    }

    // create new chef
    @PostMapping(value="/chefregister")
    public String saveChef(@RequestBody Chef chef, @RequestParam String password){
        Optional<chef_pass> chef_passOptional = chef_passR.findByPassword(password);
        if(chef_passOptional.isEmpty())
        {
            return "Chef passcode for registration is required";
        } else {
            chefR.save(chef);
            return "chef registered";
        }
    }

    @GetMapping(value="/orders")
    public List<Orders> getOrders() {
        return ordersR.findAll();
    }

    @DeleteMapping("/order/delete/{orderId}")
    public String DeleteOrder(@PathVariable Long orderId, @RequestParam String password, @RequestParam String email) {
        Optional<Chef> chefOpt = chefR.findByEmailAndPassword(email, password);
        Optional<Customer> customerOpt = customerR.findByEmailAndPassword(email, password);
        Optional<Orders> orderOpt = ordersR.findById(orderId);
        if(chefOpt.isPresent())
        {
            if(orderOpt.isPresent())
            {
                ordersR.deleteById(orderId);
                return "Order completed";
            } else {
                return "Order not found";
            }
        } else if (customerOpt.isPresent())
        {

            // chef if it's order of current user
            if(orderOpt.isPresent())
            {
                Customer customer = customerOpt.get();
                long userId = customer.getUserId();
                Orders order = orderOpt.get();
                long orderUserId = order.getUserId();

                if(userId != orderUserId)
                {
                    return "It's order of different user.";
                }

                ordersR.deleteById(orderId);
                return "Order canceled";
            } else {
                return "Order not found";
            }
        } else {
            return "Wrong email or password";
        }
    }

    // get list of customers
    @GetMapping(value="/customers")
    public List<Customer> getCustomers() {
        return customerR.findAll();
    }

    // create new customer
    @PostMapping(value="/register")
    public String saveUser(@RequestBody Customer Customers){
        customerR.save(Customers);
        return "user registered";
    }

    // create new order with customer's login data
    @PostMapping(value="/order")
    public String saveOrder(@RequestBody Orders order, @RequestParam String email, @RequestParam String password){
        Optional<Dishes> dishOpt = dishesR.findById(order.getDishId());
        Optional<Customer> customerOpt = customerR.findByEmailAndPassword(email, password);
        if(customerOpt.isEmpty()){
            return "Wrong email or password";
        } else if (dishOpt.isEmpty()) {
            return "Dish with chosen id doesn't exist";
        }

        Customer customer = customerOpt.get();
        long userId = customer.getUserId();

        order.setUserId(userId);
        ordersR.save(order);
        return "Order created successfully";
    }

}

