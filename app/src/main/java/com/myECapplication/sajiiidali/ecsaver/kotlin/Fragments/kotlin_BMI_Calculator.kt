package com.myECapplication.sajiiidali.ecsaver.kotlin.Fragments

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
import java.lang.NumberFormatException
import java.text.DecimalFormat

class kotlin_BMI_Calculator : Fragment(R.layout.activity_bmi__calculator) {

    val MFILE_NAME                        = "Bmi_file"
    val sharePrefWeight                   = "my_weight"
    val sharePrefFeet                     = "my_feet"
    val sharePrefInch                     = "my_inch"
    lateinit var getWeight               :EditText
    lateinit var getFeet                 :EditText
    lateinit var getInch                 :EditText
    lateinit var btnCalculate            :Button
    lateinit var btnClear                :Button
    lateinit var showResult              :TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getWeight       =     view.findViewById(R.id.edittext1_weight)
        getFeet         =     view.findViewById(R.id.edittext2_feet)
        getInch         =     view.findViewById(R.id.edittext3_inch)
        btnCalculate    =     view.findViewById(R.id.btncalculate)
        btnClear        =     view.findViewById(R.id.button2)
        showResult      =     view.findViewById(R.id.showresult)

        val sharedPreferences: SharedPreferences =
            activity?.getSharedPreferences(MFILE_NAME, Context.MODE_PRIVATE)!!
        val save_preference1 = sharedPreferences.getString(sharePrefWeight, "80")
        val save_preference2 = sharedPreferences.getString(sharePrefFeet, "5")
        val save_preference3 = sharedPreferences.getString(sharePrefInch, "10")

        getWeight.setText(save_preference1)
        getFeet.setText(save_preference2)
        getInch.setText(save_preference3)

        btnCalculate.setOnClickListener {
            val editor: SharedPreferences.Editor =
                activity?.getSharedPreferences(MFILE_NAME, Context.MODE_PRIVATE)!!.edit()
            editor.putString(sharePrefWeight, getWeight.getText().toString())
            editor.putString(sharePrefFeet, getFeet.getText().toString())
            editor.putString(sharePrefInch, getInch.getText().toString())
            editor.apply()

            closekeyboard()
            var category = ""
            var total_weight = 0
            var wait = 0
            try {
                val a: Float = getFeet.getText().toString().toFloat()
                val convert_ft_into_meter = (a / 3.28).toFloat()
                val b: Float = getInch.getText().toString().toFloat()
                val inch_into_ft = (b * 0.0833).toFloat()
                val convert_inch_into_meter = (inch_into_ft / 3.28).toFloat()
                val total_hieght_in_meter = convert_ft_into_meter + convert_inch_into_meter
                val weight: Float = getWeight.getText().toString().toFloat()
                val BMI = (weight / (total_hieght_in_meter * total_hieght_in_meter)).toDouble()
                val df = DecimalFormat("##.#")
                val bmiValue: Float = df.format(BMI).toFloat()
                val w: Int = Integer.valueOf(getWeight.getText().toString())
                val f: Int = Integer.valueOf(getFeet.getText().toString())
                val i: Int = Integer.valueOf(getInch.getText().toString())
                if (f == 4 && i == 9) {
                    wait = 52
                }
                if (f == 4 && i == 10) {
                    wait = 54
                }
                if (f == 4 && i == 11) {
                    wait = 56
                }
                if (f == 4 && i == 12) {
                    wait = 58
                }
                if (f == 5 && i == 0) {
                    wait = 58
                }
                if (f == 5 && i == 1) {
                    wait = 60
                }
                if (f == 5 && i == 2) {
                    wait = 62
                }
                if (f == 5 && i == 3) {
                    wait = 64
                }
                if (f == 5 && i == 4) {
                    wait = 66
                }
                if (f == 5 && i == 5) {
                    wait = 68
                }
                if (f == 5 && i == 6) {
                    wait = 70
                }
                if (f == 5 && i == 7) {
                    wait = 72
                }
                if (f == 5 && i == 8) {
                    wait = 74
                }
                if (f == 5 && i == 9) {
                    wait = 76
                }
                if (f == 5 && i == 10) {
                    wait = 78
                }
                if (f == 5 && i == 11) {
                    wait = 81
                }
                if (f == 5 && i == 12) {
                    wait = 83
                }
                if (f == 6 && i == 0) {
                    wait = 83
                }
                if (f == 6 && i == 1) {
                    wait = 86
                }
                if (f == 6 && i == 2) {
                    wait = 88
                }
                if (f == 6 && i == 3) {
                    wait = 91
                }
                if (f == 6 && i == 4) {
                    wait = 93
                }
                if (f == 6 && i == 5) {
                    wait = 95
                }
                if (f == 6 && i == 6) {
                    wait = 98
                }
                if (f == 6 && i == 7) {
                    wait = 101
                }
                if (java.lang.Float.compare(bmiValue, 18.5.toFloat()) <= 0) category =
                    "underweight" else if (java.lang.Float.compare(
                        bmiValue,
                        18.6.toFloat()
                    ) >= 0 && java.lang.Float.compare(bmiValue, 24.9.toFloat()) <= 0
                ) category = "Normal" else if (java.lang.Float.compare(
                        bmiValue,
                        25.0.toFloat()
                    ) >= 0 && java.lang.Float.compare(bmiValue, 29.9.toFloat()) <= 0
                ) {
                    category = "Overweight"
                    total_weight = w - wait
                } else if (java.lang.Float.compare(bmiValue, 30.toFloat()) >= 0) {
                    category = "Obese"
                    total_weight = w - wait
                }
                val showResultt = "Your BMI is $bmiValue\n\nYou are $category\n\nYour Over-Weight is $total_weight KG"
                showResult.setText(showResultt)
            } catch (e: NumberFormatException) {
                Toast.makeText(activity, "Please First Put Weight and Height ", Toast.LENGTH_LONG).show()
            }
        }
        btnClear.setOnClickListener {
            getWeight.setText("")
            getFeet.setText("")
            getInch.setText("")
        }

    }

    private fun closekeyboard() {
        val view = activity?.getCurrentFocus()
        if (view != null) {
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}