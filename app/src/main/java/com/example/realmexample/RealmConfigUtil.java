package com.example.realmexample;

import com.example.realmexample.part1.Part1RealmModule;
import com.example.realmexample.part2.Part2RealmModule;
import com.example.realmexample.part3.Part3RealmModule;
import com.example.realmexample.part4.Part4RealmModule;
import com.example.realmexample.part5.Part5RealmModule;
import com.example.realmexample.part5.SampleMigration;

import io.realm.RealmConfiguration;

/**
 * Created by miguellysanchez on 8/7/17.
 */

public class RealmConfigUtil {

    private static final String BASE_CONFIG_NAME = "realmexample_";
    private static final String REALM_FILE_EXTENSION = ".realm";

    public static RealmConfiguration getPart1Config() {
        return new RealmConfiguration.Builder()
                .name(BASE_CONFIG_NAME + "part1" + REALM_FILE_EXTENSION)
                .modules(new Part1RealmModule())
                .schemaVersion(BuildConfig.DATABASE_VERSION)
                .deleteRealmIfMigrationNeeded()
                .build();
    }

    public static RealmConfiguration getPart2Config() {
        return new RealmConfiguration.Builder()
                .name(BASE_CONFIG_NAME + "part2" + REALM_FILE_EXTENSION)
                .modules(new Part2RealmModule())
                .schemaVersion(BuildConfig.DATABASE_VERSION)
                .deleteRealmIfMigrationNeeded()
                .build();
    }

    public static RealmConfiguration getPart3Config() {
        return new RealmConfiguration.Builder()
                .name(BASE_CONFIG_NAME + "part3" + REALM_FILE_EXTENSION)
                .modules(new Part3RealmModule())
                .schemaVersion(BuildConfig.DATABASE_VERSION)
                .deleteRealmIfMigrationNeeded()
                .build();
    }

    public static RealmConfiguration getPart4Config() {
        return new RealmConfiguration.Builder()
                .name(BASE_CONFIG_NAME + "part4" + REALM_FILE_EXTENSION)
                .modules(new Part4RealmModule())
                .schemaVersion(BuildConfig.DATABASE_VERSION)
                .deleteRealmIfMigrationNeeded()
                .build();
    }

    public static RealmConfiguration getPart5Config(long dbVersion, boolean inMemory) {
        RealmConfiguration.Builder builder = new RealmConfiguration.Builder()
                .modules(new Part5RealmModule())
                .schemaVersion(dbVersion);
//                .migration(new SampleMigration());

        if (inMemory) {
            builder = builder.inMemory().name(BASE_CONFIG_NAME + "part5_memory" + REALM_FILE_EXTENSION);
        } else {
            builder = builder.name(BASE_CONFIG_NAME + "part5" + REALM_FILE_EXTENSION);
        }

        return builder.build();

    }
}
