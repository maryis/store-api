package com.Jumbo.store.storeapi.service.impl;

import com.Jumbo.store.storeapi.dto.Position;
import com.Jumbo.store.storeapi.entity.Store;
import com.Jumbo.store.storeapi.repository.StoreRepository;
import com.Jumbo.store.storeapi.service.StoreService;
import com.Jumbo.store.storeapi.utility.HaversineAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultStoreService implements StoreService {
    Logger logger = LoggerFactory.getLogger(DefaultStoreService.class);


    private StoreRepository storeRepository;

    public DefaultStoreService() {
    }

    @Autowired
    public DefaultStoreService(StoreRepository repository) {
        this.storeRepository=repository;
    }

    @Override
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public Optional<Store> getStoreById(String uuid) {
        return storeRepository.findById(uuid);
    }

    @Override
    public Store createStore(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public Optional<Store> updateStore(String uuid, Store store) {
        if(storeRepository.existsById(uuid))
            return Optional.of(storeRepository.save(store));

        return Optional.empty();

    }

    @Override
    public Optional<Store> deleteStoreById(String uuid) {
        Optional<Store> toBeDeleted=storeRepository.findById(uuid);

        if(toBeDeleted.isPresent())
            storeRepository.delete(toBeDeleted.get());

        return toBeDeleted;
    }

    @Override
    public List<Store> createStores(List<Store> stores) {
        return storeRepository.saveAll(stores);
    }

    @Override
    public List<Store> GetTopStoresByDistance(Position position) {

        logger.info("fetching top 5 stores close to {}",position);
        Comparator<Store> distanceToPositionCompare=(s1,s2) -> {
            double distance1= HaversineAlgorithm.distanceInKm(s1.getLocation().getLatitude(),
                    s1.getLocation().getLongitude(),
                    position.getLatitude(),
                    position.getLongitude());
            double distance2= HaversineAlgorithm.distanceInKm(s2.getLocation().getLatitude(),
                    s2.getLocation().getLongitude(),
                    position.getLatitude(),
                    position.getLongitude());
            return Double.compare(distance1,distance2);
        };

        List<Store> stores=storeRepository.findAll()
                .stream()
                .sorted(distanceToPositionCompare)
                .limit(5)
                .collect(Collectors.toList());

        return stores;
    }
}
