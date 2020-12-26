package com.supinfo.whereiscage.Activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.supinfo.whereiscage.Activity.Classes.MyOpenHelper;
import com.supinfo.whereiscage.R;

public class AddRecordHallOfFameActivity extends AppCompatActivity implements View.OnClickListener{
    private String userScore;
    private String gamemode;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record_hall_of_fame);

        editText = (EditText) findViewById(R.id.editTextName);

        //Retrieve user score and game mode to add in the database with the name.
        userScore = getIntent().getStringExtra("Score");
        gamemode = getIntent().getStringExtra("game");
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnAddRecord){
            String username = String.valueOf(editText.getText());
            if(username.equals("")) username = "anonymous";

            MyOpenHelper myOpenHelper = new MyOpenHelper(this);
            SQLiteDatabase db = myOpenHelper.getWritableDatabase();
            String insert = "INSERT INTO user (username, score, gamemode) Values " +
                    "('" + username + "', '" + userScore + "', '" + gamemode + "')";

            db.execSQL(insert);
            db.close();

            startActivity(new Intent(AddRecordHallOfFameActivity.this, HallOfFameActivity.class));
            finish();
        }
    }
}
