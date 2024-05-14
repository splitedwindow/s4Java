package com.roman.app.rest.Controller;

import com.roman.app.rest.Models.Chef;
import com.roman.app.rest.Models.Customer;
import com.roman.app.rest.Models.Dishes;
import com.roman.app.rest.Models.Orders;
import com.roman.app.rest.Repo.ChefRepo;
import com.roman.app.rest.Repo.customerRepo;
import com.roman.app.rest.Repo.dishesRepo;
import com.roman.app.rest.Repo.ordersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiControllers {

    @Autowired
    private ChefRepo chefRepo;
    @Autowired
    private customerRepo cRepo;
    @Autowired
    private dishesRepo dRepo;
    @Autowired
    private ordersRepo oRepo;

    @GetMapping(value = "/")
    public String getPage() {
        return "Welcome";
    }

    @GetMapping(value="/chefs")
    public List<Chef> getChefs() {
        return chefRepo.findAll();
    }


    @GetMapping(value="/dishes")
    public List<Dishes> getDishes() {
        return dRepo.findAll();
    }

    @PostMapping(value = "/dish")
    public String saveDish(@RequestBody Dishes dish){
        dRepo.save(dish);
        return "dish created";
    }

    @PostMapping(value="/chefregister")
    public String saveChef(@RequestBody Chef chef){
        chefRepo.save(chef);
        return "chef registered";
    }

    @GetMapping(value="/customers")
    public List<Customer> getCustomers() {
        return cRepo.findAll();
    }

    @PostMapping(value="/register")
    public String saveUser(@RequestBody Customer Customers){
        cRepo.save(Customers);
        return "user registered";
    }
}
