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
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class DefaultStoreServiceTest {

    @Mock
    static StoreRepository storeRepository;

    @InjectMocks
    private DefaultStoreService storeService;

    List<Store> list = new ArrayList<>();

    @Before
    public void setup() {
        Location loc = new Location.Builder()
                .setCity("c1")
                .setLatitude(32.3)
                .setLongitude(45)
                .build();
        Store store1 = new Store.Builder()
                .setLocation(loc)
                .setAddressName("name")
                .setUuid("12345")
                .build();
        list.add(store1);

    }

    @Test
    @DisplayName("test service layer of GetTopStoresByDistance")
    public void testGetTopStoresByDistance(){
        when(storeRepository.findAll()).thenReturn(list);

        Position position=new Position(12,34);

        List<Store> stores=storeService.GetTopStoresByDistance(position);
        Assert.assertEquals(1,stores.size());
        Assert.assertEquals("12345",stores.get(0).getUuid());
    }



}
