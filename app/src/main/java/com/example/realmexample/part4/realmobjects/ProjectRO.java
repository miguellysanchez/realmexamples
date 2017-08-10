package com.example.realmexample.part4.realmobjects;

import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.LinkingObjects;
import io.realm.annotations.PrimaryKey;

/**
 * Created by miguellysanchez on 8/9/17.
 */

public class ProjectRO extends RealmObject {
    @PrimaryKey
    private long id;
    private String name;
    @LinkingObjects("projects")
    private final RealmResults<EmployeeRO> contributors = null;

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

    public RealmResults<EmployeeRO> getContributors() {

        return contributors;
    }
}
