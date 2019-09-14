package com.projects.naduni.eventplanner.Service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.projects.naduni.eventplanner.Model.Event;
import com.projects.naduni.eventplanner.Model.Guest;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="eventplanner.db";
    public static final String TABLE_NAME ="todolist";
    public static final String TABLE_EVENT = "events";
    public static final String TABLE_GUEST = "guests";

    //common column names
    public static final String COL_1 ="ID";
    //todolist columns
    public static final String COL_2 ="NAME";
    public static final String COL_3 ="NOTE";

    //Event table column names
    public static final String COL_EVENT_NAME = "EVENTNAME";
    public static final String COL_EVENT_LOCATION = "EVENTLOCATION";
    public static final String COL_EVENT_DATE = "EVENTDATE";
    public static final String COL_EVENT_NOTE = "EVENTNOTE";

    //Guest table column names
    public static final String COL_GUEST_NAME = "GUESTNAME";
    public static final String COL_GUEST_AGE= "GUESTAGE";
    public static final String COL_GUEST_EMAIL = "GUESTEMAIL";
    public static final String COL_GUEST_GENDER= "GUESTGENDER";
    public static final String COL_GUEST_NOTE= "GUESTNOTE";
    public static final String COL_GUEST_EVENT= "GUESTEVENT";

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,NOTE TEXT)");
        db.execSQL("create table " + TABLE_EVENT +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,EVENTNAME TEXT,EVENTLOCATION TEXT,EVENTDATE TEXT,EVENTNOTE TEXT)");
        db.execSQL("create table " + TABLE_GUEST +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,GUESTNAME TEXT,GUESTAGE TEXT,GUESTEMAIL TEXT,GUESTGENDER TEXT, GUESTNOTE TEXT, GUESTEVENT TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_EVENT);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_GUEST);
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

    //insert Guest details
    public boolean insertGuestData(Guest guest){
        System.out.println("Error insert GuestData " + guest.getGuestName());
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_GUEST_NAME,guest.getGuestName());
        contentValues.put(COL_GUEST_AGE,guest.getAge());
        contentValues.put(COL_GUEST_EMAIL,guest.getGuestEmail());
        contentValues.put(COL_GUEST_GENDER,guest.getGuestGender());
        contentValues.put(COL_GUEST_NOTE,guest.getNotesGuest());
        contentValues.put(COL_GUEST_EVENT,guest.getEventName());

        long result = db.insert(TABLE_GUEST,null ,contentValues);
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


    //view guests data
    public Cursor getGuests(SQLiteDatabase db){


       String[] projections = {COL_GUEST_NAME,COL_GUEST_AGE,COL_GUEST_EMAIL,COL_GUEST_GENDER,COL_GUEST_NOTE,COL_GUEST_EVENT};
        Cursor cursor = db.query(TABLE_GUEST,projections,null,null,null,null,null);
        return cursor;

    }


    //view events data
    public Cursor getEvents(SQLiteDatabase db){


        String[] projections = {COL_EVENT_NAME,COL_EVENT_DATE,COL_EVENT_LOCATION,COL_EVENT_NOTE};
        Cursor cursor = db.query(TABLE_EVENT,projections,null,null,null,null,null);
        return cursor;

    }
//    public Cursor getData(){
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res = db.rawQuery("select * from "+ TABLE_GUEST ,null);
//        return res;
//    }

    /**
    public List<String> getAllEvents() {
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  EVENTNAME FROM " + TABLE_EVENT;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1));//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }
**/


}
