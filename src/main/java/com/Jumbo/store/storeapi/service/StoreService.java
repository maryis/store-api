package com.Jumbo.store.storeapi.service;

import com.Jumbo.store.storeapi.dto.Position;
import com.Jumbo.store.storeapi.entity.Store;

import java.util.List;
import java.util.Optional;

public interface StoreService {

    List<Store> getAllStores();

    Optional<Store> getStoreById(String uuid);

    Store createStore(Store store);

    Optional<Store> updateStore(String uuid, Store store);

    Optional<Store> deleteStoreById(String uuid);

    public List<Store> createStores(List<Store> stores);

    public List<Store> GetTopStoresByDistance(Position position);


}
