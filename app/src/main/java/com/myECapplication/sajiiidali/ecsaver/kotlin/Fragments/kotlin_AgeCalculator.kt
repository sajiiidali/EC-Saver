package com.myECapplication.sajiiidali.ecsaver.kotlin.Fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.myECapplication.sajiiidali.ecsaver.R
import java.util.*

class kotlin_AgeCalculator : Fragment(R.layout.activity_age_calculator),View.OnClickListener {

    val FILE_NAME = "my_file"
    val sharedPreferencesDate       = "my_date"
    val sharedPreferencesMonth      = "my_month"
    val sharedPreferencesYear       = "my_year"
    lateinit var birthdayDate       : EditText
    lateinit var birthdayMonth      : EditText
    lateinit var birthdayYear       : EditText
    lateinit var tooDate            : EditText
    lateinit var tooMonth           : EditText
    lateinit var tooYear            : EditText
    lateinit var buttonClear        : Button
    lateinit var buttonCalculate    : Button
    lateinit var getCurrentDate     : TextView
    lateinit var showDays           : TextView
    lateinit var showMonth          : TextView
    lateinit var showYear           : TextView
    var currentDay                  : Int = 0
    var currentMonth                : Int = 0
    var currentYear                 : Int = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        birthdayDate        = view.findViewById(R.id.birthdayDate)
        birthdayMonth       = view.findViewById(R.id.birthdayMonth)
        birthdayYear        = view.findViewById(R.id.birthdayYear)
        tooDate             = view.findViewById(R.id.tooDate)
        tooMonth            = view.findViewById(R.id.tooMonth)
        tooYear             = view.findViewById(R.id.tooYear)
        buttonCalculate     = view.findViewById(R.id.button_calculate)
        buttonClear         = view.findViewById(R.id.button_clear)
        getCurrentDate      = view.findViewById(R.id.getCurrentDate)
        showDays            = view.findViewById(R.id.showDays)
        showMonth           = view.findViewById(R.id.showMonth)
        showYear            = view.findViewById(R.id.showYear)
        buttonCalculate.setOnClickListener(this)
        buttonClear.setOnClickListener(this)
        getCurrentDate.setOnClickListener(this)

        val calendar    = Calendar.getInstance(TimeZone.getDefault())
        val month       = calendar.get(Calendar.MONTH)
        currentMonth    = month+1
        currentDay      = calendar.get(Calendar.DAY_OF_MONTH)
        currentYear     = calendar.get(Calendar.YEAR)

        val sharedPreferences: SharedPreferences = activity?.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)!!
        val lastUserDay = sharedPreferences.getString(sharedPreferencesDate, "0")
        val lastUserMonth = sharedPreferences.getString(sharedPreferencesMonth, "0")
        val lastUserYear = sharedPreferences.getString(sharedPreferencesYear, "0")

        birthdayDate.setText(lastUserDay)
        birthdayMonth.setText(lastUserMonth)
        birthdayYear.setText(lastUserYear)
        val currentDate = "Current Date Is :-    $currentDay-$currentMonth-$currentYear"
        getCurrentDate.setText(currentDate)

    }

    private fun closekeyboard() {
        val view = activity?.getCurrentFocus()
        if (view != null) {
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.button_calculate
                -> { Calculate() }
            R.id.button_clear
                    -> { ClearAll()}
            R.id.getCurrentDate
                    ->{ setDate()}

        }
    }

    private fun setDate() {
        tooDate.setText(currentDay.toString())
        tooMonth.setText(currentMonth.toString())
        tooYear.setText(currentYear.toString())
    }

    private fun ClearAll() {
        birthdayDate.setText("")
        birthdayMonth.setText("")
        birthdayYear.setText("")
        tooDate.setText("")
        tooMonth.setText("")
        tooYear.setText("")
    }

    @SuppressLint("SetTextI18n")
    private fun Calculate() {
        var a = 0
        closekeyboard()
        try {
            val year = birthdayYear.text.toString().toInt()
            val month = birthdayMonth.text.toString().toInt()
            val day = birthdayYear.text.toString().toInt()

            val editor: SharedPreferences.Editor = activity?.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)!!.edit()
            editor.putString(sharedPreferencesDate, birthdayDate.text.toString())
            editor.putString(sharedPreferencesMonth, birthdayMonth.text.toString())
            editor.putString(sharedPreferencesYear, birthdayYear.text.toString())
            editor.apply()

            var todays = tooDate.text.toString().toInt()
            var tomonth = tooMonth.text.toString().toInt()
            var toyears = tooYear.text.toString().toInt()
            if (birthdayDate.text.toString().toInt() > 31) {
                birthdayDate.setError("Only 31 days in month")
            } else if (birthdayMonth.text.toString().toInt() > 12) {
                birthdayMonth.setError("Only 12 month in year")
            } else if (birthdayYear.text.toString().length != 4) {
                birthdayYear.setError("Enter Correct year")
            } else if (tooDate.text.toString().toInt() > 31) {
                tooDate.setError("Only 31 days in month")
            } else if (tooMonth.text.toString().toInt() > 12) {
                tooMonth.setError("Only 12 month in year")
            } else if (tooYear.text.toString().length != 4) {
                tooYear.setError("Enter correct year")
            } else {
                a = a + 1
            }
            if (a == 1) {
                if (todays < day) {
                    todays = todays + 30
                    tomonth--
                }
                if (tomonth < month) {
                    tomonth = tomonth + 12
                    toyears--
                }
                val Tyears = toyears - year
                val Tmonth = tomonth - month
                val Tdays = todays - day
                showYear.setText(" $Tyears Years")
                showMonth.setText(" $Tmonth Months")
                showDays.setText(" $Tdays Days")
            }
        }catch (e:NumberFormatException){
            Toast.makeText(context,"write your Birthday date",Toast.LENGTH_SHORT)
        }
    }


}