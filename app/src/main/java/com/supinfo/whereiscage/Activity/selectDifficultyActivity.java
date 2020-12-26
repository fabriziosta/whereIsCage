package com.supinfo.whereiscage.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.supinfo.whereiscage.R;

public class selectDifficultyActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_difficulty);
    }

    @Override
    public void onClick(View v) {
        Intent myIntent = new Intent(selectDifficultyActivity.this, GameActivity.class);

        if(v.getId() == R.id.btnEasy){
            myIntent.putExtra("game", "easy");
        }else if(v.getId() == R.id.btnMedium){
            myIntent.putExtra("game", "medium");
        }else{
            myIntent.putExtra("game", "hard");
        }
        startActivity(myIntent);
    }
}
