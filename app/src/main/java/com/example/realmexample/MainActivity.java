package com.example.realmexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.realmexample.part1.Part1Activity;
import com.example.realmexample.part2.Part2Activity;
import com.example.realmexample.part3.Part3Activity;
import com.example.realmexample.part4.Part4Activity;
import com.example.realmexample.part5.Part5Activity;
import com.example.realmexample.part5.Part5SetupActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToPart1(View view) {
        startActivity(new Intent(this, Part1Activity.class));
    }

    public void goToPart2(View view) {
        startActivity(new Intent(this, Part2Activity.class));
    }

    public void goToPart3(View view) {
        startActivity(new Intent(this, Part3Activity.class));
    }

    public void goToPart4(View view) {
        startActivity(new Intent(this, Part4Activity.class));
    }
    public void goToPart5Setup(View view) {
        startActivity(new Intent(this, Part5SetupActivity.class));
    }
}
