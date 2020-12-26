package com.supinfo.whereiscage.Activity.Classes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {
    // Database Info
    private static final String DATABASE_NAME = "my.db";
    private static final int DATABASE_VERSION = 2;
    //TABLE name
    private static final String TABLE_USER = "user";
    //TABLE columns
    private static final String KEY_USER_ID = "_id";
    private static final String KEY_USER_NAME = "username";
    private static final String KEY_USER_SCORE = "score";
    private static final String KEY_USER_GAMEMODE = "gamemode";
    //Table creation
    private static final String TABLE_USER_CREATE =
            "CREATE TABLE " + TABLE_USER +
                " (" +
                    KEY_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    KEY_USER_NAME + " TEXT," +
                    KEY_USER_SCORE + " TEXT," +
                    KEY_USER_GAMEMODE + " TEXT" +
                ")";

    public MyOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_USER_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }
}
