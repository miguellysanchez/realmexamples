package com.example.realmexample;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by miguellysanchez on 8/5/17.
 */

public class RealmExampleApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration defaultConfig = new RealmConfiguration.Builder()
                .name( "realmexample_v" + BuildConfig.DATABASE_VERSION + ".realm")
                .schemaVersion(BuildConfig.DATABASE_VERSION)
                .deleteRealmIfMigrationNeeded() //delete when going to prod unless schema changes really delete the realm
                .build();
        Realm.setDefaultConfiguration(defaultConfig);
    }
}
