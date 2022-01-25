package com.myECapplication.sajiiidali.ecsaver.kotlin.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.myECapplication.sajiiidali.ecsaver.Database

class dataBaseClass(context :Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        var db: SQLiteDatabase? = null

        private const val DATABASE_NAME = "EC_DataBase"
        private const val DATABASE_VERSION = 2
        private const val TABLE_NAME = "EC_Table"
        const val primaryKey = 0
        const val COLUMN_1 = "Type_of_EC"
        const val COLUMN_2 = "EC_Number"
        const val COLUMN_3 = "C_Date"
        const val COLUMN_4 = "Month_No"
        const val COLUMN_5 = "Date_No"
        const val COLUMN_6 = "Year_No"
        const val COLUMN_7 = "Row_string"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("create table $TABLE_NAME(primaryKey INTEGER PRIMARY KEY AUTOINCREMENT,Type_of_EC TEXT, EC_Number TEXT, C_Date TEXT, Month_No TEXT, Date_No TEXT, Year_No TEXT, Row_string TEXT )")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    fun insertData(ecType: String?, ecNumber: String?, currentDate: String?, monthOfYear: String?, dayOfMonth: String?, finalYear: String?, rowstring: String?): Boolean {

        db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Database.COLUMN_1, ecType)
        contentValues.put(Database.COLUMN_2, ecNumber)
        contentValues.put(Database.COLUMN_3, currentDate)
        contentValues.put(Database.COLUMN_4, monthOfYear)
        contentValues.put(Database.COLUMN_5, dayOfMonth)
        contentValues.put(Database.COLUMN_6, finalYear)
        contentValues.put(Database.COLUMN_7, rowstring)
        val result = db?.insert(TABLE_NAME, null, contentValues)
        return if (result == -1L) false else true
    }
    fun delete(EC_Number: String,primaryKey:String?) {
        db = this.writableDatabase
        db?.delete(TABLE_NAME, "EC_Number=? || primaryKey=?", arrayOf(EC_Number,primaryKey))
    }
    fun deleteall() {
        db = this.writableDatabase
        db?.delete(TABLE_NAME, null, null)
    }
    fun deletebymonth(month: Int) {
        db = this.writableDatabase
        db?.delete(TABLE_NAME, "Month_No =?", arrayOf(month.toString()))
    }
    fun show_data_by_selected_month_and_year(i: Int, datepic_month: Int, year: Int): Cursor? {
        db = this.readableDatabase

        return db?.rawQuery(
            "SELECT * FROM EC_Table WHERE Date_No = ? AND Month_No = ? AND Year_No = ?",
            arrayOf(i.toString(), datepic_month.toString(), year.toString())
        )
    }
    fun checkdata(m: Int, year: Int): Cursor? {
        db = this.readableDatabase
        return db?.rawQuery(
            "SELECT * FROM EC_Table WHERE Month_No = ? AND Year_No = ?",
            arrayOf(m.toString(), year.toString())
        )
    }
    fun showdatawithoutarguments(): Cursor? {
        db = this.readableDatabase
        return db?.rawQuery("SELECT * FROM EC_Table", null)
    }
    fun showawholedate(m: Int): Cursor? {
        db = this.readableDatabase
        return db?.rawQuery("SELECT * FROM EC_Table WHERE Month_No = ?", arrayOf(m.toString()))
    }
    fun editThisRowItems(Date: String?, EcNumber: String?, EcType: String?, ecnumber: String,primaryKey:String?): Boolean {
        db = this.writableDatabase
        val contentValues = ContentValues(3)
        contentValues.put(Database.COLUMN_1, EcType)
        contentValues.put(Database.COLUMN_2, EcNumber)
        contentValues.put(Database.COLUMN_3, Date)
        val result = db?.update(TABLE_NAME, contentValues, " EC_Number=? || primaryKey=?", arrayOf(ecnumber,primaryKey))?.toLong()
        return if (result == -1L) false else true
    }
    fun deleteDayoff(date: String) {
        db = this.writableDatabase
        db?.delete(TABLE_NAME, "C_Date =?", arrayOf(date))
    }










}