package com.example.realmexample.part5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.example.realmexample.R;
import com.example.realmexample.RealmConfigUtil;
import com.example.realmexample.part5.realmobjects.CarRO;

import io.realm.Realm;
import io.realm.RealmResults;

public class Part5Activity extends AppCompatActivity {

    private Realm realm;
    private RecyclerView mRecyclerViewCars;
    private CarAdapter mCarsAdapter;
    private EditText mEditTextCarName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part5_realms);
        int dbVersion = getIntent().getIntExtra("DB_VERSION", 1);
        boolean isInMemory = getIntent().getBooleanExtra("IS_IN_MEMORY", false);
        Realm.setDefaultConfiguration(RealmConfigUtil.getPart5Config(dbVersion, isInMemory));
        realm = Realm.getDefaultInstance();
        initializeViews();
    }

    private void initializeViews() {
        mEditTextCarName = (EditText) findViewById(R.id.activity_part5_edittext_name);
        mRecyclerViewCars = (RecyclerView) findViewById(R.id.activity_part5_recyclerview_cars);
        RealmResults<CarRO> carResults = realm.where(CarRO.class).findAll();
        mCarsAdapter = new CarAdapter(carResults);
        mRecyclerViewCars.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewCars.setAdapter(mCarsAdapter);
    }

    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }

    public void part5AddCar(View view) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                CarRO newCar = realm.createObject(CarRO.class, System.currentTimeMillis());
                newCar.setName(mEditTextCarName.getText().toString());
            }
        });
        mCarsAdapter.notifyDataSetChanged();
    }

    public void part5ResetRealm(View view) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        });
        mCarsAdapter.notifyDataSetChanged();
    }
}
