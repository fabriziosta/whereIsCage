package com.supinfo.whereiscage.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.supinfo.whereiscage.Activity.Adapter.ShowListAdapter;
import com.supinfo.whereiscage.Activity.Classes.MyOpenHelper;
import com.supinfo.whereiscage.R;

public class HallOfFameActivity extends AppCompatActivity {
    private ListView listView;
    private RadioGroup radioGroup;
    private SQLiteDatabase db;
    private ShowListAdapter myCustomAdapter;
    private String NORMAL_GAME_QUERY = "SELECT * FROM user WHERE gamemode='easy' OR gamemode='medium' OR gamemode='hard' ORDER BY score ASC";
    private String CHRONO1_QUERY = "SELECT * FROM user WHERE gamemode='chrono1' ORDER BY score DESC";
    private String CHRONO2_QUERY = "SELECT * FROM user WHERE gamemode='chrono2' ORDER BY score DESC";
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_of_fame);

        //Create Connection read-only with database
        MyOpenHelper myOpenHelper = new MyOpenHelper(this);
        db = myOpenHelper.getReadableDatabase();

        //Create Adapter and get listView.
        listView = (ListView) findViewById(R.id.listViewFame);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroupFame);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                View radiobutton = radioGroup.findViewById(checkedId);
                int index = radioGroup.indexOfChild(radiobutton);

                switch (index){
                    case 0: //first button
                        cursor = db.rawQuery(NORMAL_GAME_QUERY, null);
                        break;
                    case 1: //second button
                        cursor = db.rawQuery(CHRONO1_QUERY, null);
                        break;
                    case 2: //third button
                        cursor = db.rawQuery(CHRONO2_QUERY, null);
                        break;
                }
                listView.setAdapter(null);
                myCustomAdapter.notifyDataSetChanged();
                myCustomAdapter = new ShowListAdapter(HallOfFameActivity.this, cursor, true);
                listView.setAdapter(myCustomAdapter);
                Toast.makeText(getApplicationContext(), "Hall of Fame reloaded", Toast.LENGTH_SHORT).show();
            }
        });

        cursor = db.rawQuery(NORMAL_GAME_QUERY, null);
        myCustomAdapter = new ShowListAdapter(HallOfFameActivity.this, cursor, true);
        listView.setAdapter(myCustomAdapter);
        Toast.makeText(getApplicationContext(), "Hall of Fame loaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Close connections. If I close cursor it doesn't work anymore.
        cursor.close();
        db.close();
        finish();
    }
}
