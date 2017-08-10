package com.example.realmexample.part4.realmobjects;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by miguellysanchez on 8/9/17.
 */

public class EmployeeRO extends RealmObject {
    @PrimaryKey
    private long id;
    private String name;
    //One to one relationship
    private TeamRO team;
    //One to many relationship
    private RealmList<EmployeeRO> teammates;
    private RealmList<ProjectRO> projects;

    public EmployeeRO(){}

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

    public TeamRO getTeam() {
        return team;
    }

    public void setTeam(TeamRO team) {
        this.team = team;
    }

    public RealmList<EmployeeRO> getTeammates() {
        return teammates;
    }

    public void setTeammates(RealmList<EmployeeRO> teammates) {
        this.teammates = teammates;
    }

    public RealmList<ProjectRO> getProjects() {
        return projects;
    }

    public void setProjects(RealmList<ProjectRO> projects) {
        this.projects = projects;
    }
}
