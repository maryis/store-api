package com.Jumbo.store.storeapi.repository;

import com.Jumbo.store.storeapi.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store,String> {

}
