package com.Jumbo.store.storeapi.controller;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class LoginControllerIT {

    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("integration test for authentication with correct credentials")
    public void testAuth() throws Exception {

        String userBody="{ \"username\":\"admin\",\"password\":\"password\"}";

        mvc.perform(post("/security/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userBody))
                .andExpect(status().isOk())
                .andExpect(content().string(hasLength(174)));
    }

    @Test
    @DisplayName("integration test for authentication  with wrong credentials")
    public void testAuthWithWrongPass() throws Exception {

        String userBody="{ \"username\":\"admin\",\"password\":\"wrong\"}";

        mvc.perform(post("/security/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userBody))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("integration test for authentication  with wrong credentials")
    public void testAuthWithWrongUser() throws Exception {

        String userBody="{ \"username\":\"wrong\",\"password\":\"wrong\"}";

        mvc.perform(post("/security/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userBody))
                .andExpect(status().isUnauthorized());
    }
}