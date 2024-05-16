package com.roman.app.rest;

import com.roman.app.rest.Controller.ApiControllers;
import com.roman.app.rest.Models.dishes;
import com.roman.app.rest.Models.user;
import com.roman.app.rest.Repo.dishesRepo;
import com.roman.app.rest.Repo.userRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private userRepo userRepo;

    @MockBean
    private dishesRepo dishesR;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetUsers() throws Exception {
        List<user> users = Arrays.asList(
                new user("john@example.com", "password", "CUSTOMER"),
                new user("jane@example.com", "password", "CHEF")
        );

        when(userRepo.findAll()).thenReturn(users);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(users)));
    }


    @Test
    void testGetDishes() throws Exception {
        List<dishes> Dishes = Arrays.asList(
                new dishes(3, "New Dish"),
                new dishes(4, "Second New Dish")
        );

        when(dishesR.findAll()).thenReturn(Dishes);

        mockMvc.perform(get("/dishes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(Dishes)));
    }

    @Test
    @WithMockUser(roles = "CHEF")
    void testSaveDish_whenChef() throws Exception {
        // Prepare test data
        user chefUser = new user("chef@example.com", "password", "CHEF");
        dishes dish = new dishes(5, "Dish name");

        // Mock the repository behavior
        when(userRepo.findByEmail("chef@example.com")).thenReturn(Optional.of(chefUser));

        // Perform the POST request and assert the response
        mockMvc.perform(post("/chef/dish")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dish)))
                .andExpect(status().isOk())
                .andExpect(content().string("Dish created"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void testSaveDish_whenNotChef() throws Exception {
        // Prepare test data
        user normalUser = new user("user@example.com", "password", "USER");
        dishes dish = new dishes("Test Dish", "Description", 10.99);

        // Mock the repository behavior
        when(userRepo.findByEmail("user@example.com")).thenReturn(Optional.of(normalUser));

        // Perform the POST request and assert the response
        mockMvc.perform(post("/chef/dish")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dish)))
                .andExpect(status().isOk())
                .andExpect(content().string("only chef can create dishes"));
    }
}