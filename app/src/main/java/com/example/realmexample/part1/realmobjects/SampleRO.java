package com.example.realmexample.part1.realmobjects;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by miguellysanchez on 8/7/17.
 */

public class SampleRO extends RealmObject {

    @PrimaryKey
    private long id;
    private long myLong = 77L;
    private double myDouble = 7.0;
    private int myInt = 777;
    private String myString = "sevens";
    public String noGetterSetter;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMyLong() {
        return myLong;
    }

    public void setMyLong(long myLong) {
        this.myLong = myLong;
    }

    public double getMyDouble() {
        return myDouble;
    }

    public void setMyDouble(double myDouble) {
        this.myDouble = myDouble;
    }

    public int getMyInt() {
        return myInt;
    }

    public void setMyInt(int myInt) {
        this.myInt = myInt;
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }

    public String getInfo() {
        return "String:" + myString + "\n" +
                "Long: " + myLong + "\n" +
                "Int:" + myInt + "\n" +
                "Double: " + myDouble;
    }
}
