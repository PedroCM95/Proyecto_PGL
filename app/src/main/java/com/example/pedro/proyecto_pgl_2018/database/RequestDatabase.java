package com.example.pedro.proyecto_pgl_2018.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pedro.proyecto_pgl_2018.manager.Manager;


public class RequestDatabase extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "manager_event.db";
    private final static int DATABASE_VERSION = 1;

    public RequestDatabase( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("CREATE TABLE " + Manager.TABLE_NAME +
       // " ( _id INTEGER PRIMARY KEY ON CONFLICT ROLLBACK AUTOINCREMENT, " +
       //         ")")
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
