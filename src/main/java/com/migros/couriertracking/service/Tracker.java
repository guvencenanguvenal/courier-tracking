package com.migros.couriertracking.service;

public interface Tracker {

    void addObserver(LocationObserver observer);

    void notifyObserver();

}
