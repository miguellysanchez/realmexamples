package com.example.realmexample.part4;

import com.example.realmexample.part4.realmobjects.EmployeeRO;
import com.example.realmexample.part4.realmobjects.ProjectRO;
import com.example.realmexample.part4.realmobjects.TeamRO;

import io.realm.annotations.RealmModule;

/**
 * Created by miguellysanchez on 8/9/17.
 */

@RealmModule(classes = {EmployeeRO.class, ProjectRO.class, TeamRO.class})
public class Part4RealmModule {
}
