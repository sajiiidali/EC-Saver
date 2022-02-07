package com.myECapplication.sajiiidali.ecsaver.kotlin.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class dataBaseClass(context :Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION) {

    companion object{
        var db: SQLiteDatabase? = null

        private const val DATABASE_NAME             = "EC_DataBase"
        private const val DATABASE_VERSION          = 2
        private const val TABLE_NAME                = "EC_Table"
        const val TypeOfEcNumber                    = "Type_of_EC"
        const val EcNumber                          = "EC_Number"
        const val CurrentDate                       = "C_Date"
        const val MonthOfYear                       = "Month_No"
        const val DayOfMonth                        = "Date_No"
        const val YearNo                            = "Year_No"
        const val WholeString                       = "Row_string"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("create table $TABLE_NAME(" +
                "Type_of_EC TEXT, EC_Number TEXT, C_Date TEXT, " +
                "Month_No TEXT, Date_No TEXT, " +
                "Year_No TEXT, " +
                "Row_string TEXT )")
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertData(
        ecType: String?,
        ecNumber: String?,
        currentDate: String?,
        monthOfYear: String?,
        dayOfMonth: String?,
        finalYear: String?,
        rowstring: String?): Boolean {

        db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(TypeOfEcNumber, ecType)
        contentValues.put(EcNumber, ecNumber)
        contentValues.put(CurrentDate, currentDate)
        contentValues.put(MonthOfYear, monthOfYear)
        contentValues.put(DayOfMonth, dayOfMonth)
        contentValues.put(YearNo, finalYear)
        contentValues.put(WholeString, rowstring)
        val result = db?.insert(TABLE_NAME, null, contentValues)
        return if (result == -1L) false else true
    }

    fun deleteall() {
        db = this.writableDatabase
        db?.delete(TABLE_NAME, null, null)
    }

    fun checkDataByMonthAndYear(
        date: Int,
        month: Int,
        year: Int): Cursor? {

        db = this.readableDatabase

        return db?.rawQuery(
            "SELECT * FROM EC_Table WHERE Date_No = ? AND Month_No = ? AND Year_No = ?",
            arrayOf(date.toString(), month.toString(), year.toString())
        )
    }
    fun checkByYear(
        year: Int): Cursor? {

        db = this.readableDatabase

        return db?.rawQuery(
            "SELECT * FROM EC_Table WHERE Year_No = ?", arrayOf(year.toString())
        )
    }
    fun checkdata(m: Int, year: Int): Cursor? {
        db = this.readableDatabase
        return db?.rawQuery(
            "SELECT * FROM EC_Table WHERE Month_No = ? AND Year_No = ?",
            arrayOf(m.toString(), year.toString())
        )
    }

    fun updateRowData(
        EC_Number: String,
        EcType: String?,
        checkEcNumber:String?,
        checkEcType :String?
         ): Boolean {

        db = this.writableDatabase
        val contentValues = ContentValues(2)
        contentValues.put(TypeOfEcNumber, EcType)
        contentValues.put(EcNumber, EC_Number)
        val result = db?.update(TABLE_NAME, contentValues, " EC_Number=? AND Type_of_EC=?", arrayOf(checkEcNumber,checkEcType))?.toLong()
        return result != -1L
    }

}