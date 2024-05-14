package com.roman.app.rest.Controller;

import com.roman.app.rest.Models.Chef;
import com.roman.app.rest.Models.Customer;
import com.roman.app.rest.Models.Dishes;
import com.roman.app.rest.Models.chef_pass;
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
    private ordersRepo orderR;
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

}

