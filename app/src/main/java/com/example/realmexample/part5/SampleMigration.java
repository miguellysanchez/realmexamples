package com.example.realmexample.part5;

import io.realm.DynamicRealm;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * Created by miguellysanchez on 8/10/17.
 */

public class SampleMigration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();

        if (oldVersion == 0) {
            schema.create("CarRO")

                    .addField("id", long.class, FieldAttribute.PRIMARY_KEY)
                    .addField("name", String.class);
            oldVersion++;
        }

        if (oldVersion == 1) {
            schema.get("CarRO")
                    .addField("make", String.class);
            oldVersion++;
        }

        if (oldVersion == 2) {
            schema.get("CarRO")
                    .addField("color", String.class)
                    .addField("year", int.class)
                    .removeField("make")
                    .addField("manufacturer", String.class);
            oldVersion++;
        }


    }
}
