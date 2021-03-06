package com.example.realmexample.part4.realmobjects;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by miguellysanchez on 8/9/17.
 */

public class TeamRO extends RealmObject {

    @PrimaryKey
    private long id;
    private String name;

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
