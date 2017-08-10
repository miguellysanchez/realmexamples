package com.example.realmexample.part2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.realmexample.R;
import com.example.realmexample.RealmConfigUtil;
import com.example.realmexample.part2.realmobjects.DogRO;

import io.realm.Realm;
import io.realm.RealmResults;


/**
 * Created by miguellysanchez on 8/7/17.
 */

public class Part2Activity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private DogAdapter mDogAdapter;
    private TextView mTextViewDogName;
    private TextView mTextViewDogBreed;
    private TextView mTextViewDogAge;
    private Realm realm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part2_autoupdate);
        Realm.setDefaultConfiguration(RealmConfigUtil.getPart2Config());
        realm = Realm.getDefaultInstance();
        initializeViews();
    }

    private void initializeViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_part2_recyclerview_dogs);
        mTextViewDogName = (TextView) findViewById(R.id.activity_part2_edittext_name);
        mTextViewDogBreed = (TextView) findViewById(R.id.activity_part2_edittext_breed);
        mTextViewDogAge = (TextView) findViewById(R.id.activity_part2_edittext_age);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RealmResults<DogRO> dogResults = realm.where(DogRO.class).findAll();
        mDogAdapter = new DogAdapter(dogResults);
        mRecyclerView.setAdapter(mDogAdapter);
    }

    public void part2AddDog(View view) {
        final DogRO newDog = new DogRO();
        newDog.setName(mTextViewDogName.getText().toString());
        newDog.setBreed(mTextViewDogBreed.getText().toString());
        newDog.setAge(Integer.parseInt(mTextViewDogAge.getText().toString()));
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(newDog);
            }
        });

        mDogAdapter.notifyDataSetChanged();
        mTextViewDogName.setText("");
        mTextViewDogBreed.setText("");
        mTextViewDogAge.setText("");
    }

    public void part2UpdateDogList(View view) {
        mDogAdapter.notifyDataSetChanged();
    }

    public void part2CloseRealm(View view) {
        realm.close();
    }

    public void part2ResetRealm(View view) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        });
        mDogAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }
}
