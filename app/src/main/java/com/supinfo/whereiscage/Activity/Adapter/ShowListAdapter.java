package com.supinfo.whereiscage.Activity.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.supinfo.whereiscage.R;

public class ShowListAdapter extends CursorAdapter {
    private LayoutInflater mLayoutInflater;

    public ShowListAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return mLayoutInflater.inflate(R.layout.list_row, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView showUsername = (TextView) view.findViewById(R.id.list_fame1);
        TextView showScore = (TextView) view.findViewById(R.id.list_fame2);
        TextView showMode = (TextView) view.findViewById(R.id.list_fame3);

        showUsername.setText(cursor.getString(cursor.getColumnIndexOrThrow("username")));
        showScore.setText(cursor.getString(cursor.getColumnIndexOrThrow("score")));
        showMode.setText(cursor.getString(cursor.getColumnIndexOrThrow("gamemode")));
    }
}
