package com.projects.naduni.eventplanner.Service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.projects.naduni.eventplanner.Model.Event;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="eventplanner.db";
    public static final String TABLE_NAME ="todolist";
    public static final String TABLE_EVENT = "events";

    //common column names
    public static final String COL_1 ="ID";

    public static final String COL_2 ="NAME";
    public static final String COL_3 ="NOTE";

    //Event table column names
    public static final String COL_EVENT_NAME = "EVENTNAME";
    public static final String COL_EVENT_LOCATION = "EVENTLOCATION";
    public static final String COL_EVENT_DATE = "EVENTDATE";
    public static final String COL_EVENT_NOTE = "EVENTNOTE";

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,NOTE TEXT)");
        db.execSQL("create table " + TABLE_EVENT +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,EVENTNAME TEXT,EVENTLOCATION TEXT,EVENTDATE TEXT,EVENTNOTE TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_EVENT);
        onCreate(db);
    }

    public boolean insertData(String task, String note){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,task);
        contentValues.put(COL_3,note);
        long result = db.insert(TABLE_NAME,null, contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    //insert Event details
    public boolean insertEventData(Event event){
        System.out.println("Error insertEventData " + event.getEventName());
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_EVENT_NAME,event.getEventName());
        contentValues.put(COL_EVENT_LOCATION,event.getLocation());
        contentValues.put(COL_EVENT_DATE,event.getDate());
        contentValues.put(COL_EVENT_NOTE,event.getNote());

        long result = db.insert(TABLE_EVENT,null ,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor viewAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME ,null);
        return res;
    }
}
