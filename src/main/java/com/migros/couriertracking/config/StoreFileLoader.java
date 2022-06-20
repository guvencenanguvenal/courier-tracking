package com.migros.couriertracking.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.migros.couriertracking.model.Store;
import com.migros.couriertracking.model.Stores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Configuration
@DependsOn({"objectMapper"})
public class StoreFileLoader {

    @Autowired
    ObjectMapper objectMapper;

    @PostConstruct
    private void initialize(){

        StringBuilder stringBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader
                (StoreFileLoader.class.getClassLoader().getResourceAsStream("store.json"),
                        Charset.forName(StandardCharsets.UTF_8.name())))) {

            int c = 0;
            while ((c = reader.read()) != -1) {
                stringBuilder.append((char) c);
            }

            Store[] storeArray = objectMapper.readValue(stringBuilder.toString(), Store[].class);
            Stores.init(Arrays.stream(storeArray).toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
