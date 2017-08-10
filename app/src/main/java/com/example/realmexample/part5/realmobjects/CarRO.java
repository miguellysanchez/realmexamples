package com.example.realmexample.part5.realmobjects;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by miguellysanchez on 8/10/17.
 */

public class CarRO extends RealmObject{

    @PrimaryKey
    private long id;
    private String name;
//    private String make;
//    private String manufacturer;
//    private int year;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
