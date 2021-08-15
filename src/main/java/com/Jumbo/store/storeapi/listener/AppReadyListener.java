package com.Jumbo.store.storeapi.listener;

import com.Jumbo.store.storeapi.entity.Store;
import com.Jumbo.store.storeapi.exception.DataInitializeException;
import com.Jumbo.store.storeapi.service.StoreService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Component
@Profile(value = {"dev","test"})
public class AppReadyListener implements ApplicationListener<ApplicationReadyEvent> {
    Logger logger = LoggerFactory.getLogger(AppReadyListener.class);

    @Autowired
    private StoreService storeService;

    @Value(value = "${data.initial.path}")
    private String dataPath;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        try {
            loadData();
        } catch (Exception e) {
            logger.error("Error during initializing Database {}",e.getMessage());
            throw new DataInitializeException("Error during initializing Database",e);
        }
    }

    private void loadData() throws IOException, URISyntaxException {
        logger.info("persisting json file to database");
        List<Store> storeList;
        ObjectMapper mapper=new ObjectMapper();
        File file = ResourceUtils.getFile("classpath:"+dataPath);
        JsonNode masterJSON = mapper.readTree(file);
        storeList=mapper.readValue(masterJSON.path("stores").toString(), new TypeReference<List<Store>>() {});
        storeService.createStores(storeList);
        logger.info("finished persisting json file to database");
    }
}
