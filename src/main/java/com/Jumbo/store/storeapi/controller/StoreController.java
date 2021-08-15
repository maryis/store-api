package com.Jumbo.store.storeapi.controller;

import com.Jumbo.store.storeapi.dto.Position;
import com.Jumbo.store.storeapi.entity.Store;
import com.Jumbo.store.storeapi.exception.DataNotFoundException;
import com.Jumbo.store.storeapi.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/stores")
public class StoreController {
    Logger logger = LoggerFactory.getLogger(StoreController.class);

    private StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService=storeService;
    }

    @GetMapping
    public List<Store> getAllStores(){
        logger.info("get all stores");
        return storeService.getAllStores();
    }

    @PostMapping
    public Store createStore(@Valid @RequestBody Store store){
        logger.info("creating {}",store);
        return storeService.createStore(store);
    }

    @GetMapping(value = "/{id}")
    public Store getStore(@PathVariable String id) {
        logger.info("getting store with uuid: {}",id);
        return storeService.getStoreById(id)
                .orElseThrow(()->new DataNotFoundException("Could not find store with ID provided"));
    }

    @PutMapping(value = "/{id}")
    public Store updateStore(@RequestBody Store store) {
        logger.info("update store with uuid: {} , {}", store.getUuid(), store);
        return storeService.updateStore(store.getUuid(),store)
                .orElseThrow(()->new DataNotFoundException("Could not find store with ID provided"));
    }

    @DeleteMapping(value = "/{id}")
    public Store deleteStore(@PathVariable String id) {
        logger.info("delete store with uuid: {} ", id);
        return storeService.deleteStoreById(id)
                .orElseThrow(()->new DataNotFoundException("Could not find store with ID provided"));
    }

    //http://localhost/api/v1/stores/closeTo?longitude=2&latitude=4
    @GetMapping(value = "/closeTo")
    public List<Store> getClosestStores(@Valid Position position) {
        logger.info("getting top 5 stores close to {} ",position);
        return storeService.GetTopStoresByDistance(position);
    }

}
