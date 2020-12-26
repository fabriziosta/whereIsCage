package com.supinfo.whereiscage.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.supinfo.whereiscage.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        Intent myIntent;

        switch (v.getId()) {
            case R.id.btnFame:
                myIntent = new Intent(MainActivity.this, HallOfFameActivity.class); break;
            case R.id.btnNormal:
                myIntent = new Intent(MainActivity.this, selectDifficultyActivity.class).putExtra("game", "normal"); break;
            case R.id.btnChrono1:
                myIntent = new Intent(MainActivity.this, GameActivity.class).putExtra("game", "chrono1"); break;
            default: //R.id.btnChrono2
                myIntent = new Intent(MainActivity.this, GameActivity.class).putExtra("game", "chrono2");
        }
        startActivity(myIntent);
    }
}


