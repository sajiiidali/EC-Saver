package com.myECapplication.sajiiidali.ecsaver;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*Created by Sajid Ali FR Bahawalnagar on 05/08/2018*/

public class Database extends SQLiteOpenHelper {

    SQLiteDatabase db;

    private static final String DATABASE_NAME = "EC_DataBase";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "EC_Table";

    public static final String COLUMN_1 = "Type_of_EC";
    public static final String COLUMN_2 = "EC_Number";
    public static final String COLUMN_3 = "C_Date";
    public static final String COLUMN_4 = "Month_No";
    public static final String COLUMN_5 = "Date_No";
    public static final String COLUMN_6 = "Year_No";
    public static final String COLUMN_7 = "Row_string";


    public Database(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(Type_of_EC TEXT, EC_Number TEXT, C_Date TEXT, Month_No TEXT, Date_No TEXT, Year_No TEXT, Row_string TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String ECT, String ECN, String CD, String month, String date, String year, String rowstring) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_1, ECT);
        contentValues.put(COLUMN_2, ECN);
        contentValues.put(COLUMN_3, CD);
        contentValues.put(COLUMN_4, month);
        contentValues.put(COLUMN_5, date);
        contentValues.put(COLUMN_6, year);
        contentValues.put(COLUMN_7, rowstring);


        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public void delete(String EC_Number) {

        db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "EC_Number =?", new String[]{EC_Number});

    }

    public void deleteall() {
        db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
    }

    public void deletebymonth(int month) {

        db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "Month_No =?", new String[]{String.valueOf(month)});

    }

    public Cursor show_data_by_selected_month_and_year(int i, int datepic_month, int year) {
        SQLiteDatabase mydb = this.getReadableDatabase();
        Cursor res = mydb.rawQuery("SELECT * FROM EC_Table WHERE Date_No = ? AND Month_No = ? AND Year_No = ?", new String[]{String.valueOf(i), String.valueOf(datepic_month), String.valueOf(year)});
        return res;
    }

    public Cursor checkdata(int m, int year) {

        SQLiteDatabase mydb = this.getReadableDatabase();
        Cursor res = mydb.rawQuery("SELECT * FROM EC_Table WHERE Month_No = ? AND Year_No = ?",new String[]{String.valueOf(m), String.valueOf(year)});
        return res;

    }

    public Cursor showdatawithoutarguments() {
        SQLiteDatabase mydb = this.getReadableDatabase();
        Cursor res = mydb.rawQuery("SELECT * FROM EC_Table",null);
        return res;
    }

    public Cursor showawholedate(int m) {
        SQLiteDatabase mydb = this.getReadableDatabase();
        Cursor res = mydb.rawQuery("SELECT * FROM EC_Table WHERE Month_No = ?",new String[]{String.valueOf(m)});
        return res;
    }


    public boolean editThisRowItems(String Date, String EcNumber, String EcType, String ecnumber) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues(3);

        contentValues.put(COLUMN_1, EcType);
        contentValues.put(COLUMN_2, EcNumber);
        contentValues.put(COLUMN_3, Date);

        long result = db.update(TABLE_NAME,contentValues," EC_Number=?",new String[]{ecnumber});

        if (result == -1)
            return false;
        else
            return true;
    }

    public void deleteDayoff(String date) {

        db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "C_Date =?", new String[]{date});

    }
}
