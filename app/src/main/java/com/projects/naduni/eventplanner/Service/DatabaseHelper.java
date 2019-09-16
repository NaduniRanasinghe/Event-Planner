package com.projects.naduni.eventplanner.Service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.projects.naduni.eventplanner.Model.Budget;
import com.projects.naduni.eventplanner.Model.Event;
import com.projects.naduni.eventplanner.Model.Guest;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="eventplanner.db";
    public static final String TABLE_NAME ="todolist";
    public static final String TABLE_EVENT = "events";
    public static final String TABLE_GUEST = "guests";
    public static final String TABLE_BUDGET ="budget";

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
    public static final String COL_GUEST_STATUS= "GUESTSTATUS";

    //Budget Table column names
    public static final String COL_BUDGET_ITEM = "BUDGETNAME";
    public static final String COL_BUDGET_EVENT = "BUDGETEVENT";
    public static final String COL_BUDGET_AMOUNT = "AMOUNT";
    public static final String COL_BUDGET_PAID = "PAID";
    public static final String COL_BUDGET_NOTE = "NOTES";


    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,NOTE TEXT)");
        db.execSQL("create table "+ TABLE_BUDGET +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,BUDGETNAME TEXT,BUDGETEVENT TEXT, AMOUNT INTEGER,PAID INTEGER, NOTES TEXT)");
        db.execSQL("create table " + TABLE_EVENT +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,EVENTNAME TEXT,EVENTLOCATION TEXT,EVENTDATE TEXT,EVENTNOTE TEXT)");
        db.execSQL("create table " + TABLE_GUEST +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,GUESTNAME TEXT,GUESTAGE TEXT,GUESTEMAIL TEXT,GUESTGENDER TEXT, GUESTNOTE TEXT, GUESTEVENT TEXT, GUESTSTATUS TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_EVENT);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_GUEST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUDGET);
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
    //delete guests
    public void deleteGuests(int id, SQLiteDatabase db){


        String selection = COL_1+"="+id;
        db.delete(TABLE_GUEST,selection,null);
    }

    public Cursor viewAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME ,null);
        return res;
    }
    //view guests data
    public Cursor getGuests(SQLiteDatabase db){


        String[] projections = {COL_1,COL_GUEST_NAME,COL_GUEST_AGE,COL_GUEST_EMAIL,COL_GUEST_GENDER,COL_GUEST_NOTE,COL_GUEST_EVENT,COL_GUEST_STATUS};
        Cursor cursor = db.query(TABLE_GUEST,projections,null,null,null,null,null);
        return cursor;

    }
    //search guests
    public Cursor searchGuests(String name, SQLiteDatabase db){

        String sql = "SELECT * FROM " + TABLE_GUEST + " WHERE " + COL_GUEST_NAME + " LIKE '%" + name + "%'";
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }


    //insert budget details
    public boolean insertBudgetData(Budget budget){
        System.out.println("Error insert Budget Data " + budget.getBudgetItem());
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_BUDGET_ITEM,budget.getBudgetItem());
        contentValues.put(COL_BUDGET_EVENT,budget.getEvent());
        contentValues.put(COL_BUDGET_AMOUNT,budget.getAmount());
        contentValues.put(COL_BUDGET_PAID,budget.getPaid());
        contentValues.put(COL_BUDGET_NOTE,budget.getNote());


        long result = db.insert(TABLE_BUDGET,null ,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    //view budget data
    public Cursor getBudgets(SQLiteDatabase db){

        String[] projections = {COL_1,COL_BUDGET_ITEM,COL_BUDGET_EVENT,COL_BUDGET_AMOUNT,COL_BUDGET_PAID,COL_BUDGET_NOTE};
        Cursor cursor = db.query(TABLE_BUDGET,projections,null,null,null,null,null);
        return cursor;

    }
    //delete guests
    public void deleteBudgets(int id, SQLiteDatabase db){


        String selection = COL_1+"="+id;
        db.delete(TABLE_BUDGET,selection,null);
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


    //view events data
    public Cursor getEvents(SQLiteDatabase db){


        String[] projections = {COL_1,COL_EVENT_NAME,COL_EVENT_DATE,COL_EVENT_LOCATION,COL_EVENT_NOTE};
        Cursor cursor = db.query(TABLE_EVENT,projections,null,null,null,null,null);
        return cursor;

    }


//Delete Events by Inusha
public void deleteEvents(int id, SQLiteDatabase db){


    String selection = COL_1+"="+id;
    db.delete(TABLE_EVENT,selection,null);

}




}
