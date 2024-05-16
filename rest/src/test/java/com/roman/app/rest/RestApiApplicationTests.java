package com.roman.app.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = RestApiApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
class RestApiApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private RestApiApplication restTemplate;

    @Test
    void getPage_thenReturnWelcome() throws Exception {
        mvc.perform(get("/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Welcome"));
    }

    @Test
    void getPage_thenReturnWelcomeUser() throws Exception {
        mvc.perform(get("/customer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Welcome, customer"));
    }

    @Test
    void getPage_thenReturn404() throws Exception {
        mvc.perform(get("/not/valid/uri"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getPage_thenReturnAccessDenied() throws Exception {
        mvc.perform(get("/access/denied"))
                .andExpect(status().isUnauthorized());
    }






}
