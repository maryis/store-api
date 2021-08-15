package com.Jumbo.store.storeapi.service;

import com.Jumbo.store.storeapi.dto.Position;
import com.Jumbo.store.storeapi.entity.Location;
import com.Jumbo.store.storeapi.entity.Store;
import com.Jumbo.store.storeapi.repository.StoreRepository;
import com.Jumbo.store.storeapi.service.impl.DefaultStoreService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test")
public class DefaultStoreServiceIT {

    @Autowired
    static StoreRepository storeRepository;

    @Autowired
    private DefaultStoreService storeService;


    @Test
    @DisplayName("integration test for GetTopStoresByDistance")
    public void testGetTopStoresByDistance(){

        Position position=new Position(12,34);

        List<Store> stores=storeService.GetTopStoresByDistance(position);
        Assert.assertEquals(5,stores.size());

    }



}
