package com.migros.couriertracking.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Stores {

    private static Stores INSTANCE;

    public static List<Store> getInstance(){
        return INSTANCE.getStores();
    }

    public static List<Store> init(List<Store> stores){
        if (INSTANCE == null) {
            INSTANCE = new Stores();
            INSTANCE.setStores(stores);
        }

        return INSTANCE.getStores();
    }

    private Stores(){}

    private List<Store> stores;

}
