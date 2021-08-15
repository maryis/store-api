package com.Jumbo.store.storeapi.controller;

import com.Jumbo.store.storeapi.entity.Location;
import com.Jumbo.store.storeapi.entity.Store;
import com.Jumbo.store.storeapi.service.StoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest( value = StoreController.class
        ,excludeAutoConfiguration = { SecurityAutoConfiguration.class,
                ManagementWebSecurityAutoConfiguration.class},
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.REGEX,pattern = {".*[WebSecurityConfig]*",".[Jwt]*"})
)
@Import(StoreController.class)
public class StoreControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StoreService service;

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    private List<Store> list = new ArrayList<>();

    @Before
    public void setup() {
        Location loc = new Location.Builder()
                .setCity("Eindhoven")
                .setLatitude(32.3)
                .setLongitude(45)
                .build();
        Store store1 = new Store.Builder()
                .setLocation(loc)
                .setAddressName("name")
                .setUuid("id")
                .build();
        list.add(store1);

    }

    @Test
    @DisplayName("test web layer of get all stores")
    public void testGetAll() throws Exception {
        when(service.getAllStores()).thenReturn(list);

        mvc.perform(get("/api/v1/stores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].city",equalTo("Eindhoven")));

    }

    @Test
    @DisplayName("test web layer of update a store")
    public void testUpdate() throws Exception {
        Location loc = new Location.Builder()
                .setCity("Eindhoven")
                .setLatitude(32.3)
                .setLongitude(45)
                .build();
        Store store1 = new Store.Builder()
                .setLocation(loc)
                .setAddressName("name")
                .setUuid("id")
                .build();

        when(service.updateStore(Mockito.anyString(), Mockito.any(Store.class)))
                .thenReturn(Optional.of(store1));

        mvc.perform(put("/api/v1/stores/{id}",store1.getUuid())
                .content(ow.writeValueAsString(store1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city",equalTo(store1.getLocation().getCity())));

    }

}