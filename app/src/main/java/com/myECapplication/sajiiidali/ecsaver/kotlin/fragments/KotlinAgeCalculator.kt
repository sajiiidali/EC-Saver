package com.myECapplication.sajiiidali.ecsaver.kotlin.fragments

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
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.myECapplication.sajiiidali.ecsaver.R
import java.util.*


class KotlinAgeCalculator : Fragment(R.layout.activity_age_calculator),View.OnClickListener {

    private val fileName = "my_file"
    private val sharedPreferencesDate       = "my_date"
    private val sharedPreferencesMonth      = "my_month"
    private val sharedPreferencesYear       = "my_year"
    private lateinit var birthdayDate       : EditText
    private lateinit var birthdayMonth      : EditText
    private lateinit var birthdayYear       : EditText
    private lateinit var tooDate            : EditText
    private lateinit var tooMonth           : EditText
    private lateinit var tooYear            : EditText
    private lateinit var buttonClear        : Button
    private lateinit var buttonCalculate    : Button
    private lateinit var getCurrentDate     : TextView
    private lateinit var showDays           : TextView
    private lateinit var showMonth          : TextView
    private lateinit var showYear           : TextView
    private var currentDay                  : Int = 0
    private var currentMonth                : Int = 0
    private var currentYear                 : Int = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

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

        val sharedPreferences: SharedPreferences = activity?.getSharedPreferences(fileName, Context.MODE_PRIVATE)!!
        val lastUserDay = sharedPreferences.getString(sharedPreferencesDate, "0")
        val lastUserMonth = sharedPreferences.getString(sharedPreferencesMonth, "0")
        val lastUserYear = sharedPreferences.getString(sharedPreferencesYear, "0")

        birthdayDate.setText(lastUserDay)
        birthdayMonth.setText(lastUserMonth)
        birthdayYear.setText(lastUserYear)
        val currentDate = "Current Date Is :-    $currentDay-$currentMonth-$currentYear"
        getCurrentDate.text = currentDate

    }

    private fun closeKeyBoard() {
        val view = activity?.currentFocus
        if (view != null) {
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.button_calculate
                -> { calculate() }
            R.id.button_clear
                    -> { clearAll()}
            R.id.getCurrentDate
                    ->{ setDate()}

        }
    }

    private fun setDate() {
        tooDate.setText(currentDay.toString())
        tooMonth.setText(currentMonth.toString())
        tooYear.setText(currentYear.toString())
    }

    private fun clearAll() {
        birthdayDate.setText("")
        birthdayMonth.setText("")
        birthdayYear.setText("")
        tooDate.setText("")
        tooMonth.setText("")
        tooYear.setText("")
    }

    @SuppressLint("SetTextI18n")
    private fun calculate() {
        var a = 0
        closeKeyBoard()
        try {
            val year = birthdayYear.text.toString().toInt()
            val month = birthdayMonth.text.toString().toInt()
            val day = birthdayDate.text.toString().toInt()

            val editor: SharedPreferences.Editor = activity?.getSharedPreferences(fileName, Context.MODE_PRIVATE)!!.edit()
            editor.putString(sharedPreferencesDate, birthdayDate.text.toString())
            editor.putString(sharedPreferencesMonth, birthdayMonth.text.toString())
            editor.putString(sharedPreferencesYear, birthdayYear.text.toString())
            editor.apply()

            var toDays = tooDate.text.toString().toInt()
            var toMonth = tooMonth.text.toString().toInt()
            var toYears = tooYear.text.toString().toInt()
            when {
                birthdayDate.text.toString().toInt() > 31 -> {
                    birthdayDate.error = "Only 31 days in month"
                }
                birthdayMonth.text.toString().toInt() > 12 -> {
                    birthdayMonth.error = "Only 12 month in year"
                }
                birthdayYear.text.toString().length != 4 -> {
                    birthdayYear.error = "Enter Correct year"
                }
                tooDate.text.toString().toInt() > 31 -> {
                    tooDate.error = "Only 31 days in month"
                }
                tooMonth.text.toString().toInt() > 12 -> {
                    tooMonth.error = "Only 12 month in year"
                }
                tooYear.text.toString().length != 4 -> {
                    tooYear.error = "Enter correct year"
                }
                else -> {
                    a += 1
                }
            }
            if (a == 1) {
                if (toDays < day) {
                    toDays += 30
                    toMonth--
                }
                if (toMonth < month) {
                    toMonth += 12
                    toYears--
                }
                val tYears = toYears - year
                val tMonth = toMonth - month
                val tDays = toDays - day

                showYear.text = " $tYears Years"
                showMonth.text = " $tMonth Months"
                showDays.text = " $tDays Days"
            }
        }catch (e:NumberFormatException){
            Toast.makeText(activity, "write your Birthday date", Toast.LENGTH_SHORT).show()
        }
    }


}