package com.roman.app.rest.Controller;

import com.roman.app.rest.Models.Chef;
import com.roman.app.rest.Repo.ChefRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiControllers {

    @Autowired
    private ChefRepo chefRepo;

    @GetMapping(value = "/")
    public String getPage() {
        return "Welcome";
    }

    @GetMapping(value="/chefs")
    public List<Chef> getChefs() {
        return chefRepo.findAll();
    }
}
