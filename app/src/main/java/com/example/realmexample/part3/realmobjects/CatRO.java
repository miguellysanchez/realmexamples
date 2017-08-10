package com.example.realmexample.part3.realmobjects;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by miguellysanchez on 8/9/17.
 */

public class CatRO extends RealmObject {

    @PrimaryKey
    private String name;
    @Index
    private String color;
    private int numStripes;


    private long dateAdded;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNumStripes() {
        return numStripes;
    }

    public void setNumStripes(int numStripes) {
        this.numStripes = numStripes;
    }

    public long getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(long dateAdded) {
        this.dateAdded = dateAdded;
    }

}
