package com.Jumbo.store.storeapi.controller;

import com.Jumbo.store.storeapi.entity.Location;
import com.Jumbo.store.storeapi.entity.Store;
import com.Jumbo.store.storeapi.service.StoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StoreControllerIT {

    @Autowired
    MockMvc mvc;

    @Autowired
    StoreService storeService;

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Test
    @DisplayName("integration test for get all stores ")
    public void test1GetAll() throws Exception {

        mvc.perform(get("/api/v1/stores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)));

    }

    @Test
    @DisplayName("integration test for deleting a store ")
    public void test2Delete() throws Exception {

        mvc.perform(delete("/api/v1/stores/{id}","7ewKYx4Xqp0AAAFIHigYwKrH"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.addressName", equalTo("Jumbo 's-Heerenberg Stadsplein")));

        Assert.assertEquals(storeService.getAllStores().size() ,9);

    }

    @Test
    @DisplayName("integration test for adding a store ")
    public void test3Insert() throws Exception {


        Store toBeAdded=new Store.Builder()
                .setAddressName("addName")
                .setUuid("1234Yx4Xqp0AAAFIHigYwKrH")
                .setLocation(new Location.Builder()
                    .setCity("city1")
                    .setLatitude(234)
                    .setLatitude(34)
                    .build())
                .build();

        mvc.perform(post("/api/v1/stores/")
                .content(ow.writeValueAsString(toBeAdded))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.addressName", equalTo("addName")));

        Assert.assertEquals(storeService.getAllStores().size() ,10);

    }

}