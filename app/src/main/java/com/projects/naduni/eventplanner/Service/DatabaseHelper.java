package com.projects.naduni.eventplanner.Service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.projects.naduni.eventplanner.Model.Event;
import com.projects.naduni.eventplanner.Model.Guest;
import com.projects.naduni.eventplanner.Model.Todo;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="eventplanner.db";
    public static final String TABLE_TODO = "todotable";
    public static final String TABLE_EVENT = "events";
    public static final String TABLE_GUEST = "guests";

    //common column names
    public static final String COL_1 ="ID";


    //todolist columns
    public static final String COL_TASKS ="TASKS";
    public static final String COL_NOTES ="NOTES";

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
    public static final String COL_GUEST_STATUS= "GUESTSTATUS";

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,1);

    }

    //creating tables in here
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_TODO +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,TASKS TEXT, NOTES TEXT)");
        db.execSQL("create table " + TABLE_EVENT +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,EVENTNAME TEXT,EVENTLOCATION TEXT,EVENTDATE TEXT,EVENTNOTE TEXT)");
        db.execSQL("create table " + TABLE_GUEST +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,GUESTNAME TEXT,GUESTAGE TEXT,GUESTEMAIL TEXT,GUESTGENDER TEXT, GUESTNOTE TEXT, GUESTEVENT TEXT, GUESTSTATUS TEXT)");
    }

    //if the table exits delete them
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_TODO);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_EVENT);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_GUEST);
        onCreate(db);
    }


    //insert data to the Todolist
    public boolean insertTodoList(Todo t){
        System.out.println("Error InsertingData to todolist " + t.getTask());
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_TASKS,t.getTask());
        contentValues.put(COL_NOTES,t.getNotes());
        long result = db.insert(TABLE_TODO,null,contentValues);

        if(result == -1){
            return false;
        }
        else{
            return true;
        }

    }

    //view todolist data
    public Cursor getTodoListData(SQLiteDatabase db){

        String[] projections = {COL_1,COL_TASKS,COL_NOTES};
        Cursor cursor = db.query(TABLE_TODO, projections,null,null,null,null,null);
        return cursor;

    }





    //view guests data
    public Cursor getGuests(SQLiteDatabase db){


        String[] projections = {COL_1,COL_GUEST_NAME,COL_GUEST_AGE,COL_GUEST_EMAIL,COL_GUEST_GENDER,COL_GUEST_NOTE,COL_GUEST_EVENT,COL_GUEST_STATUS};
        Cursor cursor = db.query(TABLE_GUEST,projections,null,null,null,null,null);
        return cursor;

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
        contentValues.put(COL_GUEST_STATUS,guest.getStatus());

        long result = db.insert(TABLE_GUEST,null ,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }





    //view events data
    public Cursor getEvents(SQLiteDatabase db){


        String[] projections = {COL_1,COL_EVENT_NAME,COL_EVENT_DATE,COL_EVENT_LOCATION,COL_EVENT_NOTE};
        Cursor cursor = db.query(TABLE_EVENT,projections,null,null,null,null,null);
        return cursor;

    }
//delete guests
    public void deleteGuests(int id, SQLiteDatabase db){


        String selection = COL_1+"="+id;
        db.delete(TABLE_GUEST,selection,null);
    }

//Delete Events by Inusha
public void deleteEvents(int id, SQLiteDatabase db){


    String selection = COL_1+"="+id;
    db.delete(TABLE_EVENT,selection,null);

}

//search guests
    public Cursor searchGuests(String name, SQLiteDatabase db){

        String sql = "SELECT * FROM " + TABLE_GUEST + " WHERE " + COL_GUEST_NAME + " LIKE '%" + name + "%'";
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }
}
