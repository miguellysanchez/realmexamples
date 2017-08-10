package com.example.realmexample.part1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.realmexample.R;
import com.example.realmexample.RealmConfigUtil;
import com.example.realmexample.part1.realmobjects.SampleRO;
import com.example.realmexample.part2.realmobjects.DogRO;

import java.util.Random;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by miguellysanchez on 8/6/17.
 */

public class Part1Activity extends AppCompatActivity {

    private TextView mSampleTextView;
    private Realm realm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part1_simple);
        Realm.setDefaultConfiguration(RealmConfigUtil.getPart1Config());
        mSampleTextView = (TextView) findViewById(R.id.activity_part1_textview_sample);
        realm = Realm.getDefaultInstance();
    }

    public void part1Randomize(View view) {
        final String randomString = UUID.randomUUID().toString();
        final double randomDouble = (new Random()).nextDouble();
        final int randomInt = (new Random()).nextInt();
        final long randomLong = (new Random()).nextLong();
        final SampleRO sampleRO = new SampleRO();
        sampleRO.setId(1);
        sampleRO.setMyString(randomString);
        sampleRO.setMyInt(randomInt);
        sampleRO.setMyDouble(randomDouble);
        sampleRO.setMyLong(randomLong);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(sampleRO);
            }
        });
        SampleRO newSampleRO = realm.where(SampleRO.class).findFirst();
        String info = newSampleRO.getInfo();
        mSampleTextView.setText(info);
        if (realm != null) {
            realm.close();
        }
    }

    public void part1ResetRealm(View view) {
        realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        });
        if (realm != null) {
            realm.close();
        }
    }
}
