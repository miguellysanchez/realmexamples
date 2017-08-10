package com.example.realmexample.part5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.realmexample.R;
import com.example.realmexample.RealmConfigUtil;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Part5SetupActivity extends AppCompatActivity {
    private EditText edittextDBVersion;
    private CheckBox inMemoryCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part5_setup);
        edittextDBVersion = (EditText) findViewById(R.id.activity_part5_setup_edittext_dbversion);
        inMemoryCheckBox = (CheckBox) findViewById(R.id.activity_part5_setup_checkbox_inmemory);
    }

    public void part5SetupCompleteSetup(View view) {
        finish();
        Intent goToPart5 = new Intent(this, Part5Activity.class);
        int version = (edittextDBVersion.getText().toString().equals("")) ? 1 : Integer.parseInt(edittextDBVersion.getText().toString());
        goToPart5.putExtra("DB_VERSION", version);
        goToPart5.putExtra("IS_IN_MEMORY", inMemoryCheckBox.isChecked());
        startActivity(goToPart5);
    }

    public void part5SetupDeleteRealm(View view) {
        int version = (edittextDBVersion.getText().toString().equals("")) ? 1 : Integer.parseInt(edittextDBVersion.getText().toString());
        RealmConfiguration config = RealmConfigUtil.getPart5Config(version, false);
        Toast.makeText(this, "Realm " + config.getRealmFileName() + " version_" + version +" deleted", Toast.LENGTH_LONG).show();
        Realm.deleteRealm(config);
    }

}
