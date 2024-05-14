package com.roman.app.rest.Controller;

import com.roman.app.rest.Models.Chef;
import com.roman.app.rest.Models.Customer;
import com.roman.app.rest.Models.Dishes;
import com.roman.app.rest.Repo.ChefRepo;
import com.roman.app.rest.Repo.customerRepo;
import com.roman.app.rest.Repo.dishesRepo;
import com.roman.app.rest.Repo.ordersRepo;
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
    public String saveChef(@RequestBody Chef chef){
        chefR.save(chef);
        return "chef registered";
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

